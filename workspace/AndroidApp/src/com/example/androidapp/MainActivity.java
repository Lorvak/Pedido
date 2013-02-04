package com.example.androidapp;

import java.util.ArrayList;
import java.util.Date;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.androidapp.bean.Registro;
import com.example.androipapp.services.WebServicesProperties;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {
	private static MainActivity telaAtual;
	private Registro registro=null;
	
	
    public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] items=null;
        try{
        	items = this.getProjetos();
        }catch(Exception e){
        	items=null;
        }
        if(items== null){ 		
        	items=new String[1];
        	items[0]="Nenhum projeto encontrado!";
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinnerProjeto);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void abrirListagem(View view){
      Intent intent = new Intent(this, ActivityListagem.class);
  	  startActivity(intent);
    }
    
    public void apresentaMensagem(View view) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	ArrayList<String> erros= this.validaFormulario();
    	String mensagens="";
    	for(String erro: erros){
    		mensagens+=erro;
    		mensagens+="\n";
    	}
    	if (mensagens.equals("")){
    		mensagens="Deseja Enviar os dados?";
    		builder.setTitle("Avaliação do formulário");
    		telaAtual=this;
    		builder.setPositiveButton ("Sim", new DialogInterface.OnClickListener() {
    		      public void onClick(DialogInterface dialog, int which) {  
    		    	  try{
    		    		  AlertDialog.Builder builderConfirm = new AlertDialog.Builder(telaAtual);
    		    		  builderConfirm.setPositiveButton("OK", null);
    		    		  builderConfirm.setMessage(telaAtual.getRegistro().toString());
    		    		  builderConfirm.show();
    		    		  telaAtual.enviaRegistro(telaAtual.getRegistro());
    		    	  }catch(Exception e){}
    		    	  Intent intent = new Intent(telaAtual, TelaSucesso.class);
    		    	  startActivity(intent);
    		    } });
    		builder.setNegativeButton("Não", null);
    	}else{
    		builder.setNegativeButton("OK", null);
    	}
    	builder.setMessage(mensagens);
    	
    	builder.create().show();
    }
    
    public ArrayList<String> validaFormulario(){
    	ArrayList<String> erros= new ArrayList<String>();
    	Spinner spinner = (Spinner) findViewById(R.id.spinnerProjeto);
    	DatePicker datePicker = (DatePicker) findViewById(R.id.datePickerData);
        EditText campoHoras=(EditText)findViewById(R.id.editTextHoras);
        EditText campoDescricao= (EditText)findViewById(R.id.editTextDescricao);
        Registro r= new Registro();
    	String projeto= spinner.getSelectedItem().toString();
    	int dia=datePicker.getDayOfMonth();
    	int mes=datePicker.getMonth();
    	int ano=datePicker.getYear()-1900;
    	Date data=new Date(ano,mes,dia);
    	if (projeto.equals("")){
    		erros.add("Campo projeto está em branco.");
    	}else{
    		int idProjeto= Integer.parseInt(projeto.substring(0,projeto.indexOf("-")).trim());
    		r.setIdProjeto(idProjeto);
    	}
    	if(data.after(new Date())){
    		erros.add("A data selecionada está no futuro: "+ data.toString());
    	}else{
    		r.setData(data);
    	}
    	double horas=-1;
    	try{
            horas=Double.parseDouble(campoHoras.getText().toString());
            r.setHoras(horas);
    	}catch(Exception e){
    		erros.add("Campo horas preenchido com valor não numérico.");
    	}
    	String descricao= campoDescricao.getText().toString();
    	if(descricao.equals("")){
    		erros.add("Você deve informar a descrição.");
        }else{
        	r.setDescricao(descricao);
        }
    	r.setIdUsuario(AuthenticationActivity.getIdUsuario());
    	this.registro=r;
    	return erros;
    }
    
    
    public String[] getProjetos() throws Exception {
    	
    	String wsdlURL =WebServicesProperties.WSDL_URL_PONTO_ELETRONICO;
    	String namespace = WebServicesProperties.NAMESPACE_PONTO_ELETRONICO; // namespace on WSDL
    	String soapAction = WebServicesProperties.SOAPACTION_PONTO_ELETRONICO; //portType tag on WSDL
    	String methodName = "getProjetos";  //operation on WSDL
    	  
		SoapObject request = new SoapObject(namespace, methodName); 
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(wsdlURL);
	
		ServiceRequester sr= new ServiceRequester(androidHttpTransport,envelope,namespace+soapAction);
		//androidHttpTransport.call(namespace+soapAction, envelope);
		sr.start();
		sr.waitToThreadFinish();
		SoapObject retornoRequisicao = (SoapObject) envelope.bodyIn;	
		String[] projetos= new String[retornoRequisicao.getPropertyCount()];
		for(int i=0;i<projetos.length;i++){
			projetos[i]=retornoRequisicao.getProperty(i).toString();
		}
		
	
		return projetos;
    }
    
 public String enviaRegistro(Registro r) throws Exception {
	    String wsdlURL =WebServicesProperties.WSDL_URL_PONTO_ELETRONICO;
 	    String namespace = WebServicesProperties.NAMESPACE_PONTO_ELETRONICO; // namespace on WSDL
 	    String soapAction = WebServicesProperties.SOAPACTION_PONTO_ELETRONICO; //portType tag on WSDL
    	String methodName = "registra";  //operation on WSDL
    	  
		SoapObject request = new SoapObject(namespace, methodName); 
		request.addProperty("descricao", r.getDescricao());
		request.addProperty("dia", String.valueOf(r.getData().getDate()));
		request.addProperty("mes", String.valueOf(r.getData().getMonth()));
		request.addProperty("ano", String.valueOf(r.getData().getYear()));
		request.addProperty("horas", String.valueOf(r.getHoras()));
		request.addProperty("idProjeto", r.getIdProjeto());
		request.addProperty("idUsuario", r.getIdUsuario());
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(wsdlURL);
		ServiceRequester sr= new ServiceRequester(androidHttpTransport,envelope,namespace+soapAction);
		//androidHttpTransport.call(namespace+soapAction, envelope);
		sr.start();
		sr.waitToThreadFinish();
		SoapObject retornoRequisicao = (SoapObject) envelope.bodyIn;	
		
		String resposta=retornoRequisicao.getProperty(0).toString();
		return resposta;
  
 }
    
    
}
