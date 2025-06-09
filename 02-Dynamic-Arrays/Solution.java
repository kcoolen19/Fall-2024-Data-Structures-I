/**
 * A simple class to demonstrate dynamic behavior with arrays. Objects of this
 * class store strings in an array that grows to match the demand for storage.
 * 
 * The class is based on an underlying string array. Objects can be initialized
 * to any size; otherwise they'll be initialized to the default size. For
 * example,
 * 
 * DynamicArray da1 = new DynamicArray(10);
 * 
 * will have initially room for 10 strings, while
 * 
 * DynamicArray da2 = new DynamicArray();
 * 
 * will have initially room for 4 strings.
 */
public class DynamicArray {

    /** Default size for underlying array */
    private static final int DEFAULT_SIZE = 4;

    /** The underlying array for this class */
    private String[] foundation;

    /** Measures how many places in the array are in use */
    private int occupancy;

    /**
     * Full constructor. Initializes the underlying array to the specified size. The
     * size must be a positive, non zero value. Otherwise the constructor uses the
     * default size value.
     */
    public DynamicArray(int size) {
        // If size <= 0 use default -- this is a good time to demo ternary operator
        size = (size > 0) ? size : DEFAULT_SIZE;
        this.foundation = new String[size];
        this.occupancy = 0;
    } // full constructor

    /**
     * Array-based constructor -- used for testing.
     * 
     * WARNING: SHALLOW ARRAY COPY
     * 
     * @param data
     */
    public DynamicArray(String[] data) {
        this(DEFAULT_SIZE);
        if (data != null) {
            // Create a deep copy of data into foundation
            // this foundation = data;
            this.foundation = new String[data.length];
            for (int i = 0;i < data.length;i++) {
                this.foundation[i] = data[i];
            }
            this.occupancy = data.length;
        }
    } // array-based constructor

    /**
     * Default constructor
     */
    public DynamicArray() {
        this(DEFAULT_SIZE);
    } // default constructor

    /**
     * Checks if the specified string is present in the dynamic array.
     * 
     * @param target The string to search for in the array
     * @return true if the string is found, false otherwise
     */
    public boolean contains(String target) {
        boolean found = false;
        /*
         * Before introducing this.occupancy in the object, the method traversed
         * this.foundation through its entire length, i.e., the while loop allowed its
         * index i to reach this.foundation.length. As we saw, however, not every
         * element in this.foundation may be used. this.occupancy tells us what is the
         * last used element in this.foundation. There is no point searching after that
         * element, as all values are going to be null. So, for the while loop here we
         * change the condition from while(i<this.foundation length &...) to
         * while(i<this.occupancy &&...)
         */
        if (target != null && this.foundation != null) {
            int i = 0;
            // No need to guard against occupancy==0, because if array is empty, loop will
            // not even run.
            while (i < this.occupancy && !found) {
                found = this.foundation[i] != null && this.foundation[i].equals(target);
                i++;
            }
        }
        return found;
    } // method contains

    /**
     * Retrieves the string at the specified index in the array.
     * 
     * @param index The index of the string to retrieve
     * @return The string at the specified index, or null if the index is invalid
     */
    public String get(int index) {
        String string = null;
        // No need to guard against occupancy==0, because if array is empty, the method
        // will return null anyway
        if (index >= 0 && this.foundation != null && index < this.foundation.length) {
            string = this.foundation[index];
        }
        return string;
    } // method get

    /**
     * Removes the string at the specified index in the array and sets its position
     * to null. Then it moves every element to the right of the removed element, one
     * position to the left. The position of the last element to be copied to the
     * left is then emptied out (null).
     * 
     * @param index The index of the string to remove
     * @return The string that was removed, or null if the index is invalid
     */
    public String remove(int index) {
        String removed = null;
        // We check occupancy, because there is no reason to perform this in an empty
        // array
        if (this.occupancy > 0 && index >= 0 && index < this.foundation.length) {
            removed = this.foundation[index];
            this.foundation[index] = null;
            // Shift things after the removed string, one position to the left
            for (int i = index; i < occupancy - 1; i++) {
                this.foundation[i] = this.foundation[i + 1];
            }
            // Previously last occupied cell, now empty
            this.foundation[occupancy - 1] = null;
            // update occupancy
            this.occupancy--;
        }
        return removed;
    } // method remove

