import java.util.*;

/*
 Copyright (c) 2003-2006 Niels Kokholm and Peter Sestoft
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
*/
  /// <summary>
  /// A utility class with functions for sorting arrays with respect to an IComparer&lt;T&gt;
  /// </summary>
  public class IntroSortAlgorithm extends SortAlgorithm
  {
    /// <summary>
    /// Sort part of array in place using IntroSort
    /// </summary>
    /// <exception cref="ArgumentOutOfRangeException">If the <code>start</code>
    /// and <code>count</code> arguments does not describe a valid range.</exception>
    /// <param name="array">Array to sort</param>
    /// <param name="start">Index of first position to sort</param>
    /// <param name="count">Number of elements to sort</param>
    /// <param name="comparer">IComparer&lt;T&gt; to sort by</param>
    public void IntroSort(int[] array, int start, int count, Comparator<Integer> comparer) throws Exception
    {
      if (start < 0 || count < 0 || start + count > array.length)
        throw new Exception();
      new Sorter(array, comparer).IntroSort(start, start + count);
    }

    /// <summary>
    /// Sort an array in place using IntroSort and default comparer
    /// </summary>
    /// <exception cref="NotComparableException">If T is not comparable</exception>
    /// <param name="array">Array to sort</param>
    public void sort(int[] array) throws Exception
    {
      new Sorter(array, new ComparableComparator()).IntroSort(0, array.length);
    }


    /// <summary>
    /// Sort part of array in place using Insertion Sort
    /// </summary>
    /// <exception cref="ArgumentOutOfRangeException">If the <code>start</code>
    /// and <code>count</code> arguments does not describe a valid range.</exception>
    /// <param name="array">Array to sort</param>
    /// <param name="start">Index of first position to sort</param>
    /// <param name="count">Number of elements to sort</param>
    /// <param name="comparer">IComparer&lt;T&gt; to sort by</param>
    public void InsertionSort(int[] array, int start, int count, Comparator<Integer> comparer) throws Exception
    {
      if (start < 0 || count < 0 || start + count > array.length)
        throw new Exception();
      new Sorter(array, comparer).InsertionSort(start, start + count);
    }

    public void QuickSort(int[] array, int start, int count, Comparator<Integer> comparer) throws Exception
    {
      if (start < 0 || count < 0 || start + count > array.length)
        throw new Exception();
      Sorter s = new Sorter(array, comparer);
      s.QuickSort(start, start + count);
    }


    /// <summary>
    /// Sort part of array in place using Heap Sort
    /// </summary>
    /// <exception cref="ArgumentOutOfRangeException">If the <code>start</code>
    /// and <code>count</code> arguments does not describe a valid range.</exception>
    /// <param name="array">Array to sort</param>
    /// <param name="start">Index of first position to sort</param>
    /// <param name="count">Number of elements to sort</param>
    /// <param name="comparer">IComparer&lt;T&gt; to sort by</param>
    public void HeapSort(int[] array, int start, int count, Comparator<Integer> comparer) throws Exception
    {
      if (start < 0 || count < 0 || start + count > array.length)
        throw new Exception();
      new Sorter(array, comparer).HeapSort(start, start + count);
    }


    class Sorter
    {
      int[] a;

      Comparator<Integer> c;


      Sorter(int[] a, Comparator<Integer> c) { this.a = a; this.c = c; }


      void IntroSort(int f, int b) throws Exception
      {
        if (b - f > 31)
        {
          int depth_limit = (int)Math.floor(2.5 * Math.ceil( (double)Math.log ( (double)b - f ) / Math.log(2)));

          introSort(f, b, depth_limit);
        }
        else {
          QuickSort(f, b-1);
          InsertionSort(f, b);
	    }
      }


      private void introSort(int f, int b, int depth_limit) throws Exception
      {
        int size_threshold = 14;//24;

        if (depth_limit-- == 0)
          HeapSort(f, b);
        else if (b - f <= size_threshold) {
          QuickSort(f, b-1);
          InsertionSort(f, b);
        } else
        {
          int p = partition(f, b);

          introSort(f, p, depth_limit);
          introSort(p, b, depth_limit);
        }
      }


      private int compare(int i1, int i2) { return i1-i2; }


      private int partition(int f, int b) throws Exception
      {
        int bot = f, mid = (b + f) / 2, top = b - 1;
        int abot = a[bot], amid = a[mid], atop = a[top];

        if (compare(abot, amid) < 0)
        {
          if (compare(atop, abot) < 0)//atop<abot<amid
          { a[top] = amid; amid = a[mid] = abot; a[bot] = atop; }
          else if (compare(atop, amid) < 0) //abot<=atop<amid
          { a[top] = amid; amid = a[mid] = atop; }
          //else abot<amid<=atop
        }
        else
        {
          if (compare(amid, atop) > 0) //atop<amid<=abot
          { a[bot] = atop; a[top] = abot; }
          else if (compare(abot, atop) > 0) //amid<=atop<abot
          { a[bot] = amid; amid = a[mid] = atop; a[top] = abot; }
          else //amid<=abot<=atop
          { a[bot] = amid; amid = a[mid] = abot; }
        }

        int i = bot, j = top;

        while (true)
        {
          while (compare(a[++i], amid) < 0) ;

          while (compare(amid, a[--j]) < 0) ;

          if (i < j)
          {
            int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
          }
          else
            return i;
        }
      }

	private void swap(int i, int j)
	{
		int T;
		T = a[i];
		a[i] = a[j];
		a[j] = T;
	}

	private void QuickSort(int l, int r) throws Exception
   {
	int M = 4;
	int i;
	int j;
	int v;

	if ((r-l)>M)
	{
		i = (r+l)/2;
		if (a[l]>a[i]) swap(l,i);	// Tri-Median Methode!
		if (a[l]>a[r]) swap(l,r);
		if (a[i]>a[r]) swap(i,r);

		j = r-1;
		swap(i,j);
		i = l;
		v = a[j];
		for(;;)
		{
			while(a[++i]<v);
			while(a[--j]>v);
			if (j<i) break;
			swap (i,j);
			pause(i,j);
                        if (stopRequested) {
                            return;
                        }
		}
		swap(i,r-1);
		pause(i);
		QuickSort(l,j);
		QuickSort(i+1,r);
	}
}

	private void InsertionSort(int lo0, int hi0) throws Exception
	{
		int i;
		int j;
		int v;

		for (i=lo0+1;i<=hi0;i++)
		{
			v = a[i];
			j=i;
			while ((j>lo0) && (a[j-1]>v))
			{
				a[j] = a[j-1];
				pause(i,j);
				j--;
			}
			a[j] = v;
	 	}
	}


      void HeapSort(int f, int b) throws Exception
      {
pause();
        for (int i = (b + f) / 2; i >= f; i--) heapify(f, b, i);

        for (int i = b - 1; i > f; i--)
        {
          int tmp = a[f]; a[f] = a[i]; a[i] = tmp;
          heapify(f, i, f);
        }
      }


      private void heapify(int f, int b, int i)
      {
        int pv = a[i], lv, rv, max = pv;
        int j = i, maxpt = j;

        while (true)
        {
          int l = 2 * j - f + 1, r = l + 1;

          if (l < b && compare(lv = a[l], max) > 0) { maxpt = l; max = lv; }

          if (r < b && compare(rv = a[r], max) > 0) { maxpt = r; max = rv; }

          if (maxpt == j)
            break;

          a[j] = max;
          max = pv;
          j = maxpt;
        }

        if (j > i)
          a[j] = pv;
      }
    }
  }



class ComparableComparator
  implements Comparator
  {
  public int compare(Object o1, Object o2)
  {
  Comparable c1 = (Comparable) o1;
  Comparable c2 = (Comparable) o2;
  return c1.compareTo(c2);
  } /* compare */
} /* class ComparableComparator */
