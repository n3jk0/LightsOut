package com.example.api;

import com.example.api.payload.ProblemPayload;
import com.example.ejbs.problem.ProblemEJB;
import com.example.model.Problem;
import com.example.solver.SolverProcessor;
import com.google.gson.Gson;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created 11.10.2022
 * @author Nejc Kozlevčar
 */
@Path("/problems")
@Stateless
public class ProblemRestImpl {
    private static final Logger logger = LogManager.getLogger(ProblemRestImpl.class);
    @EJB
    private ProblemEJB problemEJB;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Problem> allProblems = problemEJB.getAll();
        return Response.ok(RestApiUtils.createGson().toJson(allProblems)).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/{id}")
    @Produces("text/plain")
    public Response get(@PathParam("id") Long problemId) {
        Problem problem = problemEJB.get(problemId);
        if (problem == null) {
            return Response.noContent().build();
        }
        return Response.ok(RestApiUtils.createGson().toJson(problem)).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ProblemPayload payload) {
        if (payload == null) {
            logger.warn("Empty payload. Can't save problem");
            return Response.serverError().header("Access-Control-Allow-Origin", "*").build();
        }

        String initialState = payload.getInitialState();
        if (initialState == null || initialState.isEmpty()) {
            logger.warn("Empty initialState. Can't save problem");
            return Response.serverError().header("Access-Control-Allow-Origin", "*").build();
        }

        int stateSize = initialState.split(",").length;
        double problemSize = Math.pow(stateSize, 0.5);

        if (problemSize % 1 != 0) {
            logger.warn("Wrong initial state. Can't save problem");
            return Response.serverError().header("Access-Control-Allow-Origin", "*").build();
        }

        Problem problem = problemEJB.create(initialState, (int)problemSize);
        if (problem == null) {
            logger.warn("Problem wasn't saved!");
            return Response.serverError().header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.ok(RestApiUtils.createGson().toJson(problem)).header("Access-Control-Allow-Origin", "*").build();
    }
}