    /**
     * Deletes the string at the specified index in the array.
     * 
     * This method uses this.remove and simply ignores the returned string.
     * 
     * @param index The index of the string to delete
     */
    public void delete(int index) {
        String whatEver = remove(index);
    } // method delete

    /**
     * Resizes the underlying array by increasing its capacity by 1.
     * 
     * This method is called internally when the current array reaches its capacity
     * and a new element needs to be inserted.
     */
    private void resize() {
        String[] temp = new String[this.foundation.length + 1];
        /*
         * Instead of:
         * for (int i = 0; i < this.foundation.length; i++) {
         * we can write
         * for (int i = 0; i < this.occupancy; i++) {
         * since there is no reason to copy null values from one array to another.
         */
        for (int i = 0; i < this.occupancy; i++) {
            temp[i] = this.foundation[i];
        }
        this.foundation = temp;
    } // method resize

    /**
     * Inserts a new string into the dynamic array.
     * 
     * If the string is not null and the array is full, it will be resized to
     * accommodate the new element.
     * 
     * @param string The string to insert into the array
     */
    public void insert(String string) {
        // Guard against null argument
        if (string != null) {
            // If there is no room left in underlying array, resize it first
            if (this.occupancy == this.foundation.length) {
                this.resize();
            }
            // Room in underlying array assured
            this.foundation[this.occupancy] = string;
            this.occupancy++;
        }
    } // method insert

    /**
     * The method will print the format of how the dynamic array will look like
     * The design output is made from personal preference
     * If the array is empty or null, empty brackets - [ ] will be displayed
     * When the array is not empty, the element(s) present will be printed.
     * The separator between the elements which is the "," is printed each time after an element
     * There is an if statement to keep printing the separator until after the before-to-last element.
     * As a result, The "," is not present before the closing bracket "]"
     * The format is returned at the end of the method
     * 
     * 
     * @return The format of the printing style of the dynamic array
     */
    public String toString() {
        // Declaring and intiating a variable to create the format of the array
        // "[" is the opening bracket to the array
        String format = "[";
        // If the array is empty or null, only the brackets will be printed
        if (this.foundation.length == 0 || this.foundation == null) {
            // Standard form for the array which is [ ]
            format = "[ ]";
        }
        // Check if foundation is not null
        if (this.foundation != null) {
            // For loop to transverse the foundation array
            for (int i = 0; i < this.foundation.length;i++) {
                // Check if the current element is not null to print the element present at the position i
                if (this.foundation[i] != null) {
                // The format adds each element of the corresponding index, i
                format += this.foundation[i];
                // Else if statement for when the above condition is not satiafied
                } else {
                // The format will print the string "null" to demonstrate the presence of a null element/absence of an element
                format += "null"; 
                }
                // Add a comma after each element except for the last one to maintain a neat structure
                if (i < this.foundation.length - 1) {
                format += " , ";
                } 
                // Condition when after the last element is reached, the closing bracket is added
                if (i == foundation.length-1) {
                // The format is finished with a closing bracket "]"
                format += "]";
                }
            }
        }   
        // The format is returned and will be used for printing the dynamic array
        return format;
        }

