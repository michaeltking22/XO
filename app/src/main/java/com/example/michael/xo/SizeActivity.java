package com.example.michael.xo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.SeekBar;

public class SizeActivity extends AppCompatActivity{
        private SeekBar bar;
        private int size;

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_size);
            bar = findViewById(R.id.bar);
    }

    public void OnSeekBarChangeListener() {
        bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar bar, int i, boolean b) {
                        size = bar.getProgress();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar SeekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar SeekBar) {

                    }
                }
        );
    }

    @Override
    public void onBackPressed(){
        Intent result = new Intent();
        result.putExtra("size", size);
        setResult(RESULT_OK, result);
        finish();
    }
}
