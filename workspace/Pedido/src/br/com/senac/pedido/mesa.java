package br.com.senac.pedido;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Toast;

public class mesa extends ListActivity {

	static List<String> mesas = new ArrayList<String>();
	public static String mesaEscolhida;

	// } { "A01", "A02", "A03",
	// "A04", "A05", "A06", "A07", "A08",
	// "A09", "A10", "A11", "A12", "A13" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mesas.add("A01");
		mesas.add("A02");

		setListAdapter(new ArrayAdapter<String>(getBaseContext(),
				android.R.layout.simple_list_item_1, mesas));

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		mesaEscolhida = l.getAdapter().getItem(position).toString();
		Toast.makeText(getBaseContext(), "" + mesaEscolhida, Toast.LENGTH_LONG)
				.show();
		startActivity(new Intent(getBaseContext(), Bebida.class));
	}

}
