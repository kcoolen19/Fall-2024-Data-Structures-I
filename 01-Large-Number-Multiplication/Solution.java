// Import java.util to print the array in its right form
import java.util.Arrays;

/*
Structure of the code and adjustments made during the planning and running.
1. Allow single-digit multiplication for the first number without and with respect to its length.
2. Allow single-digit multiplication for the second number without and with respect to its length.
3. Store the numbers in an array directly rather than converting the integer to an array later.
4. Using the maximum size of the product length will create leading zeros where the size will be smaller than the maximum.
5. Create a conditional situation so that the right array printed without any leading zeros.
 */

public class SingleDigitMultiplication {
    public static void main(String[] args) {
        // Initializing and declaring the first array/set of numbers.
        int[] number1 = {1,2,3,4};
        // Initializing and declaring the second array/set of number.
        int[] number2 = {5,6,7,8};
        // Initializing and declaring the base number which is 10.
        int base = 10;
        /* Calling the method that will print the array of the product.
        Array printed should be [7,0,0,6,6,5,2].
         */
        multiply(number1,number2,base);
        // Initializing and declaring the first array/set of numbers.
        int[] number3 = {9,9,9};
        // Initializing and declaring the second array/set of number.
        int[] number4 = {8,8,9};
        /* Calling the method that will print the array of the product.
        Array printed should be [8,8,8,1,1,1].
         */
        multiply(number3,number4,base);
    }

    /*
    1. The plan to multiply each digit from each array and use their carry digits to add up to them while the for loop runs.
    2. In multiplication, the rule is set with the length of a product is either the sum of the length of both numbers
    or one number smaller than that maximum length.

    3. The new array was set a fixed length of the maximum size with an alteration provided later in case the product length is indeed smaller.
    4. The two loops are run so that each number is multiplied one by one similar to multiplication done by hand.
    5. The numbers from each array gets multiplied together, but they are added by the carry digit
    and another number stored in a position in the array(This number is stored in the array from the beginning
    so that each number is stored in the right position for the carry digits to be successfully added later on)

    6. "i + j + 1" represents the correct position for storing the product of number1[i] and number2[j].
    When [1,2,3,4] and [5,6,7,8] are used, productArray with the index "i+j+1" for index 3 in both arrays is 3+3+1 = 7
    which is attributed as the final position in the array as the multiplication takes place between two digits in the ast position in each array.


    7. The multiplication of the final positions in each array should receive the final position in the product array.
    8. Its single-digit value is calculated as a remainder of the product operation.

    9. The last digit is already stored so that it can be used again in calculation when the loop gets run again to the
    position in question.
    10. Since the second array has more than 1 digit, the final digit will be 0 in the second round when the loop runs again.
    11. Thus, the first remainder will remain unchanged in its position

    12. productArray[i] allows the remaining carry digits to be added back to obtain the final individual digit in each position of the array.
     */

    public static void multiply(int[] x, int[] y, int base) {
        /* Declaring the variable that will be store the carry digit when a product of
         above 9 is obtained when multiplying the single digit in each array.
         */
        int carryDigit;
        /* The variable denotes the maximum length of the product number.
       The length of the product can be either the sum of the size of the individual numbers or one number lesser.
         */
        int maxProductLength = x.length + y.length;
        /* Declaring the array that will store the product
         of the two numbers in digit by digit form.
         */
        int[] productArray = new int[maxProductLength];
        // For loop to multiply one number by number in the first array of digits
        for (int i = x.length - 1; i >= 0; i--) {
            // Initializing the carryDigit to 0
            carryDigit = 0;
            // For loop to multiply one number by number in the second array of digits
            for (int j = y.length - 1; j >= 0; j--) {
                // Creating a variable that addresses the multiplication of the digit from each array
                int singleProduct = (x[i] * y[j]);
                /* Calculating the total product of the numbers while adding
                the carry digit and single digit in the position outlined taken by the new number
                 when the single digits from both arrays are multiplied together
                 */
                int product = singleProduct + productArray[i + j + 1] + carryDigit;
                // Using division to obtain the carry digit which is the quotient as result of the equation
                carryDigit = product / base;
                /* Obtaining the single digit for a given position in the array
                where the product is present in the form of single digits only
                For example:
                If the first array has [1,2] and the second one has [1,2]
                So, 2 x 2 = 4 which will take up the last position in the array which is index 3.
                Additionally, the array stores the digit temporarily until
                they are added upon in the next step with the carry digit from the upcoming multiplication steps.
                 */
                productArray[i + j + 1] = product % base;
            }
            /* Adding the carry digit to the position
            From the example with arrays [1,2] and [1,2]
            The index 2 is calculated twice. The first one from above will be 2. (as 12 x 2 = 24)
            After multiplying all digits of number2 by number1[i], any remaining carry digit needs to be added to productArray[i].
            The second one will be 2 as well with the procedure being (12 x 10 = 120). [1] = 1, [2] = 2, [3] = 0
             */
            productArray[i] += carryDigit;
        }
        /* If the first position is zero, this means that the array is smaller
            than the maximum size of the product of two numbers.
            Then, the first position in the array will take 0.
            The condition checks if this is the case, a new array will be generated with
             the product in single digits without the leading zero.
             The method returnArray with the used productArray are used as reference.
         */
        if (productArray[0] == 0) {
            adjustedArray(productArray);
            // Else, if the product is indeed of maximum size, then, the current array is printed.
        } else {
            System.out.println(Arrays.toString(productArray));
        }
    }

    /*
    1. This method is an alternate way to print the array of the product of the single digits.
    2. The array for the product has to be fixed. I decided to set the size of the product array as the sum of the length of the arrays.

    3. However, in multiplication, the size of the product can be either the sum of the length of the numbers or one lesser.

    4. Like 99 x 99 = 9801 and 10 x 10 = 100. The individual numbers have the same length but their product length varies.

    5. Both scenarios are possible. This method is applicable only if the first digit in the productArray is 0.(Leading zero)
     */

    public static void adjustedArray(int[] productArray) {
        /* Create a variable that will record the length of the new array deemed
        / to be one number lesser than the productArray
         */
        int lengthOfNewArray = 0;
        // Redo the check whether the first position is zero in the productArray.
        if (productArray[0] == 0) {
            // The length of the new array is adjusted to one lesser than the product array.
            lengthOfNewArray = productArray.length - 1;
        }
        // Creating the new array with the right length
        int[] multiplicationArray = new int[productArray.length - 1];
        /* For loop to add the elements of the productArray from the
        / second position as the first position has a leading zero.
         */
        for (int i = 0;i <= lengthOfNewArray - 1;i++) {
            multiplicationArray[i] = productArray[i+1];
        }
        // Print the array of the product in single digits without the leading zero
        System.out.println(Arrays.toString(multiplicationArray));
    }
}
