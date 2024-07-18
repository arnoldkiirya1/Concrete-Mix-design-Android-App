package com.autorun.mixdesign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTestsActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    DBHelper dbHelper;

    private EditText waterTestsPH, waterTestsSulphate, waterTestsPhosphate, waterTestsNitrate, waterTestsTSS,
            waterTestsTDS, waterTestsCarbonate, waterTestsBicarbonate,
            aggregateTestsWaterAbsorption, aggregateTestsSpecificGravity, aggregateTestsTenPercentFinesValue,
            aggregateTestsAggregateCrushingValue, aggregateTestsAggregateImpactValue, aggregateTestsFlakinessIndex,
            aggregateTestsElongationIndex, aggregateTestsBulkDensity,
            fineAggregateTestsSilkContent, fineAggregateTestsOrganicContent, fineAggregateTestsBulkDensity,
            fineAggregateTestsSpecificGravity,
            cementTestsCementFineness, cementTestsSettingTimeInitial, cementTestsSettingTimeFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tests);

        // Initialize DBHelper object
        dbHelper = new DBHelper(this);

        waterTestsPH = findViewById(R.id.waterTestsPH);
        waterTestsSulphate = findViewById(R.id.waterTestsSulphate);
        waterTestsPhosphate = findViewById(R.id.waterTestsPhosphate);
        waterTestsNitrate = findViewById(R.id.waterTestsNitrate);
        waterTestsTSS = findViewById(R.id.waterTestsTSS);
        waterTestsTDS = findViewById(R.id.waterTestsTDS);
        waterTestsCarbonate = findViewById(R.id.waterTestsCarbonate);
        waterTestsBicarbonate = findViewById(R.id.waterTestsBicarbonate);

        aggregateTestsWaterAbsorption = findViewById(R.id.aggregateTestsWaterAbsorption);
        aggregateTestsSpecificGravity = findViewById(R.id.aggregateTestsSpecificGravity);
        aggregateTestsTenPercentFinesValue = findViewById(R.id.aggregateTestsTenPercentFinesValue);
        aggregateTestsAggregateCrushingValue = findViewById(R.id.aggregateTestsAggregateCrushingValue);
        aggregateTestsAggregateImpactValue = findViewById(R.id.aggregateTestsAggregateImpactValue);
        aggregateTestsFlakinessIndex = findViewById(R.id.aggregateTestsFlakinessIndex);
        aggregateTestsElongationIndex = findViewById(R.id.aggregateTestsElongationIndex);
        aggregateTestsBulkDensity = findViewById(R.id.aggregateTestsBulkDensity);

        fineAggregateTestsSilkContent = findViewById(R.id.fineAggregateTestsSilkContent);
        fineAggregateTestsOrganicContent = findViewById(R.id.fineAggregateTestsOrganicContent);
        fineAggregateTestsBulkDensity = findViewById(R.id.fineAggregateTestsBulkDensity);
        fineAggregateTestsSpecificGravity = findViewById(R.id.fineAggregateTestsSpecificGravity);

        cementTestsCementFineness = findViewById(R.id.cementTestsCementFineness);
        cementTestsSettingTimeInitial = findViewById(R.id.cementTestsSettingTimeInitial);
        cementTestsSettingTimeFinal = findViewById(R.id.cementTestsSettingTimeFinal);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTestResults();
            }
        });
    }




    private void saveTestResults() {

        // Create and show ProgressDialog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving test results...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Perform database insertion in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
        // Get the values from EditText fields


        String waterTestsPHValue = waterTestsPH.getText().toString();
        String waterTestsSulphateValue = waterTestsSulphate.getText().toString();
        String waterTestsPhosphateValue = waterTestsPhosphate.getText().toString();
        String waterTestsNitrateValue = waterTestsNitrate.getText().toString();
        String waterTestsTSSValue = waterTestsTSS.getText().toString();
        String waterTestsTDSValue = waterTestsTDS.getText().toString();
        String waterTestsCarbonateValue = waterTestsCarbonate.getText().toString();
        String waterTestsBicarbonateValue = waterTestsBicarbonate.getText().toString();

        String aggregateTestsWaterAbsorptionValue = aggregateTestsWaterAbsorption.getText().toString();
        String aggregateTestsSpecificGravityValue = aggregateTestsSpecificGravity.getText().toString();
        String aggregateTestsTenPercentFinesValueValue = aggregateTestsTenPercentFinesValue.getText().toString();
        String aggregateTestsAggregateCrushingValueValue = aggregateTestsAggregateCrushingValue.getText().toString();
        String aggregateTestsAggregateImpactValueValue = aggregateTestsAggregateImpactValue.getText().toString();
        String aggregateTestsFlakinessIndexValue = aggregateTestsFlakinessIndex.getText().toString();
        String aggregateTestsElongationIndexValue = aggregateTestsElongationIndex.getText().toString();
        String aggregateTestsBulkDensityValue = aggregateTestsBulkDensity.getText().toString();

        String fineAggregateTestsSilkContentValue = fineAggregateTestsSilkContent.getText().toString();
        String fineAggregateTestsOrganicContentValue = fineAggregateTestsOrganicContent.getText().toString();
        String fineAggregateTestsBulkDensityValue = fineAggregateTestsBulkDensity.getText().toString();
        String fineAggregateTestsSpecificGravityValue = fineAggregateTestsSpecificGravity.getText().toString();

        String cementTestsCementFinenessValue = cementTestsCementFineness.getText().toString();
        String cementTestsSettingTimeInitialValue = cementTestsSettingTimeInitial.getText().toString();
        String cementTestsSettingTimeFinalValue = cementTestsSettingTimeFinal.getText().toString();

        // Insert the values into the database
        long newRowId = dbHelper.insertTestData(ProjectAreaActivity.project_id, waterTestsPHValue, waterTestsSulphateValue, waterTestsPhosphateValue,
                waterTestsNitrateValue, waterTestsTSSValue, waterTestsTDSValue, waterTestsCarbonateValue,
                waterTestsBicarbonateValue, aggregateTestsWaterAbsorptionValue, aggregateTestsSpecificGravityValue,
                aggregateTestsTenPercentFinesValueValue, aggregateTestsAggregateCrushingValueValue,
                aggregateTestsAggregateImpactValueValue, aggregateTestsFlakinessIndexValue,
                aggregateTestsElongationIndexValue, aggregateTestsBulkDensityValue,
                fineAggregateTestsSilkContentValue, fineAggregateTestsOrganicContentValue,
                fineAggregateTestsBulkDensityValue, fineAggregateTestsSpecificGravityValue,
                cementTestsCementFinenessValue, cementTestsSettingTimeInitialValue, cementTestsSettingTimeFinalValue);

                // Update UI on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Dismiss ProgressDialog
                        progressDialog.dismiss();

                        if (newRowId != -1) {

                            // Close the activity
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            // Failed to insert data
                            // You can show an error message or perform any other action
//                            Toast.makeText(ProjectAreaActivity.this, "Failed to save test results", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();

    }
}
