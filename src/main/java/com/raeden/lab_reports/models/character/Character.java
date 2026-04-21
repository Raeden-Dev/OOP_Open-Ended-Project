package com.raeden.lab_reports.models.character;

import java.util.UUID;

public abstract class Character {
    private final String characterID = UUID.randomUUID().toString();
    private final String name;
    private final String description;
    private int maxHealth;
    private int curHealth;
    private int damage;

    protected Character(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getCharacterID() {
        return characterID;
    }
}
