public interface BagInterface<T>
{
    public BagInterface<T> Union(BagInterface<T> bag);

    public BagInterface<T> Intersection(BagInterface<T> bag);

    public BagInterface<T> Difference(BagInterface<T> bag);

    /**
     * @return the integer number of entries currently in bag
     */
    public int getCurrentSize();

    public boolean isEmpty();

    public boolean add(T newEntry);

    public T remove();

    public boolean remove(T anEntry);

    public void clear();

    public int getFrequencyOf(T anEntry);

    public T[] toArray();
}
