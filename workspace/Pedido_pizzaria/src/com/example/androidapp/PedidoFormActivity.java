package com.example.androidapp;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidapp.bean.Bebida;
import com.example.androidapp.bean.Mesa;
import com.example.androidapp.bean.Pedido;
import com.example.androidapp.bean.Pizza;
import com.example.androipapp.services.WebServicesProperties;

public class PedidoFormActivity extends Activity {
	String wsdlURL = WebServicesProperties.WSDL_URL_PEDIDO;
 	String namespace = WebServicesProperties.NAMESPACE_PEDIDO; 
 	String soapAction = WebServicesProperties.SOAPACTION_PEDIDO;
 	String methodName;
 	SoapObject request;
 	SoapSerializationEnvelope envelope;
 	HttpTransportSE androidHttpTransport;
 	ServiceRequester sr;
 	
 	Mesa mesa;
 	List<Mesa> mesas;
 	List<String> mesasString;
 	private List<Pizza> pizzas;
 	private Pizza pizza;
 	ArrayAdapter<String> arrayAdapterMesas;
 	Spinner spnMesas;
 	
 	Bebida bebida;
 	List<Bebida> bebidas;
 	List<String> bebidasString;
 	ArrayAdapter<String> arrayAdapterBebida;
 	Spinner spnBebida;
 	
 	List<String> tamanhosString;
 	ArrayAdapter<String> arrayAdapterPizza;
 	Spinner spnPizza;
 	
