package br.dev.s2w.jfoods.api.adapter.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    BUSINESS_ERROR("/business-error", "Business rule violation"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),
    MESSAGE_NOT_READABLE("/message-not-readable", "Message not readable"),
    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://jfoods.com.br" + path;
        this.title = title;
    }

}
