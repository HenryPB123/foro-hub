package com.henryPB.foro_hub.infra.errors;

public record FieldErrorResponse(
        String field,
        String message
) {
}
