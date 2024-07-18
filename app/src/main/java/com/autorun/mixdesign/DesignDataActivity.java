package com.autorun.mixdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.autorun.mixdesign.classes.AggregateProportionCalculator;

import java.util.Objects;

public class DesignDataActivity extends AppCompatActivity {

    DBHelper dbHelper;

    Button saveBtn;

    EditText standardDeviation,defectivePortion, defectiveRate, compressiveStrength,days,margin,
    targetMeanStrength, freeWaterCementRatio_, free_water_content, cement_content, max_cement_content,
            min_cement_content, relative_density, concrete_density, total_Aggregate_Content, grading_fines, proportion_of_fines,fine_aggregate_content,course_aggregate_content;
    Spinner spinnerResultSelection,spinnerAggregateTypeCoarse,spinnerAggregateTypeFine, spinnerSlump, spinnerMaxAgg, spinnerCement_class ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_data);

        // Initialize DBHelper object
        dbHelper = new DBHelper(this);

        // Assuming you have references to your EditText fields
         standardDeviation = findViewById(R.id.standard_deviation);
         defectiveRate = findViewById(R.id.defective_rate);
         defectivePortion = findViewById(R.id.portion_defective);
         compressiveStrength = findViewById(R.id.characteristic_strength);
         margin = findViewById(R.id.margin);
         cement_content = findViewById(R.id.cement_content);
         targetMeanStrength = findViewById(R.id.target_mean_strength);
         freeWaterCementRatio_ = findViewById(R.id.free_water_cement_ratio);
         spinnerResultSelection = findViewById(R.id.result_selection);
         spinnerAggregateTypeCoarse = findViewById(R.id.aggregate_type_coarse);
         spinnerAggregateTypeFine = findViewById(R.id.aggregate_type_fine);
         spinnerSlump = findViewById(R.id.slump_or_vebe);
         spinnerMaxAgg = findViewById(R.id.maximum_aggregate_size);
         spinnerCement_class = findViewById(R.id.spinner_cement_strength_class);
         free_water_content = findViewById(R.id.free_water_content);
         max_cement_content = findViewById(R.id.max_cement_content);
         min_cement_content = findViewById(R.id.min_cement_content);
         relative_density = findViewById(R.id.relative_density);
         concrete_density = findViewById(R.id.concrete_density);
         total_Aggregate_Content = findViewById(R.id.total_Aggregate_Content);
         grading_fines = findViewById(R.id.grading_fines);
         proportion_of_fines = findViewById(R.id.proportion_of_fines);
         fine_aggregate_content = findViewById(R.id.fine_aggregate_content);
         course_aggregate_content = findViewById(R.id.course_aggregate_content);
          days = findViewById(R.id.days);

        saveBtn = findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show ProgressDialog
                ProgressDialog progressDialog = new ProgressDialog(DesignDataActivity.this);
                progressDialog.setMessage("Saving design data...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                // Perform database insertion in a background thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Call the method to save design data
                        saveDesignData();

                        // Dismiss ProgressDialog on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        });
                    }
                }).start();
            }
        });



        // Event listener for result selection spinner
        spinnerResultSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get values from inputs
                String compStrengthText = compressiveStrength.getText().toString().trim();
                String defectRateText = defectiveRate.getText().toString().trim();

                // Check if the input fields are not empty
                if (!compStrengthText.isEmpty() && !defectRateText.isEmpty()) {
                    double compStrength = Double.parseDouble(compStrengthText);
                    double defectRate = Double.parseDouble(defectRateText);

                    // Initialize standard deviation
                    double stdDeviation = 0;

                    // Determine result count selection and adjust standard deviation calculation
                    String resultSelection = parent.getItemAtPosition(position).toString();
                    if (resultSelection.equals("Fewer than 20 results")) {
                        if (compStrength == 10) {
                            stdDeviation = 4;
                        } else if (compStrength >= 20 && compStrength <= 70) {
                            stdDeviation = 8;
                        }
                    } else if (resultSelection.equals("20 or more results")) {
                        if (compStrength == 10) {
                            stdDeviation = 2;
                        } else if (compStrength >= 20 && compStrength <= 70) {
                            stdDeviation = 4;
                        }
                    }

                    // Update the standard deviation field
                    standardDeviation.setText(String.valueOf(stdDeviation));

                    // Calculate margin and target mean strength
                    double marginValue = defectRate * stdDeviation;
                    double targetMeanStrengthValue = compStrength + marginValue;

                    // Round off marginValue and targetMeanStrengthValue
                    long roundedMarginValue = Math.round(marginValue);
                    long roundedTargetMeanStrengthValue = Math.round(targetMeanStrengthValue);

                    // Update the margin and target mean strength fields
                    margin.setText(String.valueOf(roundedMarginValue));
                    targetMeanStrength.setText(String.valueOf(roundedTargetMeanStrengthValue));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where nothing is selected (if needed)
            }
        });

        // Event listener for result selection spinner
        spinnerAggregateTypeCoarse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get values from inputs
                String compStrengthText = compressiveStrength.getText().toString().trim();
                String defectRateText = defectiveRate.getText().toString().trim();
                // Check if the input fields are not empty
                if (!compStrengthText.isEmpty() && !defectRateText.isEmpty()) {
                    calculateFreeWaterCementRatio();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where nothing is selected (if needed)
            }
        });

        // Event listener for result selection spinner
        spinnerSlump.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get values from inputs
                String compStrengthText = compressiveStrength.getText().toString().trim();
                String defectRateText = defectiveRate.getText().toString().trim();
                // Check if the input fields are not empty
                if (!compStrengthText.isEmpty() && !defectRateText.isEmpty()) {
                    Double water_content = calculateWaterContent(spinnerSlump.getSelectedItem().toString(), spinnerMaxAgg.getSelectedItem().toString(),spinnerAggregateTypeCoarse.getSelectedItem().toString(), spinnerAggregateTypeFine.getSelectedItem().toString());
                    free_water_content.setText(water_content.toString());

                    double cement = water_content / Double.parseDouble(freeWaterCementRatio_.getText().toString());
                    int roundedCement = (int) Math.round(cement);
                    // Cement Content
                    cement_content.setText(String.valueOf(roundedCement));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where nothing is selected (if needed)
            }
        });

        // Event listener for result selection spinner
        spinnerMaxAgg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get values from inputs
                String compStrengthText = compressiveStrength.getText().toString().trim();
                String defectRateText = defectiveRate.getText().toString().trim();
                // Check if the input fields are not empty
                if (!compStrengthText.isEmpty() && !defectRateText.isEmpty()) {
                    Double water_content = calculateWaterContent(spinnerSlump.getSelectedItem().toString(), spinnerMaxAgg.getSelectedItem().toString(),spinnerAggregateTypeCoarse.getSelectedItem().toString(), spinnerAggregateTypeFine.getSelectedItem().toString());
                    free_water_content.setText(water_content.toString());

                    double cement = water_content / Double.parseDouble(freeWaterCementRatio_.getText().toString());
                    int roundedCement = (int) Math.round(cement);
                    // Cement Content
                    cement_content.setText(String.valueOf(roundedCement));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where nothing is selected (if needed)
            }
        });

        max_cement_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called when the text is changed

                getCementContent();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // This method is called after the text has changed
                Double water_content = calculateWaterContent(spinnerSlump.getSelectedItem().toString(), spinnerMaxAgg.getSelectedItem().toString(),spinnerAggregateTypeCoarse.getSelectedItem().toString(), spinnerAggregateTypeFine.getSelectedItem().toString());
                free_water_content.setText(water_content.toString());

                double cement = water_content / Double.parseDouble(freeWaterCementRatio_.getText().toString());

                // Cement Content
                cement_content.setText(String.valueOf(cement));

                getCementContent();
            }
        });

        defectivePortion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called when the text is changed


            }

            @Override
            public void afterTextChanged(Editable editable) {


                double portion =  Double.parseDouble(defectivePortion.getText().toString());
                double rate = getDefectiveRate(portion);

                // Cement Content
                defectiveRate.setText(String.valueOf(rate));

            }
        });

        min_cement_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called when the text is changed
                getCementContent();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // This method is called after the text has changed
                getCementContent();
            }
        });

        relative_density.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called when the text is changed
                getConcreteDensity();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // This method is called after the text has changed
                getConcreteDensity();
            }
        });

        grading_fines.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called when the text is changed
                int maximumAggregateSize = 0;
                if(spinnerMaxAgg.getSelectedItem().toString().equals("10mm")){
                    maximumAggregateSize = 10;
                } else if (spinnerMaxAgg.getSelectedItem().toString().equals("20mm")){
                    maximumAggregateSize = 20;
                } else if (spinnerMaxAgg.getSelectedItem().toString().equals("40mm")){
                    maximumAggregateSize = 40;
                }

                //slump
                int slump = 0;
                if(spinnerSlump.getSelectedItem().toString().equals("0 – 10")){
                    slump = 1;
                } else if (spinnerSlump.getSelectedItem().toString().equals("10 – 30")){
                    slump = 2;
                } else if (spinnerSlump.getSelectedItem().toString().equals("30 – 60")){
                    slump = 3;
                } else if (spinnerSlump.getSelectedItem().toString().equals("60 – 180")){
                    slump = 4;
                }

                double freeWaterCementRatio = Double.parseDouble(freeWaterCementRatio_.getText().toString());
                double gradingOfFineAggregate = convertInputToOptionValue(Integer.valueOf(grading_fines.getText().toString()));
                double totalAggregateContent = Double.parseDouble(total_Aggregate_Content.getText().toString());

                System.out.println("freeWaterCementRatio " +freeWaterCementRatio);
                System.out.println("gradingOfFineAggregate " +gradingOfFineAggregate);
                System.out.println("slump " +slump);
                System.out.println("maximumAggregateSize " +maximumAggregateSize);

                // Call methods from AggregateProportionCalculator class
                double proportionOfFineAggregate = AggregateProportionCalculator.calculateProportionOfFineAggregate(maximumAggregateSize, slump, freeWaterCementRatio, gradingOfFineAggregate);
                double fineAggregateContent = AggregateProportionCalculator.calculateFineAggregateContent(totalAggregateContent, proportionOfFineAggregate);
                double coarseAggregateContent = AggregateProportionCalculator.calculateCoarseAggregateContent(totalAggregateContent, fineAggregateContent);

                // Displaying the results

                System.out.println("proportionOfFineAggregate " +proportionOfFineAggregate);


                proportion_of_fines.setText(String.valueOf(proportionOfFineAggregate));
                fine_aggregate_content.setText(String.valueOf(fineAggregateContent));
                course_aggregate_content.setText(String.valueOf(coarseAggregateContent));

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // This method is called after the text has changed
                int maximumAggregateSize = 0;
                if(spinnerMaxAgg.getSelectedItem().toString().equals("10mm")){
                    maximumAggregateSize = 10;
                } else if (spinnerMaxAgg.getSelectedItem().toString().equals("20mm")){
                    maximumAggregateSize = 20;
                } else if (spinnerMaxAgg.getSelectedItem().toString().equals("40mm")){
                    maximumAggregateSize = 40;
                }

                //slump
                int slump = 0;
                if(spinnerSlump.getSelectedItem().toString().equals("0 – 10")){
                    slump = 1;
                } else if (spinnerSlump.getSelectedItem().toString().equals("10 – 30")){
                    slump = 2;
                } else if (spinnerSlump.getSelectedItem().toString().equals("30 – 60")){
                    slump = 3;
                } else if (spinnerSlump.getSelectedItem().toString().equals("60 – 180")){
                    slump = 4;
                }

                double freeWaterCementRatio = Double.parseDouble(freeWaterCementRatio_.getText().toString());
                double gradingOfFineAggregate = convertInputToOptionValue(Integer.valueOf(grading_fines.getText().toString()));
                double totalAggregateContent = Double.parseDouble(total_Aggregate_Content.getText().toString());

                // Call methods from AggregateProportionCalculator class
                double proportionOfFineAggregate = AggregateProportionCalculator.calculateProportionOfFineAggregate(maximumAggregateSize, slump, freeWaterCementRatio, gradingOfFineAggregate);
                double fineAggregateContent = AggregateProportionCalculator.calculateFineAggregateContent(totalAggregateContent, proportionOfFineAggregate);
                double coarseAggregateContent = AggregateProportionCalculator.calculateCoarseAggregateContent(totalAggregateContent, fineAggregateContent);

                // Displaying the results
                proportion_of_fines.setText(String.valueOf(proportionOfFineAggregate));
                fine_aggregate_content.setText(String.valueOf(fineAggregateContent));
                course_aggregate_content.setText(String.valueOf(coarseAggregateContent));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            showAboutDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About");
        builder.setMessage("KYAMBOGO UNIVERSITY\n\nFACULTY OF ENGINEERING\n\nDEPARTMENT OF CIVIL AND ENVIRONMENTAL ENGINEERING\n\nTOPIC TITLE: DESIGN OF A SOFTWARE SYSTEM FOR STREAMLINING CONSTRUCTION PROCESSES OF CONCRETE MIX DESIGN\n\nSTUDENT: KIIRYA ARNOLD\n\nYEAR: 2019-2024\n\nSUPERVISED BY: SSAALONGO LUWALAGGA MUNZI");
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private double getCementContent(){

        double maximum_cement_content;
        double minimum_cement_content;

        String maxCementContentText = max_cement_content.getText().toString();
        String minCementContentText = min_cement_content.getText().toString();

        if (!maxCementContentText.isEmpty()) {
            maximum_cement_content = Double.parseDouble(maxCementContentText);
        } else {
            maximum_cement_content = 0; // Default value when the EditText is empty or null
        }

        if (!minCementContentText.isEmpty()) {
            minimum_cement_content = Double.parseDouble(minCementContentText);
        } else {
            minimum_cement_content = 0; // Default value when the EditText is empty or null
        }

        double content = Double.parseDouble(cement_content.getText().toString());
        double final_cement_content = 0.0;

        if(content <= maximum_cement_content){
            final_cement_content = content;
        } else  if (content > minimum_cement_content){
            final_cement_content = content;
        }
        return final_cement_content;
    }

    public double getDefectiveRate(double percentageFailurePermitted){

        if (percentageFailurePermitted >= 16) {
            return  1.00;
        } else if (percentageFailurePermitted >= 10) {
            return 1.28;
        } else if (percentageFailurePermitted >= 5) {
            return 1.65;
        } else if (percentageFailurePermitted >= 2.5) {
            return 1.96;
        } else if (percentageFailurePermitted >= 2) {
            return 2.05;
        } else if (percentageFailurePermitted >= 1) {
            return  2.33;
        } else {
            return 1.65;
        }

    }

    public int approximateCompressiveStrength(double cementStrengthClass, String typeOfCoarseAggregate, int days) {
        // Check the input variables and approximate the compressive strength accordingly
        if (cementStrengthClass == 32.5) {
            if (typeOfCoarseAggregate.equals("Uncrushed")) {
                if (days == 3) return 15;
                else if (days == 7) return 23;
                else if (days == 28) return 36;
                else if (days == 91) return 44;
            } else if (typeOfCoarseAggregate.equals("Crushed")) {
                if (days == 3) return 20;
                else if (days == 7) return 29;
                else if (days == 28) return 43;
                else if (days == 91) return 51;
            }
        } else if (cementStrengthClass == 42.5) {
            if (typeOfCoarseAggregate.equals("Uncrushed")) {
                if (days == 3) return 22;
                else if (days == 7) return 30;
                else if (days == 28) return 42;
                else if (days == 91) return 49;
            } else if (typeOfCoarseAggregate.equals("Crushed")) {
                if (days == 3) return 27;
                else if (days == 7) return 36;
                else if (days == 28) return 49;
                else if (days == 91) return 56;
            }
        } else if (cementStrengthClass == 52.5) {
            if (typeOfCoarseAggregate.equals("Uncrushed")) {
                if (days == 3) return 29;
                else if (days == 7) return 37;
                else if (days == 28) return 48;
                else if (days == 91) return 54;
            } else if (typeOfCoarseAggregate.equals("Crushed")) {
                if (days == 3) return 34;
                else if (days == 7) return 43;
                else if (days == 28) return 55;
                else if (days == 91) return 61;
            }
        }

        // Default return value if input combination is not found
        return 0;
    }


    private void calculateFreeWaterCementRatio() {
        // Get input values
        String aggType = spinnerAggregateTypeCoarse.getSelectedItem().toString();
        double fm = Double.parseDouble(targetMeanStrength.getText().toString());
        double cement_strength_class = Double.parseDouble(spinnerCement_class.getSelectedItem().toString());
        int characteristicStrength = approximateCompressiveStrength(cement_strength_class, aggType, Integer.parseInt(days.getText().toString()));


        // Calculate Free Water/Cement Ratio based on equations
        double freeWaterCementRatio = 0.0;

        // Apply equations based on conditions
        if (aggType.equals("Uncrushed")) {
            if (characteristicStrength >= 10 && characteristicStrength <= 42) {
                freeWaterCementRatio = 0.000295 * Math.pow(fm, 2) - 0.0312 * fm + 1.2912;
            } else if (characteristicStrength > 42 && characteristicStrength <= 80) {
                freeWaterCementRatio = 0.00008519157 * Math.pow(fm, 2) - 0.01571 * fm + 1.0097;
            }
        } else if (aggType.equals("Crushed")) {
            if (characteristicStrength >= 10 && characteristicStrength <= 42) {
                freeWaterCementRatio = 0.000295 * Math.pow(fm, 2) - 0.0312 * fm + 1.2912;
            } else if (characteristicStrength > 42 && characteristicStrength <= 80) {
                freeWaterCementRatio = 0.00008519157 * Math.pow(fm, 2) - 0.01571 * fm + 1.0697;
            }
        }

        // Update the readonly field
        freeWaterCementRatio_.setText(String.format("%.2f", freeWaterCementRatio - 0.01));
    }

    private double calculateWaterContent(String slump, String maxAggregateSize, String aggregateTypeCoarse, String aggregateTypeFine) {
        double freeWaterContent = 0;
        double freeWaterContentFine = 0;
        double freeWaterContentCoarse = 0;


        if ("10mm".equals(maxAggregateSize)) {
            if (aggregateTypeCoarse.equals("Uncrushed") && aggregateTypeFine.equals("Uncrushed")) {
                if ("0 – 10".equals(slump)) {
                    freeWaterContent = 150;
                } else if ("10 – 30".equals(slump)) {
                    freeWaterContent = 180;
                } else if ("30 – 60".equals(slump)) {
                    freeWaterContent = 205;
                } else if ("60 – 180".equals(slump)) {
                    freeWaterContent = 225;
                }
            } else if (aggregateTypeCoarse.equals("Crushed") && aggregateTypeFine.equals("Crushed")) {
                if ("0 – 10".equals(slump)) {
                    freeWaterContent = 180;
                } else if ("10 – 30".equals(slump)) {
                    freeWaterContent = 205;
                } else if ("30 – 60".equals(slump)) {
                    freeWaterContent = 230;
                } else if ("60 – 180".equals(slump)) {
                    freeWaterContent = 250;
                }
            } else if (aggregateTypeFine.equals("Uncrushed") && aggregateTypeCoarse.equals("Crushed")) {
                if ("0 – 10".equals(slump)) {
                    freeWaterContentFine = 150;
                    freeWaterContentCoarse = 180;
                } else if ("10 – 30".equals(slump)) {
                    freeWaterContentFine = 180;
                    freeWaterContentCoarse = 205;
                } else if ("30 – 60".equals(slump)) {
                    freeWaterContentFine = 205;
                    freeWaterContentCoarse = 230;
                } else if ("60 – 180".equals(slump)) {
                    freeWaterContentFine = 225;
                    freeWaterContentCoarse = 250;
                }
                freeWaterContent = (2.0 / 3.0) * freeWaterContentFine + (1.0 / 3.0) * freeWaterContentCoarse;
            }
        } else if ("20mm".equals(maxAggregateSize)) {

            // Similar calculations for maxAggregateSize 20
            if ("Uncrushed".equals(aggregateTypeCoarse) && "Uncrushed".equals(aggregateTypeFine )) {
                if ("0 – 10".equals(slump)) {
                    freeWaterContent = 135;
                } else if ("10 – 30".equals(slump)) {
                    freeWaterContent = 160;
                } else if ("30 – 60".equals(slump)) {
                    freeWaterContent = 180;
                } else if ("60 – 180".equals(slump)) {
                    freeWaterContent = 195;
                }
            }
            else if ("Crushed".equals(aggregateTypeCoarse) && "Crushed".equals(aggregateTypeFine)) {
                if ("0 – 10".equals(slump)) {
                    freeWaterContent = 170;
                } else if ("10 – 30".equals(slump)) {
                    freeWaterContent = 190;
                } else if ("30 – 60".equals(slump)) {
                    freeWaterContent = 210;
                } else if ("60 – 180".equals(slump)) {
                    freeWaterContent = 225;
                }
            }
            else if ("Uncrushed".equals(aggregateTypeFine) && "Crushed".equals(aggregateTypeCoarse)) {
                if ("0 – 10".equals(slump )) {
                    freeWaterContentFine = 135;
                    freeWaterContentCoarse = 170;
                } else if ("10 – 30".equals(slump )) {
                    freeWaterContentFine = 160;
                    freeWaterContentCoarse = 190;
                } else if ("30 – 60".equals(slump)) {
                    freeWaterContentFine = 180;
                    freeWaterContentCoarse = 210;
                } else if ("60 – 180".equals(slump)) {
                    freeWaterContentFine = 195;
                    freeWaterContentCoarse = 225;
                }
                freeWaterContent = (2.0 / 3.0) * freeWaterContentFine + (1.0 / 3.0) * freeWaterContentCoarse;
            }
        } else if ("40mm".equals(maxAggregateSize)) {
            // Similar calculations for maxAggregateSize 40
            if (Objects.equals(aggregateTypeCoarse, "Uncrushed") && Objects.equals(aggregateTypeFine, "Uncrushed")) {
                if ("0 – 10".equals(slump)) {
                    freeWaterContent = 115;
                } else if ("10 - 30".equals(slump)) {
                    freeWaterContent = 140;
                } else if ("30 - 60".equals(slump)) {
                    freeWaterContent = 160;
                } else if ("60 - 180".equals(slump)) {
                    freeWaterContent = 175;
                }
            }
            else if (Objects.equals(aggregateTypeCoarse, "Crushed") && Objects.equals(aggregateTypeFine, "Crushed")) {
                if ("0 - 10".equals(slump)) {
                    freeWaterContent = 155;
                } else if ("10 - 30".equals(slump)) {
                    freeWaterContent = 175;
                } else if ("30 - 60".equals(slump)) {
                    freeWaterContent = 190;
                } else if ("60 - 180".equals(slump)) {
                    freeWaterContent = 205;
                }
            }
            else if (Objects.equals(aggregateTypeFine, "Uncrushed") && Objects.equals(aggregateTypeCoarse, "Crushed")) {
                if ("0 - 10".equals(slump)) {
                    freeWaterContentFine = 115;
                    freeWaterContentCoarse = 155;
                } else if ("10 - 30".equals(slump)) {
                    freeWaterContentFine = 140;
                    freeWaterContentCoarse = 175;
                } else if ("30 - 60".equals(slump)) {
                    freeWaterContentFine = 160;
                    freeWaterContentCoarse = 190;
                } else if ("60 - 180".equals(slump)) {
                    freeWaterContentFine = 175;
                    freeWaterContentCoarse = 205;
                }
                freeWaterContent = (2.0 / 3.0) * freeWaterContentFine + (1.0 / 3.0) * freeWaterContentCoarse;
            }
        } else {
            freeWaterContent = 0; // Not defined
        }

        return freeWaterContent;
    }

    public void getConcreteDensity() {


            double freeWaterContent = Double.parseDouble(free_water_content.getText().toString());
            double relativeDensity = Double.parseDouble(relative_density.getText().toString());
            double cementContent = Double.parseDouble(cement_content.getText().toString());
            double wetDensity;

            if (relativeDensity == 2.9) {
                wetDensity = -1.71875 * freeWaterContent + 2896.875;
            } else if (relativeDensity == 2.8) {
                wetDensity = -1.59375 * freeWaterContent + 2804.375;
            } else if (relativeDensity == 2.7) {
                wetDensity = -1.4375 * freeWaterContent + 2703.75;
            } else if (relativeDensity == 2.6) {
                wetDensity = -1.25 * freeWaterContent + 2605;
            } else if (relativeDensity == 2.5) {
                wetDensity = -1.03125 * freeWaterContent + 2493.125;
            } else if (relativeDensity == 2.4) {
                wetDensity = -0.925 * freeWaterContent + 2402;
            } else {
                // Handle unknown relative density
                wetDensity = 0; // Or any other appropriate value
            }

            // Round to nearest hundred
            wetDensity = Math.floor(wetDensity / 100) * 100;

            // Update EditText fields
            concrete_density.setText(String.valueOf(wetDensity));
            total_Aggregate_Content.setText(String.valueOf(wetDensity - cementContent - freeWaterContent));


    }

    public int convertInputToOptionValue(int inputValue) {
        int optionValue = -1; // Default value if input does not fall into any range

        if (inputValue >= 80 && inputValue <= 100) {
            optionValue = 4;
        } else if (inputValue >= 60 && inputValue < 80) {
            optionValue = 3;
        } else if (inputValue >= 40 && inputValue < 60) {
            optionValue = 2;
        } else if (inputValue >= 15 && inputValue < 40) {
            optionValue = 1;
        } else if (inputValue >= 0 && inputValue < 15) {
            optionValue = 0;
        }

        return optionValue;
    }

    public void saveDesignData() {
        // Retrieve values from all input fields
        String standardDeviationValue = standardDeviation.getText().toString();
        String defectiveRateValue = defectiveRate.getText().toString();
        String compressiveStrengthValue = compressiveStrength.getText().toString();
        String marginValue = margin.getText().toString();
        String cementContentValue = cement_content.getText().toString();
        String targetMeanStrengthValue = targetMeanStrength.getText().toString();
        String freeWaterCementRatioValue = freeWaterCementRatio_.getText().toString();
        String spinnerResultSelectionValue = spinnerResultSelection.getSelectedItem().toString();
        String spinnerAggregateTypeCoarseValue = spinnerAggregateTypeCoarse.getSelectedItem().toString();
        String spinnerAggregateTypeFineValue = spinnerAggregateTypeFine.getSelectedItem().toString();
        String spinnerSlumpValue = spinnerSlump.getSelectedItem().toString();
        String spinnerMaxAggValue = spinnerMaxAgg.getSelectedItem().toString();
        String freeWaterContentValue = free_water_content.getText().toString();
        String maxCementContentValue = max_cement_content.getText().toString();
        String minCementContentValue = min_cement_content.getText().toString();
        String relativeDensityValue = relative_density.getText().toString();
        String concreteDensityValue = concrete_density.getText().toString();
        String totalAggregateContentValue = total_Aggregate_Content.getText().toString();
        String gradingFinesValue = grading_fines.getText().toString();
        String proportionOfFinesValue = proportion_of_fines.getText().toString();
        String fineAggregateContentValue = fine_aggregate_content.getText().toString();
        String courseAggregateContentValue = course_aggregate_content.getText().toString();

        // Check if all required fields have values
        if (TextUtils.isEmpty(spinnerResultSelectionValue) || TextUtils.isEmpty(spinnerAggregateTypeCoarseValue) ||
                TextUtils.isEmpty(spinnerAggregateTypeFineValue) && TextUtils.isEmpty(spinnerAggregateTypeFineValue) && TextUtils.isEmpty(spinnerSlumpValue) ) {
            // Handle the case when any required field is empty
            // For example, display an error message or alert dialog
            Toast.makeText(this, "All required fields must be filled!", Toast.LENGTH_LONG).show();

        } else {

            // Call insertDesignData method
            long newRowId = dbHelper.insertDesignData(ProjectAreaActivity.project_id,standardDeviationValue, defectiveRateValue, compressiveStrengthValue,
                    marginValue, cementContentValue, targetMeanStrengthValue, freeWaterCementRatioValue,
                    spinnerResultSelectionValue, spinnerAggregateTypeCoarseValue, spinnerAggregateTypeFineValue,
                    spinnerSlumpValue, spinnerMaxAggValue, freeWaterContentValue, maxCementContentValue, minCementContentValue,
                    relativeDensityValue, concreteDensityValue, totalAggregateContentValue, gradingFinesValue, proportionOfFinesValue,
                    fineAggregateContentValue, courseAggregateContentValue);

            if (newRowId != -1) {
                // Data inserted successfully


                // Close the activity
                setResult(RESULT_OK);
                finish();
            } else {
                // Failed to insert data
                // You can show an error message or perform any other action
                //Toast.makeText(this, "Failed to save design data", Toast.LENGTH_SHORT).show();
            }

        }

    }
}