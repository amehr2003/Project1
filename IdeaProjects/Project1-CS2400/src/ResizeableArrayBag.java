import java.util.Arrays;

public class ResizeableArrayBag<T> implements BagInterface<T> {
    private T[] bag;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 25; //initialized capacity of bag
    private static final int MAX_CAPACITY = 1000000;
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
    } // end checkCapacity

    private void doubleCapacity() {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    } //end doubleCapacity

    public boolean add(T newEntry) {
        checkInitialization();
        boolean result = true;
        if (isArrayFull()) {
            doubleCapacity();
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

        BagInterface<T> I = new ResizeableArrayBag<T>();
        T[] array = this.toArray();
        for (T elem : array) {
            I.add(elem);
        }
        T[] other = bag.toArray();
        for (T elem : other) {
            I.add(elem);
        }
        return I;
    }

    public BagInterface<T> Intersection(BagInterface<T> bag) {

        BagInterface<T> I = new ResizeableArrayBag<T>();

        T[] array = this.toArray();
        T[] other = bag.toArray();

        boolean status[] = new boolean[other.length];

        for ( int i = 0; i < array.length; i++) {
            for ( int j = 0; j < other.length; j++) {
                if (other[i].equals(array[i]) && (!status[j])) {
                    I.add(array[i]);
                    status[j] = true;
                }
            }
        }
        return I;
} //end Intersection

    public BagInterface<T> Difference(BagInterface<T> bag){
        return null;
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
        return counter;
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
