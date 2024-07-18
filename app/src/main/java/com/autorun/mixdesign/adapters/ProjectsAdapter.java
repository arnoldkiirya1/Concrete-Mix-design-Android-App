package com.autorun.mixdesign.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.autorun.mixdesign.ProjectAreaActivity;
import com.autorun.mixdesign.R;
import com.autorun.mixdesign.classes.Project;

import java.util.ArrayList;

public class ProjectsAdapter extends ArrayAdapter<Project> {

    private ArrayList<Project> projectsList;
    private Context mContext;

    public ProjectsAdapter(Context context, ArrayList<Project> list) {
        super(context, 0, list);
        mContext = context;
        projectsList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_project,parent,false);

        final Project currentProject = projectsList.get(position);

        TextView projectName = listItem.findViewById(R.id.textViewProjectName);
        projectName.setText(currentProject.getName());

        TextView characteristicStrength = listItem.findViewById(R.id.textViewCharacteristicStrength);
        characteristicStrength.setText("Characteristic Strength: " + currentProject.getCharacteristicStrength() + " N/mm²");


        TextView mixDesignStandard = listItem.findViewById(R.id.textViewMixDesignStandard);
        mixDesignStandard.setText("Type: " + currentProject.getMixDesignStandard());

        // Set click listener for the item view
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch ProjectAreaActivity for the specific project tapped on
                Intent intent = new Intent(mContext, ProjectAreaActivity.class);
                intent.putExtra("projectId", (long) currentProject.getId()); // Cast to long
                System.out.println("Project ID:"+currentProject.getId());
                mContext.startActivity(intent);
            }
        });

        return listItem;
    }
}