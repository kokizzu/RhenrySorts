public class BucketSortAlgorithm extends SortAlgorithm {

public void sort(int[] a) throws Exception {
 int m=128;
 int[] count = new int[m];

 for (int i=0;i<m;++i) count[i]=0;
 for (int j=0;j<a.length;++j) {
   ++count[a[j]];
   pause(j);
 }
 for (int i=0,j=0;i<m;++i)
   for (; count[i]>0;--count[i]) {
      a[j++]=i;
      pause(i,j);
   }
}

}
