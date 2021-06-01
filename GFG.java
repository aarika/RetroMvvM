package com.nisyst.realmtest;


// Java program to fill a matrix with values from
// 1 to n*n in spiral fashion.
public class GFG {


    // Fills a[m][n] with values from 1 to m*n in
    // spiral fashion.
    static void spiralFill(int row, int col, int arr[][]) {
        // Initialize value to be filled in matrix
        int val = 1;

		/*
		sr - starting row index
		m - ending row index
		sc - starting column index
		n - ending column index */
        int sr = 0, sc = 0;
        //Running the loop until starting postion is less than row and col
        while (sr < row && sc < col) {
			/* Print the first row from the remaining
		rows */
            for (int i = sc; i < col; ++i) {
                arr[sr][i] = val++;
            }

            sr++;

			/* Print the last column from the remaining
		columns */
            for (int i = sr; i < row; ++i) {
                arr[i][col - 1] = val++;
            }
            col--;

			/* Print the last row from the remaining
		rows */
            if (sr < row) {
                for (int i = col - 1; i >= sc; --i) {
                    arr[row - 1][i] = val++;
                }
                row--;
            }

			/* Print the first column from the remaining
		columns */
            if (sc < col) {
                for (int i = row - 1; i >= sr; --i) {
                    arr[i][sc] = val++;
                }
                sc++;
            }
        }
    }

}

/* This Java code is contributed by PrinciRaj1992*/

