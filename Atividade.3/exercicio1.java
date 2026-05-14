// Implementação de Árvore Binária (com livros) //

class BookNode {
    String title;
    BookNode left, right;

    BookNode(String title) {
        this.title = title;
        left = right = null;
    }
}

class BinaryTree {
    BookNode root;

    public void insert(String title) {
        root = insertRecursive(root, title);
    }

    private BookNode insertRecursive(BookNode node, String title) {
        if (node == null) return new BookNode(title);
        if (title.compareTo(node.title) < 0) node.left = insertRecursive(node.left, title);
        else node.right = insertRecursive(node.right, title);
        return node;
    }

    // Pré-ordem
    public void preOrder(BookNode node) {
        if (node != null) {
            System.out.println(node.title);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // Em ordem
    public void inOrder(BookNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.title);
            inOrder(node.right);
        }
    }

    // Pós-ordem
    public void postOrder(BookNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.title);
        }
    }
}
