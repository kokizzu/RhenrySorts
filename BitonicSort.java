public class BitonicSortAlgorithm extends SortAlgorithm
{
    private int[] a;
    // sorting direction:
    private final static boolean ASCENDING=true, DESCENDING=false;

public void sort(int[] a) throws Exception
{
    this.a = a;
    bitonicSort(0, a.length, ASCENDING);
}

private void bitonicSort(int lo, int n, boolean dir) throws Exception
{
    if (n>1)
    {
        int k=n/2;
        bitonicSort(lo, k, ASCENDING);
        bitonicSort(lo+k, k, DESCENDING);
        bitonicMerge(lo, n, dir);
    }
}

private void bitonicMerge(int lo, int n, boolean dir) throws Exception
{
    if (n>1)
    {
        int k=n/2;
        for (int i=lo; i<lo+k; i++)
            compare(i, i+k, dir);
        bitonicMerge(lo, k, dir);
        bitonicMerge(lo+k, k, dir);
    }
}

private void compare(int i, int j, boolean dir) throws Exception
{
    if (dir==(a[i]>a[j]))
    {
        int h=a[i];
        a[i]=a[j];
        a[j]=h;
        pause();
    }
}

}    // end class BitonicSorter
