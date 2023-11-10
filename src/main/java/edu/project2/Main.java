package edu.project2;

import org.jetbrains.annotations.NotNull;
import java.util.Scanner;

public class Main {

    private static void showInvalidInputMessage() {
        System.out.println("Invalid input! Please try again.");
    }

    private static int inputDimension(String dimension, @NotNull Scanner input) {

        System.out.println("Input " + dimension + ":");
        String inputStr = input.nextLine();
        while (!inputStr.matches("\\d+")) {
            showInvalidInputMessage();
            System.out.println("Input " + dimension + ":");
            inputStr = input.nextLine();
        }
        return Integer.parseInt(inputStr);
    }

    private static String inputMethodNumber(String action, @NotNull Scanner input) {
        System.out.println("Choose method for " + action + " maze:");
        System.out.println("(1 - for BFS, 2 - for DFS)");
        String method = input.nextLine();
        while (!(method.equals("1") || method.equals("2"))) {
            showInvalidInputMessage();
            System.out.println("Choose method for " + action + " maze:");
            System.out.println("(1 - for BFS, 2 - for DFS)");
            method = input.nextLine();
        }
        return method;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("WARNING! For better UX please set line height to 0.7");
        System.out.println("WARNING! If you input even number, it will be incremented by 1\n");

        int width = inputDimension("width", input);
        int height = inputDimension("height", input);
        String methodGen = inputMethodNumber("generating", input);

        Maze maze = switch (methodGen) {
            case "1" -> BFSGenerator.generateMaze(height, width);
            case "2" -> DFSGenerator.generateMaze(height, width);
            default -> throw new IllegalStateException("Unexpected value: " + methodGen);
        };

        Renderer renderer = new Renderer(maze);
        System.out.println(renderer.render(maze));

        int startX = inputDimension("start X", input);
        int startY = inputDimension("start Y", input);
        int endX = inputDimension("end X", input);
        int endY = inputDimension("end Y", input);
        Coordinate start = new Coordinate(startY, startX);
        Coordinate end = new Coordinate(endY, endX);
        String methodSolve = inputMethodNumber("solving", input);

        switch (methodSolve) {
            case "1" -> BFSSolver.solveMaze(maze, start, end);
            case "2" -> DFSSolver.solveMaze(maze, start, end);
        }

        System.out.println(renderer.render(maze));

        input.close();
    }
}
