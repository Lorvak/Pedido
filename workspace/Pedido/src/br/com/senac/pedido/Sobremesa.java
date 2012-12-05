package br.com.senac.pedido;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Sobremesa extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sobremesa);
	
		findViewById(R.id.tab3).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) { 
						startActivity(new Intent(getBaseContext(),Sobremesa.class));
					}
				});

}}
