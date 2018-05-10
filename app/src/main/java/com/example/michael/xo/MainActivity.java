package com.example.michael.xo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String player1, player2;
    private ArrayList<String> iconList;
    private Random r;
    private Drawable p2, p1;
    private Button play, icon, sizeButton;
    private int size, spots;
    private int p1score, p2score;
    private TextView play1, play2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iconList = new ArrayList<>();
        play = findViewById(R.id.play);
        icon = findViewById(R.id.icons);
        sizeButton = findViewById(R.id.size);
        play1 = findViewById(R.id.tb);
        play2 = findViewById(R.id.td);
        size = 3;
        spots = 9;
        player1 = "Player 1";
        player2 = "Player 2";
        p1score =0;
        p2score =0;


        AssetManager manager = getAssets();
        try {
            for (String filename : manager.list("")) {
                //iconList.add(R.drawable.filename);
            }
        }catch(IOException e) {
            Toast.makeText(this, "Bad Asset!", Toast.LENGTH_LONG).show();
        }


    }
        public void startPressed(View v) {
            Intent i = new Intent(this, GameActivity.class);
            i.putExtra("spots", spots);
            i.putExtra("size", size);
            startActivityForResult(i, 3);
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data) {
            if (requestCode == 2) {
                size = data.getIntExtra("size", 3);
                spots = size * size;
            } else if (requestCode == 3) {
                Boolean p1End = data.getBooleanExtra("p1Win", false);
                Boolean p2End = data.getBooleanExtra("p2Win", false);
                Boolean draw = data.getBooleanExtra("draw", false);

                if (p1End == true) {
                    p1score += 1;
                    play1.setText(""+p1score);
                } else if (p2End == true) {
                    p2score += 1;
                    play2.setText(""+p2score);
                } else if (draw == true) {
                    return;
                } else {
                    return;
                }
            }
        }





    public void sizePressed(View v){
        Intent j = new Intent(this, SizeActivity.class);
        startActivityForResult(j, 2);
    }


}
