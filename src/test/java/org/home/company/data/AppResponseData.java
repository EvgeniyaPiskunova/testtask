package org.home.company.data;

public class AppResponseData {
    private Boolean type;
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public Boolean getType() {
        return type;
    }
}
