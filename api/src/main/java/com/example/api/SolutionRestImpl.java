package com.example.api;

import com.example.ejbs.problem.ProblemEJB;
import com.example.ejbs.solution.SolutionEJB;
import com.example.model.Problem;
import com.example.model.Solution;
import com.google.gson.Gson;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Created 11.10.2022
 * @author Nejc Kozlevčar
 */
@Path("solutions")
public class SolutionRestImpl {
    @EJB
    private SolutionEJB solutionEJB;

    @EJB
    private ProblemEJB problemEJB;

    @GET
    @Produces("text/plain")
    public Response getAll() {
        List<Solution> allSolutions = solutionEJB.getAll();
        return Response.ok(RestApiUtils.createGson().toJson(allSolutions)).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/problem/{id}")
    @Produces("text/plain")
    public Response getSolutionForProblem(@PathParam("id") Long problemId) {
        Problem problem = problemEJB.get(problemId);
        if (problem == null) {
            return Response.noContent().build();
        }
        return Response.ok(RestApiUtils.createGson().toJson(problem.getSolution())).header("Access-Control-Allow-Origin", "*").build();
    }


}
