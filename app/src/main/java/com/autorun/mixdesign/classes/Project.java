package com.autorun.mixdesign.classes;

public class Project {
    private long id;
    private String name;
    private String characteristicStrength;
    private String atDays;
    private String mixDesignStandard;

    public Project(long id,String name, String characteristicStrength, String atDays, String mixDesignStandard) {
        this.id = id;
        this.name = name;
        this.characteristicStrength = characteristicStrength;
        this.atDays = atDays;
        this.mixDesignStandard = mixDesignStandard;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacteristicStrength() {
        return characteristicStrength;
    }

    public void setCharacteristicStrength(String characteristicStrength) {
        this.characteristicStrength = characteristicStrength;
    }

    public String getAtDays() {
        return atDays;
    }

    public void setAtDays(String atDays) {
        this.atDays = atDays;
    }

    public String getMixDesignStandard() {
        return mixDesignStandard;
    }

    public void setMixDesignStandard(String mixDesignStandard) {
        this.mixDesignStandard = mixDesignStandard;
    }
}
