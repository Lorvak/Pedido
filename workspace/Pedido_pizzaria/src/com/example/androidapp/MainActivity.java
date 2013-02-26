package com.example.androidapp;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidapp.bean.Pedido;
import com.example.androipapp.services.WebServicesProperties;

public class MainActivity extends Activity {
	private String wsdlURL = WebServicesProperties.WSDL_URL_PEDIDO;
 	private String namespace = WebServicesProperties.NAMESPACE_PEDIDO; 
 	private String soapAction = WebServicesProperties.SOAPACTION_PEDIDO;
 	private String methodName;
 	private SoapObject request;
 	private SoapSerializationEnvelope envelope;
 	private HttpTransportSE androidHttpTransport;
 	private ServiceRequester sr;
 	private List<Pedido> pedidos;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btMainCadPedido).setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				startActivity(new Intent(getBaseContext(),PedidoFormActivity.class));				
			}
		});
        this.getPedidos();
        ArrayList<String> pedidosString = new ArrayList<String>();
		for (Pedido p : pedidos) {
			pedidosString.add(p.getResumo());
		}
		ListView lwPedidos = (ListView) findViewById(R.id.listViewPedido);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pedidosString);
		lwPedidos.setAdapter(adapter);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }   
    
    public void getPedidos() {
		methodName = "listarPedidos" ;
		request = new SoapObject(namespace, methodName);
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		androidHttpTransport = new HttpTransportSE(wsdlURL);
		sr= new ServiceRequester(androidHttpTransport,envelope,namespace+soapAction);
		sr.start();
		sr.waitToThreadFinish();
		request = (SoapObject) envelope.bodyIn;
	
		pedidos = new ArrayList<Pedido>();
		for (int i = 0; i < request.getPropertyCount(); i++) {
			String retorno = request.getProperty(i).toString();
			Pedido pedido= new Pedido();
			pedido.setResumo(retorno);
			pedidos.add(pedido);
		}
	}
}
