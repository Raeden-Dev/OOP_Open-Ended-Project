package com.raeden.lab_reports.models.character.types;

import com.raeden.lab_reports.models.character.Character;
import com.raeden.lab_reports.models.character.CharacterActions;

import static com.raeden.lab_reports.ui.printStrings.printCharacterAction;

public class PlayerCharacter extends Character implements CharacterActions {
    public PlayerCharacter(String characterType, String name, String description) {
        super(characterType, name, description);
    }

    @Override
    public void attack() {
        printCharacterAction(getName(),"The player plunged into an attack fearlessly!");
    }

    @Override
    public void skillUse() {
        printCharacterAction(getName(),"Used his amazing skill!");
    }

    @Override
    public void loot() {
        printCharacterAction(getName(),"The player looted all the treasures!");
    }

    @Override
    public void kill() {
        printCharacterAction(getName(),"The player killed the encounter!");
    }

    @Override
    public void death() {
        printCharacterAction(getName(),"The player has died a heroic death!");
    }
}
