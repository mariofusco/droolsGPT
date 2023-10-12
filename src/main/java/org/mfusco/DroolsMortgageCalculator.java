package org.mfusco;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.service.V;
import org.drools.model.codegen.ExecutableModelProject;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DroolsMortgageCalculator {

    private final Map<String, Person> personsRegistry = new HashMap<>();

    private final KieBase kieBase;

    public DroolsMortgageCalculator() {
        kieBase = new KieHelper().addFromClassPath("/mortgage.drl").build(ExecutableModelProject.class);
    }

    @Tool("Grant mortgage to {{name}}")
    public String grantMortgage(@V("name") String name) {
        Person person = personsRegistry.get(name);

        if (person == null) {
            return "Unknown person";
        }

        KieSession kieSession = kieBase.newKieSession();
        List<String> answers = new ArrayList<>();
        kieSession.setGlobal("answers", answers);
        kieSession.insert(person);
        kieSession.fireAllRules();

        if (answers.isEmpty()) {
            return "Yes, mortgage can be granted";
        }
        return "Mortgage cannot be granted cannot be granted because " + answers;
    }

    public void register(Person person) {
        personsRegistry.put(person.getFirstName(), person);
    }
}
