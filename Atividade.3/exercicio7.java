// DFS //
class DFS {
    public void dfs(Graph g, String start) {
        Set<String> visited = new HashSet<>();
        dfsRecursive(start, visited, g.getAdjList());
    }

    private void dfsRecursive(String node, Set<String> visited, Map<String, List<String>> adj) {
        visited.add(node);
        System.out.println(node);

        for (String neighbor : adj.get(node)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited, adj);
            }
        }
    }
}

