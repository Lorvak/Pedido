package br.com.senac.pedido;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Bebida extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bebida);
	
		findViewById(R.id.tab2).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) { 
						startActivity(new Intent(getBaseContext(),Bebida.class));
					}
				});

}}
