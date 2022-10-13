package com.example.ejbs.problem;

import com.example.model.Problem;
import com.example.model.Solution;
import com.example.model.SolutionStep;
import com.example.solver.SolverProcessor;
import com.sun.istack.NotNull;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

/**
 * Created 11.10.2022
 * @author Nejc Kozlevčar
 */
@Stateless
@LocalBean
public class ProblemEJB {

    @PersistenceContext(name = "default")
    private EntityManager entityManager;

    public Problem get(@NotNull Long problemId) {
        return entityManager.find(Problem.class, problemId);
    }

    public List<Problem> getAll() {
        return entityManager.createQuery("select p from Problem p", Problem.class).getResultList();
    }

    public Problem create() {
        Problem problem = new Problem();
        problem.setInitialState("0,1,0,1,1,0,1,1,0");
        problem.setProblemSize(3);

        SolverProcessor processor = new SolverProcessor(problem);
        Solution solution = processor.solveProblem();
        if (solution == null) {
            System.out.println("Problem doesn't have solution. Problem is not saved");
            return null;
        }
        solution.setProblem(problem);

        problem.setSolution(solution);
        entityManager.persist(problem);
        return problem;
    }
}
