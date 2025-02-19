package by.it.group151001.nesteruk.lesson11;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskC {
    public static String root;
    public static boolean[] visited;
    public static int[] prev;
    public static int[] post;
    public static int iterator;
    public static List<Character> line = new ArrayList<>();

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.setName(0, 'A');
        graph.setName(1, 'B');
        graph.setName(2, 'C');
        graph.setName(3, 'D');
        graph.setName(4, 'E');
        graph.setName(5, 'F');
        graph.setName(6, 'G');
        graph.setName(7, 'H');
        graph.addOrientedEdge(0, 2);
        graph.addOrientedEdge(1, 2);
        graph.addOrientedEdge(2, 3);
        graph.addOrientedEdge(2, 4);
        graph.addOrientedEdge(3, 5);
        graph.addOrientedEdge(4, 5);
        graph.addOrientedEdge(5, 6);
        graph.addOrientedEdge(5, 7);
        dfs(graph,0);
        for (int i = 0; i < graph.getVertexCount(); i++)
            System.out.println(graph.getName(i) + ": pre = " + prev[i] + ", post = " + post[i]);
        List<Character> sources = sourceAndSink(graph);
        System.out.println("One of linearizations of graph: " + line);
        System.out.println("Number of linearizations: " + sources.size() * 2 * 2);
    }

    public static void search(Graph graph, int vertex) {
        visited[vertex] = true;
        prev[vertex] = iterator++;
        for (int i : graph.getNeighbors(vertex)) {
            if (!visited[i]) {
                search(graph, i);
            }
            line.add(graph.getName(vertex));
            post[vertex] = iterator++;
        }
    }

    public static void dfs (Graph graph,int start)
    {
        visited = new boolean[graph.getVertexCount()];
        prev = new int[graph.getVertexCount()];
        post = new int[graph.getVertexCount()];
        iterator = 0;
        for (int i = start; i < graph.getVertexCount(); i++)
            if (!visited[i]) {
                search(graph, i);
            }
        Collections.reverse(line);
    }

    public static List<Character> sourceAndSink(Graph graph) {
        List<Character> sources = new ArrayList<>();
        List<Character> sinks = new ArrayList<>();
        for (int i = 0; i < graph.getVertexCount(); i++){
            if (graph.getNeighbors(i).length == 0)
                sinks.add(graph.getName(i));
            boolean isSource = true;
            for (int j = 0; j < graph.getVertexCount(); j++)
                if (graph.graph.get(j).contains(i)){
                    isSource = false;
                    break;
                }
            if (isSource)
                sources.add(graph.getName(i));
        }
        System.out.println("Sources: " + sources);
        System.out.println("Sinks: " + sinks);
        return sources;
    }
}
