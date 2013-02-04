package com.example.androidapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class TelaSucesso extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sucesso);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tela_sucesso, menu);
        return true;
    }
    
    public void voltar(View view){
    	Intent intent = new Intent(this, MainActivity.class);
  	    startActivity(intent);
    }
}
