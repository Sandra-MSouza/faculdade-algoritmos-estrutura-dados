//Representação de Grafos (Lista de Adjacência)//
import java.util.*;

class Graph {
    private Map<String, List<String>> adjList = new HashMap<>();

    public void addVertex(String v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(String v1, String v2) {
        adjList.get(v1).add(v2);
    }

    public Map<String, List<String>> getAdjList() {
        return adjList;
    }
}