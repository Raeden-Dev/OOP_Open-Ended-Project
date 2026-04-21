package com.raeden.lab_reports.managers;

import com.raeden.lab_reports.models.character.Character;
import com.raeden.lab_reports.models.character.types.EncounterCharacter;
import com.raeden.lab_reports.models.character.types.PlayerCharacter;
import com.raeden.lab_reports.models.dungeon.DungeonPointers;

import java.util.ArrayList;
import java.util.List;

import static com.raeden.lab_reports.ui.printStrings.*;

public class PlayerManager {
    private final List<Character> activeCharacters = new ArrayList<>();

    public void createCharacter(DungeonPointers pointer, String name, String description, int maxHealth, int damage) {
        Character character = null;

        if (pointer.equals(DungeonPointers.PLAYER)) {
            character = new PlayerCharacter(name, description);
        } else if (pointer.equals(DungeonPointers.ENCOUNTER)) {
            character = new EncounterCharacter(name, description);
        } else {
            character = new PlayerCharacter(name, description);
        }

        character.setMaxHealth(maxHealth);
        character.setCurHealth(maxHealth);
        character.setDamage(damage);

        printInfo("Created character with ID: " + character.getCharacterID());
        activeCharacters.add(character);
    }

    public void showCharacters() {
        if(activeCharacters.isEmpty()) {
            printInfo("There are no active characters. Please create a character first!");
            return;
        }
        for(Character c : activeCharacters) {
            printOptionDash("[" + c.getName() + "] HP: " + c.getMaxHealth() + " | DMG: "
                    + c.getDamage() + "    //    {" + c.getCharacterID() + "}");
        }
    }

    public List<Character> getActiveCharacters() {
        return activeCharacters;
    }

    public void addCharacter(Character character) {
        this.activeCharacters.add(character);
    }

    public Character getCharacter(String id) {
        Character c = activeCharacters.stream()
                .filter(character -> character.getCharacterID().equals(id))
                .findFirst()
                .orElse(null);

        if(c == null) printWarning("Could not find a character with id: " + id);
        return c;
    }
}