//Caminho mais curto em grafo ponderado//
class WeightedGraph {
    private Map<String, List<Edge>> adj = new HashMap<>();

    public void addVertex(String v) {
        adj.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(String v1, String v2, int weight) {
        adj.get(v1).add(new Edge(v2, weight));
    }

    public Map<String, List<Edge>> getAdj() {
        return adj;
    }
}

class Edge {
    String dest;
    int weight;

    Edge(String dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

class Dijkstra {
    public Map<String, Integer> shortestPath(WeightedGraph graph, String source) {
        Map<String, Integer> dist = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        for (String v : graph.getAdj().keySet()) dist.put(v, Integer.MAX_VALUE);
        dist.put(source, 0);

        pq.add(new Edge(source, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            for (Edge e : graph.getAdj().get(curr.dest)) {
                int newDist = dist.get(curr.dest) + e.weight;

                if (newDist < dist.get(e.dest)) {
                    dist.put(e.dest, newDist);
                    pq.add(new Edge(e.dest, newDist));
                }
            }
        }

        return dist;
    }
}
