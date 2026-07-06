package com.enterprise.ai.prompt;

public final class PromptTemplate {

    private PromptTemplate() {
    }

    public static String buildIncidentClassificationPrompt(

            String shortDescription,

            String description) {

        return IncidentClassificationPrompt.SYSTEM_PROMPT.formatted(

                shortDescription,

                description);

    }

}