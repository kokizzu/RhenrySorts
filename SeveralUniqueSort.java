public class SeveralUniqueSortAlgorithm extends SortAlgorithm {

public void sort (int[] Element) throws Exception {

    int EndIndex;                /* Index where current scan should end.  */
    int Position;                /* Current scan position.  */
    int HighValue;               /* Highest value found in current scan.  */
    int SwapIndex = 0;               /* First index of highest value found.  */
    int NewValue;                /* Value of current element.  */

    /* Scan all elements on the first pass.  */
    EndIndex = Element.length - 1;

    /* For each distinct element value...  */
    do
    {
        /* Start with no highest value (use lowest possible value).  */
        HighValue = 0;

        /* For each element, scanning from start to end...  */
        Position = -1;
        while ( Position < EndIndex )
        {
			//pause(Position, EndIndex);
            /* Advance to the next element.  */
            Position++;

            /* Get the current value.  */
            NewValue = Element[ Position ];

            /* If the current value is lower, swap it with highest so far.
             * Rather than swapping the lower value again, use the next
             * position which is the first occurrence of the highest value.  */
            if ( NewValue < HighValue )
            {
                Element[ SwapIndex ] = NewValue;
                SwapIndex++;
                Element[ Position ] = HighValue;
            }

            /* If the current value is higher, remember its value/position.  */
            if ( NewValue > HighValue )
            {
                SwapIndex = Position;
                HighValue = Element[ Position ];
            }
		pause(Position, EndIndex);
        }

        /* Finish the next scan prior to the highest value found in this pass,
         * as its elements are now in their final positions.  */
        EndIndex = SwapIndex - 1;
    }
    while ( Position >= SwapIndex );

}

}
