package com.raeden.lab_reports.managers;

import com.raeden.lab_reports.models.character.Character;
import com.raeden.lab_reports.models.character.types.EncounterCharacter;
import com.raeden.lab_reports.models.character.types.PlayerCharacter;
import com.raeden.lab_reports.models.Pointers;

import java.util.ArrayList;
import java.util.List;

import static com.raeden.lab_reports.Main.playerSavePath;
import static com.raeden.lab_reports.managers.FileManager.saveJsonFile;
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
        Pointers pointer = null;
        boolean isLogicValid = false;
        for(Pointers p : Pointers.values()) {
            if(p.name().equalsIgnoreCase(vars[4].trim())) {
                isLogicValid = true;
                pointer = p;
                break;
            }
        }

        if(pointer != null && (!pointer.equals(Pointers.PLAYER) && !pointer.equals(Pointers.ENCOUNTER))) {
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

    public void createCharacter(Pointers pointer, String name, String description, int maxHealth, int damage) {
        Character character = null;

        if (pointer.equals(Pointers.PLAYER)) {
            character = new PlayerCharacter(Pointers.PLAYER.name().toLowerCase(), name, description);
        } else if (pointer.equals(Pointers.ENCOUNTER)) {
            character = new EncounterCharacter(Pointers.ENCOUNTER.name().toLowerCase(), name, description);
        } else {
            character = new PlayerCharacter(Pointers.PLAYER.name().toLowerCase(), name, description);
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