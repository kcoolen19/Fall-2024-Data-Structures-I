/**
 * A simple binary search tree
 */
public class BST {
    /** The entry point to the tree */
    private TreeNode root;
    /** Count of nodes in the tree */
    private int numberOfNodes;
    /** Longest and shortest words stored in the tree */
    private String longest;
    private String shortest;
    public static final String DELETE_NODE = "The node to be deleted is ";
    public static final String SUCCESSOR_NODE = "The successor node is ";
    public static final String FORMAT = "\"";

    /** Default constructor */
    public BST() {
        this.root = null;
        this.numberOfNodes = 0;
        this.shortest = null;
        this.longest = null;
    } // default constructor

    /**
     * Overloaded add to take a string, wrap it into a TreeNode object, and invoke
     * the principal method that adds a note to the tree.
     * 
     * @param word String to add, as a node, to the tree
     * 
     */
    public void add(String word) {
        this.add(new TreeNode(word));
    } // method add

    /**
     * Insert a new node into the tree; the method takes no action if a node with
     * the same payload already exists in the tree.
     * 
     * @param newNode node to insert
     */
    public void add(TreeNode newNode) {
        if (this.root == null) {
            this.root = newNode;
            this.numberOfNodes = 1;
            this.shortest = newNode.getWord();
            this.longest = newNode.getWord();
        } else {
            TreeNode cursor = this.root;
            TreeNode parent = null;
            boolean duplicate = false;
            while (cursor != null && !duplicate) {
                parent = cursor;
                duplicate = newNode.compareTo(cursor) == 0;
                if (newNode.compareTo(cursor) < 0) {
                    cursor = cursor.getLeft();
                } else {
                    cursor = cursor.getRight();
                }
            }
            // The while loop ends when it finds a spot for the new node or when discovering
            // a duplicate entry. If there is a duplicate entry, there will be no insertion.
            if (!duplicate) {
                if (newNode.compareTo(parent) < 0) {
                    parent.setLeft(newNode);
                } else {
                    parent.setRight(newNode);
                }
                // Update the number of nodes in the tree
                this.numberOfNodes++;
                // Check if new node contains a string longer than the longest string
                if (newNode.getWord().length() > this.longest.length()) {
                    this.longest = newNode.getWord();
                }
                // Check if new node has a string shorter than the shortest string
                if (newNode.getWord().length() < this.shortest.length()) {
                    this.shortest = newNode.getWord();
                }
            }
        }
    } // method add

