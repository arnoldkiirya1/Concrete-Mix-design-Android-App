package com.autorun.mixdesign;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.autorun.mixdesign.adapters.ProjectsAdapter;
import com.autorun.mixdesign.classes.Project;
import com.autorun.mixdesign.contracts.ProjectsContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class ProjectsActivity extends AppCompatActivity {
    private ArrayList<Project> projectsList;
    private ProjectsAdapter projectsAdapter;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);



        // Initialize database
        DBHelper dbHelper = new DBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        // Initialize projects list and adapter
        projectsList = new ArrayList<>();
        projectsAdapter = new ProjectsAdapter(this, projectsList);
        ListView projectsListView = findViewById(R.id.projectsListView);
        projectsListView.setAdapter(projectsAdapter);

        // Load projects from database
        loadProjectsFromDatabase();

        // Add project button click listener
        FloatingActionButton addProjectFab = findViewById(R.id.addProjectFab);
        addProjectFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProjectDialog();
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

    private void showAddProjectDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_add_project, null);

        final EditText projectNameEditText = dialogView.findViewById(R.id.editTextProjectName);
        final EditText characteristicStrengthEditText = dialogView.findViewById(R.id.editTextCharacteristicStrength);
        final EditText atDaysEditText = dialogView.findViewById(R.id.editTextAtDays);
        final Spinner mixDesignStandardEditText = dialogView.findViewById(R.id.spinner_design_type);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)

                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String projectName = projectNameEditText.getText().toString().trim();
                        String characteristicStrength = characteristicStrengthEditText.getText().toString().trim();
                        String atDays = atDaysEditText.getText().toString().trim();
                        String mixDesignStandard = mixDesignStandardEditText.getSelectedItem().toString().trim();

                        if (!projectName.isEmpty()) {
                            addProject(projectName, characteristicStrength, atDays, mixDesignStandard);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }

    private void addProject(final String projectName, final String characteristicStrength, final String atDays, final String mixDesignStandard) {
        // Create and show ProgressDialog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving project...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Perform database insertion in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                ContentValues cv = new ContentValues();
                cv.put(ProjectsContract.ProjectEntry.COLUMN_NAME, projectName);
                cv.put(ProjectsContract.ProjectEntry.COLUMN_CHARACTERISTIC_STRENGTH, characteristicStrength);
                cv.put(ProjectsContract.ProjectEntry.COLUMN_AT_DAYS, atDays);
                cv.put(ProjectsContract.ProjectEntry.COLUMN_MIX_DESIGN_STANDARD, mixDesignStandard);

                final long id = mDatabase.insert(ProjectsContract.ProjectEntry.TABLE_NAME, null, cv);

                // Update UI on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Dismiss ProgressDialog
                        progressDialog.dismiss();

                        // Add project to list and update adapter
                        projectsList.add(new Project(id, projectName, characteristicStrength, atDays, mixDesignStandard));
                        projectsAdapter.clear();
                        loadProjectsFromDatabase();
                    }
                });
            }
        }).start();
    }


    private void loadProjectsFromDatabase() {
        Cursor cursor = mDatabase.query(
                ProjectsContract.ProjectEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                ProjectsContract.ProjectEntry.COLUMN_ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") long projectID = cursor.getInt(cursor.getColumnIndex(ProjectsContract.ProjectEntry.COLUMN_ID));
                @SuppressLint("Range") String projectName = cursor.getString(cursor.getColumnIndex(ProjectsContract.ProjectEntry.COLUMN_NAME));
                @SuppressLint("Range") String characteristicStrength = cursor.getString(cursor.getColumnIndex(ProjectsContract.ProjectEntry.COLUMN_CHARACTERISTIC_STRENGTH));
                @SuppressLint("Range") String atDays = cursor.getString(cursor.getColumnIndex(ProjectsContract.ProjectEntry.COLUMN_AT_DAYS));
                @SuppressLint("Range") String mixDesignStandard = cursor.getString(cursor.getColumnIndex(ProjectsContract.ProjectEntry.COLUMN_MIX_DESIGN_STANDARD));

                projectsList.add(new Project(projectID, projectName, characteristicStrength, atDays, mixDesignStandard));
            } while (cursor.moveToNext());
        }

        cursor.close();
        projectsAdapter.notifyDataSetChanged();
    }
}