package com.example.saveabuck.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.saveabuck.db.contracts.ControllerContract.CategoriasEntry;
import com.example.saveabuck.db.contracts.ControllerContract.ContasEntry;
import com.example.saveabuck.db.contracts.ControllerContract.LancamentosEntry;
import com.example.saveabuck.model.Categorias;
import com.example.saveabuck.model.Contas;
import com.example.saveabuck.model.Lancamentos;

public class SaveABuckData extends SQLiteOpenHelper {
	// If you change the database schema, you must increment the database version.
	public static final int DATABASE_VERSION = 1;
	
	public static final String DATABASE_NAME 	= "Controller.db";
	private static final String TEXT_TYPE 		= " TEXT";
	private static final String INTEGER_TYPE 	= " INTEGER";
	private static final String TABLEID_TYPE 	= " INTEGER PRIMARY KEY";
	
	
	private static final String COMMA_SEP = ",";
	
	public SaveABuckData(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    
	}
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_TABLE_CONTAS);
		db.execSQL(SQL_CREATE_TABLE_CATEGORIAS);
		db.execSQL(SQL_CREATE_TABLE_LANCAMENTOS);
	}
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This database is only a cache for online data, so its upgrade policy is
		// to simply to discard the data and start over
		db.execSQL(SQL_DELETE_TABLE_CONTAS);
		db.execSQL(SQL_DELETE_TABLE_CATEGORIAS);
		db.execSQL(SQL_DELETE_TABLE_LANCAMENTOS);        
		onCreate(db);
	}
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
	
	// Contas		///////////////////////////////////////////////////////////////////////
	
	// Create Table
	private static final String SQL_CREATE_TABLE_CONTAS =
		"CREATE TABLE " + ContasEntry.TABLE_NAME + 
		" (" +
		ContasEntry._ID + TABLEID_TYPE + COMMA_SEP +
		ContasEntry.COLUMN_NAME_TITLE 		+ TEXT_TYPE 		+ COMMA_SEP +
		ContasEntry.COLUMN_NAME_TYPE 		+ INTEGER_TYPE 		+
		" )";

	// Delete Table
	private static final String SQL_DELETE_TABLE_CONTAS =
		"DROP TABLE IF EXISTS " + ContasEntry.TABLE_NAME;    
		
	// Store a conta in the DB
	public long storeConta(Contas aConta) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(ContasEntry.COLUMN_NAME_TITLE, aConta.getTitle());
		values.put(ContasEntry.COLUMN_NAME_TYPE, aConta.getType());
		
		long newRowId;
		newRowId = db.insert(ContasEntry.TABLE_NAME, null, values);
		
		return newRowId;
	}
	
	public ArrayList<Contas> getContas() {
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<Contas> contas = new ArrayList<Contas>();
		
		String[] projection = {
				ContasEntry._ID,
				ContasEntry.COLUMN_NAME_TITLE,
				ContasEntry.COLUMN_NAME_TYPE
				};
		
		// Sort by name
		String sortOrder = ContasEntry.COLUMN_NAME_TITLE + " DESC";
		
		Cursor c = db.query(
				ContasEntry.TABLE_NAME,  				// The table to query
				projection,                             // The columns to return
				null,                             		// The columns for the WHERE clause
				null,                        			// The values for the WHERE clause
				null,                                   // don't group the rows
				null,                                   // don't filter by row groups
				sortOrder                               // The sort order
				);
		
		c.moveToFirst();
		
		while(c.isAfterLast() == false) {
			// Get the indexes and then get the values
			String titleConta = c.getString(c.getColumnIndex(ContasEntry.COLUMN_NAME_TITLE));
			Integer typeConta = Integer.parseInt(c.getString(c.getColumnIndex(ContasEntry.COLUMN_NAME_TYPE)));
				
			// Put it in the return ArrayList			
			Contas conta = new Contas(titleConta, typeConta);
			contas.add(conta);
			
			c.moveToNext();
		}
			
		return contas;
	}
	
	// Categorias	///////////////////////////////////////////////////////////////////////
	
	// Create Table
	private static final String SQL_CREATE_TABLE_CATEGORIAS =
		"CREATE TABLE " + CategoriasEntry.TABLE_NAME + 
		" (" +
		CategoriasEntry._ID 					+ TABLEID_TYPE 	+ COMMA_SEP +
		CategoriasEntry.COLUMN_NAME_TITLE 		+ TEXT_TYPE 	+ 
		" )";
	
	// Delete Table
	private static final String SQL_DELETE_TABLE_CATEGORIAS =
		"DROP TABLE IF EXISTS " + CategoriasEntry.TABLE_NAME;
	
	// Store a categoria in the DB	
	public long storeCategorias(Categorias aCategoria) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(CategoriasEntry.COLUMN_NAME_TITLE, aCategoria.getTitle());
		
		long newRowId;
		newRowId = db.insert(CategoriasEntry.TABLE_NAME, null, values);
		
		return newRowId;
	}
	
	// Fetch an arraylist of all categorias
	public ArrayList<Categorias> getCategorias() {
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<Categorias> categorias = new ArrayList<Categorias>();
		
		String[] projection = {
				CategoriasEntry._ID,
				CategoriasEntry.COLUMN_NAME_TITLE,
				};
		
		// Sort by name
		String sortOrder = CategoriasEntry.COLUMN_NAME_TITLE + " DESC";
		
		Cursor c = db.query(
				CategoriasEntry.TABLE_NAME,  			// The table to query
				projection,                             // The columns to return
				null,                             		// The columns for the WHERE clause
				null,                        			// The values for the WHERE clause
				null,                                   // don't group the rows
				null,                                   // don't filter by row groups
				sortOrder                               // The sort order
				);
		
		c.moveToFirst();
		
		while(c.isAfterLast() == false) {
			// Get the index and then get the values
			String nomeCategoria = c.getString(c.getColumnIndex(CategoriasEntry.COLUMN_NAME_TITLE));
			
			// Put it in the return ArrayList
			Categorias categoria = new Categorias(nomeCategoria);
			categorias.add(categoria);
			
			c.moveToNext();
		}
			
		return categorias;
	}
	
	// Lancamentos	///////////////////////////////////////////////////////////////////////
	
	// Create Table
	private static final String SQL_CREATE_TABLE_LANCAMENTOS =
		"CREATE TABLE " + LancamentosEntry.TABLE_NAME + 
		" (" +
		LancamentosEntry._ID 					+ TABLEID_TYPE	+ COMMA_SEP +
		LancamentosEntry.COLUMN_NAME_TITLE 		+ TEXT_TYPE		+ COMMA_SEP +
		LancamentosEntry.COLUMN_NAME_TIMESTAMP	+ INTEGER_TYPE 	+ COMMA_SEP +
		LancamentosEntry.COLUMN_NAME_CONTA 		+ INTEGER_TYPE 	+ COMMA_SEP +      
		LancamentosEntry.COLUMN_NAME_CATEGORIA	+ INTEGER_TYPE 	+
		" )";
	
	// Delete Table
	private static final String SQL_DELETE_TABLE_LANCAMENTOS =
		"DROP TABLE IF EXISTS " + LancamentosEntry.TABLE_NAME;     
	   
	// Store a lancamento in the DB
	public long storeLancamento(Lancamentos aLancamento) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(LancamentosEntry.COLUMN_NAME_TITLE, 		aLancamento.getTitle());
		values.put(LancamentosEntry.COLUMN_NAME_TIMESTAMP, 	aLancamento.getTimestamp());
		values.put(LancamentosEntry.COLUMN_NAME_CONTA, 		aLancamento.getConta());
		values.put(LancamentosEntry.COLUMN_NAME_CATEGORIA, 	aLancamento.getCategoria());
		
		long newRowId;
		newRowId = db.insert(LancamentosEntry.TABLE_NAME, null, values);
		
		return newRowId;
	}
	
	public ArrayList<Lancamentos> getLancamentos() {
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<Lancamentos> lancamentos = new ArrayList<Lancamentos>();
		
		String[] projection = {
				LancamentosEntry._ID,
				LancamentosEntry.COLUMN_NAME_TITLE,
				LancamentosEntry.COLUMN_NAME_TIMESTAMP,
				LancamentosEntry.COLUMN_NAME_CONTA,
				LancamentosEntry.COLUMN_NAME_CATEGORIA
				};
		
		// Sort by timestamp
		String sortOrder = LancamentosEntry.COLUMN_NAME_TIMESTAMP + " DESC";
		
		Cursor c = db.query(
				LancamentosEntry.TABLE_NAME, 			// The table to query
				projection,                             // The columns to return
				null,                             		// The columns for the WHERE clause
				null,                        			// The values for the WHERE clause
				null,                                   // don't group the rows
				null,                                   // don't filter by row groups
				sortOrder                               // The sort order
				);
		
		c.moveToFirst();
		
		while(c.isAfterLast() == false) {
			// Get the indexes and then get the values
			String titleLancamento 		= c.getString(c.getColumnIndex(LancamentosEntry.COLUMN_NAME_TITLE));
			Integer timestampLancamento	= Integer.parseInt(c.getString(c.getColumnIndex(LancamentosEntry.COLUMN_NAME_TIMESTAMP)));
			Integer contaLancamento		= Integer.parseInt(c.getString(c.getColumnIndex(LancamentosEntry.COLUMN_NAME_CONTA)));
			Integer categoriaLancamento	= Integer.parseInt(c.getString(c.getColumnIndex(LancamentosEntry.COLUMN_NAME_CATEGORIA)));
				
			// Put it in the return ArrayList			
			Lancamentos lancamento = new Lancamentos(titleLancamento, timestampLancamento, contaLancamento, categoriaLancamento);
			lancamentos.add(lancamento);
			
			c.moveToNext();
		}
			
		return lancamentos;
	}	
}