        /**
         * The goal is to obtain and return the index of the String used as parameter in the array on one or more instances
         * If not obtained, the value -1 will be returned as a sign of that the index  is not found for the desired String parameter
         * A variable denoting the index to be returned is declared first
         * It is set to -1 initially to show that the index is not found yet
         * A variable i is declared and it will be used as counter in the while loop when trasversing the array.
         * A while loop is run within the number of elements present in the array.
         * An if statement with the conditions that the element is not null AND
         * that the string parameter is the same as the element present in the array, are set.
         * Both string parameter and String element in the array are set to lower case to remove case sensitivity
         * If found, the number of i, which is the position of the element that matches that of the string parameter will
         * be set to the variable, "index"
         * i is incremented to keep the loop going.
         * 
         * @param string The string to insert in the array
         * @return The index of the string parameter that is present in the array on one or more occurences(The first occurence is returned)
         */
        public int index(String string) {
        // Declaring and initializing an integer variable for the index
        // The variable is set to -1 to show that the index is yet to be found
        int index = -1; 
        // Creating a variable that will be incremented when traversing through the array in a while loop
        int i = 0;
        // An if statement to check when the string parameter is not null and array is not null
        // If ever the string parameter is null or the foundation is null, -1 is returned at the end 
        if (string != null && this.foundation != null) {
            // Using a while loop to check each element within the number of elements present in the array
            // Keeping the index to -1 so that the loop exited when the first occurence of the string parameter is obtained
            // It is useful since if the array is empty, the loop will not run, not needing to find any index
            while (i < this.occupancy && index == -1) {
                // Check if the current element is not null and matches the string parameter
                // The parameter and element is turned to lower case to eliminate case sensitivity
                if (this.foundation[i] != null && this.foundation[i].toLowerCase().equals(string.toLowerCase())) {
                    // The index variable now becomes that value of i where the condition is satisfied
                    // The loop will be exited  
                    index = i; 
                }
            // Increment i while traversing the array
            i++; 
            }
        }   
        // The index of the string entered as a parameter, is returned 
        // -1 is returned if the index is not found
        return index;
   }
    
        /**
         * The target is to calculate the percentage of values which are not null in an array
         * A counter is created to count those elements and is initialized to zero
         * A for loop is run to transverse the array
         * If the element present is not a null value, the counter is incremented by 1
         * A variable of type double is used to obtain the percentage of the non-null elements
         * The number of non-elements is taken on top of the total elements in the array as a ratio
         * The percentage is calculated in full numbers by multiplying by 100
         * In getting the final answer to 2 decimal places, (nonNullPercentage * 100) is carried out to move the decimal 2 places forward
         * Math.round is applied to get a whole number without the decimal
         * Finally, the value obtained Math.round(nonNullPercentage * 100) is divided by 100 to get it to 2 decimal places 
         * as indicated in the program instructions
         * If the full calculated percentage is 80.333333333, the steps to get to 2 decimal places occur as shown below:
         * (nonNullPercentage * 100) = 8033.3333333
         * Math.round(nonNullPercentage * 100) = 8033
         * Math.round(nonNullPercentage * 100) / 100 = 80.33
         * 
         * @return The final percentage of the non-null elements is returned(rounded off to 3 decimal places)
         */
        public double usage() {
            // Declare and initialize an integer variable that will count the non-null values
            int nonNullCount = 0;
            // Variable to calculate percentage of non-null elements in array
            double nonNullPercentage = 0.00;
            // Ensure there are elements in the array 
            if (this.occupancy > 0 && this.foundation != null) {
                // For loop used in counting non-null elements when traversing the array
                for (int i = 0; i < this.foundation.length; i++) {
                    // If the element in the array is not a null element
                    if (this.foundation[i] != null) {
                    // The variable is incremented by 1.
                    nonNullCount++;
                    }
                }
                /* Calculate presence of non-null element present in the array 
                as a percentage of the total number of elements present
                */
                nonNullPercentage = ((double)nonNullCount / this.foundation.length) * 100.0;
                // Rounding the full percentage to 2 decimal places using Math.round 
                nonNullPercentage = Math.round(nonNullPercentage * 100.0) / 100.0;
                // Returning the obtained percentage set to 2 decimal places
            }
            return nonNullPercentage;
        }
} // class DynamicArray
