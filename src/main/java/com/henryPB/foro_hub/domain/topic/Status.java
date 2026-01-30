package com.henryPB.foro_hub.domain.topic;

public enum Status {
    ABIERTO,        // Se pueden responder
    RESUELTO,       // Tiene una respuesta aceptada
    CERRADO,        // No admite más respuestas
    ARCHIVADO,      // Visible pero inactivo
    ELIMINADO       // Soft delete
}
