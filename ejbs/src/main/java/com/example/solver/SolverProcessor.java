package com.example.solver;

import com.example.model.Problem;
import com.example.model.Solution;
import com.example.model.SolutionStep;
import com.google.common.collect.Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created 12.10.2022
 * @author Nejc Kozlevčar
 */
public class SolverProcessor {
    private final int problemSize;
    private final List<Boolean> initialStateAsList;

    public SolverProcessor(Problem problem) {
        this.problemSize = problem.getProblemSize();
        this.initialStateAsList = problem.getInitialStateAsList();
    }

    public SolverProcessor(int problemSize, List<Boolean> initialStateAsList) {
        this.problemSize = problemSize;
        this.initialStateAsList = initialStateAsList;
    }

    public Solution solveProblem() {
        long start = System.currentTimeMillis();
        List<String> correctMoves;
        for (int i = 0; i < Math.pow(2, problemSize); i++) {
            List<Boolean> currentState = new ArrayList<>(initialStateAsList);
            correctMoves = new ArrayList<>();

            // Get binary number with starting zeros
            String firstRowMoves = String.format("%" + problemSize + "s", Integer.toBinaryString(i)).replaceAll(" ", "0");
            correctMoves.add(firstRowMoves);
            // Special case for first row
            currentState = makeMovesForRow(firstRowMoves, currentState);

            for (int j = 0; j < problemSize - 1; j++) {
                List<Boolean> upperRow = currentState.subList(j * problemSize, j * problemSize + problemSize);
                String moves = upperRow.stream().map(bool -> bool ? "1" : "0").collect(Collectors.joining());
                correctMoves.add(moves);
                currentState = makeMovesForRow(moves, currentState, j + 1);
            }
            boolean correctSolution = isCorrectSolution(currentState);
            if (correctSolution) {
                System.out.println("FINAL SOLUTION: '" + firstRowMoves + "'");
                System.out.println("Moves: '" + correctMoves + "'");
                System.out.println("Solution found in " + (System.currentTimeMillis() - start) + "ms");
                String allMoves = String.join("", correctMoves);
                return createSolution(allMoves);
            } else {
                System.out.printf("First row move '%S' isn't correct!\n", firstRowMoves);
            }
        }
        System.out.println("Solution wasn't found in " + (System.currentTimeMillis() - start) + "ms");
        return null;
    }

    private Solution createSolution(String movesAsString) {
        Solution solution = new Solution();
        int order = 1;
        int move = movesAsString.indexOf("1");
        while(move >= 0) {
            solution.addSolutionStep(new SolutionStep(move, order++));
            move = movesAsString.indexOf("1", move + 1);
        }
        return solution;
    }

    List<Boolean> getToggledLights(int move) {
        // Create list where all elements are false
        List<Boolean> toggledList = Arrays.asList(new Boolean[problemSize * problemSize]);
        Collections.fill(toggledList, false);

        if (move < 0 || toggledList.size() < move) {
            // Wrong move
            return toggledList;
        }

        toggledList.set(move, true);
        // Toggle left
        if (move % problemSize != 0) {
            toggledList.set(move - 1, true);
        }
        // Toggle right
        if (move % problemSize != problemSize - 1) {
            toggledList.set(move + 1, true);
        }
        // Toggle up
        if (move - problemSize >= 0) {
            toggledList.set(move - problemSize, true);
        }
        // Toggle down
        if (move + problemSize < problemSize * problemSize) {
            toggledList.set(move + problemSize, true);
        }
        return toggledList;
    }

    List<Boolean> makeMove(List<Boolean> currentState, int move) {
        List<Boolean> toggledLights = getToggledLights(move);
        return Streams.zip(currentState.stream(), toggledLights.stream(), (current, toggled) -> current ^ toggled).toList();
    }

    List<Boolean> makeMovesForRow(String moves, List<Boolean> currentState) {
        return makeMovesForRow(moves, currentState, 0);
    }

    List<Boolean> makeMovesForRow(String moves, List<Boolean> currentState, int row) {
        int move = moves.indexOf("1");
        while(move >= 0) {
            currentState = makeMove(currentState, move + (row * problemSize));
            move = moves.indexOf("1", move + 1);
        }
        return currentState;
    }

    boolean isCorrectSolution(List<Boolean> state) {
        // All are false
        return state.stream().noneMatch(Boolean::booleanValue);
    }

    void printTo2D(List<Boolean> state) {
        for (int i = 0; i < state.size(); i++) {
            if (i % 3 == 0) {
                System.out.println();
            }
            System.out.print(" " + (state.get(i) ? "1" : "0"));
        }
        System.out.println();
    }
}
