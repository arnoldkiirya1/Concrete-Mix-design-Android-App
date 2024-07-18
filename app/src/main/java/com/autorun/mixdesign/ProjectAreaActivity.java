package com.autorun.mixdesign;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.autorun.mixdesign.R;
import com.autorun.mixdesign.adapters.ProjectAreaAdapter;
import com.autorun.mixdesign.classes.DesignData;
import com.autorun.mixdesign.classes.Test;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectAreaActivity extends AppCompatActivity {

    private ExpandableListView listView;
    // Declare an ActivityResultLauncher
    private static final int REQUEST_CODE_RELOAD = 1;
    ProjectAreaAdapter adapter;
    private FloatingActionButton fabAdd;
    DBHelper dbHelper;
    public static long project_id;

    TextView cem, water, fine, course10, course20, course40, s_class;

    TextView cem_trial, water_trial, fine_trial, course10_trial, course20_trial, course40_trial, trialmix_text, ratio, water_cem_ratio;

    EditText trialmix;

    TextView cement, water_, fine_agg, course_agg;
    PieChart pieChart;


    TableLayout resultsLayout;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_area);

        // Retrieve project ID from Intent extras
        project_id = getIntent().getLongExtra("projectId", -1);

        System.out.println("Project ID:"+project_id);

        fabAdd = findViewById(R.id.fabAdd);

        resultsLayout = findViewById(R.id.manageTable);

        cem = findViewById(R.id.cem);
        water = findViewById(R.id.water);
        fine = findViewById(R.id.fine);
        course10 = findViewById(R.id.course10);
        course20 = findViewById(R.id.course20);
        s_class = findViewById(R.id.s_class);


        trialmix = findViewById(R.id.trialmix);

        cem_trial = findViewById(R.id.cem_trial);
        water_trial = findViewById(R.id.water_trial);
        fine_trial = findViewById(R.id.fine_trial);
        course10_trial = findViewById(R.id.course10_trial);
        course20_trial = findViewById(R.id.course20_trial);

        pieChart = findViewById(R.id.piechart);

        trialmix_text = findViewById(R.id.mix);

        ratio = findViewById(R.id.ratio);
        water_cem_ratio = findViewById(R.id.water_cem_ratio);

        trialmix.setText("0.05");

        dbHelper = new DBHelper(this);

        // Set adapter to ListView

        listView = findViewById(R.id.listView);
        adapter = new ProjectAreaAdapter(this, getData());
        listView.setAdapter(adapter);

        // Handle click events of FloatingActionButton
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

        // Results
        resultsfetch();

        trialmix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called when the text is changed

                resultsfetch();
                trialmix_text.setText(charSequence + " m³");

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // This method is called after the text has changed
                resultsfetch();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the result is from the current activity and the result code indicates success
        if (requestCode == REQUEST_CODE_RELOAD && resultCode == RESULT_OK) {
            // Reload or refresh this activity
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    @SuppressLint("SetTextI18n")
    public void resultsfetch(){

        pieChart.clear();

        ArrayList<DesignData> designDataList = (ArrayList<DesignData>) dbHelper.getDesignData(project_id);
        if (!designDataList.isEmpty()) {
            DesignData designData = designDataList.get(0);
            // Proceed

            // Set values in TextViews
            fine.setText(roundToNearest5(Double.parseDouble(designData.getFineAggregateContent())));
            cem.setText(roundToNearest5(Double.parseDouble(designData.getCementContent())));
            water.setText(roundToNearest5(Double.parseDouble(designData.getFreeWaterContent())));

            double total_agg = Double.parseDouble(designData.getCourseAggregateContent());
            double volume;
            if (trialmix.getText().toString().isEmpty()) {
                // Set volume to 0 if the text is empty
                volume = 0;
            } else {
                // Parse the text to double
                volume = Double.parseDouble(trialmix.getText().toString());
            }

            // Calculate the course
            course10.setText(roundToNearest5( (0.33333333) * total_agg));
            course20.setText(roundToNearest5(  (0.66666667) * total_agg));

            // Per trial mix
            fine_trial.setText(roundToNearest5(volume * Double.parseDouble(designData.getFineAggregateContent())));
            cem_trial.setText(roundToNearest5(volume * Double.parseDouble(designData.getCementContent())));
            water_trial.setText(roundToNearest5(volume * Double.parseDouble(designData.getFreeWaterContent())));
            course10_trial.setText(roundToNearest5( volume * (0.33333333) * total_agg));
            course20_trial.setText(roundToNearest5( volume * (0.66666667) * total_agg));


            //Get the mix ratio
            int fine_ratio = (int) Math.round((Double.parseDouble(fine.getText().toString()) / Double.parseDouble(cem.getText().toString())));
            int agg_ratio = (int) Math.round(total_agg / Double.parseDouble(cem.getText().toString()));

            // Set the Ratio in textview
            String concrete_class = designData.getCompressiveStrength();
            s_class.setText("Mix ratios for C"+concrete_class);
            ratio.setText("1 : "+ fine_ratio+ " : " + agg_ratio + " (Cement : Fine : Course)");
            water_cem_ratio.setText("Water / Cement : "+designData.getFreeWaterCementRatio());

            // Set the percentage of language used
            int total = (int) Math.round(Double.parseDouble(cem.getText().toString()) + Double.parseDouble(designData.getFreeWaterContent()) + Double.parseDouble(fine.getText().toString()) + total_agg);
            int per_cement  = (int) Math.round((Double.parseDouble(cem.getText().toString()) / total) * 100);
            int per_fine  = (int) Math.round((Double.parseDouble(fine.getText().toString()) / total) * 100);
            int per_agg  = (int) Math.round((total_agg / total) * 100);
            int per_water  = (int) Math.round((Double.parseDouble(designData.getFreeWaterContent()) / total) * 100);

            // Set the data and color to the pie chart

            ArrayList<PieEntry> pieEntries = new ArrayList<>();
            String label = "type";

            //initializing data
            Map<String, Integer> typeAmountMap = new HashMap<>();
            typeAmountMap.put("Cement",Integer.parseInt(String.valueOf(per_cement)));
            typeAmountMap.put("Fine Agg",Integer.parseInt(String.valueOf(per_fine)));
            typeAmountMap.put("Coarse Agg",Integer.parseInt(String.valueOf(per_agg)));
            typeAmountMap.put("Water",Integer.parseInt(String.valueOf(per_water)));


            //initializing colors for the entries
            ArrayList<Integer> colors = new ArrayList<>();
            colors.add(Color.parseColor("#304567"));
            colors.add(Color.parseColor("#309967"));
            colors.add(Color.parseColor("#476567"));
            colors.add(Color.parseColor("#890567"));
//        colors.add(Color.parseColor("#a35567"));
//        colors.add(Color.parseColor("#ff5f67"));
//        colors.add(Color.parseColor("#3ca567"));

            //input data and fit data into pie chart entry
            for(String type: typeAmountMap.keySet()){
                pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
            }

            //collecting the entries with label name
            PieDataSet pieDataSet = new PieDataSet(pieEntries,label);
            //setting text size of the value
            pieDataSet.setValueTextSize(12f);
            //providing color list for coloring different entries
            pieDataSet.setColors(colors);
            //grouping the data set from entry to chart
            PieData pieData = new PieData(pieDataSet);
            //showing the value of the entries, default true if not set
            pieData.setDrawValues(true);

            pieChart.setData(pieData);
            pieChart.invalidate();

            // To animate the pie chart
            pieChart.animate();

        } else {

            // Handle the case where the ArrayList is empty, maybe show a message to the user or perform alternative logic

        }


    }



    private String roundToNearest5(double value) {
        return String.valueOf(Math.round(value / 5) * 5);
    }

    private HashMap<String, List<?>> getData() {
        HashMap<String, List<?>> sectionItems = new HashMap<>();

        List<Test> materialTests = dbHelper.getMaterialTests(project_id);
        sectionItems.put("Material Tests", materialTests);

        List<DesignData> designDataTests = dbHelper.getDesignData(project_id);
        sectionItems.put("Design Data", designDataTests);

        return sectionItems;
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        // Get the Menu object associated with the PopupMenu
        Menu menu = popupMenu.getMenu();

        // Find the menu item by its ID
        MenuItem addMaterialTestsItem = menu.findItem(R.id.menu_add_tests);
        List<Test> materialTests = dbHelper.getMaterialTests(project_id);
        if (materialTests.isEmpty()) {
            addMaterialTestsItem.setVisible(true);
        } else {
            addMaterialTestsItem.setVisible(false);
        }

        // Check if design data is empty
        List<DesignData> designData = dbHelper.getDesignData(project_id);
        MenuItem addDesignDataItem = menu.findItem(R.id.menu_add_design_data);
        if (designData.isEmpty()) {
            addDesignDataItem.setVisible(true);
        } else {
            addDesignDataItem.setVisible(false);
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle menu item clicks
                switch (item.getItemId()) {
                    case R.id.menu_add_tests:
                        // Open activity_add_tests.xml activity
                        Intent intent = new Intent(ProjectAreaActivity.this, AddTestsActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_RELOAD);
                        return true;
                    case R.id.menu_add_design_data:
                        // Handle add design data action
                        Intent intent2 = new Intent(ProjectAreaActivity.this, DesignDataActivity.class);
                        startActivityForResult(intent2, REQUEST_CODE_RELOAD);
                        return true;
                    default:
                        return false;
                }
            }

        });

        popupMenu.show();
    }

}