package com.example.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created 11.10.2022
 * @author Nejc Kozlevčar
 */
@Entity
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long id;

    // Maybe not the best solution that we use EAGER fetch
    @OrderBy("stepOrder")
    @OneToMany(mappedBy = "solution", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Expose
    private List<SolutionStep> solutionSteps;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_SOLUTION_PROBLEM"))
    @Expose
    private Problem problem;

    public Solution() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public List<SolutionStep> getSolutionSteps() {
        return solutionSteps;
    }

    public void setSolutionSteps(List<SolutionStep> solutionSteps) {
        this.solutionSteps = solutionSteps;
    }

    @Transient
    public boolean addSolutionStep(SolutionStep solutionStep) {
        if (solutionSteps == null) {
            solutionSteps = new ArrayList<>();
        }
        solutionStep.setSolution(this);
        return solutionSteps.add(solutionStep);
    }
}
