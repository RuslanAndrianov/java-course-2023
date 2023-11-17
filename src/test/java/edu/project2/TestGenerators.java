package edu.project2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class TestGenerators {

    void BFS(int y, int x) {
        System.out.println("x = " + x + ", y = " + y + "\n");
        Maze maze = BFSGenerator.generateMaze(y, x);
        System.out.println(new Renderer(maze).render(maze));
        assertEquals(maze.width, x);
        assertEquals(maze.height, y);
    }

    void DFS(int y, int x) {
        System.out.println("x = " + x + ", y = " + y + "\n");
        Maze maze = DFSGenerator.generateMaze(y, x);
        System.out.println(new Renderer(maze).render(maze));
        assertEquals(maze.width, x);
        assertEquals(maze.height, y);
    }

    @Test
    @DisplayName("Тест BFSGenerator")
    void generateMazeBFS() {
        BFS(11,6);
        BFS(6,11);
        BFS(11,11);
        BFS(6,6);
        BFS(11,7);
        BFS(7,11);
        BFS(6,10);
        BFS(10,6);
    }

    @Test
    @DisplayName("Тест DFSGenerator")
    void generateMazeDFS() {
        DFS(11,6);
        DFS(6,11);
        DFS(11,11);
        DFS(6,6);
        DFS(11,7);
        DFS(7,11);
        DFS(6,10);
        DFS(10,6);
    }


}
