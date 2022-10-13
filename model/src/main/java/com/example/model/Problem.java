package com.example.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

import java.util.Arrays;
import java.util.List;

/**
 * Created 11.10.2022
 * @author Nejc Kozlevčar
 */
@Entity
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long id;
    @Column
    @Expose
    private String initialState;
    @Column
    @Expose
    private Integer problemSize;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_PROBLEM_SOLUTION"))
    private Solution solution;

    public Problem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    @Transient
    public List<Boolean> getInitialStateAsList() {
        return Arrays.stream(initialState.split(",")).map(s -> s.equals("1")).toList();
    }

    public Integer getProblemSize() {
        return problemSize;
    }

    public void setProblemSize(Integer problemSize) {
        this.problemSize = problemSize;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
}
