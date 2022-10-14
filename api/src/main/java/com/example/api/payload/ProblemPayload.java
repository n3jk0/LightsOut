package com.example.api.payload;

import java.io.Serializable;

/**
 * Created 14.10.2022
 * @author Nejc Kozlevčar
 */
public class ProblemPayload implements Serializable {
    private Long id;
    private String initialState;
    private String problemSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProblemSize() {
        return problemSize;
    }

    public void setProblemSize(String problemSize) {
        this.problemSize = problemSize;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }
}
