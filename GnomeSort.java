class GnomeSortAlgorithm extends SortAlgorithm {
    void sort(int a[]) throws Exception {

		int i=0, temp;

		while(i < a.length) {

			if(i == 0 || a[i-1] <= a[i]) i++;

			else{

				temp = a[i];

				a[i] = a[i-1];

				a[--i] = temp;

			}

			pause(i);

		}

		pause(-1,-1);
    }
}



/*

	for (int i = a.length; --i>=0; ) {
            boolean flipped = false;
	    for (int j = 0; j<i; j++) {
		if (stopRequested) {
		    return;
		}
		if (a[j] > a[j+1]) {
		    int T = a[j];
		    a[j] = a[j+1];
		    a[j+1] = T;
		    flipped = true;
		}
		pause(i,j);

	    }
	    if (!flipped) {
	        return;
	    }
        }

        */
