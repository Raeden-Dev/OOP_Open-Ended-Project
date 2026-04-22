package com.raeden.lab_reports.managers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.raeden.lab_reports.models.character.Character;
import com.raeden.lab_reports.models.character.types.EncounterCharacter;
import com.raeden.lab_reports.models.character.types.PlayerCharacter;
import com.raeden.lab_reports.models.dungeon.Dungeon;
import com.raeden.lab_reports.models.dungeon.types.AStarDungeon;
import com.raeden.lab_reports.models.dungeon.types.BFSDungeon;
import com.raeden.lab_reports.models.dungeon.types.DFSDungeon;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static com.raeden.lab_reports.Main.*;
import static com.raeden.lab_reports.ui.printStrings.*;

public class GlobalManager {

    public void showAvailableDungeons() {
        File dungeonDir = dungeonSavePath.toFile();
        if (!dungeonDir.exists() || dungeonDir.listFiles() == null || dungeonDir.listFiles().length == 0) {
            printInfo("No saved dungeons were found...");
            return;
        }

        int totalFiles = dungeonDir.listFiles().length;
        printInfo("Available Saved Dungeons: " + totalFiles);
        for (File file : dungeonDir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".json")) {
                String cleanId = file.getName().replace(".json", "");
                printOptionDash(cleanId);
            }
        }
    }

    public void showAvailablePlayers() {
        File playerDir = playerSavePath.toFile();
        if (!playerDir.exists() || playerDir.listFiles() == null || playerDir.listFiles().length == 0) {
            printInfo("No saved players were found...");
            return;
        }

        int totalFiles = playerDir.listFiles().length;
        printInfo("Available Saved Players: " + totalFiles);
        for (File file : playerDir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".json")) {
                String cleanId = file.getName().replace(".json", "");
                printOptionDash(cleanId);
            }
        }
    }

    public void deleteDungeon(String command) {
        String targetUuid = command.replace("-d", "").trim();

        if (targetUuid.isEmpty()) {
            printWarning("Please provide a valid UUID to delete.");
            return;
        }

        File dungeonDir = dungeonSavePath.toFile();
        if (!dungeonDir.exists() || dungeonDir.listFiles() == null) {
            printWarning("No saved dungeons folder was found...");
            return;
        }

        boolean fileDeleted = false;

        for (File file : dungeonDir.listFiles()) {
            if (file.isFile() && file.getName().contains(targetUuid)) {
                if (file.delete()) {
                    printInfo("Successfully deleted dungeon file: " + file.getName());
                    fileDeleted = true;
                } else {
                    printWarning("Found the dungeon, but failed to delete: " + file.getName());
                }
                break;
            }
        }

        if (!fileDeleted) {
            printWarning("Could not find any dungeon matching UUID: " + targetUuid);
        }
    }

    public void deletePlayer(String command) {
        String targetUuid = command.replace("-d", "").trim();

        if (targetUuid.isEmpty()) {
            printWarning("Please provide a valid UUID to delete.");
            return;
        }

        File playerDir = playerSavePath.toFile();

        if (!playerDir.exists() || playerDir.listFiles() == null) {
            printWarning("No saved players folder was found...");
            return;
        }

        boolean fileDeleted = false;

        for (File file : playerDir.listFiles()) {
            if (file.isFile() && file.getName().contains(targetUuid)) {
                if (file.delete()) {
                    printInfo("Successfully deleted player file: " + file.getName());
                    fileDeleted = true;
                } else {
                    printWarning("Found the player, but failed to delete: " + file.getName());
                }
                break;
            }
        }

        if (!fileDeleted) {
            printWarning("Could not find any player matching UUID: " + targetUuid);
        }
    }

    public void editDungeon(String id) {
        File dungeonDir = dungeonSavePath.toFile();
        if (!dungeonDir.exists() || dungeonDir.listFiles() == null) {
            printWarning("No saved dungeons folder was found...");
            return;
        }

        File oldFile = null;
        for (File file : dungeonDir.listFiles()) {
            if (file.isFile() && file.getName().contains(id)) {
                oldFile = file;
                break;
            }
        }

        if (oldFile == null) {
            printWarning("Could not find any dungeon matching UUID: " + id);
            return;
        }

        Path oldFilePath = oldFile.toPath();
        Dungeon targetDungeon = null;

        try (Reader reader = Files.newBufferedReader(oldFilePath)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            if (!jsonObject.has("generationLogic")) {
                printWarning("File is missing generationLogic! Cannot edit.");
                return;
            }

            String logic = jsonObject.get("generationLogic").getAsString();

            if (logic.equalsIgnoreCase("BFS_GEN")) {
                targetDungeon = GSON.fromJson(jsonObject, BFSDungeon.class);
            } else if (logic.equalsIgnoreCase("DFS_GEN")) {
                targetDungeon = GSON.fromJson(jsonObject, DFSDungeon.class);
            } else if (logic.equalsIgnoreCase("A_STAR_GEN")) {
                targetDungeon = GSON.fromJson(jsonObject, AStarDungeon.class);
            } else {
                printWarning("Unknown generation logic: " + logic);
                return;
            }

        } catch (Exception e) {
            printWarning("Failed to parse dungeon JSON file.\n" + e);
            return;
        }

        if (targetDungeon == null) return;

        printInfo("Editing Dungeon: " + targetDungeon.getDungeonName());
        System.out.print("  | Input New Variables (Name, SizeX, SizeY) >>>  ");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] vars = input.trim().split(",");

        if (vars.length < 3) {
            printWarning("Expected 3 inputs for editing. Edit cancelled.");
            return;
        }

        try {
            String newName = vars[0].trim().replace(" ", "_");
            int newX = Integer.parseInt(vars[1].trim());
            int newY = Integer.parseInt(vars[2].trim());

            targetDungeon.setDungeonName(newName);
            targetDungeon.setDungeonSizeX(newX);
            targetDungeon.setDungeonSizeY(newY);

            try {
                Files.deleteIfExists(oldFilePath);
            } catch (Exception e) {
                printWarning("Failed to delete old file, but will save new one.");
            }

            String newFileName = targetDungeon.getDungeonName() + "_" + targetDungeon.getDungeonID();
            Path newFilePath = dungeonSavePath.resolve(newFileName + ".json");
            FileManager.saveJsonFile(newFileName, newFilePath, targetDungeon, true);

            printInfo("Successfully updated dungeon!");

        } catch (NumberFormatException e) {
            printWarning("Invalid size format! Edit cancelled.");
        }
    }

    public void editPlayer(String id) {
        File playerDir = playerSavePath.toFile();
        if (!playerDir.exists() || playerDir.listFiles() == null) {
            printWarning("No saved players folder was found...");
            return;
        }

        File oldFile = null;
        for (File file : playerDir.listFiles()) {
            if (file.isFile() && file.getName().contains(id)) {
                oldFile = file;
                break;
            }
        }

        if (oldFile == null) {
            printWarning("Could not find any player matching UUID: " + id);
            return;
        }

        Path oldFilePath = oldFile.toPath();
        Character targetCharacter = null;

        try (Reader reader = Files.newBufferedReader(oldFilePath)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            if (!jsonObject.has("characterType")) {
                printWarning("File is missing characterType! Cannot edit.");
                return;
            }

            String type = jsonObject.get("characterType").getAsString();

            if (type.equalsIgnoreCase("player")) {
                targetCharacter = GSON.fromJson(jsonObject, PlayerCharacter.class);
            } else if (type.equalsIgnoreCase("encounter")) {
                targetCharacter = GSON.fromJson(jsonObject, EncounterCharacter.class);
            } else {
                printWarning("Unknown character type: " + type);
                return;
            }

        } catch (Exception e) {
            printWarning("Failed to parse player JSON file.");
            return;
        }

        if (targetCharacter == null) return;

        printInfo("Editing Player: " + targetCharacter.getName());
        System.out.print("  | Input New Variables (Name, Description, Health, Damage) >>>  ");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] vars = input.trim().split(",");

        if (vars.length < 4) {
            printWarning("Expected 4 inputs for editing. Edit cancelled.");
            return;
        }

        try {
            String newName = vars[0].trim().replace(" ", "_");
            String newDesc = vars[1].trim();
            int newHealth = Integer.parseInt(vars[2].trim());
            int newDamage = Integer.parseInt(vars[3].trim());

            targetCharacter.setName(newName);
            targetCharacter.setDescription(newDesc);
            targetCharacter.setMaxHealth(newHealth);
            targetCharacter.setCurHealth(newHealth);
            targetCharacter.setDamage(newDamage);

            try {
                Files.deleteIfExists(oldFilePath);
            } catch (Exception e) {
                printWarning("Failed to delete old file, but will save new one.");
            }

            String newFileName = targetCharacter.getName() + "_" + targetCharacter.getCharacterID();
            Path newFilePath = playerSavePath.resolve(newFileName + ".json");
            FileManager.saveJsonFile(newFileName, newFilePath, targetCharacter, true);

            printInfo("Successfully updated player!");

        } catch (NumberFormatException e) {
            printWarning("Invalid health/damage format! Edit cancelled.");
        }
    }
}