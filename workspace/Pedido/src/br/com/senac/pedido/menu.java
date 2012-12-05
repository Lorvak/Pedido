package br.com.senac.pedido;

 
 import android.app.ActivityGroup;
import android.content.Intent;
import android.content.res.Resources;
 
 import android.os.Bundle;
import android.widget.TabHost;

//MainActivity herda de ActivityGroup, porque vocÊ está lidando
 //com várias atividades em um elemento só. Ou seja, o TabHost hospeda um grupo de atividades.
 @SuppressWarnings("deprecation")
public class menu extends ActivityGroup   {
 static TabHost tabHost;
 static int tab = -1;
 Intent intent;
 
@Override
 public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.menu);
 Resources res = getResources();
 
 tabHost = (TabHost)findViewById(R.id.tabhost);
 tabHost.setup(this.getLocalActivityManager());
 TabHost.TabSpec spec;

//Adiciona Tab #1;
intent = new Intent().setClass(this, Pizza.class);
spec = tabHost.newTabSpec("0").setIndicator("Pizza", 
		res.getDrawable(R.drawable.pizza1)).setContent(intent);
tabHost.addTab(spec);

//Adiciona Tab #2
intent = new Intent(this, Bebida.class);
spec = tabHost.newTabSpec("1").setIndicator("Bebida",
		res.getDrawable(R.drawable.bebida1)).setContent(intent);
tabHost.addTab(spec);

//Adiciona Tab #3
intent = new Intent().setClass(this, Sobremesa.class);
spec = tabHost.newTabSpec("2").setIndicator("Sobremesa", 
		res.getDrawable(R.drawable.sobremesa1)).setContent(intent);
tabHost.addTab(spec);

//Adiciona Tab #4
intent = new Intent().setClass(this, FecharConta.class);
spec = tabHost.newTabSpec("3").setIndicator("Fechar Conta", 
		res.getDrawable(R.drawable.fecharconta1)).setContent(intent);
tabHost.addTab(spec);

//essa ultima linha indica qual tab será carregada ao iniciar essa activity. No nosso caso, a Primeira!!!

tabHost.setCurrentTab(0);
 
 }

}