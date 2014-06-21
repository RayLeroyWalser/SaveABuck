package com.caux.saveabuck.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.caux.saveabuck.db.contracts.SaveABuckContract.EnvelopeEntry;
import com.caux.saveabuck.db.contracts.SaveABuckContract.GroupEntry;
import com.caux.saveabuck.db.contracts.SaveABuckContract.TransactionEntry;
import com.caux.saveabuck.model.Envelope;
import com.caux.saveabuck.model.Group;
import com.caux.saveabuck.model.Transaction;

public class SaveABuckData extends SQLiteOpenHelper {
	// If you change the database schema, you must increment the database version.
	public static final int DATABASE_VERSION = 1;
	
	public static final String DATABASE_NAME 	= "SaveABuck.db";
	private static final String TEXT_TYPE 		= " TEXT";
	private static final String INTEGER_TYPE 	= " INTEGER";
	private static final String REAL_TYPE 		= " REAL";
	private static final String TABLEID_TYPE 	= " INTEGER PRIMARY KEY";
	
	
	private static final String COMMA_SEP = ",";
	
	public SaveABuckData(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    
	}
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_TABLE_ENVELOPES);
		db.execSQL(SQL_CREATE_TABLE_GROUPS);
		db.execSQL(SQL_CREATE_TABLE_LANCAMENTOS);
	}
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This database is only a cache for online data, so its upgrade policy is
		// to simply to discard the data and start over
		db.execSQL(SQL_DELETE_TABLE_ENVELOPES);
		db.execSQL(SQL_DELETE_TABLE_GROUPS);
		db.execSQL(SQL_DELETE_TABLE_LANCAMENTOS);        
		onCreate(db);
	}
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
	
	// Envelopes		///////////////////////////////////////////////////////////////////////
	
	// Create Table
	private static final String SQL_CREATE_TABLE_ENVELOPES =
		"CREATE TABLE " + EnvelopeEntry.TABLE_NAME + 
		" (" +
		EnvelopeEntry._ID 					+ TABLEID_TYPE 	+ COMMA_SEP +
		EnvelopeEntry.COLUMN_NAME_TITLE 	+ TEXT_TYPE 	+ COMMA_SEP +
		EnvelopeEntry.COLUMN_NAME_RESETVAL	+ REAL_TYPE 	+
		" )";

	// Delete Table
	private static final String SQL_DELETE_TABLE_ENVELOPES =
		"DROP TABLE IF EXISTS " + EnvelopeEntry.TABLE_NAME;    
		
	// Store a conta in the DB
	public Long storeConta(Envelope aEnvelope) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(EnvelopeEntry.COLUMN_NAME_TITLE,		aEnvelope.getTitle());
		values.put(EnvelopeEntry.COLUMN_NAME_RESETVAL,	aEnvelope.getResetToValue());
		
		Long newRowId;
		newRowId = db.insert(EnvelopeEntry.TABLE_NAME, null, values);
		
		return newRowId;
	}
	
	public ArrayList<Envelope> getEnvelopes() {
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<Envelope> Envelopes = new ArrayList<Envelope>();
		
		String[] projection = {
				EnvelopeEntry._ID,
				EnvelopeEntry.COLUMN_NAME_TITLE,
				EnvelopeEntry.COLUMN_NAME_RESETVAL
				};
		
		// Sort by name
		String sortOrder = EnvelopeEntry.COLUMN_NAME_TITLE + " DESC";
		
		Cursor c = db.query(
				EnvelopeEntry.TABLE_NAME,  				// The table to query
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
			Integer id			= Integer.parseInt(c.getString(c.getColumnIndex(EnvelopeEntry._ID)));		
			String titleConta 	= c.getString(c.getColumnIndex(EnvelopeEntry.COLUMN_NAME_TITLE));
			Double valueToReset = Double.parseDouble(c.getString(c.getColumnIndex(EnvelopeEntry.COLUMN_NAME_RESETVAL)));
				
			// Put it in the return ArrayList			
			Envelope conta = new Envelope(id, titleConta, valueToReset);
			Envelopes.add(conta);
			
			c.moveToNext();
		}
			
		return Envelopes;
	}
	
	// Group	///////////////////////////////////////////////////////////////////////
	
	// Create Table
	private static final String SQL_CREATE_TABLE_GROUPS =
		"CREATE TABLE " + GroupEntry.TABLE_NAME + 
		" (" +
		GroupEntry._ID 					+ TABLEID_TYPE 	+ COMMA_SEP +
		GroupEntry.COLUMN_NAME_TITLE 	+ TEXT_TYPE 	+ COMMA_SEP +
		GroupEntry.COLUMN_NAME_ICON 	+ INTEGER_TYPE 	+ 		
		" )";
	
	// Delete Table
	private static final String SQL_DELETE_TABLE_GROUPS =
		"DROP TABLE IF EXISTS " + GroupEntry.TABLE_NAME;
	
	// Store a group in the DB	
	public Long storeGroup(Group aGroup) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(GroupEntry.COLUMN_NAME_TITLE, aGroup.getTitle());
		values.put(GroupEntry.COLUMN_NAME_ICON, aGroup.getIcon());
		
		Long newRowId;
		newRowId = db.insert(GroupEntry.TABLE_NAME, null, values);
		
		return newRowId;
	}
	
	// Fetch a group
	public Group getGroup(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		String[] projection = {
				GroupEntry._ID,
				GroupEntry.COLUMN_NAME_TITLE,
				GroupEntry.COLUMN_NAME_ICON
				};
		
		// Sort by timestamp
		String sortOrder 	= GroupEntry.COLUMN_NAME_TITLE + " DESC";
		String whereClause 	= GroupEntry._ID + " = ?";
		String[] whereArgs	= new String[] {id.toString()};
		
		Cursor c = db.query(
				GroupEntry.TABLE_NAME, 			// The table to query
				projection,                             // The columns to return
				whereClause,                       		// The columns for the WHERE clause
				whereArgs,                    			// The values for the WHERE clause
				null,                                   // don't group the rows
				null,                                   // don't filter by row groups
				sortOrder
				);
				
		c.moveToFirst();
	
		// Get the index and then get the values
		Integer groupId				= Integer.parseInt(c.getString(c.getColumnIndex(GroupEntry._ID)));
		String nomeGroup	= c.getString(c.getColumnIndex(GroupEntry.COLUMN_NAME_TITLE));
		Integer iconGroup	= Integer.parseInt(c.getString(c.getColumnIndex(GroupEntry.COLUMN_NAME_ICON)));
		
		// Put it in the return ArrayList
		Group group = new Group(groupId, nomeGroup, iconGroup);

			
		return group;
	}
	
	// Fetch an arraylist of all group
	public ArrayList<Group> getGroups() {
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<Group> groups = new ArrayList<Group>();
		
		String[] projection = {
				GroupEntry._ID,
				GroupEntry.COLUMN_NAME_TITLE,
				GroupEntry.COLUMN_NAME_ICON
				};
		
		// Sort by name
		String sortOrder = GroupEntry.COLUMN_NAME_TITLE + " DESC";
		
		Cursor c = db.query(
				GroupEntry.TABLE_NAME,  			// The table to query
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
			Integer id			= Integer.parseInt(c.getString(c.getColumnIndex(GroupEntry._ID)));
			String nomeGroup	= c.getString(c.getColumnIndex(GroupEntry.COLUMN_NAME_TITLE));
			Integer iconGroup	= Integer.parseInt(c.getString(c.getColumnIndex(GroupEntry.COLUMN_NAME_ICON)));
			
			// Put it in the return ArrayList
			Group newGroup = new Group(id, nomeGroup, iconGroup);
			groups.add(newGroup);
			
			c.moveToNext();
		}
			
		return groups;
	}
	
	// Transactions	///////////////////////////////////////////////////////////////////////
	
	// Create Table
	private static final String SQL_CREATE_TABLE_LANCAMENTOS =
		"CREATE TABLE " + TransactionEntry.TABLE_NAME + 
		" (" +
		TransactionEntry._ID 					+ TABLEID_TYPE	+ COMMA_SEP +
		TransactionEntry.COLUMN_NAME_GROUP		+ INTEGER_TYPE 	+ COMMA_SEP +
		TransactionEntry.COLUMN_NAME_ENVELOPE 	+ INTEGER_TYPE 	+ COMMA_SEP +      
		TransactionEntry.COLUMN_NAME_TIMESTAMP	+ INTEGER_TYPE 	+ COMMA_SEP +
		TransactionEntry.COLUMN_NAME_VALUE		+ REAL_TYPE 	+
		" )";
	
	// Delete Table
	private static final String SQL_DELETE_TABLE_LANCAMENTOS =
		"DROP TABLE IF EXISTS " + TransactionEntry.TABLE_NAME;     
	   
	// Store a transaction in the DB
	public Long storeTransaction(Transaction aTransaction) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(TransactionEntry.COLUMN_NAME_GROUP, 		aTransaction.getGroup());
		values.put(TransactionEntry.COLUMN_NAME_TIMESTAMP, 	aTransaction.getTimestamp());
		values.put(TransactionEntry.COLUMN_NAME_ENVELOPE, 	aTransaction.getEnvelope());
		values.put(TransactionEntry.COLUMN_NAME_VALUE, 		aTransaction.getValue());
		
		Long newRowId;
		newRowId = db.insert(TransactionEntry.TABLE_NAME, null, values);
		
		return newRowId;
	}
	
	public Transaction getTransaction(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		String[] projection = {
				TransactionEntry._ID,
				TransactionEntry.COLUMN_NAME_GROUP,
				TransactionEntry.COLUMN_NAME_TIMESTAMP,
				TransactionEntry.COLUMN_NAME_ENVELOPE,
				TransactionEntry.COLUMN_NAME_VALUE
				};
		
		// Sort by timestamp
		String sortOrder 	= TransactionEntry.COLUMN_NAME_TIMESTAMP + " DESC";
		String whereClause 	= TransactionEntry._ID + " = ?";
		String[] whereArgs	= new String[] {id.toString()};
		
		Cursor c = db.query(
				TransactionEntry.TABLE_NAME, 			// The table to query
				projection,                             // The columns to return
				whereClause,                       		// The columns for the WHERE clause
				whereArgs,                    			// The values for the WHERE clause
				null,                                   // don't group the rows
				null,                                   // don't filter by row groups
				sortOrder
				);
				
		c.moveToFirst();
		

		// Get the indexes and then get the values
		Integer transactionId					= Integer.parseInt(c.getString(c.getColumnIndex(TransactionEntry._ID)));
		Integer groupTransaction 	= Integer.parseInt(c.getString(c.getColumnIndex(TransactionEntry.COLUMN_NAME_GROUP)));
		Integer envelopeTransaction	= Integer.parseInt(c.getString(c.getColumnIndex(TransactionEntry.COLUMN_NAME_ENVELOPE)));
		Long timestampTransaction	= Long.parseLong(c.getString(c.getColumnIndex(TransactionEntry.COLUMN_NAME_TIMESTAMP)));
		Double valueTransaction		= Double.parseDouble(c.getString(c.getColumnIndex(TransactionEntry.COLUMN_NAME_VALUE)));
			
		// Put it in the return ArrayList			
		Transaction transaction = new Transaction(transactionId, groupTransaction,  envelopeTransaction, timestampTransaction, valueTransaction);

			
		return transaction;		
	}
	
	public ArrayList<Transaction> getTransactions() {
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		String[] projection = {
				TransactionEntry._ID,
				TransactionEntry.COLUMN_NAME_GROUP,
				TransactionEntry.COLUMN_NAME_TIMESTAMP,
				TransactionEntry.COLUMN_NAME_ENVELOPE,
				TransactionEntry.COLUMN_NAME_VALUE
				};
		
		// Sort by timestamp
		String sortOrder = TransactionEntry.COLUMN_NAME_TIMESTAMP + " DESC";
		
		Cursor c = db.query(
				TransactionEntry.TABLE_NAME, 			// The table to query
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
			Integer id					= Integer.parseInt(c.getString(c.getColumnIndex(TransactionEntry._ID)));
			Integer groupTransaction 	= Integer.parseInt(c.getString(c.getColumnIndex(TransactionEntry.COLUMN_NAME_GROUP)));
			Integer envelopeTransaction	= Integer.parseInt(c.getString(c.getColumnIndex(TransactionEntry.COLUMN_NAME_ENVELOPE)));
			Long timestampTransaction	= Long.parseLong(c.getString(c.getColumnIndex(TransactionEntry.COLUMN_NAME_TIMESTAMP)));
			Double valueTransaction		= Double.parseDouble(c.getString(c.getColumnIndex(TransactionEntry.COLUMN_NAME_VALUE)));
				
			// Put it in the return ArrayList			
			Transaction transaction = new Transaction(id, groupTransaction,  envelopeTransaction, timestampTransaction, valueTransaction);
			transactions.add(transaction);
			
			c.moveToNext();
		}
			
		return transactions;
	}	
}
