public class TrainLine {

    /** The name of the trainline */
    private String name;
    /** Points to the first station in the trainline */
    private TrainStation head;
    /** Points to the last station in the trainline */
    private TrainStation tail;
    /** Keeps a running tally of train stations in the trainline */
    private int numberOfStations;

    /**  Declaring the strings and symbols for the toString() method */
    private final String RIGHT_ARROW = " --> ";
    private final String LEFT_ARROW = " <-- ";
    private final int MAX_CHARACTERS = 80;
    private final int EVEN_NUMBER = 2;
    private final String NULL_STATION = "null";


    /** Full constructor */
    public TrainLine(String name, TrainStation head) {
        this.name = name;
        this.head = head;
        this.numberOfStations = 0;
        if (this.head != null) {
            // If head is not null, there is one station in the line
            this.numberOfStations = 1;
        }
        // At initialization head and tail point to the same station even if null
        this.tail = null;
    } // full constructor

    /** Basic constructor */
    public TrainLine(String name) {
        this(name, null);
    } // basic constructor

    /**
     * Creates a new station with the given name and adds it to the end of the line.
     */
    public void add(String name) {
        // Create the new station to add
        TrainStation newStation = new TrainStation(name);
        // Determine where to place the new station
        if (this.head == null) {
            // Trainline is empty, make new station the head of the line
            this.head = newStation;
        } else {
            // When there is a head station already, add the new station after the last
            // station in the line.
            this.tail.setNext(newStation);
        }
        // The new station becomes the tail station of the line
        this.tail = newStation;
        // Update station count
        this.numberOfStations++;
    } // method add

    /** Returns the number of stations in the line >= 0 */
    public int getNumberOfStations() {
        return numberOfStations;
    } // method getNumberOfStations

     /**
      * The goal is to check whether the name in the parameter matches that of a station in the train line
      * A train station for the current station reached while traversing the train line is created.
      * The while loop runs while the current station exists and until the station name in the parameter is obtained
      * The next station is obtained if the current station name does not match the one from the parameter.
      * In the end, the condition will be returned depending on if the parameters matches the name of a station in the train line or not 
      * It will either be true or false
      *
      * @param string Name of station to be compared with
      * @return whether the parameter representing the station name is present in the trainline(True/False)
      */
      
      public boolean contains(String name) {
        // Create a train station that represents the current station
        // Label it as the head since we are starting from there
        TrainStation cursor = this.head;
        // The loop runs as long as the station exists and until the station name in the parameter is obtained
        while (cursor != null && !cursor.getName().equalsIgnoreCase(name)) {
            // The next station is obtained 
            cursor = cursor.getNext();
    }
        // Will return true if the station in the parameter is obtained
        return cursor != null;
    }
    
    /**
     * The goal is to return the index of the station name in the parameter present in the train line
     * Create an integer variable to store the index of each station
     * Create a train station for the current station reached while traversing the train line
     * The while loop runs while the current station exists and until the station name in the parameter is obtained
     * The next station is obtained, and the corresponding index is incremented accordingly.
     * The above is done while the station name is not the one from the parameter.
     * The index of the station is returned if obtained
     * Else, -1 is returned if the station name in the parameter is not in the train line
     * 
     * @param name name of the station whose index will be retrieved
     * @return index of the station name in the parameter else -1 if not obtained
     */

    public int indexOf(String name) {
        // Index of station to be returned is initialized to 0(from the first station)
        int index = 0;
        // The current station is labelled as the head at the start
        TrainStation cursor = this.head;
        /* The loop runs as long as the station exists and 
        until the station name in the parameter matches that of one in the train line
        */
        while (cursor != null && !cursor.getName().equalsIgnoreCase(name)) {
            // The next station is obtained 
            cursor = cursor.getNext();
            // Index is incremented and becomes index of the next station
            index++;
        }
        // Index of station is returned if obtained or else -1 is returned
        return cursor != null ? index : -1;
    }

    /**
     * 
     * The goal is to return the stations in the train line in reverse order
     * A string variable is created to store the stations obtained in reverse
     * A train station for the current station reached is created
     * A while loop is run as long as the current station exists
     * The reverse string will store the name of the current station
     * Afterwards, the next station is printed with the previous station stored in variable reverse on the following line.
     * The loop runs until the end, and the stations will be printed in reverse order
     * 
     * Example of the process for 2 stations:
     * If the 2 stations are Howard and Jarvis, the reverse process will be done as follows:
     * reverse = ""
     * reverse = cursor + \n + reverse
     * reverse = Howard + \n + ""
     * Howard
     * ---------(This represent "")
     *
     * To get Jarvis:
     * reverse = cursor + \n + reverse
     * reverse = Jarvis + \n + (Howard + \n + "")
     * reverse stores the previous station, Howard 
     * Jarvis
     * Howard 
     * ---------(This represent "")
     * 
     * @return The stations in reverse order
     */

    public String reverseList() {
        // Create string variable that will store the reverse order of the stations
        String reverse = "";
        // Label it as the head since we are starting from there
        TrainStation cursor = this.head;
        // The loop runs as long there is an existing current station
        while (cursor != null) {
            // The reverse string will store the current stations in reverse  
            // The next station is printed and the reverse string will print the stored previous station on the next line
            reverse = cursor.getName() + "\n" + reverse;
            // The next station is obtained
            cursor = cursor.getNext();
        }
        // The stations are returned in reverse
        return reverse;
    }
    
