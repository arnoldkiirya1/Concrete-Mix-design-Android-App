package com.autorun.mixdesign.contracts;


import android.provider.BaseColumns;

public class DesignDataContract {

    private DesignDataContract() {}

    public static class DesignDataEntry implements BaseColumns {
        public static final String TABLE_NAME = "design_data";
        public static final String COLUMN_PROJECT_ID = "project_id";
        public static final String COLUMN_STANDARD_DEVIATION = "standard_deviation";
        public static final String COLUMN_DEFECTIVE_RATE = "defective_rate";
        public static final String COLUMN_COMPRESSIVE_STRENGTH = "compressive_strength";
        public static final String COLUMN_MARGIN = "margin";
        public static final String COLUMN_CEMENT_CONTENT = "cement_content";
        public static final String COLUMN_TARGET_MEAN_STRENGTH = "target_mean_strength";
        public static final String COLUMN_FREE_WATER_CEMENT_RATIO = "free_water_cement_ratio";
        public static final String COLUMN_SPINNER_RESULT_SELECTION = "spinner_result_selection";
        public static final String COLUMN_SPINNER_AGGREGATE_TYPE_COARSE = "spinner_aggregate_type_coarse";
        public static final String COLUMN_SPINNER_AGGREGATE_TYPE_FINE = "spinner_aggregate_type_fine";
        public static final String COLUMN_SPINNER_SLUMP = "spinner_slump";
        public static final String COLUMN_SPINNER_MAX_AGG = "spinner_max_agg";
        public static final String COLUMN_FREE_WATER_CONTENT = "free_water_content";
        public static final String COLUMN_MAX_CEMENT_CONTENT = "max_cement_content";
        public static final String COLUMN_MIN_CEMENT_CONTENT = "min_cement_content";
        public static final String COLUMN_RELATIVE_DENSITY = "relative_density";
        public static final String COLUMN_CONCRETE_DENSITY = "concrete_density";
        public static final String COLUMN_TOTAL_AGGREGATE_CONTENT = "total_aggregate_content";
        public static final String COLUMN_GRADING_FINES = "grading_fines";
        public static final String COLUMN_PROPORTION_OF_FINES = "proportion_of_fines";
        public static final String COLUMN_FINE_AGGREGATE_CONTENT = "fine_aggregate_content";
        public static final String COLUMN_COURSE_AGGREGATE_CONTENT = "course_aggregate_content";


        // SQL query to create the table
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + DesignDataEntry.TABLE_NAME + " (" +
                        DesignDataEntry._ID + " INTEGER PRIMARY KEY," +
                        DesignDataEntry.COLUMN_PROJECT_ID + " INTEGER," +
                        DesignDataEntry.COLUMN_STANDARD_DEVIATION + " REAL," +
                        DesignDataEntry.COLUMN_DEFECTIVE_RATE + " REAL," +
                        DesignDataEntry.COLUMN_COMPRESSIVE_STRENGTH + " REAL," +
                        DesignDataEntry.COLUMN_MARGIN + " REAL," +
                        DesignDataEntry.COLUMN_CEMENT_CONTENT + " REAL," +
                        DesignDataEntry.COLUMN_TARGET_MEAN_STRENGTH + " REAL," +
                        DesignDataEntry.COLUMN_FREE_WATER_CEMENT_RATIO + " REAL," +
                        DesignDataEntry.COLUMN_SPINNER_RESULT_SELECTION + " TEXT," +
                        DesignDataEntry.COLUMN_SPINNER_AGGREGATE_TYPE_COARSE + " TEXT," +
                        DesignDataEntry.COLUMN_SPINNER_AGGREGATE_TYPE_FINE + " TEXT," +
                        DesignDataEntry.COLUMN_SPINNER_SLUMP + " INTEGER," +
                        DesignDataEntry.COLUMN_SPINNER_MAX_AGG + " INTEGER," +
                        DesignDataEntry.COLUMN_FREE_WATER_CONTENT + " REAL," +
                        DesignDataEntry.COLUMN_MAX_CEMENT_CONTENT + " REAL," +
                        DesignDataEntry.COLUMN_MIN_CEMENT_CONTENT + " REAL," +
                        DesignDataEntry.COLUMN_RELATIVE_DENSITY + " REAL," +
                        DesignDataEntry.COLUMN_CONCRETE_DENSITY + " REAL," +
                        DesignDataEntry.COLUMN_TOTAL_AGGREGATE_CONTENT + " REAL," +
                        DesignDataEntry.COLUMN_GRADING_FINES + " REAL," +
                        DesignDataEntry.COLUMN_PROPORTION_OF_FINES + " REAL," +
                        DesignDataEntry.COLUMN_FINE_AGGREGATE_CONTENT + " REAL," +
                        DesignDataEntry.COLUMN_COURSE_AGGREGATE_CONTENT + " REAL)";
    }


}
