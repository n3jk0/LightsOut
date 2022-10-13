package com.example.api;

import com.example.ejbs.problem.ProblemEJB;
import com.example.model.Problem;
import com.google.gson.Gson;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Created 11.10.2022
 * @author Nejc Kozlevčar
 */
@Path("/problems")
@Stateless
public class ProblemRestImpl {
    @EJB
    private ProblemEJB problemEJB;

    @GET
    @Produces("text/plain")
    public Response getAll() {
        List<Problem> allProblems = problemEJB.getAll();
        return Response.ok(RestApiUtils.createGson().toJson(allProblems)).build();
    }

    @GET
    @Path("/{id}")
    @Produces("text/plain")
    public Response get(@PathParam("id") Long problemId) {
        Problem problem = problemEJB.get(problemId);
        if (problem == null) {
            return Response.noContent().build();
        }
        return Response.ok(RestApiUtils.createGson().toJson(problem)).build();
    }

    @POST
    @Produces("text/plain")
    public String create() {
        Problem problem = problemEJB.create();
        if (problem == null) {
            return "Problem wasn't saved!";
        }
        return String.format("Problem with %d id is saved.", problem.getId());
    }
}
