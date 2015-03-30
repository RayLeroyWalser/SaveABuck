package com.caux.saveabuck.db.contracts;

import android.provider.BaseColumns;

public class SaveABuckContract {
	
	// Empty constructor, can't instantiate
	public SaveABuckContract() {}
	
	public static abstract class EnvelopeEntry implements BaseColumns {
        public static final String TABLE_NAME 			= "envelopes";
        public static final String COLUMN_NAME_TITLE 	= "envelopes_title";
        public static final String COLUMN_NAME_RESETVAL	= "envelopes_resetToValue";
 	}
	
	public static abstract class GroupEntry implements BaseColumns {
        public static final String TABLE_NAME 			= "groups";
        public static final String COLUMN_NAME_TITLE 	= "groups_title";
        public static final String COLUMN_NAME_ICON 	= "groups_icon";
    }
	
	public static abstract class TransactionEntry implements BaseColumns {
        public static final String TABLE_NAME 			= "transactions";
        public static final String COLUMN_NAME_GROUP	= "transactions_group";
        public static final String COLUMN_NAME_ENVELOPE	= "transactions_envelope";
        public static final String COLUMN_NAME_TIMESTAMP= "transactions_timestamp";
        public static final String COLUMN_NAME_VALUE	= "transactions_value";
    }	
}
