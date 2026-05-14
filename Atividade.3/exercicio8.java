//Menor valor em uma BST//
class BSTMinValue {
    public BookNode findMin(BookNode root) {
        while (root.left != null) root = root.left;
        return root;
    }
}
