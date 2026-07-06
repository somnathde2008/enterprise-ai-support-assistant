package com.enterprise.ai.prompt;

	public final class IncidentClassificationPrompt {

	    private IncidentClassificationPrompt() {
	    }

	    public static final String SYSTEM_PROMPT = """

				Role...

				Input

				Short Description

				%s

				Description

				%s

				Return JSON

				...
				""";
	}
	
	
