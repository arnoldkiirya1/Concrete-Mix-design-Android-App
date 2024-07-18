package com.autorun.mixdesign;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.autorun.mixdesign.classes.DesignData;
import com.autorun.mixdesign.classes.Test;
import com.autorun.mixdesign.contracts.DesignDataContract;
import com.autorun.mixdesign.contracts.ProjectsContract;
import com.autorun.mixdesign.contracts.TestContract;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "projects.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_PROJECTS_TABLE = "CREATE TABLE " +
                ProjectsContract.ProjectEntry.TABLE_NAME + " (" +
                ProjectsContract.ProjectEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ProjectsContract.ProjectEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ProjectsContract.ProjectEntry.COLUMN_CHARACTERISTIC_STRENGTH + " TEXT NOT NULL, " +
                ProjectsContract.ProjectEntry.COLUMN_AT_DAYS + " TEXT NOT NULL, " +
                ProjectsContract.ProjectEntry.COLUMN_MIX_DESIGN_STANDARD + " TEXT NOT NULL" +
                ");";

        db.execSQL(SQL_CREATE_PROJECTS_TABLE);
        db.execSQL(TestContract.TestEntry.SQL_CREATE_TABLE);
        db.execSQL(DesignDataContract.DesignDataEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ProjectsContract.ProjectEntry.TABLE_NAME);
        onCreate(db);
    }

    // Method to insert test data into the Test table
    public long insertTestData(long projectId, String waterTestsPH, String sulphate, String phosphate, String nitrate,
                               String tss, String tds, String carbonate, String bicarbonate, String waterAbsorption,
                               String specificGravity, String tenPercentFinesValue, String aggregateCrushingValue,
                               String aggregateImpactValue, String flakinessIndex, String elongationIndex,
                               String bulkDensity, String silkContent, String organicContent,
                               String fineAggregateSpecificGravity, String cementFineness,
                               String settingTimeInitial, String settingTimeFinal, String cementTestsSettingTimeFinalValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TestContract.TestEntry.COLUMN_PROJECT_ID, projectId);
        values.put(TestContract.TestEntry.COLUMN_WATER_TESTS_PH, waterTestsPH);
        values.put(TestContract.TestEntry.COLUMN_SULPHATE, sulphate);
        values.put(TestContract.TestEntry.COLUMN_PHOSPHATE, phosphate);
        values.put(TestContract.TestEntry.COLUMN_NITRATE, nitrate);
        values.put(TestContract.TestEntry.COLUMN_TSS, tss);
        values.put(TestContract.TestEntry.COLUMN_TDS, tds);
        values.put(TestContract.TestEntry.COLUMN_CARBONATE, carbonate);
        values.put(TestContract.TestEntry.COLUMN_BICARBONATE, bicarbonate);
        values.put(TestContract.TestEntry.COLUMN_WATER_ABSORPTION, waterAbsorption);
        values.put(TestContract.TestEntry.COLUMN_SPECIFIC_GRAVITY, specificGravity);
        values.put(TestContract.TestEntry.COLUMN_TEN_PERCENT_FINES_VALUE, tenPercentFinesValue);
        values.put(TestContract.TestEntry.COLUMN_AGGREGATE_CRUSHING_VALUE, aggregateCrushingValue);
        values.put(TestContract.TestEntry.COLUMN_AGGREGATE_IMPACT_VALUE, aggregateImpactValue);
        values.put(TestContract.TestEntry.COLUMN_FLAKINESS_INDEX, flakinessIndex);
        values.put(TestContract.TestEntry.COLUMN_ELONGATION_INDEX, elongationIndex);
        values.put(TestContract.TestEntry.COLUMN_BULK_DENSITY, bulkDensity);
        values.put(TestContract.TestEntry.COLUMN_SILK_CONTENT, silkContent);
        values.put(TestContract.TestEntry.COLUMN_ORGANIC_CONTENT, organicContent);
        values.put(TestContract.TestEntry.COLUMN_FINE_AGGREGATE_SPECIFIC_GRAVITY, fineAggregateSpecificGravity);
        values.put(TestContract.TestEntry.COLUMN_CEMENT_FINENESS, cementFineness);
        values.put(TestContract.TestEntry.COLUMN_SETTING_TIME_INITIAL, settingTimeInitial);
        values.put(TestContract.TestEntry.COLUMN_SETTING_TIME_FINAL, settingTimeFinal);

        // Insert the data into the Test table
        long newRowId = db.insert(TestContract.TestEntry.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public List<Test> getMaterialTests(long projectId) {
        List<Test> materialTests = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                TestContract.TestEntry._ID,
                TestContract.TestEntry.COLUMN_WATER_TESTS_PH,
                TestContract.TestEntry.COLUMN_SULPHATE,
                TestContract.TestEntry.COLUMN_PHOSPHATE,
                TestContract.TestEntry.COLUMN_NITRATE,
                TestContract.TestEntry.COLUMN_TSS,
                TestContract.TestEntry.COLUMN_TDS,
                TestContract.TestEntry.COLUMN_CARBONATE,
                TestContract.TestEntry.COLUMN_BICARBONATE,
                TestContract.TestEntry.COLUMN_WATER_ABSORPTION,
                TestContract.TestEntry.COLUMN_SPECIFIC_GRAVITY,
                TestContract.TestEntry.COLUMN_TEN_PERCENT_FINES_VALUE,
                TestContract.TestEntry.COLUMN_AGGREGATE_CRUSHING_VALUE,
                TestContract.TestEntry.COLUMN_AGGREGATE_IMPACT_VALUE,
                TestContract.TestEntry.COLUMN_FLAKINESS_INDEX,
                TestContract.TestEntry.COLUMN_ELONGATION_INDEX,
                TestContract.TestEntry.COLUMN_BULK_DENSITY,
                TestContract.TestEntry.COLUMN_SILK_CONTENT,
                TestContract.TestEntry.COLUMN_ORGANIC_CONTENT,
                TestContract.TestEntry.COLUMN_FINE_AGGREGATE_SPECIFIC_GRAVITY,
                TestContract.TestEntry.COLUMN_CEMENT_FINENESS,
                TestContract.TestEntry.COLUMN_SETTING_TIME_INITIAL,
                TestContract.TestEntry.COLUMN_SETTING_TIME_FINAL
        };

        String selection = TestContract.TestEntry.COLUMN_PROJECT_ID + " = ?";
        String[] selectionArgs = { String.valueOf(projectId) };

        Cursor cursor = db.query(
                TestContract.TestEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long testId = cursor.getLong(cursor.getColumnIndexOrThrow(TestContract.TestEntry._ID));
                String waterTestsPH = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_WATER_TESTS_PH));
                String sulphate = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_SULPHATE));
                String phosphate = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_PHOSPHATE));
                String nitrate = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_NITRATE));
                String tss = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_TSS));
                String tds = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_TDS));
                String carbonate = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_CARBONATE));
                String bicarbonate = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_BICARBONATE));
                String waterAbsorption = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_WATER_ABSORPTION));
                String specificGravity = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_SPECIFIC_GRAVITY));
                String tenPercentFinesValue = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_TEN_PERCENT_FINES_VALUE));
                String aggregateCrushingValue = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_AGGREGATE_CRUSHING_VALUE));
                String aggregateImpactValue = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_AGGREGATE_IMPACT_VALUE));
                String flakinessIndex = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_FLAKINESS_INDEX));
                String elongationIndex = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_ELONGATION_INDEX));
                String bulkDensity = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_BULK_DENSITY));
                String silkContent = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_SILK_CONTENT));
                String organicContent = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_ORGANIC_CONTENT));
                String fineAggregateSpecificGravity = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_FINE_AGGREGATE_SPECIFIC_GRAVITY));
                String cementFineness = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_CEMENT_FINENESS));
                String settingTimeInitial = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_SETTING_TIME_INITIAL));
                String settingTimeFinal = cursor.getString(cursor.getColumnIndexOrThrow(TestContract.TestEntry.COLUMN_SETTING_TIME_FINAL));

                // Create a Test object and add it to the list
                Test test = new Test(projectId, waterTestsPH, sulphate, phosphate, nitrate, tss, tds, carbonate, bicarbonate, waterAbsorption,
                        specificGravity, tenPercentFinesValue, aggregateCrushingValue, aggregateImpactValue, flakinessIndex, elongationIndex,
                        bulkDensity, silkContent, organicContent, fineAggregateSpecificGravity, cementFineness, settingTimeInitial, settingTimeFinal);
                materialTests.add(test);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return materialTests;
    }



    public long insertDesignData(long projectId,String standardDeviation, String defectiveRate, String compressiveStrength,
                                 String margin, String cementContent, String targetMeanStrength,
                                 String freeWaterCementRatio, String spinnerResultSelection,
                                 String spinnerAggregateTypeCoarse, String spinnerAggregateTypeFine,
                                 String spinnerSlump, String spinnerMaxAgg, String freeWaterContent,
                                 String maxCementContent, String minCementContent,
                                 String relativeDensity, String concreteDensity,
                                 String totalAggregateContent, String gradingFines,
                                 String proportionOfFines, String fineAggregateContent,
                                 String coarseAggregateContent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DesignDataContract.DesignDataEntry.COLUMN_PROJECT_ID, projectId);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_STANDARD_DEVIATION, standardDeviation);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_STANDARD_DEVIATION, standardDeviation);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_DEFECTIVE_RATE, defectiveRate);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_COMPRESSIVE_STRENGTH, compressiveStrength);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_MARGIN, margin);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_CEMENT_CONTENT, cementContent);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_TARGET_MEAN_STRENGTH, targetMeanStrength);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_FREE_WATER_CEMENT_RATIO, freeWaterCementRatio);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_SPINNER_RESULT_SELECTION, spinnerResultSelection);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_SPINNER_AGGREGATE_TYPE_COARSE, spinnerAggregateTypeCoarse);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_SPINNER_AGGREGATE_TYPE_FINE, spinnerAggregateTypeFine);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_SPINNER_SLUMP, spinnerSlump);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_SPINNER_MAX_AGG, spinnerMaxAgg);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_FREE_WATER_CONTENT, freeWaterContent);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_MAX_CEMENT_CONTENT, maxCementContent);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_MIN_CEMENT_CONTENT, minCementContent);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_RELATIVE_DENSITY, relativeDensity);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_CONCRETE_DENSITY, concreteDensity);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_TOTAL_AGGREGATE_CONTENT, totalAggregateContent);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_GRADING_FINES, gradingFines);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_PROPORTION_OF_FINES, proportionOfFines);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_FINE_AGGREGATE_CONTENT, fineAggregateContent);
        values.put(DesignDataContract.DesignDataEntry.COLUMN_COURSE_AGGREGATE_CONTENT, coarseAggregateContent);

        // Insert the data into the Design Data table
        long newRowId = db.insert(DesignDataContract.DesignDataEntry.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    @SuppressLint("Range")
    public List<DesignData> getResultData(long projectId) {
        List<DesignData> designResults = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        // Define a projection that specifies the columns from the table you care about
        String[] projection = {

                DesignDataContract.DesignDataEntry.COLUMN_CEMENT_CONTENT,
                DesignDataContract.DesignDataEntry.COLUMN_FREE_WATER_CONTENT,
                DesignDataContract.DesignDataEntry.COLUMN_FINE_AGGREGATE_CONTENT,
                DesignDataContract.DesignDataEntry.COLUMN_COURSE_AGGREGATE_CONTENT,
                DesignDataContract.DesignDataEntry.COLUMN_TOTAL_AGGREGATE_CONTENT
        };

        // Filter results WHERE "project_id" = projectId
        String selection = DesignDataContract.DesignDataEntry.COLUMN_PROJECT_ID + " = ?";
        String[] selectionArgs = { String.valueOf(projectId) };

        cursor = db.query(
                DesignDataContract.DesignDataEntry.TABLE_NAME,  // The table to query
                projection,                                     // The array of columns to return
                selection,                                      // The columns for the WHERE clause
                selectionArgs,                                  // The values for the WHERE clause
                null,                                           // don't group the rows
                null,                                           // don't filter by row groups
                null                                            // don't order the rows
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                DesignData designData = new DesignData();
                designData.setFineAggregateContent(cursor.getString(cursor.getColumnIndex(DesignDataContract.DesignDataEntry.COLUMN_FINE_AGGREGATE_CONTENT)));
                designData.setCourseAggregateContent(cursor.getString(cursor.getColumnIndex(DesignDataContract.DesignDataEntry.COLUMN_COURSE_AGGREGATE_CONTENT)));
                designData.setCementContent(cursor.getString(cursor.getColumnIndex(DesignDataContract.DesignDataEntry.COLUMN_CEMENT_CONTENT)));
                designData.setFreeWaterContent(cursor.getString(cursor.getColumnIndex(DesignDataContract.DesignDataEntry.COLUMN_FREE_WATER_CONTENT)));
                designData.setFreeWaterContent(cursor.getString(cursor.getColumnIndex(DesignDataContract.DesignDataEntry.COLUMN_TOTAL_AGGREGATE_CONTENT)));
                designResults.add(designData);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return designResults;
    }

    public List<DesignData> getDesignData(long projectId) {
        List<DesignData> designTests = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Define a projection that specifies the columns from the table you care about
            String[] projection = {
                    DesignDataContract.DesignDataEntry.COLUMN_STANDARD_DEVIATION,
                    DesignDataContract.DesignDataEntry.COLUMN_DEFECTIVE_RATE,
                    DesignDataContract.DesignDataEntry.COLUMN_COMPRESSIVE_STRENGTH,
                    DesignDataContract.DesignDataEntry.COLUMN_MARGIN,
                    DesignDataContract.DesignDataEntry.COLUMN_CEMENT_CONTENT,
                    DesignDataContract.DesignDataEntry.COLUMN_TARGET_MEAN_STRENGTH,
                    DesignDataContract.DesignDataEntry.COLUMN_FREE_WATER_CEMENT_RATIO,
                    DesignDataContract.DesignDataEntry.COLUMN_SPINNER_RESULT_SELECTION,
                    DesignDataContract.DesignDataEntry.COLUMN_SPINNER_AGGREGATE_TYPE_COARSE,
                    DesignDataContract.DesignDataEntry.COLUMN_SPINNER_AGGREGATE_TYPE_FINE,
                    DesignDataContract.DesignDataEntry.COLUMN_SPINNER_SLUMP,
                    DesignDataContract.DesignDataEntry.COLUMN_SPINNER_MAX_AGG,
                    DesignDataContract.DesignDataEntry.COLUMN_FREE_WATER_CONTENT,
                    DesignDataContract.DesignDataEntry.COLUMN_MAX_CEMENT_CONTENT,
                    DesignDataContract.DesignDataEntry.COLUMN_MIN_CEMENT_CONTENT,
                    DesignDataContract.DesignDataEntry.COLUMN_RELATIVE_DENSITY,
                    DesignDataContract.DesignDataEntry.COLUMN_CONCRETE_DENSITY,
                    DesignDataContract.DesignDataEntry.COLUMN_TOTAL_AGGREGATE_CONTENT,
                    DesignDataContract.DesignDataEntry.COLUMN_GRADING_FINES,
                    DesignDataContract.DesignDataEntry.COLUMN_PROPORTION_OF_FINES,
                    DesignDataContract.DesignDataEntry.COLUMN_FINE_AGGREGATE_CONTENT,
                    DesignDataContract.DesignDataEntry.COLUMN_COURSE_AGGREGATE_CONTENT
            };

            // Filter results WHERE "project_id" = projectId
            String selection = DesignDataContract.DesignDataEntry.COLUMN_PROJECT_ID + " = ?";
            String[] selectionArgs = { String.valueOf(projectId) };

            // Query the database
            cursor = db.query(
                    DesignDataContract.DesignDataEntry.TABLE_NAME,  // The table to query
                    projection,                                     // The array of columns to return
                    selection,                                      // The columns for the WHERE clause
                    selectionArgs,                                  // The values for the WHERE clause
                    null,                                           // don't group the rows
                    null,                                           // don't filter by row groups
                    null                                            // don't order the rows
            );

            // Iterate through the cursor and create Test objects
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // Extract values from cursor

                    String standardDeviation = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_STANDARD_DEVIATION));
                    String defectiveRate = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_DEFECTIVE_RATE));
                    String compressiveStrength = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_COMPRESSIVE_STRENGTH));
                    String margin = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_MARGIN));
                    String cementContent = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_CEMENT_CONTENT));
                    String targetMeanStrength = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_TARGET_MEAN_STRENGTH));
                    String freeWaterCementRatio = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_FREE_WATER_CEMENT_RATIO));
                    String spinnerResultSelection = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_SPINNER_RESULT_SELECTION));
                    String spinnerAggregateTypeCoarse = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_SPINNER_AGGREGATE_TYPE_COARSE));
                    String spinnerAggregateTypeFine = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_SPINNER_AGGREGATE_TYPE_FINE));
                    String spinnerSlump = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_SPINNER_SLUMP));
                    String spinnerMaxAgg = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_SPINNER_MAX_AGG));
                    String freeWaterContent = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_FREE_WATER_CONTENT));
                    String maxCementContent = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_MAX_CEMENT_CONTENT));
                    String minCementContent = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_MIN_CEMENT_CONTENT));
                    String relativeDensity = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_RELATIVE_DENSITY));
                    String concreteDensity = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_CONCRETE_DENSITY));
                    String totalAggregateContent = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_TOTAL_AGGREGATE_CONTENT));
                    String gradingFines = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_GRADING_FINES));
                    String proportionOfFines = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_PROPORTION_OF_FINES));
                    String fineAggregateContent = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_FINE_AGGREGATE_CONTENT));
                    String courseAggregateContent = cursor.getString(cursor.getColumnIndexOrThrow(DesignDataContract.DesignDataEntry.COLUMN_COURSE_AGGREGATE_CONTENT));

                    // Create a Test object and add it to the list
                    DesignData design = new DesignData();
                    design.setStandardDeviation(standardDeviation);
                    design.setDefectiveRate(defectiveRate);
                    design.setCompressiveStrength(compressiveStrength);
                    design.setMargin(margin);
                    design.setCementContent(cementContent);
                    design.setTargetMeanStrength(targetMeanStrength);
                    design.setFreeWaterCementRatio(freeWaterCementRatio);
                    design.setSpinnerResultSelection(spinnerResultSelection);
                    design.setSpinnerAggregateTypeCoarse(spinnerAggregateTypeCoarse);
                    design.setSpinnerAggregateTypeFine(spinnerAggregateTypeFine);
                    design.setSpinnerSlump(spinnerSlump);
                    design.setSpinnerMaxAgg(spinnerMaxAgg);
                    design.setFreeWaterContent(freeWaterContent);
                    design.setMaxCementContent(maxCementContent);
                    design.setMinCementContent(minCementContent);
                    design.setRelativeDensity(relativeDensity);
                    design.setConcreteDensity(concreteDensity);
                    design.setTotalAggregateContent(totalAggregateContent);
                    design.setGradingFines(gradingFines);
                    design.setProportionOfFines(proportionOfFines);
                    design.setFineAggregateContent(fineAggregateContent);
                    design.setCourseAggregateContent(courseAggregateContent);

                    designTests.add(design);
                } while (cursor.moveToNext());
            }

            return designTests;

        } finally {
            // Make sure to close the cursor when done
            if (cursor != null) {
                cursor.close();
            }
        }

    }
}