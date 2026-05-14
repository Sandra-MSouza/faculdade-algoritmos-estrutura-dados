// Validar se uma árvore é BST //
class ValidateBST {
    public boolean isBST(BookNode root) {
        return isBSTUtil(root, null, null);
    }

    private boolean isBSTUtil(BookNode node, String min, String max) {
        if (node == null) return true;

        if ((min != null && node.title.compareTo(min) <= 0) ||
            (max != null && node.title.compareTo(max) >= 0))
            return false;

        return isBSTUtil(node.left, min, node.title) &&
               isBSTUtil(node.right, node.title, max);
    }
}
