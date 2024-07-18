package com.autorun.mixdesign.contracts;

import android.provider.BaseColumns;

public class ProjectsContract {
    private ProjectsContract() {
    }

    public static class ProjectEntry implements BaseColumns {

        public static final String TABLE_NAME = "projects";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CHARACTERISTIC_STRENGTH = "characteristic_strength";
        public static final String COLUMN_AT_DAYS = "at_days";
        public static final String COLUMN_MIX_DESIGN_STANDARD = "mix_design_standard";
    }
}