package com.example.androidapp;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.androipapp.services.WebServicesProperties;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AuthenticationActivity extends Activity {
	private static int idUsuario=-1;
		
    public static int getIdUsuario() {
		return idUsuario;
	}

	public static void setIdUsuario(int idUsuario) {
		AuthenticationActivity.idUsuario = idUsuario;
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_authentication, menu);
        return true;
    }
    
    public void acaoAutentica(View view){
    	EditText campoLogin=(EditText)findViewById(R.id.editText2);
    	EditText campoSenha=(EditText)findViewById(R.id.editText1);
    	String login=campoLogin.getText().toString();
    	String senha= campoSenha.getText().toString();
    	try{
    		idUsuario=autentica(login, senha);
    	}catch(Exception e){
    		idUsuario=-1;
    	}
    	
    	if(idUsuario!=-1){
    		Intent navegador=  new Intent(this, MainActivity.class);
    		startActivity(navegador);
    	}else{
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setPositiveButton ("Sim",null);
        	builder.setMessage("Não foi possível autenticar seu usuário.");
        	builder.create().show();
    	}
    	
    }
    
    public int autentica(String login,String senha) throws Exception{
         	String wsdlURL =WebServicesProperties.WSDL_URL_USUARIO;
        	String namespace = WebServicesProperties.NAMESPACE_USUARIO; // namespace on WSDL
        	String soapAction = WebServicesProperties.SOAPACTION_USUARIO; //portType tag on WSDL
        	String methodName = "autentica";  //operation on WSDL
        	  
    		SoapObject request = new SoapObject(namespace, methodName); 
    		request.addProperty("login", login);
    		request.addProperty("senha", senha);
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
    		int id = Integer.valueOf(retornoRequisicao.getProperty(0).toString());
    		return id;
    }
}
