package com.example.webquiz;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Quiz {

    private int id;
    private String title;
    private String text;
    private List<String> options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> answer;

    public Quiz(int id, String title, String text, List<String> options, List<Integer> answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }
}
