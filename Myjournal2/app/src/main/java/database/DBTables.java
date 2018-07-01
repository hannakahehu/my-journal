package database;

import android.provider.BaseColumns;

public final class DBTables {

    public static class tableJournal implements  BaseColumns {

        public static final String TABLE_NAME = "journals";
        public static final String FIELD_TITLE = "title";
        public static final String FIELD_ID = "id";
        public static final String FIELD_DATE_TIME = "date_time";
        public static final String FIELD_CONTENT = "content";

    }

}
