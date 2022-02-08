public interface BagInterface<T>
{
    public BagInterface<T> Union(BagInterface<T> bag);

    public BagInterface<T> Intersection(BagInterface<T> bag);

    public BagInterface<T> Difference(BagInterface<T> bag);
}
