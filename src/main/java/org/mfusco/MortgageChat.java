package org.mfusco;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import static java.time.Duration.ofSeconds;

public class MortgageChat {

    private ChatLanguageModel model = OpenAiChatModel.builder()
            .apiKey(ApiKeys.OPENAI_API_KEY)
            .timeout(ofSeconds(60))
            .build();

    private PersonExtractor extractor = AiServices.create(PersonExtractor.class, model);

    private DroolsMortgageCalculator droolsMortgageCalculator = new DroolsMortgageCalculator();

    private Assistant assistant = AiServices.builder(Assistant.class)
            .chatLanguageModel(model)
            .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
            .tools(droolsMortgageCalculator)
            .build();

    public String chat(String text) {
        return text.endsWith("?") ? assistant.chat(text) : extractPerson(text);
    }

    private String extractPerson(String text) {
        Person person = extractor.extractPersonFrom(text);
        droolsMortgageCalculator.register(person);
        return person.toString();
    }
}
