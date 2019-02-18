package uk.co.vertigomoon.htmltopdf.model;

import java.util.Map;

public class RenderTemplate {
    private final String template;
    private final Map<String, Object> content;

    public RenderTemplate(String template, Map<String, Object> content) {
        this.template = template;
        this.content = content;
    }

    public String getTemplate() {
        return template;
    }

    public Map<String, Object> getContent() {
        return content;
    }
}
