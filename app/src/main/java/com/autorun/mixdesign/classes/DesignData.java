package com.autorun.mixdesign.classes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DesignData {
    private String testId;
    private String standardDeviation;
    private String defectiveRate;
    private String compressiveStrength;
    private String margin;
    private String cementContent;
    private String targetMeanStrength;
    private String freeWaterCementRatio;
    private String spinnerResultSelection;
    private String spinnerAggregateTypeCoarse;
    private String spinnerAggregateTypeFine;
    private String spinnerSlump;
    private String spinnerMaxAgg;
    private String freeWaterContent;
    private String maxCementContent;
    private String minCementContent;
    private String relativeDensity;
    private String concreteDensity;
    private String totalAggregateContent;
    private String gradingFines;
    private String proportionOfFines;
    private String fineAggregateContent;
    private String courseAggregateContent;

    // Constructor
    public DesignData() {
        // Default constructor
    }

    // Getters and setters for each field
    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(String standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public String getDefectiveRate() {
        return defectiveRate;
    }

    public void setDefectiveRate(String defectiveRate) {
        this.defectiveRate = defectiveRate;
    }

    public String getCompressiveStrength() {
        return compressiveStrength;
    }

    public void setCompressiveStrength(String compressiveStrength) {
        this.compressiveStrength = compressiveStrength;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getCementContent() {
        return cementContent;
    }

    public void setCementContent(String cementContent) {
        this.cementContent = cementContent;
    }

    public String getTargetMeanStrength() {
        return targetMeanStrength;
    }

    public void setTargetMeanStrength(String targetMeanStrength) {
        this.targetMeanStrength = targetMeanStrength;
    }

    public String getFreeWaterCementRatio() {
        return freeWaterCementRatio;
    }

    public void setFreeWaterCementRatio(String freeWaterCementRatio) {
        this.freeWaterCementRatio = freeWaterCementRatio;
    }

    public String getSpinnerResultSelection() {
        return spinnerResultSelection;
    }

    public void setSpinnerResultSelection(String spinnerResultSelection) {
        this.spinnerResultSelection = spinnerResultSelection;
    }

    public String getSpinnerAggregateTypeCoarse() {
        return spinnerAggregateTypeCoarse;
    }

    public void setSpinnerAggregateTypeCoarse(String spinnerAggregateTypeCoarse) {
        this.spinnerAggregateTypeCoarse = spinnerAggregateTypeCoarse;
    }

    public String getSpinnerAggregateTypeFine() {
        return spinnerAggregateTypeFine;
    }

    public void setSpinnerAggregateTypeFine(String spinnerAggregateTypeFine) {
        this.spinnerAggregateTypeFine = spinnerAggregateTypeFine;
    }

    public String getSpinnerSlump() {
        return spinnerSlump;
    }

    public void setSpinnerSlump(String spinnerSlump) {
        this.spinnerSlump = spinnerSlump;
    }

    public String getSpinnerMaxAgg() {
        return spinnerMaxAgg;
    }

    public void setSpinnerMaxAgg(String spinnerMaxAgg) {
        this.spinnerMaxAgg = spinnerMaxAgg;
    }

    public String getFreeWaterContent() {
        return freeWaterContent;
    }

    public void setFreeWaterContent(String freeWaterContent) {
        this.freeWaterContent = freeWaterContent;
    }

    public String getMaxCementContent() {
        return maxCementContent;
    }

    public void setMaxCementContent(String maxCementContent) {
        this.maxCementContent = maxCementContent;
    }

    public String getMinCementContent() {
        return minCementContent;
    }

    public void setMinCementContent(String minCementContent) {
        this.minCementContent = minCementContent;
    }

    public String getRelativeDensity() {
        return relativeDensity;
    }

    public void setRelativeDensity(String relativeDensity) {
        this.relativeDensity = relativeDensity;
    }

    public String getConcreteDensity() {
        return concreteDensity;
    }

    public void setConcreteDensity(String concreteDensity) {
        this.concreteDensity = concreteDensity;
    }

    public String getTotalAggregateContent() {
        return totalAggregateContent;
    }

    public void setTotalAggregateContent(String totalAggregateContent) {
        this.totalAggregateContent = totalAggregateContent;
    }

    public String getGradingFines() {
        return gradingFines;
    }

    public void setGradingFines(String gradingFines) {
        this.gradingFines = gradingFines;
    }

    public String getProportionOfFines() {
        return proportionOfFines;
    }

    public void setProportionOfFines(String proportionOfFines) {
        this.proportionOfFines = proportionOfFines;
    }

    public String getFineAggregateContent() {
        return fineAggregateContent;
    }

    public void setFineAggregateContent(String fineAggregateContent) {
        this.fineAggregateContent = fineAggregateContent;
    }

    public String getCourseAggregateContent() {
        return courseAggregateContent;
    }

    public void setCourseAggregateContent(String courseAggregateContent) {
        this.courseAggregateContent = courseAggregateContent;
    }

    public Map<String, String> getAttributes() {
        Map<String, String> attributes = new LinkedHashMap<>();
        attributes.put("Compressive Strength", compressiveStrength +" N/mm²");
        attributes.put("Defective Rate", defectiveRate );
        attributes.put("Standard Deviation ", standardDeviation +" N/mm²");
        attributes.put("Margin", margin +" N/mm²");
        attributes.put("Cement Content", cementContent +" kg/m³");
        attributes.put("Target Mean Strength", targetMeanStrength +" N/mm²");
        attributes.put("Free Water Cement Ratio", freeWaterCementRatio);
        attributes.put("Result Selection", spinnerResultSelection);
        attributes.put("Coarse Aggregate Type", spinnerAggregateTypeCoarse);
        attributes.put("Fine Aggregate Type", spinnerAggregateTypeFine);
        attributes.put("Slump ", spinnerSlump +" (mm)");
        attributes.put("Max Agg ", spinnerMaxAgg +" mm");
        attributes.put("Free Water Content ", freeWaterContent +" kg/m³");
        attributes.put("Max Cement Content ", maxCementContent +" kg/m³");
        attributes.put("Min Cement Content ", minCementContent +" kg/m³");
        attributes.put("Relative Density", relativeDensity);
        attributes.put("Concrete Density ", concreteDensity +" kg/m³");
        attributes.put("Total Aggregate Content ", totalAggregateContent +" kg/m³");
        attributes.put("Grading Fines ", gradingFines +" %");
        attributes.put("Proportion of Fines", proportionOfFines +" %");
        attributes.put("Fine Aggregate Content ", fineAggregateContent +" kg/m³");
        attributes.put("Course Aggregate Content ", courseAggregateContent +" kg/m³");
        return attributes;
    }
}