 	static Pedido pedido;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedido_form);
		pedido = new Pedido();
		getMesas();
		getPizzas();
		mesasString = new ArrayList<String>();
		mesasString.add("Selecione uma mesa");
		for (Mesa m : mesas) {
			mesasString.add(m.getNumero());
		}
		spnMesas = (Spinner) findViewById(R.id.spnMesa);
		arrayAdapterMesas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mesasString);
		arrayAdapterMesas.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnMesas.setAdapter(arrayAdapterMesas);
        spnMesas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if(!arg0.getItemAtPosition(arg2).toString().equals("Selecione uma mesa")){
					Toast.makeText(PedidoFormActivity.this, "Mesa Selecionada: " + arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_SHORT).show();
					pedido.setMesa(mesas.get(arg2-1));
				}
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        tamanhosString = new ArrayList<String>();
        tamanhosString.add("Escolha um tamanho para a pizza");
        tamanhosString.add("pequena");
        tamanhosString.add("m�dia");
        tamanhosString.add("grande");
        spnPizza = (Spinner) findViewById(R.id.spnPizza);
        arrayAdapterPizza = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tamanhosString);
        spnPizza.setAdapter(arrayAdapterPizza);
        spnPizza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if(!arg0.getItemAtPosition(arg2).toString().equals("Escolha um tamanho para a pizza")){
					Toast.makeText(PedidoFormActivity.this, "Tamanho Selecionado: " + arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_SHORT).show();
				}
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
		});

        getBebidas();
        bebidasString = new ArrayList<String>();
		bebidasString.add("Selecione uma bebida");
		for (Bebida b : bebidas) {
			bebidasString.add(b.getNome());
		}
		spnBebida = (Spinner) findViewById(R.id.spnBebida);
		arrayAdapterBebida = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bebidasString);
		arrayAdapterBebida.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnBebida.setAdapter(arrayAdapterBebida);
		spnBebida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if(!arg0.getItemAtPosition(arg2).toString().equals("Selecione uma bebida")){
					Toast.makeText(PedidoFormActivity.this, "Bebida Selecionada: " + arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_SHORT).show();
					bebida = bebidas.get(arg2 - 1);
				}
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
		//pizzas
		 	ArrayList<String> pizzasString = new ArrayList<String>();
			pizzasString.add("Selecione uma pizza");
			for (Pizza p : pizzas) {
				pizzasString.add(p.getTamanho());
			}
			spnPizza= (Spinner) findViewById(R.id.spinnerPizzas);
			arrayAdapterPizza = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pizzasString);
			arrayAdapterPizza.setDropDownViewResource(android.R.layout.simple_spinner_item);
			spnPizza.setAdapter(arrayAdapterPizza);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_pedido_form, menu);
		return true;
	}

	public void realizarPedido(View view) {
		spnMesas = (Spinner) findViewById(R.id.spnMesa);
		spnPizza = (Spinner) findViewById(R.id.spnPizza);
		spnBebida = (Spinner) findViewById(R.id.spnBebida);
		int posMesa=spnMesas.getSelectedItemPosition();
		int posPizza=spnPizza.getSelectedItemPosition();
		int posBebida=spnBebida.getSelectedItemPosition();
		mesa=mesas.get(posMesa);
		pizza=pizzas.get(posPizza);
		bebida=bebidas.get(posBebida);
		long funcionarioId=AuthenticationActivity.getIdUsuario();
		try{
			Long idPedido=realizaPedido(String.valueOf(mesa.getId()),String.valueOf(pizza.getId()),String.valueOf(funcionarioId), String.valueOf(bebida.getId()) ); 
			Toast.makeText(PedidoFormActivity.this, "Pedido Realizado:"+ idPedido.longValue(),Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			
		}
	}
	
	public void getMesas() {
		methodName = "listarMesas" ;
		request = new SoapObject(namespace, methodName);
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		androidHttpTransport = new HttpTransportSE(wsdlURL);
		sr= new ServiceRequester(androidHttpTransport,envelope,namespace+soapAction);
		sr.start();
		sr.waitToThreadFinish();
		request = (SoapObject) envelope.bodyIn;
		
		mesas = new ArrayList<Mesa>();
		for (int i = 0; i < request.getPropertyCount(); i++) {
			String retorno = request.getProperty(i).toString();
			Integer posicao = retorno.indexOf("-");
			mesa = new Mesa();
			mesa.setId(Long.parseLong(retorno.substring(0, posicao)));
			mesa.setNumero(retorno.substring(posicao + 1));
			mesas.add(mesa);
		}
	}
	
	public void getBebidas() {
		methodName = "listarBebidas" ;
		request = new SoapObject(namespace, methodName);
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		androidHttpTransport = new HttpTransportSE(wsdlURL);
		sr= new ServiceRequester(androidHttpTransport,envelope,namespace+soapAction);
		sr.start();
		sr.waitToThreadFinish();
		request = (SoapObject) envelope.bodyIn;
		
		bebidas = new ArrayList<Bebida>();
		for (int i = 0; i < request.getPropertyCount(); i++) {
			String retorno = request.getProperty(i).toString();
			Integer posicao = retorno.indexOf("-");
			bebida = new Bebida();
			bebida.setId(Long.parseLong(retorno.substring(0, posicao)));
			bebida.setNome(retorno.substring(posicao + 1));
			bebidas.add(bebida);
		}
	}
	
	public void getPizzas() {
		methodName = "listarPizzas" ;
		request = new SoapObject(namespace, methodName);
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		androidHttpTransport = new HttpTransportSE(wsdlURL);
		sr= new ServiceRequester(androidHttpTransport,envelope,namespace+soapAction);
		sr.start();
		sr.waitToThreadFinish();
		request = (SoapObject) envelope.bodyIn;
		
		pizzas = new ArrayList<Pizza>();
		for (int i = 0; i < request.getPropertyCount(); i++) {
			String retorno = request.getProperty(i).toString();
			Integer posicao = retorno.indexOf("-");
			pizza = new Pizza();
			pizza.setId(Long.parseLong(retorno.substring(0, posicao)));
			pizza.setTamanho(retorno.substring(posicao + 1));
			pizzas.add(pizza);
		}
	}
	
	 public Long realizaPedido(String idMesa,String idPizza,String idFuncionario, String idBebida) throws Exception{
 		Long id = null;
      	String wsdlURL =WebServicesProperties.WSDL_URL_PEDIDO;
     	String namespace = WebServicesProperties.NAMESPACE_PEDIDO; // namespace on WSDL
     	String soapAction = WebServicesProperties.SOAPACTION_PEDIDO; //portType tag on WSDL
     	String methodName = "realizaPedido";  //operation on WSDL
     	  
 		SoapObject request = new SoapObject(namespace, methodName); 
 		request.addProperty("idMesa", idMesa);
 		request.addProperty("idPizza", idPizza);
 		request.addProperty("idBebida", idBebida);
 		request.addProperty("idFuncionario", idFuncionario);
 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
 		envelope.setOutputSoapObject(request);
 		HttpTransportSE androidHttpTransport = new HttpTransportSE(wsdlURL);
 	
 		ServiceRequester sr= new ServiceRequester(androidHttpTransport,envelope,namespace+soapAction);
 		//androidHttpTransport.call(namespace+soapAction, envelope);
 		Log.e("web", "vai iniciar o envio");
 		sr.start();
 		sr.waitToThreadFinish();
 		SoapObject retornoRequisicao = (SoapObject) envelope.bodyIn;
 		Log.e("web", "recebeu a resposta");
 		String aux = retornoRequisicao.getProperty(0).toString();
 		if(!aux.equals("0")){
 			id = Long.parseLong(aux);
 		}
 		Log.e("web", "valor do id: " + id.toString());
 		return id;
 }

}
