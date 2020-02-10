/* end is the last index + 1 */

public class CountingSortAlgorithm extends SortAlgorithm {

public void sort(int[] a) throws Exception {
   csort(a,128,0);
pause();
}

void csort(int array[], int max, int min) throws Exception
{
  int i,j;
  int range = max-min;
  int[] count = new int[max-min+1];
  int[] scratch = new int[array.length];

  for(i = 0; i < range; i++) {
    count[i] = 0;
    pause(i);
  }

  /* 
   * Set the value of count[i] to the number of
   * elements in array with value i+min-1. 
   */
  for(i = 0; i < array.length; i++) {
    int c = array[i]+1-min;
    count[c]++;
    pause(i,max-i);
  }

  /* 
   * Update count[i] to be the number of
   * elements with value less than i+min. 
   */
  for (i = 1; i < count.length; i++)
    count[i] += count[i-1];

  /* 
   * Copy the elements of array into scratch in
   * sorted order. 
   */
  for(i = 0; i < array.length; i++) {
    int c = array[i]-min;
    int s = count[c];
    scratch[s] = array[i];
    /* 
     * Increment count so that the next element
     * with the same value as the current element
     * is placed into its own position in scratch. 
     */
    count[c]++;
  }

  for(i = 0; i < array.length; i++)
    array[i] = scratch[i];
}

}