    /**
     * In order traversal of a tree
     * 
     * @return a String[] with the contents of the tree as they appear
     */
    public void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.println(node.getWord());
            traverseInOrder(node.getRight());
        }
    } // method traverseInOrder

    /**
     * Helper method to start in-order traversal
     */
    public void traverseInOrder() {
        if (this.root != null) {
            this.traverseInOrder(this.root);
        }
    } // helper method traverseInOrder

    /**
     * Helper method that initiates removal of a node with a specific string. The
     * method calls an overloaded version of itself to do the actual digging. The
     * overloaded method can focus on the tree itself (starting from this.root) or
     * any subtree thereof.
     * 
     * @param target string contents of node we wish to remove
     * @return the removed node; if no node found, method returns null
     */
    public TreeNode remove(String target) {
        TreeNode removed = null;
        if (target != null && this.root != null) {
            removed = this.remove(target, this.root);
        }
        return removed;
    } // helper method remove

    /**************************************************************************
     * METHOD STUBS FOR ASSIGNMENT DUE 11/15/24. THESE METHODS ARE INCOMPLETE AND,
     * OBVIOUSLY, LACK DOCUMENTATION. AS PART OF THE ASSIGNMENT, YOU'LL PROVIDE THE
     * NECESSARY COMMENTS AND, OF COURSE, SOME AWESOME CODE.
     **************************************************************************/

    /**
     * The method will check whether the Binary Search Tree contains the String parameter, target 
     * A current node is used to traverse through the tree and starts at the root
     * A boolean condition set to return if the String parameter is present
     * 
     * @param target word
     * @return whether the String parameter, target is present
     */
    public boolean contains(String target) {
        // Condition for the presence of the String parameter
        boolean found = false;
        /* The presence of the parameter is checked as long as:
        1) There is a root node in the tree
        2) The String parameter is not null
        */
        if (this.root != null && target != null) {
            // Declaring a current node that will check each node in the tree
            // The current node starts at the root
            TreeNode current = this.root;
            // A while loop is run until the String parameter is found
            while (!found && current != null) {
                // Change boolean variable if the word in the current node matches the one from the parameter
                found = (current.getWord().equals(target));
                // Declare a variable that will compare the two words lexicographically
                int comp = target.compareTo(current.getWord());
                // If the word target is lexicographically smaller than the word in the current node
                if (comp < 0) {
                    // The left child is set as the current node
                    current = current.getLeft();
                }
                else {
                    // The right child is set as the current node
                    current = current.getRight();
                }
            }
        }
        // True if the word in the parameter is found, false if not 
        return found;
    } 
    /**
     * The method returns statements that describe the different characteristics of the binary search tree.
     * It prints:
     * Whether the node is empty
     * The number of nodes in the tree
     * The shortest string present in a node in the tree
     * The longest string present in a node in the tree
     * 
     * @return Description of the tree
     */

    public String toString() {
        final String numberOfNodes = "The number of nodes in the tree is ";
        final String shortestString = "The shortest word in the tree is ";
        final String longestString = "The longest word in the tree is ";
        final String emptyTree = "The tree is empty";
        final String rootNode = "The root is ";
        final String newLine = "\n";
        String treeContent = "";
        if (this.root == null) {
            treeContent += emptyTree;
        } else {
            treeContent += numberOfNodes + this.numberOfNodes + newLine;
            treeContent += shortestString + FORMAT + this.shortest + FORMAT + newLine;
            treeContent += longestString + FORMAT + this.longest + FORMAT + newLine;
            treeContent += rootNode + FORMAT + this.root + FORMAT + newLine;
        }
        return treeContent;
    } // method toString

    /**
     * The process is split into the removing the node containing the target parameter of type String.
     * The removal depends on three cases belonging to the status of the tree:
     * 1) When the root has no children
     * 2) When it has one child
     * 3) When it has two children
     * 
     * The process is split into:
     * 1) Traversing the tree to obtain the node with the String parameter in it
     * 2) Removing that node based on how many nodes are attached to it
     * 
     * When the node has zero child,
     * The node to be removed, is directly set to null through its parent node and is removed
     * 
     * When the node has one child,
     * The position of the current node is checked with respect to its parent node.
     * Since the current node has only one child, its child node is checked as either a left or a right child
     * The child node of the current node is then attached to the grandparent node(the parent node of the current node)
     * It replaces the current node and become the new child of the original parent node
     * It bypasses the current node and the latter is removed as its pointer is eliminated from the parent node
     * 
     * When the node has two children,
     * A successor node is created to replace the node having two children. 
     * The successor is retrieved as the leftmost node as the right subtree.
     * The current node to be removed is then replaced by the successor node.
     * However, there is a duplicate node holding the same word as the successor node.
     * The node is removed through a recursive call of the remove() method 
     * 
     * The number of nodes is decreased upon successful deletion
     * The shortest and longest string is updated if the deletion affects them
     * 
     * @param target The String value in the node which is to be removed 
     * @param root The root from which the node is to be removed
     * @return The node being removed
     */

    public TreeNode remove(String target, TreeNode belowNode) {
        // Variables parent and current are used when traversing through the tree
        // Node used to find the node to be deleted
        TreeNode current = belowNode;
        TreeNode parent = null;
        // Condition to see if String parameter is found
        boolean found = false;
        // Treenode removed that will be returned
        TreeNode removed = null;
    
        // Traversal the tree until the target parameter is found
        while (current != null && !found) {
            // The parent node is set to the current node
            parent = current;
            // Changing condition to when String parameter is found
            found = target.equals(current.getWord());;
            // Variable that will compare target parameter with String in the current node
            int compareCurrentNode = target.compareTo(current.getWord());
            // If the target parameter is lexicographically smaller than the word in the current node
            if (compareCurrentNode < 0) {
                // The current node is moved to the left child of the parent node
                current = current.getLeft();  
            // If the target parameter is lexicographically greater than the word in the current node
            } else if (compareCurrentNode > 0){
                // The current node is moved to the right child of the parent node
                current = current.getRight(); 
            }
        }
        // If the String parameter target is obtained in the tree, the deletion process occurs in the following way
        if (found) {
            // Setting the node to removed and returned to the current node
            removed = current;
            // Situation #1: The node has no children
            if (current.numberOfChildren() == 0) {
                // If the current node is the left child of the parent node 
                if (parent.getLeft() == current) {
                    // The current node is set to null(The left child)
                    parent.setLeft(null);  
                // Else if the current node is the right child of the parent node 
                } else {
                    // The current node is set to null(The right child)
                    parent.setRight(null); 
                }
                System.out.println(DELETE_NODE + FORMAT + current.getWord() + FORMAT);
            
            // Situation #2: The node has 1 child
            } else if (current.numberOfChildren() == 1) {
                // Check whether the current node is the left child of the parent child
                if (parent.getLeft() == current) {
                    // If the only child of the current node is a left child
                    if (current.hasLeft()) {
                        // The current node is replaced by its left child as the left child of the parent node
                        parent.setLeft(current.getLeft());
                        // Else, if the only child of the current node is a right child
                    } else {
                        // The current node is replaced by its right child as the left child of the parent node
                        parent.setLeft(current.getRight());
                    }
                }
                // Check whether the current node is the right child of the parent child
                else {
                    // If the only child of the current node is a left child
                    if (current.getLeft() != null) {
                        // The current node is replaced by its left child as the right child of the parent node
                        parent.setRight(current.getLeft());
                    // Else, if the only child of the current node is a right child    
                    } else {
                        // The current node is replaced by its right child as the right child of the parent node
                        parent.setRight(current.getRight());
                    }
                }
                System.out.println(DELETE_NODE + FORMAT + current.getWord() + FORMAT);
            // Situation #3: The node has 2 children
            } else {
                // Declaring a variable that will become the successor the root of the subtree which has 2 children
                TreeNode successor = current.getRight();
                while (successor.getLeft() != null) {
                    // The success is retrieved from the left child of the right most node in the tree
                    successor = successor.getLeft();
                }
                System.out.println(DELETE_NODE + FORMAT + current.getWord() + FORMAT);
                System.out.println(SUCCESSOR_NODE + FORMAT + successor.getWord() + FORMAT);
                // The node to be removed is replaced by the the successor node
                current.setWord(successor.getWord());
                // The duplicate node is removed through a recursive call 
                this.remove(successor.getWord(),current.getRight());
            }
            // Upon successful deletion, the number of nodes in the tree is decremented
            this.numberOfNodes--;
            /*  The shortest and longest string are updated at the end of the removal if the node deleted had:
                1) The shortest string or
                2) The longest string
            */
            updateLongest();
            updateShortest();
        }
        // The node to be removed, which is the current node reached during traversal, is returned
        return removed;
    }


    /**
     * This method is to update shortest string in the tree in case the node removed, contained the shortest string
     * The shortest string is set to the word in the current node provided:
     * The word in the current node is smaller in length compared to this.shortest
     * 
     */
    public void updateShortest() {
        // Statement to show and update shortest string if node with shortest string is removed
        String shortestWordStatement = "The shortest word is: ";
        // Declaring current that will check each node from the root
        TreeNode current = this.root;
        // Resetting the shortest string
        this.shortest = this.root.getWord();
        // Traversal loop
        while (current != null) {
            // The tree is traversed as the length of the current node is checked 
            if (this.shortest == null || current.getWord().length() < this.shortest.length()) {
                // If the length of the current node is smaller than this.shortest
                // this.shortest is set to that word
                this.shortest = current.getWord();
            }
            // If the current node has a left child
            if (current.getLeft() != null) {
                // The current node is moved to that left child
                current = current.getLeft();
            } else {
                // Else, the current node is moved to the right child if present
                current = current.getRight();
            }
        }
        // Statement to show the shortest string in the tree if the removal affects it
        System.out.println(shortestWordStatement + FORMAT + this.shortest + FORMAT);
    }
    
    /**
     * This method is to update longest string in the tree in case the node removed, contained the longest string
     * The longest string is set to the word in the current node provided:
     * The word in the current node is greater in length compared to this.longest
     * 
     */
    public void updateLongest() {
        // Statement to show and update longest string if node with longest string is removed
        String longestWordStatement = "The longest word is: ";
        // Declaring current that will check each node from the root
        TreeNode current = this.root;
        // Resetting the longtest string
        this.longest = this.root.getWord();
        // Traversal loop
        while (current != null) {
            // Traverse the tree to find the longest word
            if (this.longest == null || current.getWord().length() > this.longest.length()) {
                // If the length of the current node is greater than this.longest
                // this.longest is set to that word
                this.longest = current.getWord();
            }
            // If the current node has a left child
            if (current.getLeft() != null) {
                // The current node is moved to that left child
                current = current.getLeft();
            } else {
                // Else, the current node is moved to the right child if present
                current = current.getRight();
            }
        }
        // Statement to show the longest string in the tree if the removal affects it
        System.out.println(longestWordStatement + FORMAT + this.longest + FORMAT);
    }
    
    
    /******************************* Accessors *******************************/
    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public TreeNode getRoot() {
        return this.root;
    }

    public String getLongest() {
        return longest;
    }

    public String getShortest() {
        return shortest;
    }
} // class BST
