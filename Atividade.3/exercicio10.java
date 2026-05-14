//Ordenação Topológica//
class TopologicalSort {
    public List<String> sort(Graph graph) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for (String vertex : graph.getAdjList().keySet()) {
            if (!visited.contains(vertex)) {
                dfs(vertex, visited, stack, graph.getAdjList());
            }
        }

        List<String> result = new ArrayList<>();
        while (!stack.isEmpty()) result.add(stack.pop());
        return result;
    }

    private void dfs(String v, Set<String> visited, Stack<String> stack, Map<String, List<String>> adj) {
        visited.add(v);
        for (String neighbor : adj.get(v)) {
            if (!visited.contains(neighbor)) dfs(neighbor, visited, stack, adj);
        }
        stack.push(v);
    }
}
