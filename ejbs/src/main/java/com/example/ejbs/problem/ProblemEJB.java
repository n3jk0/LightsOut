package com.example.ejbs.problem;

import com.example.model.Problem;
import com.example.model.Solution;
import com.example.solver.SolverProcessor;
import com.sun.istack.NotNull;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created 11.10.2022
 * @author Nejc Kozlevčar
 */
@Stateless
@LocalBean
public class ProblemEJB {
    private static final Logger logger = LogManager.getLogger(ProblemEJB.class);

    @PersistenceContext(name = "default")
    private EntityManager entityManager;

    public Problem get(@NotNull Long problemId) {
        return entityManager.find(Problem.class, problemId);
    }

    public List<Problem> getAll() {
        return entityManager.createQuery("select p from Problem p", Problem.class).getResultList();
    }

    public Problem create(String initialState, Integer problemSize) {
        Problem problem = new Problem();
        problem.setInitialState(initialState);
        problem.setProblemSize(problemSize);

        SolverProcessor processor = new SolverProcessor(problem);
        Solution solution = processor.solveProblem();
        if (solution == null) {
            logger.info("Problem doesn't have solution. Problem wasn't saved");
            return null;
        }
        solution.setProblem(problem);

        problem.setSolution(solution);
        entityManager.persist(problem);
        return problem;
    }
}
