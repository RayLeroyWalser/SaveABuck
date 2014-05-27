package com.example.saveabuck.db.contracts;

import android.provider.BaseColumns;

public class ControllerContract {
	
	// Empty constructor, can't instantiate
	public ControllerContract() {}
	
	public static abstract class ContasEntry implements BaseColumns {
        public static final String TABLE_NAME 			= "contas";
        public static final String COLUMN_NAME_TITLE 	= "contas_title";
        public static final String COLUMN_NAME_TYPE 	= "contas_type";  

	}
	
	public static abstract class CategoriasEntry implements BaseColumns {
        public static final String TABLE_NAME 			= "categorias";
        public static final String COLUMN_NAME_TITLE 	= "categorias_title";
    }
	
	public static abstract class LancamentosEntry implements BaseColumns {
        public static final String TABLE_NAME 			= "lancamentos";
        public static final String COLUMN_NAME_TITLE 	= "lancamentos_title";
        public static final String COLUMN_NAME_TIMESTAMP= "lancamentos_timestamp";
        public static final String COLUMN_NAME_CONTA	= "lancamentos_conta";
        public static final String COLUMN_NAME_CATEGORIA= "lancamentos_categoria";              
    }
	
	
}
