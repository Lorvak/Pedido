package br.com.senac.pedido;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
 
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

	private static String dbName  = "pedido.db";
	
	private static String sql1  = "CREATE TABLE [borda] " +
			"([id] INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
			"[nome] varchar(30));";
			
			
			private static String sql2  = "CREATE TABLE [bebida] " +
					"([id] INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
					"[nome] varchar(120), " + 
					"[unidadeMedida] char(4));";
			
			private static String sql3  = "CREATE TABLE [mesa] " +
					"([id] INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
					"[numMesa] varchar(3));";
			
			private static String sql4  = "CREATE TABLE [cardapio] " +
					"([id] INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
					"[sabor] varchar(150), " + 
					"[valor] float, " +
					"[descricao] text));";
			
			private static String sql5  = "CREATE TABLE [pedido_has_cardapio] " +
					
					"[pedido_idpedido] INTEGER, " + 
					"[cardapio_idcardapio] INTEGER, " +
					"[borda_idborda] INTEGER, " +
					"[adicionado] VARCHAR(500), " +
					"[descricao] text), " +
					"FOREIGN KEY (pedido_idpedido)REFERENCES pedido (id));";
			
			private static String sql6  = "CREATE TABLE [pedido] " +
					"([id] INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
					"[tamanho] VARCHAR(20), " + 
					"[numSabores] VARCHAR(30), " +
					"[preco] VARCHAR(45), " +
					"[adicionado] VARCHAR(500), " +
					"[mesa_idmesa] INT), " +
					"FOREIGN KEY (mesa_idmesa )REFERENCES mesa (id));";
			
			private static String sql7  = "CREATE TABLE [pedido_has_bebida] " +
					"([id] INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
					"[pedido_idpedido] INT, " + 
					"[bebida_idbebida] INT, " +
					"[preco] VARCHAR(45), " +
					"[adicionado] VARCHAR(500), " +
					"[mesa_idmesa] INT)," +
					"FOREIGN KEY (pedido_idpedido )REFERENCES pedido (id));";
					
					
			private static int    version1 = 1;
			
	public DB(Context ctx) {
		super(ctx, dbName, null, version1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sql1);
		db.execSQL(sql2);
		db.execSQL(sql3);
		db.execSQL(sql4);
		db.execSQL(sql5);
		db.execSQL(sql6);
		db.execSQL(sql7);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

	

}
