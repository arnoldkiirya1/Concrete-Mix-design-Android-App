package com.autorun.mixdesign.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.autorun.mixdesign.DBHelper;
import com.autorun.mixdesign.R;
import com.autorun.mixdesign.classes.DesignData;
import com.autorun.mixdesign.classes.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectAreaAdapter extends BaseExpandableListAdapter {

    private static final int SECTION_MATERIAL_TEST = 0;
    private static final int SECTION_DESIGN_DATA = 1;
    private static final int SECTION_RESULTS_BY_WEIGHT = 2;
    private static final int SECTION_RESULTS_BY_VOLUME = 3;
    private static final int SECTION_COUNT = 2;

    private Context context;
    private HashMap<Integer, List<?>> sectionItems;
    private HashMap<Integer, String> sectionHeaders;

    public ProjectAreaAdapter(Context context, HashMap<String, List<?>> data) {
        this.context = context;
        this.sectionItems = new HashMap<>();
        this.sectionHeaders = new HashMap<>();
        this.sectionItems.put(SECTION_MATERIAL_TEST, (List<Test>) data.get("Material Tests"));
        this.sectionItems.put(SECTION_DESIGN_DATA, (List<DesignData>) data.get("Design Data"));

        // Add more sections and headers as needed
        this.sectionHeaders.put(SECTION_MATERIAL_TEST, "Material Tests");
        this.sectionHeaders.put(SECTION_DESIGN_DATA, "Design Data");
    }

    @Override
    public int getGroupCount() {
        return SECTION_COUNT;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return sectionItems.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return sectionItems.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return sectionItems.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = sectionHeaders.get(groupPosition);
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_expandable, null);
        }

        TextView textViewHeader = view.findViewById(R.id.textViewHeader);
        textViewHeader.setText(headerTitle);

        // Set different icons for different headers
        ImageView icon = view.findViewById(R.id.icon);
        if (headerTitle.equals("Material Tests")) {
            if (isExpanded) {
                // Set the icon for expanded state
                icon.setImageResource(R.drawable.ic_expand_more);
            } else {
                // Set the icon for collapsed state
                icon.setImageResource(R.drawable.ic_expand_less);
            }
        } else if (headerTitle.equals("Design Data")) {
            if (isExpanded) {
                // Set the icon for expanded state
                icon.setImageResource(R.drawable.ic_expand_more);
            } else {
                // Set the icon for collapsed state
                icon.setImageResource(R.drawable.ic_expand_less);
            }
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // Check the type of data (Test or DesignData)
        Object childItem = getChild(groupPosition, childPosition);
        if (childItem instanceof Test) {
            Test test = (Test) childItem;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_item_table_row, parent, false);
            TableLayout tableLayout = rowView.findViewById(R.id.tableLayout);

            // Iterate over the test attributes and create TableRows dynamically
            for (Map.Entry<String, String> entry : test.getAttributes().entrySet()) {
                String attributeName = entry.getKey();
                String attributeValue = entry.getValue();

                // Inflate the TableRow layout for each attribute
                TableRow attributeRow = (TableRow) inflater.inflate(R.layout.table_row_attribute, tableLayout, false);

                // Find TextViews within the TableRow and set their text
                TextView textViewName = attributeRow.findViewById(R.id.textViewItem);
                TextView textViewValue = attributeRow.findViewById(R.id.textViewItem2);
                textViewName.setText(attributeName);
                textViewValue.setText(attributeValue);

                // Add the TableRow to the TableLayout
                tableLayout.addView(attributeRow);
            }

            return rowView;
        } else if (childItem instanceof DesignData) {
            DesignData design = (DesignData) childItem;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_item_table_row, parent, false);
            TableLayout tableLayout = rowView.findViewById(R.id.tableLayout);

            // Iterate over the test attributes and create TableRows dynamically
            for (Map.Entry<String, String> entry : design.getAttributes().entrySet()) {
                String attributeName = entry.getKey();
                String attributeValue = entry.getValue();

                // Inflate the TableRow layout for each attribute
                TableRow attributeRow = (TableRow) inflater.inflate(R.layout.table_row_attribute, tableLayout, false);

                // Find TextViews within the TableRow and set their text
                TextView textViewName = attributeRow.findViewById(R.id.textViewItem);
                TextView textViewValue = attributeRow.findViewById(R.id.textViewItem2);
                textViewName.setText(attributeName);
                textViewValue.setText(attributeValue);

                // Add the TableRow to the TableLayout
                tableLayout.addView(attributeRow);
            }



            return rowView;
        } else if (childItem instanceof DesignData) {
            DesignData design = (DesignData) childItem;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_item_table_row, parent, false);
            TableLayout tableLayout = rowView.findViewById(R.id.tableLayout);

            // Iterate over the test attributes and create TableRows dynamically
            for (Map.Entry<String, String> entry : design.getAttributes().entrySet()) {
                String attributeName = entry.getKey();
                String attributeValue = entry.getValue();

                // Inflate the TableRow layout for each attribute
                TableRow attributeRow = (TableRow) inflater.inflate(R.layout.table_row_attribute, tableLayout, false);

                // Find TextViews within the TableRow and set their text
                TextView textViewName = attributeRow.findViewById(R.id.textViewItem);
                TextView textViewValue = attributeRow.findViewById(R.id.textViewItem2);
                textViewName.setText(attributeName);
                textViewValue.setText(attributeValue);

                // Add the TableRow to the TableLayout
                tableLayout.addView(attributeRow);
            }


            return rowView;
        }

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}