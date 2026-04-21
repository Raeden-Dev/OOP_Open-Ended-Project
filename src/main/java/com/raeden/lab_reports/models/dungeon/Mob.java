package com.raeden.lab_reports.models.dungeon;

import java.util.UUID;

public abstract class Mob {
    private final String mobID = UUID.randomUUID().toString();
    private final String mobName;
    private int mobDamage;
    private int maxHealth;
    private int curHealth;

    private int totalDefeated;

    public Mob(String mobName) {
        this.mobName = mobName;
        this.mobDamage = 5;
        this.maxHealth = 25;
        this.curHealth = 25;
        this.totalDefeated = 0;
    }

    public Mob(String mobName, int mobDamage, int maxHealth) {
        this.mobName = mobName;
        this.mobDamage = mobDamage;
        this.maxHealth = maxHealth;
        this.curHealth = maxHealth;
        this.totalDefeated = 0;
    }

    public int getMobDamage() {
        return mobDamage;
    }

    public void setMobDamage(int mobDamage) {
        this.mobDamage = mobDamage;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurHealth() {
        return curHealth;
    }

    public void setCurHealth(int curHealth) {
        this.curHealth = curHealth;
    }

    public int getTotalDefeated() {
        return totalDefeated;
    }

    public void setTotalDefeated(int totalDefeated) {
        this.totalDefeated = totalDefeated;
    }

    public String getMobID() {
        return mobID;
    }

    public String getMobName() {
        return mobName;
    }
}
