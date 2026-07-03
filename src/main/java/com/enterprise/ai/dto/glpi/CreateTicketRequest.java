package com.enterprise.ai.dto.glpi;

public class CreateTicketRequest {

    private Input input = new Input();

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public static class Input {

        private String name;
        private String content;
        private int itilcategories_id;
        private int urgency;
        private int impact;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getItilcategories_id() {
            return itilcategories_id;
        }

        public void setItilcategories_id(int itilcategories_id) {
            this.itilcategories_id = itilcategories_id;
        }

        public int getUrgency() {
            return urgency;
        }

        public void setUrgency(int urgency) {
            this.urgency = urgency;
        }

        public int getImpact() {
            return impact;
        }

        public void setImpact(int impact) {
            this.impact = impact;
        }

    }

}