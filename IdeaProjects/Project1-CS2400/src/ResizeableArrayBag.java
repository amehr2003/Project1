import java.util.Arrays;

public class ResizeableArrayBag<T> implements BagInterface<T> {
    private T[] bag;
    private int numberOfEntries;
    private boolean integrityOK = false; //used in the checkInitialization class
    private static final int DEFAULT_CAPACITY = 25; //initialized capacity of bag
    private static final int MAX_CAPACITY = 10000;
    //creating an empty bag whose initial capacity is 25
    public ResizeableArrayBag() {
        this(DEFAULT_CAPACITY);
    }
    public ResizeableArrayBag(int initialCapacity) {

        if (initialCapacity <= MAX_CAPACITY) {
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[]) new Object[initialCapacity]; //unchecked cast
            bag = tempBag;
            numberOfEntries = 0;
            integrityOK = true;
        } // end if
        else {
            throw new IllegalStateException("Attempt to make bag too big");
        } //end constructor

    }

    public ResizeableArrayBag(T[] contents) {
        checkCapacity(contents.length);
        bag = Arrays.copyOf(contents, contents.length);
        numberOfEntries = contents.length;
        integrityOK = true;
    }
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose " +
                    "capacity exceeds allowed " +
                    "maximum of " + MAX_CAPACITY);
    } // end checkCapacity (makes sure the bag contents don't exceed the allowed amount)

    private void doubleCapacity() {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    } //end doubleCapacity (In order to add a new entry to the bag, this class will double the size of the capacity and make that it's new length)

    public boolean add(T newEntry) {
        checkInitialization(); 
        boolean result = true;
        if (isArrayFull()) { 
            doubleCapacity(); //if it's full it will double the array like I said for doubleCapacity method)
        } //end if
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;

        return true;
    } //end add

    public T[] toArray()
    {
        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
        for ( int index = 0; index < numberOfEntries; index++ ) {
            result[index] = bag[index];
        } //end for
        return result;
    } // end toArray

    public boolean isEmpty() {
        return numberOfEntries == 0;
    } //end isEmpty

    public BagInterface<T> Union(BagInterface<T> bag) {

        BagInterface<T> I = new ResizeableArrayBag<T>(); //makes a new bag called I
        T[] array = this.toArray(); //creates two new arrays, array and other (below)
        for (T elem : array) {
            I.add(elem);
        }
        T[] other = bag.toArray(); //the enhanced for loop goes through the array and adds the elements to the new array
        for (T elem : other) {
            I.add(elem);
        }
        return I; //then returns I with the unionized elements of T and I bag arrays (other and array)
    }

    public BagInterface<T> Intersection(BagInterface<T> bag) {

        BagInterface<T> I = new ResizeableArrayBag<T>(); //creates a new resizeableArrayBag called I

        T[] array = this.toArray(); //other and array are back
        T[] other = bag.toArray();

        boolean status[] = new boolean[other.length]; //makes sure the lengths are okay

        for ( int i = 0; i < array.length; i++) { //loops through the contents of the first array
            for ( int j = 0; j < other.length; j++) { //while in a nested for loop for the contents of the second array (called other^)
                if (other[i].equals(array[i]) && (!status[j])) { 
                    I.add(array[i]); //adds the contents of array array
                    status[j] = true;
                }
            }
        }
        return I; //yeah, return :)
} //end Intersection

    public BagInterface<T> Difference(BagInterface<T> bag){
        return null; //this is hard 
        }
    public int getCurrentSize() {
        return numberOfEntries;
    } //end getCurrentSize

    public int getFrequencyOf(T anEntry) {

        int counter = 0;

        for (int index = 0; index < numberOfEntries; index++) {
            if (anEntry.equals(bag[index])) {
                counter++;
            } //end if
        } //end for
        return counter; //in notes, didn't really understand well
    } //end getFrequencyOf

    public T remove() {
        return null; // STUB
    } // end remove

    public boolean remove(T anEntry) {
        return false; // STUB
    } // end remove

    /** Removes all entries from this bag. */

    public void clear() {
        while (!isEmpty())
            remove();
    } //end clear
    private void checkInitialization() {
        if (!integrityOK) {
            throw new SecurityException("Uninitialized object used to call an arrayBag method");
        }
    }  //end checkInitialization
    private boolean isArrayFull() {
        return numberOfEntries >= bag.length;
    }
}
