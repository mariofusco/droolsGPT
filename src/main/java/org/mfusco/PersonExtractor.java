package org.mfusco;

import dev.langchain4j.service.UserMessage;

public interface PersonExtractor {

    @UserMessage("Extract information about a person from {{it}}")
    Person extractPersonFrom(String text);
}

