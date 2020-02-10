class BinaryBucketSortAlgorithm extends SortAlgorithm{
  private final static int BIT_IN_LEFTMOST_POSITION=1<<62;

  void sort(int[] array) throws Exception{
    sortItAscending(array,BIT_IN_LEFTMOST_POSITION,-1,array.length-1);
  }

  private void sortItDescending(int[] array,int mask,int low,int high) throws Exception{
    if(stopRequested)
      return;
    if(mask==0)
      return;
    if(low==high)
      return;

    int ctr=low;
    for(int j=low;j<high;j++)
      if((array[j] & mask)!=0)
	swap(array,j,ctr++);
    sortItDescending(array,mask>>1,low,ctr);
    sortItDescending(array,mask>>1,ctr,high);
  }

  private void sortItAscending(int[] array,int mask,int low,int high) throws Exception{
    if(stopRequested)
      return;
    if(mask==0)
      return;
    if(low==high)
      return;

    int ctr=high;
    for(int j=high;j>low;j--)
      if((array[j] & mask)!=0)
	swap(array,j,ctr--);
    sortItAscending(array,mask>>1,ctr,high);
    sortItAscending(array,mask>>1,low,ctr);
  }

  private void swap(int[] array,int i,int j) throws Exception{
    if(i==j)
      return;
    int temp=array[i];
    array[i]=array[j];
    array[j]=temp;
    pause(i,j);
  }
}
