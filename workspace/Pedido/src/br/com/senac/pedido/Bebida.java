package br.com.senac.pedido;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Bebida extends Activity{
	
	private TextView txtMesa;
	private int RETORNO_PIZZA = 1;
	
	
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
		
		Button bt = (Button) findViewById(R.id.cadPedido);

}
	
 

}
