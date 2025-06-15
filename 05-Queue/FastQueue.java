public class FastQ {
    private String[] array;
    private int size;
    private int used;
    private int front;
    private int back;

    private static final int DEFAULT_SIZE = 4;

    /** Full constructor */
    public FastQ(int size) {
        if (size <1){
            size = DEFAULT_SIZE;
        }
        this.size = size;
        this.array = new String[this.size];
        this.used = 0;
        this.front = 0;
        this.back = 0;
    } // full constructor

    /** Default constructor */
    public FastQ() {
        this(DEFAULT_SIZE);
    } // default constructor

    /**
     * The goal is to remove an element in the array from the first position available
     * The first element is removed in the array
     * A variable is used to store that element which will be returned later
     * That first element is set to null
     * The new index for front is updated to the original next element 
     * The number of element in the arrays is reduced as one element was removed
     * 
     * @return The element removed from the first position
     */
    public String remove() {
        // The string to be removed is stored in the variable, removed 
        // This variable is removed later
        String removed = this.array[this.front];
        // The element in the first position is set to null
        this.array[this.front] = null;
        // The new front element become the next element after the removed one
        this.front = (this.front + 1) % this.array.length;
        // The amount of elements in the array is reduced 
        this.used--;
        // The removed element is returned
        return removed;
    }

    /**
     * The goal is to return whether or not the addition of an element to the array was a success
     * The string element in the parameter will be added at the back of the array 
     * The addition will be done as long as there is space in the array
     * The variable back is updated after the addition 
     * The number of elements in the array is incremented with a successful addition
     * 
     * @param string A string element to be added
     * @return whether the parameter of type String was added to the array
     */
    public boolean add(String string) {
        boolean success = (this.used < this.array.length);
        // If the amount of elements in the array is less that the capacity
        if (success) {
            // The new string is added at the back of the array
            this.array[this.back] = string;
            // The index of the last used in the array is updated 
            this.back = (this.back + 1) % this.array.length;
            // The amount of elements in the array is increased with the addition 
            this.used++;
        } 
        // The condition whether an element was added is returned
        return success;
    }

} // class FastQ
