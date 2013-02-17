package com.example.androidapp;

import java.io.IOException;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

public class ServiceRequester extends Thread {
	private HttpTransportSE androidHttpTransport;
	private SoapSerializationEnvelope envelope;
	private String serviceNameSpace;
	private boolean hasFinished=false;
	
	public ServiceRequester(HttpTransportSE androidHttpTransport,SoapSerializationEnvelope envelope,String serviceNameSpace){
		this.androidHttpTransport=androidHttpTransport;
		this.envelope=envelope;
		this.serviceNameSpace=serviceNameSpace;
	}
	public void run() {
		try {
			Log.e("web", "entrou no run");
			this.hasFinished=false;
			Log.e("web", "vai enviar");
			this.androidHttpTransport.call(serviceNameSpace,envelope);
			Log.e("web", "enviou");
			this.hasFinished=true;
		} catch (IOException e) {
			Log.e("web", "caiu no 1º catch");
			Log.e("web", e.toString());
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			Log.e("web", "caiu no 2º catch");
			Log.e("web", e.toString());
			e.printStackTrace();
		}
	}
	
	public void waitToThreadFinish(){
		while(!this.hasFinished){}
	}


}
