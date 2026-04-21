package com.raeden.lab_reports.models.character.types;

import com.raeden.lab_reports.models.character.Character;
import com.raeden.lab_reports.models.character.CharacterActions;

import static com.raeden.lab_reports.ui.printStrings.printCharacterAction;

public class EncounterCharacter extends Character implements CharacterActions {
    public EncounterCharacter(String characterType, String name, String description) {
        super(characterType, name, description);
    }

    @Override
    public void attack() {
        printCharacterAction(getName(),"The encounter rushed into an attack ruthlessly!");
    }

    @Override
    public void skillUse() {
        printCharacterAction(getName(),"Used his subpar skill against opponent!");
    }

    @Override
    public void loot() {
        printCharacterAction(getName(),"Hey can encounters even loot?!");
    }

    @Override
    public void kill() {
        printCharacterAction(getName(),"The encounter killed his opponent!");
    }

    @Override
    public void death() {
        printCharacterAction(getName(),"The encounter died as fate dictated...");
    }
}
