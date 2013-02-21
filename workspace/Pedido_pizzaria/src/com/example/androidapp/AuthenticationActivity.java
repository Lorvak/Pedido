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
import android.widget.Toast;

public class AuthenticationActivity extends Activity {
	private static Long idFuncionario;
		
    public static Long getIdUsuario() {
		return idFuncionario;
	}

	public static void setIdUsuario(Long idFuncionario) {
		AuthenticationActivity.idFuncionario = idFuncionario;
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
    		idFuncionario=autentica(login, senha);
    	}catch(Exception e){
    		idFuncionario=null;
    	}
    	
    	if(idFuncionario!=null){
    		Toast.makeText(getBaseContext(), idFuncionario.toString(), Toast.LENGTH_SHORT).show();
    		Intent navegador=  new Intent(this, MainActivity.class);
    		startActivity(navegador);
    	}else{
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setPositiveButton ("Sim",null);
        	builder.setMessage("Não foi possível autenticar seu usuário.");
        	builder.create().show();
    	}
    	
    }
    
    public Long autentica(String login,String senha) throws Exception{
    		Long id = null;
         	String wsdlURL =WebServicesProperties.WSDL_URL_LOGAR;
        	String namespace = WebServicesProperties.NAMESPACE_LOGAR; // namespace on WSDL
        	String soapAction = WebServicesProperties.SOAPACTION_LOGAR; //portType tag on WSDL
        	String methodName = "logar";  //operation on WSDL
        	  
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
    		String aux = retornoRequisicao.getProperty(0).toString();
    		if(!aux.equals("0")){
    			id = Long.parseLong(aux);
    		}
    		Log.e("web", "valor do id: " + id.toString());
    		return id;
    }
}
