package com.sistempintar.app.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HasilPenilaian extends AppCompatActivity {
    TextView mtvHasilAkhir;
    Button mbtnMenu;
    //rickyjulpiter -> github profile
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_penilaian);

        mtvHasilAkhir = (TextView) findViewById(R.id.tvSkorAkhir);
        mbtnMenu = (Button) findViewById(R.id.btnMenu);

        setSkor();

        mbtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HasilPenilaian.this, MainActivity.class);
                HasilPenilaian.this.finish();
                startActivity(i);
            }
        });
    }

    public void setSkor(){
        String skorPil = getIntent().getStringExtra("skorAkhir");

        mtvHasilAkhir.setText("Nilai: "+skorPil);
    }

    public void onBackPressed(){
        Toast.makeText(this, "Tidak bisa kembali, silahkan tekan beranda", Toast.LENGTH_SHORT).show();
    }
}
