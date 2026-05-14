// BFS (Caminho mais curto em grafo NÃO ponderado) //
class BFS {
    public List<String> shortestPath(Graph graph, String start, String goal) {
        Map<String, List<String>> adj = graph.getAdjList();
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();

        queue.add(start);
        parent.put(start, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(goal)) break;

            for (String neighbor : adj.get(current)) {
                if (!parent.containsKey(neighbor)) {
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        List<String> path = new ArrayList<>();
        String cur = goal;
        while (cur != null) {
            path.add(cur);
            cur = parent.get(cur);
        }

        Collections.reverse(path);
        return path;
    }
}

