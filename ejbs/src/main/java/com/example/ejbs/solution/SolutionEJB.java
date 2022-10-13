package com.example.ejbs.solution;

import com.example.model.Solution;
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
public class SolutionEJB {
    @PersistenceContext(name = "default")
    private EntityManager entityManager;

    public Solution get(@NotNull Long solutionId) {
        return entityManager.find(Solution.class, solutionId);
    }

    public List<Solution> getAll() {
        return entityManager.createQuery("select s from Solution s", Solution.class).getResultList();
    }
}
