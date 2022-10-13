package com.example.solver;

import com.example.model.Problem;
import com.example.model.Solution;
import com.example.model.SolutionStep;
import com.example.solver.SolverProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created 12.10.2022
 * @author Nejc Kozlevčar
 */

public class SolverProcessorTest {

    private Problem create3x3Problem() {
        Problem problem = new Problem();
        //problem.setInitialState("0,1,0,1,1,0,1,1,0");
        problem.setInitialState("1,1,1,0,0,0,0,1,0");
        problem.setProblemSize(3);
        return problem;
    }

    private Problem create5x5Problem() {
        Problem problem = new Problem();
        String initState = "";
        initState += "1,0,0,1,1,";
        initState += "0,1,0,0,1,";
        initState += "0,0,1,1,0,";
        initState += "1,1,0,1,1,";
        initState += "0,1,1,1,0";
        //problem.setInitialState("0,1,0,1,1,0,1,1,0");
        //problem.setInitialState("0,1,1,0,0,1,1,1,1,1,0,1,1,0,0,0,0,0,1,1,1,0,1,0,1");
        problem.setInitialState(initState);
        problem.setProblemSize(5);
        return problem;
    }


    @Test
    public void shouldCreateToggledList() {
        SolverProcessor solverProcessor = new SolverProcessor(create3x3Problem());
        Assertions.assertEquals(Arrays.asList(true, true, false, true, false, false, false, false, false), solverProcessor.getToggledLights(0));
        Assertions.assertEquals(Arrays.asList(true, true, true, false, true, false, false, false, false), solverProcessor.getToggledLights(1));

        Assertions.assertEquals(Arrays.asList(false, false, false, false, true, false, true, true, true), solverProcessor.getToggledLights(7));
    }

    @Test
    public void shouldMakeAMove() {
        Problem problem = create3x3Problem();
        SolverProcessor solverProcessor = new SolverProcessor(problem);

        Assertions.assertEquals(Arrays.asList(true, false, true, true, false, false, true, true, false), solverProcessor.makeMove(problem.getInitialStateAsList(), 1));
    }

    @Test
    public void shouldSolveProblem() {
        Problem problem = create5x5Problem();
        SolverProcessor solverProcessor = new SolverProcessor(problem);

        solverProcessor.solveProblem();
    }
}
