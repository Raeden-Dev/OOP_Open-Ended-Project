package com.raeden.lab_reports.models.dungeon;

import java.util.UUID;

public abstract class Modifier {
    private final String mobID = UUID.randomUUID().toString();
    private final String modifierName;
    private final String modifierDescription;
    private int modifierDuration;
    private int modifierCurDuration;

    public Modifier(String modifierName, String modifierDescription) {
        this.modifierName = modifierName;
        this.modifierDescription = modifierDescription;
        this.modifierDuration = 10;
        this.modifierCurDuration = 10;
    }

    public Modifier(String modifierName, String modifierDescription, int modifierDuration) {
        this.modifierName = modifierName;
        this.modifierDescription = modifierDescription;
        this.modifierDuration = modifierDuration;
        this.modifierCurDuration = modifierDuration;
    }

    public String getMobID() {
        return mobID;
    }

    public String getModifierName() {
        return modifierName;
    }

    public String getModifierDescription() {
        return modifierDescription;
    }

    public int getModifierDuration() {
        return modifierDuration;
    }

    public void setModifierDuration(int modifierDuration) {
        this.modifierDuration = modifierDuration;
    }

    public int getModifierCurDuration() {
        return modifierCurDuration;
    }

    public void setModifierCurDuration(int modifierCurDuration) {
        this.modifierCurDuration = modifierCurDuration;
    }
}
