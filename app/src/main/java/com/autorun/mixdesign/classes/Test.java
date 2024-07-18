package com.autorun.mixdesign.classes;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private long id;
    private long projectId;
    private String waterTestsPH;
    private String sulphate;
    private String phosphate;
    private String nitrate;
    private String tss;
    private String tds;
    private String carbonate;
    private String bicarbonate;
    private String waterAbsorption;
    private String specificGravity;
    private String tenPercentFinesValue;
    private String aggregateCrushingValue;
    private String aggregateImpactValue;
    private String flakinessIndex;
    private String elongationIndex;
    private String bulkDensity;
    private String silkContent;
    private String organicContent;
    private String fineAggregateSpecificGravity;
    private String cementFineness;
    private String settingTimeInitial;
    private String settingTimeFinal;

    // Constructors
    public Test() {
    }

    public Test(long projectId, String waterTestsPH, String sulphate, String phosphate, String nitrate, String tss,
                String tds, String carbonate, String bicarbonate, String waterAbsorption, String specificGravity,
                String tenPercentFinesValue, String aggregateCrushingValue, String aggregateImpactValue,
                String flakinessIndex, String elongationIndex, String bulkDensity, String silkContent,
                String organicContent, String fineAggregateSpecificGravity, String cementFineness,
                String settingTimeInitial, String settingTimeFinal) {
        this.projectId = projectId;
        this.waterTestsPH = waterTestsPH;
        this.sulphate = sulphate;
        this.phosphate = phosphate;
        this.nitrate = nitrate;
        this.tss = tss;
        this.tds = tds;
        this.carbonate = carbonate;
        this.bicarbonate = bicarbonate;
        this.waterAbsorption = waterAbsorption;
        this.specificGravity = specificGravity;
        this.tenPercentFinesValue = tenPercentFinesValue;
        this.aggregateCrushingValue = aggregateCrushingValue;
        this.aggregateImpactValue = aggregateImpactValue;
        this.flakinessIndex = flakinessIndex;
        this.elongationIndex = elongationIndex;
        this.bulkDensity = bulkDensity;
        this.silkContent = silkContent;
        this.organicContent = organicContent;
        this.fineAggregateSpecificGravity = fineAggregateSpecificGravity;
        this.cementFineness = cementFineness;
        this.settingTimeInitial = settingTimeInitial;
        this.settingTimeFinal = settingTimeFinal;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getWaterTestsPH() {
        return waterTestsPH;
    }

    public void setWaterTestsPH(String waterTestsPH) {
        this.waterTestsPH = waterTestsPH;
    }

    public String getSulphate() {
        return sulphate;
    }

    public void setSulphate(String sulphate) {
        this.sulphate = sulphate;
    }

    public String getPhosphate() {
        return phosphate;
    }

    public void setPhosphate(String phosphate) {
        this.phosphate = phosphate;
    }

    public String getNitrate() {
        return nitrate;
    }

    public void setNitrate(String nitrate) {
        this.nitrate = nitrate;
    }

    public String getTss() {
        return tss;
    }

    public void setTss(String tss) {
        this.tss = tss;
    }

    public String getTds() {
        return tds;
    }

    public void setTds(String tds) {
        this.tds = tds;
    }

    public String getCarbonate() {
        return carbonate;
    }

    public void setCarbonate(String carbonate) {
        this.carbonate = carbonate;
    }

    public String getBicarbonate() {
        return bicarbonate;
    }

    public void setBicarbonate(String bicarbonate) {
        this.bicarbonate = bicarbonate;
    }

    public String getWaterAbsorption() {
        return waterAbsorption;
    }

    public void setWaterAbsorption(String waterAbsorption) {
        this.waterAbsorption = waterAbsorption;
    }

    public String getSpecificGravity() {
        return specificGravity;
    }

    public void setSpecificGravity(String specificGravity) {
        this.specificGravity = specificGravity;
    }

    public String getTenPercentFinesValue() {
        return tenPercentFinesValue;
    }

    public void setTenPercentFinesValue(String tenPercentFinesValue) {
        this.tenPercentFinesValue = tenPercentFinesValue;
    }

    public String getAggregateCrushingValue() {
        return aggregateCrushingValue;
    }

    public void setAggregateCrushingValue(String aggregateCrushingValue) {
        this.aggregateCrushingValue = aggregateCrushingValue;
    }

    public String getAggregateImpactValue() {
        return aggregateImpactValue;
    }

    public void setAggregateImpactValue(String aggregateImpactValue) {
        this.aggregateImpactValue = aggregateImpactValue;
    }

    public String getFlakinessIndex() {
        return flakinessIndex;
    }

    public void setFlakinessIndex(String flakinessIndex) {
        this.flakinessIndex = flakinessIndex;
    }

    public String getElongationIndex() {
        return elongationIndex;
    }

    public void setElongationIndex(String elongationIndex) {
        this.elongationIndex = elongationIndex;
    }

    public String getBulkDensity() {
        return bulkDensity;
    }

    public void setBulkDensity(String bulkDensity) {
        this.bulkDensity = bulkDensity;
    }

    public String getSilkContent() {
        return silkContent;
    }

    public void setSilkContent(String silkContent) {
        this.silkContent = silkContent;
    }

    public String getOrganicContent() {
        return organicContent;
    }

    public void setOrganicContent(String organicContent) {
        this.organicContent = organicContent;
    }

    public String getFineAggregateSpecificGravity() {
        return fineAggregateSpecificGravity;
    }

    public void setFineAggregateSpecificGravity(String fineAggregateSpecificGravity) {
        this.fineAggregateSpecificGravity = fineAggregateSpecificGravity;
    }

    public String getCementFineness() {
        return cementFineness;
    }

    public void setCementFineness(String cementFineness) {
        this.cementFineness = cementFineness;
    }

    public String getSettingTimeInitial() {
        return settingTimeInitial;
    }

    public void setSettingTimeInitial(String settingTimeInitial) {
        this.settingTimeInitial = settingTimeInitial;
    }

    public String getSettingTimeFinal() {
        return settingTimeFinal;
    }

    public void setSettingTimeFinal(String settingTimeFinal) {
        this.settingTimeFinal = settingTimeFinal;
    }

    // Method to retrieve all attributes as a Map
    public Map<String, String> getAttributes() {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("pH at 22.3 ", waterTestsPH +" ℃");
        attributes.put("Sulphates", sulphate);
        attributes.put("Phosphates", phosphate);
        attributes.put("Nitrates", nitrate);
        attributes.put("Total Suspended Solids", tss);
        attributes.put("Total Dissolved Solids", tds);
        attributes.put("Carbonate", carbonate);
        attributes.put("Bicarbonate", bicarbonate);
        attributes.put("Water Absorption", waterAbsorption);
        attributes.put("Specific Gravity", specificGravity);
        attributes.put("Ten Percent Fines Value", tenPercentFinesValue);
        attributes.put("Aggregate Crushing Value", aggregateCrushingValue);
        attributes.put("Aggregate Impact Value", aggregateImpactValue);
        attributes.put("Flakiness Index", flakinessIndex);
        attributes.put("Elongation Index", elongationIndex);
        attributes.put("Bulk Density", bulkDensity);
        attributes.put("Silk Content", silkContent);
        attributes.put("Organic Content", organicContent);
        attributes.put("Fine Aggregate Specific Gravity", fineAggregateSpecificGravity);
        attributes.put("Cement Fineness", cementFineness);
        attributes.put("Setting Time - Initial", settingTimeInitial);
        attributes.put("Setting Time - Final", settingTimeFinal);
        return attributes;
    }
}
