package com.sistempintar.app.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    public void onBackPressed(){
        Intent pindah = new Intent(this, MainActivity.class);
        about.this.finish();
        startActivity(pindah);
    }
}
