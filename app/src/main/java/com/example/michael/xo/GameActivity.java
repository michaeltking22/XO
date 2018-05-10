package com.example.michael.xo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Michael on 5/9/2018.
 */

public class GameActivity extends AppCompatActivity {

    private Boolean p1End, p2End, draw;
    private GridView board;
    private int spots, size;
    private static int round;
    private int[] spotsP1, spotsP2;
    private String[] boxes;
    private ArrayList<Integer> iconList;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        draw = false;
        p1End = false;
        p2End = false;
        spots = getIntent().getIntExtra("spots", 9);
        size = getIntent().getIntExtra("size", 3);
        board = findViewById(R.id.board);
        round = 0;
        spotsP1 = new int[spots];
        spotsP2 = new int[spots];
        boxes = new String[spots];


        for (int i = 0; i < spots; i++) {
            ImageView ImageView = (android.widget.ImageView) getLayoutInflater().inflate(R.layout.layout, board, false);

            board.addView(ImageView);
            spotsP1[i] = -1;
            spotsP2[i] = -1;
            boxes[i] = "" + i;


        }
        while (p1End != true || p2End != true || draw != true) {

            final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.layout, boxes);
            board.setAdapter(adapter);
            board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (round % 2 == 0) {
                        if (spotsP1[position] != 1 || spotsP2[position] != 1) {
                            spotsP1[position] = 1;
                            view.setBackgroundResource(R.drawable.one);
                        }
                    } else {
                        if (spotsP1[position] != 1 || spotsP2[position] != 1) {
                            spotsP2[position] = 1;
                            view.setBackgroundResource(R.drawable.two);
                        }
                    }

                    int p1W = checkBoard(spotsP1);
                    int p2W = checkBoard(spotsP2);

                    if (p1W == 1) {
                        p1End = true;
                    } else if (p2W == 1) {
                        p2End = true;
                    }

                    round++;
                }
            });
            }
    }

        public int checkBoard(int[] a){
    //leftDiagIsChecked, rightDiagIsChecked, etc.
        Boolean leftDiag = false;
        Boolean rightDiag = false;
        Boolean topAcross = false;
        Boolean bottomAcross = false;
        Boolean leftSide = false;
        Boolean rightSide = false;
        int winner = 0;
        while(!leftDiag) {
            int j =0;
            leftDiag = true;
            for (int i = 0; i < spots; i++) {
                if (a[i] == 1) {
                    i += size;
                    j++;
                } else {
                    break;
                }
            }
            if(j==size){
                winner =1;
                return winner;
            }
        }
        while(!rightDiag){
            int j =0;
            rightDiag = true;
            for (int i = 0; i < spots; i++) {
                i= size-1;
                if (a[i] == 1) {
                    j++;
                } else {
                    break;
                }
            }
            if(j==size){
                winner =1;
                return winner;
            }
        }
            while(!topAcross){
                int j =0;
                topAcross = true;
                for (int i = 0; i < size; i++) {
                    if (a[i] == 1) {
                        j++;
                    } else {
                        break;
                    }
                }
                if(j==size){
                    winner =1;
                    return winner;
                }
            }

            while(!bottomAcross){
                int j =0;
                bottomAcross = true;
                for (int i=spots-size; i < spots; i++) {
                    if (a[i] == 1) {
                        j++;
                    } else {
                        break;
                    }
                }
                if(j==size){
                    winner =1;
                    return winner;
                }
            }

            while(!leftSide){
                int j =0;
                leftSide = true;
                for (int i=0; i < spots-size; i+=size) {
                    if (a[i] == 1) {
                        j++;
                    } else {
                        break;
                    }
                }
                if(j==size){
                    winner =1;
                    return winner;
                }
            }
            while(!rightSide){
                int j =0;
                rightSide = true;
                for (int i=size; i < spots; i+=size) {
                    if (a[i] == 1) {
                        j++;
                    } else {
                        break;
                    }
                }
                if(j==size){
                    winner =1;
                    return winner;
                }
            }

        return winner;
        }

        @Override
        public void onBackPressed(){
            Intent data = new Intent();
            data.putExtra("p1Win", p1End);
            data.putExtra("p2Win", p2End);
            data.putExtra("draw", draw);
            setResult(RESULT_OK, data);
            finish();
        }

}

