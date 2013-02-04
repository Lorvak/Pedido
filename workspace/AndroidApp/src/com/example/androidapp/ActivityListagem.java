package com.example.androidapp;

import java.util.ArrayList;
import java.util.Date;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.androipapp.services.WebServicesProperties;

public class ActivityListagem extends Activity {
	private static ActivityListagem tela;
	private static int[] idsRegistros=null;
	private static int idExcluir=-1;
	public ActivityListagem  getTela(){
		return tela;
	}
	
	public int[] getIdsRegistros(){
		return idsRegistros;
	} 
	
    public static int getIdExcluir() {
		return idExcluir;
	}

	public static void setIdExcluir(int idExcluir) {
		ActivityListagem.idExcluir = idExcluir;
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);
        this.atualizaRegistros(new Date());
        ListView listViewRegistros=(ListView)findViewById(R.id.listViewRegistros);
        ActivityListagem.tela=this;
        listViewRegistros.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view,      int pos, long id) {
               ActivityListagem.setIdExcluir(ActivityListagem.tela.getIdsRegistros()[pos]);
               AlertDialog.Builder builder = new AlertDialog.Builder(ActivityListagem.tela);
           	   builder.setMessage("Você deseja excluir este registro?");
    		   builder.setTitle("Ponto Eletrônico");
    		   builder.setPositiveButton ("Sim", new DialogInterface.OnClickListener() {
    		      public void onClick(DialogInterface dialog, int which) {  
    		    	   	try{
    		           		   int value=tela.deletaRegistro(ActivityListagem.getIdExcluir());
    		           		   if(value!=-1){
    		           			  AlertDialog.Builder builder = new AlertDialog.Builder(ActivityListagem.tela);
    	    		    		  builder.setMessage("Registro excluído!");
    	    		    		  builder.setPositiveButton("OK", null);
    	    		    		  builder.show();
    	    		    		  ActivityListagem.tela.btAtualizaRegistros(null);
    		           		   }
    		           	   }catch(Exception e){
    		           		   
    		           	   }
    		    } });
    		  builder.setNegativeButton("Não", null);
           	  builder.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_listagem, menu);
        return true;
    }
    
    public void btAtualizaRegistros(View view){
    	DatePicker datePicker = (DatePicker) findViewById(R.id.datePickerFiltro);
        int dia=datePicker.getDayOfMonth();
    	int mes=datePicker.getMonth();
    	int ano=datePicker.getYear()-1900;
    	Date data=new Date(ano,mes,dia);
    	atualizaRegistros(data);
    }
    
    public void atualizaRegistros(Date dt){
    	ArrayList<String> listItems= new ArrayList<String>();
        try{
    	String[] registros= this.getRegistros(dt);
    	idsRegistros= new int[registros.length];
        for(int i=0;i< registros.length;i++){
        	listItems.add(registros[i]);
        	idsRegistros[i]=Integer.valueOf(registros[i].substring(0, registros[i].indexOf("-")).trim());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listItems);
        ListView listViewRegistros=(ListView)findViewById(R.id.listViewRegistros);
        listViewRegistros.setAdapter(adapter);
        }catch(Exception e){
        	System.err.println("Activity listagem: oncreate: "+e.getMessage());
        }
    }
   
    public void voltar(View view){
    	Intent intent= new Intent(this,MainActivity.class);
    	startActivity(intent);
    }
    
 public String[] getRegistros(Date data) throws Exception {	
    	String wsdlURL =WebServicesProperties.WSDL_URL_PONTO_ELETRONICO;
    	String namespace = WebServicesProperties.NAMESPACE_PONTO_ELETRONICO; // namespace on WSDL
    	String soapAction = WebServicesProperties.SOAPACTION_PONTO_ELETRONICO; //portType tag on WSDL
    	String methodName = "getRegistros";  //operation on WSDL
    	  
		SoapObject request = new SoapObject(namespace, methodName); 
		request.addProperty("dia", String.valueOf(data.getDate()));
		request.addProperty("mes", String.valueOf(data.getMonth()));
		request.addProperty("ano", String.valueOf(data.getYear()));
		request.addProperty("idUsuario", AuthenticationActivity.getIdUsuario());
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(wsdlURL);
	
		ServiceRequester sr= new ServiceRequester(androidHttpTransport,envelope,namespace+soapAction);
		//androidHttpTransport.call(namespace+soapAction, envelope);
		sr.start();
		sr.waitToThreadFinish();
		SoapObject retornoRequisicao = (SoapObject) envelope.bodyIn;	
		String[] registros= new String[retornoRequisicao.getPropertyCount()];
		for(int i=0;i<registros.length;i++){
			registros[i]=retornoRequisicao.getProperty(i).toString();
		}
		return registros;
    }
 
 public int deletaRegistro(int id) throws Exception {	
	 	String wsdlURL =WebServicesProperties.WSDL_URL_PONTO_ELETRONICO;
	 	String namespace = WebServicesProperties.NAMESPACE_PONTO_ELETRONICO; // namespace on WSDL
	 	String soapAction = WebServicesProperties.SOAPACTION_PONTO_ELETRONICO; //portType tag on WSDL
	 	String methodName = "deletaRegistro";  //operation on WSDL
 	  
		SoapObject request = new SoapObject(namespace, methodName); 
		request.addProperty("id", id);;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(wsdlURL);
	
		ServiceRequester sr= new ServiceRequester(androidHttpTransport,envelope,namespace+soapAction);
		//androidHttpTransport.call(namespace+soapAction, envelope);
		sr.start();
		sr.waitToThreadFinish();
		SoapObject retornoRequisicao = (SoapObject) envelope.bodyIn;	
		String resp =retornoRequisicao.getProperty(0).toString();
		return Integer.valueOf(resp);
 }

}
