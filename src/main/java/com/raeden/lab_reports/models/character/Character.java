package com.raeden.lab_reports.models.character;

import java.util.UUID;

public abstract class Character {
    private final String characterID = UUID.randomUUID().toString();
    private String characterType;
    private String name;
    private String description;
    private int maxHealth;
    private int curHealth;
    private int damage;

    protected Character(String characterType, String name, String description) {
        this.characterType = characterType;
        this.name = name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCharacterType() {
        return characterType;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }
}
