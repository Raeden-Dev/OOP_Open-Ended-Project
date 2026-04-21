package com.raeden.lab_reports.managers;

import com.raeden.lab_reports.models.character.Character;
import com.raeden.lab_reports.models.character.types.EncounterCharacter;
import com.raeden.lab_reports.models.character.types.PlayerCharacter;
import com.raeden.lab_reports.models.dungeon.DungeonPointers;

import java.util.ArrayList;
import java.util.List;

import static com.raeden.lab_reports.Main.playerSavePath;
import static com.raeden.lab_reports.models.filemanager.FileManager.saveJsonFile;
import static com.raeden.lab_reports.ui.printStrings.*;

public class PlayerManager {
    private final List<Character> activeCharacters = new ArrayList<>();


    public void parsePlayerCreationInput(String input) {
        String[] vars = input.trim().split(",");

        if(vars.length < 5) {
            printWarning("Expected 5 inputs. Found " + vars.length + "!");
            return;
        }

        String name = vars[0].trim().replace(" ", "_");
        String description = vars[1].trim();
        int maxHealth, damage;
        DungeonPointers pointer = null;
        boolean isLogicValid = false;
        for(DungeonPointers p : DungeonPointers.values()) {
            if(p.name().equalsIgnoreCase(vars[4].trim())) {
                isLogicValid = true;
                pointer = p;
                break;
            }
        }

        if(pointer != null && (!pointer.equals(DungeonPointers.PLAYER) && !pointer.equals(DungeonPointers.ENCOUNTER))) {
            isLogicValid = false;
        }

        if(!isLogicValid) {
            printWarning("Invalid character type! Must be PLAYER or ENCOUNTER.");
            return;
        }
        try {
            maxHealth = Integer.parseInt(vars[2].trim());
            damage = Integer.parseInt(vars[3].trim());
        } catch (NumberFormatException e) {
            printWarning("Invalid health or damage stats! They must be numbers.");
            return;
        }

        createCharacter(pointer, name, description, maxHealth, damage);
    }

    public void createCharacter(DungeonPointers pointer, String name, String description, int maxHealth, int damage) {
        Character character = null;

        if (pointer.equals(DungeonPointers.PLAYER)) {
            character = new PlayerCharacter(DungeonPointers.PLAYER.name().toLowerCase(), name, description);
        } else if (pointer.equals(DungeonPointers.ENCOUNTER)) {
            character = new EncounterCharacter(DungeonPointers.ENCOUNTER.name().toLowerCase(), name, description);
        } else {
            character = new PlayerCharacter(DungeonPointers.PLAYER.name().toLowerCase(), name, description);
        }

        character.setMaxHealth(maxHealth);
        character.setCurHealth(maxHealth);
        character.setDamage(damage);

        printInfo("Created character with ID: " + character.getCharacterID());
        String pName = character.getName() + "_" + character.getCharacterID();
        saveJsonFile(pName, playerSavePath.resolve(pName + ".json"), character);
        activeCharacters.add(character);
    }

    public void showActiveCharacters() {
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