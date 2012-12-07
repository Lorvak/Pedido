package br.com.senac.pedido;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Bebida extends Activity{
	
	private TextView txtMesa;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bebida);

		txtMesa = (TextView) findViewById(R.id.bebidaMesa);
		 
	
		txtMesa.setText(mesa.mesaEscolhida);
	 
	
	 
		findViewById(R.id.Bebida).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) { 
						startActivity(new Intent(getBaseContext(),Bebida.class));
					}
				});

}
	
 

}
