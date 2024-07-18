package com.autorun.mixdesign.contracts;

import android.provider.BaseColumns;

public class TestContract {
    private TestContract() {}

    public static class TestEntry implements BaseColumns {
        public static final String TABLE_NAME = "test";
        public static final String COLUMN_PROJECT_ID = "project_id";
        public static final String COLUMN_WATER_TESTS_PH = "water_tests_ph";
        public static final String COLUMN_SULPHATE = "sulphate";
        public static final String COLUMN_PHOSPHATE = "phosphate";
        public static final String COLUMN_NITRATE = "nitrate";
        public static final String COLUMN_TSS = "tss";
        public static final String COLUMN_TDS = "tds";
        public static final String COLUMN_CARBONATE = "carbonate";
        public static final String COLUMN_BICARBONATE = "bicarbonate";
        public static final String COLUMN_WATER_ABSORPTION = "water_absorption";
        public static final String COLUMN_SPECIFIC_GRAVITY = "specific_gravity";
        public static final String COLUMN_TEN_PERCENT_FINES_VALUE = "ten_percent_fines_value";
        public static final String COLUMN_AGGREGATE_CRUSHING_VALUE = "aggregate_crushing_value";
        public static final String COLUMN_AGGREGATE_IMPACT_VALUE = "aggregate_impact_value";
        public static final String COLUMN_FLAKINESS_INDEX = "flakiness_index";
        public static final String COLUMN_ELONGATION_INDEX = "elongation_index";
        public static final String COLUMN_BULK_DENSITY = "bulk_density";
        public static final String COLUMN_SILK_CONTENT = "silk_content";
        public static final String COLUMN_ORGANIC_CONTENT = "organic_content";
        public static final String COLUMN_FINE_AGGREGATE_SPECIFIC_GRAVITY = "fine_aggregate_specific_gravity";
        public static final String COLUMN_CEMENT_FINENESS = "cement_fineness";
        public static final String COLUMN_SETTING_TIME_INITIAL = "setting_time_initial";
        public static final String COLUMN_SETTING_TIME_FINAL = "setting_time_final";

        // SQL query to create the table
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_PROJECT_ID + " INTEGER," +
                        COLUMN_WATER_TESTS_PH + " TEXT," +
                        COLUMN_SULPHATE + " TEXT," +
                        COLUMN_PHOSPHATE + " TEXT," +
                        COLUMN_NITRATE + " TEXT," +
                        COLUMN_TSS + " TEXT," +
                        COLUMN_TDS + " TEXT," +
                        COLUMN_CARBONATE + " TEXT," +
                        COLUMN_BICARBONATE + " TEXT," +
                        COLUMN_WATER_ABSORPTION + " TEXT," +
                        COLUMN_SPECIFIC_GRAVITY + " TEXT," +
                        COLUMN_TEN_PERCENT_FINES_VALUE + " TEXT," +
                        COLUMN_AGGREGATE_CRUSHING_VALUE + " TEXT," +
                        COLUMN_AGGREGATE_IMPACT_VALUE + " TEXT," +
                        COLUMN_FLAKINESS_INDEX + " TEXT," +
                        COLUMN_ELONGATION_INDEX + " TEXT," +
                        COLUMN_BULK_DENSITY + " TEXT," +
                        COLUMN_SILK_CONTENT + " TEXT," +
                        COLUMN_ORGANIC_CONTENT + " TEXT," +
                        COLUMN_FINE_AGGREGATE_SPECIFIC_GRAVITY + " TEXT," +
                        COLUMN_CEMENT_FINENESS + " TEXT," +
                        COLUMN_SETTING_TIME_INITIAL + " TEXT," +
                        COLUMN_SETTING_TIME_FINAL + " TEXT," +
                        "FOREIGN KEY (" + COLUMN_PROJECT_ID + ") REFERENCES " +
                        TestContract.TestEntry.TABLE_NAME + "(" + TestContract.TestEntry._ID + "))";
    }
}
