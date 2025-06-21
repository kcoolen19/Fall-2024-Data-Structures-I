public class HuffmanEncodingWithHeap {

    /**
     * The method returns the forest of Huffman node built using a minimum heap with the frequencies of the characters.
     * A new node is created for each character whose frequency is greater than zero
     * The new nodes are inserted in a minimum heap of the Huffman nodes created which represents the forest
     * 
     * @param frequencies frequencies of each ascii character in integer form
     * @return a minimum Heap of HuffmanNodes which represents the forest for the Huffman Tree
     */
    public MinHeap<HuffmanNode> buildForest(int[] frequencies) {
        // A minimum Heap is created to accomodate the Huffman Nodes
        MinHeap<HuffmanNode> forest = new MinHeap<>();
        // Run a for loop to create a node for each frequency of character that is greater than zero
        for (int asciiCode = 0; asciiCode < frequencies.length; asciiCode++) {
            if (frequencies[asciiCode] > 0) {
                // A new node is created as long as its ascii value is greater than zero with its character and frequency
                HuffmanNode addedNode = new HuffmanNode((char) asciiCode, frequencies[asciiCode]);
                // The node with the labelled frequency and its character is added to the forest
                forest.insert(addedNode);
            }
        }
        // The minimum heap of Huffman Nodes created is returned
        return forest;
    }    

    /**
     * The method will build the Huffman tree using a minimum heap
     * The two nodes with the smallest frequencies are combined and is added into the minimum heap.
     * This process is repeated until the root node of the constructed Huffman tree is left
     * 
     * Process:
     * - The two nodes with the smallest frequencies are retrieved
     * - A new node with the combination of their frequencies is created
     * - The two retrieved nodes are added as left and right children of the combined node
     * - The new node is inserted in the forest
     * - The above steps are repeated until we are left with one node, which is the root of the constructued Huffman tree
     *  
     * @param forest the minimum heap containing the Huffman Nodes
     * @return the root(Huffman) node of the Huffman tree remaining in the minimum heap (forest)
     */
    public HuffmanNode buildTree(MinHeap<HuffmanNode> forest) {
        // There should be only one node at the top
        // The two smallest node are retrieved each time and combined
        while (forest.size() > 1) {
            // The two smallest freqeuencies are retrieved and the nodes are removed
            HuffmanNode firstSmallest = forest.removeMin();
            HuffmanNode secondSmallest = forest.removeMin();
            // The two nodes with the smallest frequencies are combined 
            int combinedFrequency = firstSmallest.getFrequency() + secondSmallest.getFrequency();
            HuffmanNode combinedNode = new HuffmanNode(combinedFrequency);
            // The two smallest nodes are attached to the left and right side of the combined node
            combinedNode.setLeft(firstSmallest);
            combinedNode.setRight(secondSmallest);
            // The combined node is added to the forest of Huffman nodes
            forest.insert(combinedNode);
        }
        // The only node remaining in the Minimum heap(forest) is returned at the end
        // The node remaining is the root node 
        return forest.getMin();
    }
}   
