package br.com.senac.pedido;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.senac.Vo.Borda;
import br.com.senac.dao.BordaDao;


public class cadBorda extends Activity {
	private EditText txtNome;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadborda);
		Button btnOk = (Button) findViewById(R.id.btCadePedidoBordaSubmit);

		txtNome = (EditText) findViewById(R.id.editTxtCadBordaNome);
		
		btnOk.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Borda borda = new Borda();
				borda.setNome(txtNome.getText().toString());
				BordaDao bordaDao = new BordaDao(getBaseContext());
				
				if(bordaDao.insert(borda)){
					Toast.makeText(getBaseContext(), "Sucesso", Toast.LENGTH_SHORT).show();
					finish();}
				else{Log.e("debug","NAo salvo");
				}
			}
			
		});

	};
}