    /** Will return true if the number of stations is zero 
     * 
     * @return whether the train line is empty or not
    */
    public boolean isEmpty() {
        // Returns true if there are no stations
        return this.numberOfStations == 0;
    }

    public TrainStation remove(int position) {
        TrainStation removedStation = null;
        if (position >= 1 && position <= this.numberOfStations) {
            // Commence safe operations
            if (position == 1) {
                // Remove head
                removedStation = this.head;
                this.head = this.head.getNext();
            } else {
                // Find the station prior to the one to be removed
                TrainStation cursor = this.head;
                for (int i = 1; i < position-1; i++) {
                    cursor = cursor.getNext();
                }
                // cursor should be at the prior station
                if (cursor.getNext() == this.tail) {
                    this.tail = cursor;
                }
                removedStation = cursor.getNext();
                cursor.setNext(cursor.getNext().getNext());
            }
            this.numberOfStations--;
            removedStation.setNext(null);
        }
        return removedStation;
    }

    /**
     * The goal is to place a station at the given index in the parameter
     * The new train station and current station are created
     * Special case for when the station is to be inserted at the head
     * Then, the new station is inserted at the specified index
     * The old station in that position is moved forward
     * Number of stations is increased at the end
     * 
     * @param String Name of station to be inserted in the train line
     * @param int Position at which the station will be placed
     */
    public void insert(String name, int index) {
        // Create the new station to be inserted in the train line
        TrainStation newStation = new TrainStation(name);
        // Create the current station in the train line
        if (index == 0) {
            newStation.setNext(this.head);
            this.head = newStation;
            this.numberOfStations++;
        }
        TrainStation current = this.head;
        // Varibale to be incremented while traversing train line
        int i = 0;
        // While there is a station and the loop runs until the index in the parameter 
        while (i < index && current != null) {
            // The current station is set at the previous station of the current station at the parameter index
            if (i == index - 1) {
                // The new station is set with the old station in the specified index moved forward
                newStation.setNext(current.getNext());
                current.setNext(newStation);
            }
            current = current.getNext();
            i++;
        }
        this.numberOfStations++;
    }
    
   
    /**
     * The method will print the reprensation of stations in the red line 
     * The method will print the stations in a snake like way with a maximum of 80 characters per line
     * Each altenating line will have arrows pointing in different directions
     * When the maximum number of characters per line is reached, the train line shifts to the next line
     * 
     * @return A snake like way to display the stations in the CTA Red Line 
     */
    
 public String toString() {
    String result = "";
    result += this.head.getName(); 
    // The current train station
    TrainStation current = this.head.getNext();
    // Check character per line
    int lengthStation = 0;
    // Count number of lines
    int lineCount = 0;
    String reverseLine = "";
    // While there is a station
    while (current != null) {
        // Station with arrow is meant to get the station name
        String stationWithArrow = current.getName();
        // if number of lines is even
        if (lineCount % EVEN_NUMBER == 0) { 
            // Append station with the right arrow " --> " and a station name
            stationWithArrow = RIGHT_ARROW + current.getName();
            // Add them to result
            result += stationWithArrow;
            if (current.getNext() == null) {
                reverseLine = NULL_STATION + reverseLine;
            }
            lengthStation += stationWithArrow.length();
            // Check for character limit
            if ((lengthStation + stationWithArrow.length()) > MAX_CHARACTERS || current.getNext() == null) {
                // Change line
                result += "\n"; 
                // Increase the line count
                lineCount++;
                // Reset length station to 0
                lengthStation = 0; 
            }
        } 
        // if number of lines is odd
        else { 
            // Append station with the left arrow " <-- " and a station name
            stationWithArrow = LEFT_ARROW + current.getName(); 
            reverseLine = stationWithArrow + reverseLine;
            if (current.getNext() == null) {
                reverseLine = NULL_STATION + reverseLine;
            }
            // Update the number of character per line
            lengthStation += stationWithArrow.length();
             // Check for character limit
            if ((lengthStation + stationWithArrow.length()) > MAX_CHARACTERS || current.getNext() == null) {
                result += reverseLine.trim();
                // Change line
                result += "\n";
                // Make the reverse line blank again
                reverseLine = ""; 
                // Increase the line count
                lineCount++;
                // Reset length station to 0
                lengthStation = 0; 
            } 
        }
        // Get the next station
        current = current.getNext();
    }
    return result;
}
    

    public static void main(String[] args) {
        // A few station names
        String[] stationNames = { "Howard", "Jarvis", "Morse",
                "Loyola", "Granville", "Thorndale"};
        // A populated trainline
        TrainLine redLineSB = new TrainLine("Red Line SB");
        for (String station : CTA.RED_LINE_SB_NAMES) {
            redLineSB.add(station);
        }
        // An empty trainline
        TrainLine brownLineSB = new TrainLine("Brown Line SB");
        // A random station name
        String randomName = "Oak Park";
        // Test for code 
        brownLineSB.insert(randomName, 0);
        System.out.println(brownLineSB.toString() + "\n");
        // Normal red line
        System.out.println(redLineSB.toString() + "\n");
        // Red line with new head
        redLineSB.insert(randomName,70);
        System.out.println(redLineSB.toString());
    } // method main
} // class TrainLine
