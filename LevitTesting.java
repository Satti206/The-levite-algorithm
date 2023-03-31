import java.io.*;
import java.util.*;

public class LevitTesting {
    public static void main(String[] args) throws IOException {
        // Generate test files
        generateTestFiles();

        // Test Levit class on small arrays
        testLevit("small_arrays.txt");

        // Test Levit class on big arrays
        testLevit("big_arrays.txt");
    }

    // Method to generate test files
    public static void generateTestFiles() throws IOException {
        // Small arrays
        FileWriter smallWriter = new FileWriter("small_arrays.txt");
        for (int i = 0; i < 10; i++) {
            int[][] graph = generateGraph(10, 20);
            writeGraphToFile(graph, smallWriter);
        }
        smallWriter.close();

        // Big arrays
        FileWriter bigWriter = new FileWriter("big_arrays.txt");
        for (int i = 0; i < 10; i++) {
            int[][] graph = generateGraph(1000, 10000);
            writeGraphToFile(graph, bigWriter);
        }
        bigWriter.close();
    }

    // Method to generate a random graph
    public static int[][] generateGraph(int n, int m) {
        int[][] graph = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < m; i++) {
            int u = rand.nextInt(n);
            int v = rand.nextInt(n);
            int w = rand.nextInt(100);
            graph[u][v] = w;
            graph[v][u] = w;
        }
        return graph;
    }

    // Method to write a graph to file
    public static void writeGraphToFile(int[][] graph, FileWriter writer) throws IOException {
        writer.write(graph.length + "\n");
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                writer.write(graph[i][j] + " ");
            }
            writer.write("\n");
        }
    }

    // Method to test Levit class on a file
    public static void testLevit(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        int count = 0;
        long totalTime = 0;
        while (line != null) {
            int n = Integer.parseInt(line);
            int[][] graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                String[] parts = line.split(" ");
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(parts[j]);
                }
            }
            Levit levit = new Levit(graph);
            long startTime = System.nanoTime();
            levit.shortestPath(0);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            count++;
            line = reader.readLine();
        }
        reader.close();
        long averageTime = totalTime / count;
        System.out.println("Файл: " + filename + " - Среднее время: " + averageTime + " миллисекунд - Итераций: " + Levit.iterations);
    }
}


