package br.com.senac.dao;

import br.com.senac.pedido.DB;
import br.com.senac.Vo.Borda;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BordaDao {
	private static String table_name = "borda";
	private Context ctx;
	private static String[] columns = {"id","nome"};
	
	
	public BordaDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BordaDao(Context ctx) {
		this.ctx = ctx;
		// TODO Auto-generated constructor stub
	}
	public Context getCtx() {
		return ctx;
	}
	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}
	public Boolean insert(Borda borda){
SQLiteDatabase db = new DB(ctx).getWritableDatabase();
		
		ContentValues ctv = new ContentValues();
		ctv.put("nome"    , borda.getNome());
		
		
		
		return db.insert(table_name, null, ctv) > 0 ;
		
		
		
	}
}
