package com.nisyst.realmtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.nisyst.realmtest.GFG.spiralFill;

public class SpiralMatrix extends AppCompatActivity {

    static int MAX = 100;

    EditText txtRow, txtCol;
    AppCompatButton btnGenerate;
    TextView lblOutput;
    int row, col, max;
    StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiral_matrix);

        initView();

        /*int m = 4, n = 4;
        int a[][] = new int[MAX][MAX];
        spiralFill(m, n, a);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println("");
        }*/

        btnGenerate.setOnClickListener(view -> {
            sb = new StringBuilder();
            getData();
            int arr[][] = new int[max][max];
            spiralFill(row, col, arr);
            for (int i = 0 ; i < row ; i++) {
                for (int j = 0; j < col; j++) {
                    sb.append(arr[i][j] + " ");
                }
                sb.append("\n");
            }
            lblOutput.setText(sb.toString());
        });
    }

    private void initView() {
        txtRow = findViewById(R.id.txtRow);
        txtCol = findViewById(R.id.txtCol);
        btnGenerate = findViewById(R.id.btnGenerate);
        lblOutput = findViewById(R.id.lblOutput);
    }

    private void getData() {
        row = Integer.parseInt(txtRow.getText().toString());
        col = Integer.parseInt(txtCol.getText().toString());
        max = row * col;
    }

}