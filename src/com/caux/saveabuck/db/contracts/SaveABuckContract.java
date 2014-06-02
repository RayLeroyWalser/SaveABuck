package com.caux.saveabuck.db.contracts;

import android.provider.BaseColumns;

public class SaveABuckContract {
	
	// Empty constructor, can't instantiate
	public SaveABuckContract() {}
	
	public static abstract class EnvelopeEntry implements BaseColumns {
        public static final String TABLE_NAME 			= "envelope";
        public static final String COLUMN_NAME_TITLE 	= "envelope_title";
        public static final String COLUMN_NAME_RESETVAL	= "envelope_resetToValue";
 	}
	
	public static abstract class GroupEntry implements BaseColumns {
        public static final String TABLE_NAME 			= "group";
        public static final String COLUMN_NAME_TITLE 	= "group_title";
        public static final String COLUMN_NAME_ICON 	= "group_icon";
    }
	
	public static abstract class TransactionEntry implements BaseColumns {
        public static final String TABLE_NAME 			= "transaction";
        public static final String COLUMN_NAME_GROUP	= "transaction_group";
        public static final String COLUMN_NAME_ENVELOPE	= "transaction_envelope";
        public static final String COLUMN_NAME_TIMESTAMP= "transaction_timestamp";
        public static final String COLUMN_NAME_VALUE	= "transaction_value";
    }	
}
