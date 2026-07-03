package com.enterprise.ai.provider.glpi;

public final class GlpiMappings {

    private GlpiMappings() {
    }

    public static String mapPriority(String priority) {

        if (priority == null) {
            return "";
        }

        return switch (priority) {

        case "1" -> "VERY LOW";
        case "2" -> "LOW";
        case "3" -> "MEDIUM";
        case "4" -> "HIGH";
        case "5" -> "VERY HIGH";

        default -> priority;
        };
    }

    public static String mapStatus(String status) {

        if (status == null) {
            return "";
        }

        return switch (status) {

        case "1" -> "NEW";
        case "2" -> "PROCESSING (ASSIGNED)";
        case "3" -> "PLANNED";
        case "4" -> "PENDING";
        case "5" -> "SOLVED";
        case "6" -> "CLOSED";

        default -> status;
        };
    }

}