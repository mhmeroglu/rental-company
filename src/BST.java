public class BST {
    static class Node {
        int ID;

        String name;

        int star;

        String available;
        Node left, right;

        public Node(int ID, String name, int star, String available) {
            this.ID = ID;
            this.name = name;
            this.star = star;
            this.available = available;
        }
    }

    // BST root node
    Node root;

    // Constructor for BST =>initial empty tree
    public BST() {
        root = null;
    }

    //for calling the method
    void addCaptain(Node node) {
        root = addCaptainHelper(root, node);
    }


    Node addCaptainHelper(Node root, Node node) {
//--------------------------------------------------------
// Summary:In this function we create captain and
// store it in binary search tree.
// Precondition: node and root is a Node.
// Postcondition: Values are recursively defined to nodes.
//--------------------------------------------------------
        int data = node.ID;
        if (root == null) {
            root = node;
            return root;
        } else if (data < root.ID) {
            root.left = addCaptainHelper(root.left, node);
        } else {
            root.right = addCaptainHelper(root.right, node);
        }
        return root;
    }

    //for calling the method
    void rentCar(int ID) {
        rentCarHelper(root, ID);
    }

    void rentCarHelper(Node node, int ID) {
//--------------------------------------------------------
// Summary: This method shows if the car can be rented or not.
// Precondition: root is a Node and ID is integer.
// Postcondition: Printed by recursively checking the car can be rented or not.
//--------------------------------------------------------
        if (node == null) {
            System.out.println("IsAvailable: Couldn't find any captain with ID number " + ID);
            System.out.println();
            System.out.println("----------------------------------------------------------------");
        } else if (node.ID == ID) {
            if (node.available.equalsIgnoreCase("True")) {
                System.out.println("IsAvailable: Reserve a new Ride with captain " + node.ID);
                System.out.println();
                System.out.println("----------------------------------------------------------------");
                node.available = "False";
            } else {
                System.out.println("IsAvailable: The captain " + node.name + " is not available. He is on another ride!");
                System.out.println();
                System.out.println("----------------------------------------------------------------");
            }
        } else if (node.ID > ID) {
            rentCarHelper(node.left, ID);
        } else {
            rentCarHelper(node.right, ID);
        }
    }

    //for calling the method
    public void finishRide(int data, int star) {
        finishRideHelper(root, data, star);
    }

    private void finishRideHelper(Node root, int data, int star) {
//--------------------------------------------------------
// Summary: In this method, the rental period of the vehicle ends and
// the satisfaction is evaluated.
// Precondition: root is a Node, int data is integer and star is integer.
// Postcondition: The condition of the vehicle is checked recursively,
// its satisfaction is defined and printed.
//--------------------------------------------------------
        if (root == null) {
            System.out.println("Finish: Couldn't find any captain with ID number " + data);
        } else if (root.ID == data) {
            if (root.available.equalsIgnoreCase("True")) {
                System.out.println("Finish: The captain " + root.name + " is not in a ride");
            } else {
                root.available = "True";
                root.star = star;
                System.out.println("Finish: Finish ride with captain " + root.ID);
                printCaptain(root.ID);
            }
        } else if (root.ID > data) {
            finishRideHelper(root.left, data, star);
        } else {
            finishRideHelper(root.right, data, star);
        }
    }

    //for calling the method
    void printAll() {
        printAllHelper(root);
    }

    void printAllHelper(Node root) {
//--------------------------------------------------------
// Summary:In this function we print all captain information's
// Precondition: root is a Node.
// Postcondition: Values are printed by using preorder traversal.
//--------------------------------------------------------
        if (root == null)
            return;

        System.out.println("--CAPTAIN:");
        System.out.println("");
        System.out.println("                      ID: " + root.ID);
        System.out.println("                      Name: " + root.name);
        System.out.println("                      Available: " + root.available);
        System.out.println("                      Rating star: " + root.star);
        System.out.println();
        System.out.println();

        printAllHelper(root.left);
        printAllHelper(root.right);
    }

    //for calling the method
    void printCaptain(int ID) {
        printCaptain(root, ID);
    }

    void printCaptain(Node root, int ID) {
//--------------------------------------------------------
// Summary:In this function we print captain information's
// Precondition: root is a Node and ID is integer.
// Postcondition: Values are printed recursively.
//--------------------------------------------------------
        if (root == null) {
            System.out.println("Couldn't find any captain with ID number " + ID);
        } else if (root.ID == ID) {
            System.out.println();
            System.out.println("                      ID: " + root.ID);
            System.out.println("                      Name: " + root.name);
            System.out.println("                      Available: " + root.available);
            System.out.println("                      Rating star: " + root.star);
        } else if (root.ID > ID) {
            printCaptain(root.left, ID);
        } else {
            printCaptain(root.right, ID);
        }
    }

    //for calling the method
    void delete(int value) {
        deleteHelper(root, value);
    }

    Node deleteHelper(Node root, int value) {
//--------------------------------------------------------
// Summary: In this method, we find and delete the captain that is desired to be deleted recursively.
// Precondition: root is a Node and value is integer.
// Postcondition: the found node has been deleted.
//--------------------------------------------------------
        if (root == null) {
            System.out.println("Delete Captain: Couldn't find any captain with ID number " + value);
            return null;
        }
        if (value < root.ID) {
            root.left = deleteHelper(root.left, value);
        } else if (value > root.ID) {
            root.right = deleteHelper(root.right, value);
        } else {
            System.out.println("Delete Captain:The captain " + root.name + " left CCR");
            if (root.left == null) {
                return root.right;
            } else if (root.right == null)
                return root.left;

            root.ID = successor(root.right);
            root.right = deleteHelper(root.right, (int) root.ID);
        }
        return root;
    }

    public static int successor(Node root) {
//--------------------------------------------------------
// Summary: In this method, we find min value.
// Precondition: root is a Node.
// Postcondition: returned min value.
//--------------------------------------------------------
        int minimum = (int) root.ID;
        while (root.left != null) {
            minimum = (int) root.left.ID;
            root = root.left;
        }
        return minimum;
    }
}