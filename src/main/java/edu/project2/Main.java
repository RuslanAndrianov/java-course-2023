package edu.project2;

import java.util.Scanner;
import org.jetbrains.annotations.NotNull;
import static edu.project2.CellType.WALL;

@SuppressWarnings({"HideUtilityClassConstructor", "RegexpSinglelineJava", "MultipleStringLiterals"})
public class Main {

    private static final int MIN_LENGTH = 3;

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

    private static int inputCoordinates(String strCoordinate, Maze maze, @NotNull Scanner input) {

        int coordinate = inputDimension(strCoordinate, input);
        if (strCoordinate.equals("start X") || strCoordinate.equals("end X")) {
            while (coordinate <= 0 || coordinate >= maze.width) {
                showInvalidInputMessage();
                coordinate = inputDimension(strCoordinate, input);
            }
        }
        if (strCoordinate.equals("start Y") || strCoordinate.equals("end Y")) {
            while (coordinate <= 0 || coordinate >= maze.height) {
                showInvalidInputMessage();
                coordinate = inputDimension(strCoordinate, input);
            }
        }
        return coordinate;
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

        System.out.println("WARNING! For better UX please set line height to 0.7!");
        System.out.println("WARNING! If you input even number, it will be incremented by 1!\n");

        int width = inputDimension("width", input);
        int height = inputDimension("height", input);
        while (width < MIN_LENGTH || height < MIN_LENGTH) {
            showInvalidInputMessage();
            System.out.println("HINT! Width or height cannot be less than 3!");
            width = inputDimension("width", input);
            height = inputDimension("height", input);
        }

        String methodGen = inputMethodNumber("generating", input);
        Maze maze = switch (methodGen) {
            case "1" -> BFSGenerator.generateMaze(height, width);
            case "2" -> DFSGenerator.generateMaze(height, width);
            default -> throw new IllegalStateException("Unexpected value: " + methodGen);
        };

        Renderer renderer = new Renderer(maze);
        System.out.println(renderer.render(maze));

        int startX = inputCoordinates("start X", maze, input);
        int startY = inputCoordinates("start Y", maze, input);
        int endX = inputCoordinates("end X", maze, input);
        int endY = inputCoordinates("end Y", maze, input);
        while (maze.getCell(startY, startX).type == WALL || maze.getCell(endY, endX).type == WALL) {
            showInvalidInputMessage();
            System.out.println("HINT! Start or end cell must not be wall!");
            startX = inputCoordinates("start X", maze, input);
            startY = inputCoordinates("start Y", maze, input);
            endX = inputCoordinates("end X", maze, input);
            endY = inputCoordinates("end Y", maze, input);
        }

        Coordinate start = new Coordinate(startY, startX);
        Coordinate end = new Coordinate(endY, endX);
        String methodSolve = inputMethodNumber("solving", input);
        switch (methodSolve) {
            case "1" -> BFSSolver.solveMaze(maze, start, end);
            case "2" -> DFSSolver.solveMaze(maze, start, end);
            default -> throw new IllegalStateException("Unexpected value: " + methodSolve);
        }

        System.out.println(renderer.render(maze));
        input.close();
    }
}
