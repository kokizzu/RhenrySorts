class QuickBinaryBucketSortAlgorithm extends SortAlgorithm{
  private final static int BIT_IN_LEFTMOST_POSITION=1<<62;

  void sort(int[] array) throws Exception{
    sortItAscending(array,BIT_IN_LEFTMOST_POSITION,0,array.length-1);
  }

  private void sortItAscending(int[] array,int mask,int low,int high) throws Exception{
    if(mask==0)
      return;
    if(low==high)
      return;

    int leftCtr=low;
    int rightCtr=high;

    while(true){
      if(stopRequested)
	return;

      while(leftCtr<high && (array[leftCtr]&mask)==0)
	leftCtr++;
      
      while(rightCtr>low && (array[rightCtr]&mask)!=0)
	rightCtr--;
      
      if(rightCtr<=leftCtr){
	sortItAscending(array,mask>>1,low,rightCtr);
	sortItAscending(array,mask>>1,leftCtr,high);
	return;
      }
      else swap(array,leftCtr,rightCtr);
    }
  }

  private void sortItDescending(int[] array,int mask,int low,int high) throws Exception{
    if(mask==0)
      return;
    if(low==high)
      return;

    int leftCtr=low;
    int rightCtr=high;

    while(true){
      if(stopRequested)
	return;

      while(leftCtr<high && (array[leftCtr]&mask)!=0)
	leftCtr++;
      
      while(rightCtr>low && (array[rightCtr]&mask)==0)
	rightCtr--;
      
      if(rightCtr<=leftCtr){
	sortItDescending(array,mask>>1,low,rightCtr);
	sortItDescending(array,mask>>1,leftCtr,high);
	return;
      }
      else swap(array,leftCtr,rightCtr);
    }
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
