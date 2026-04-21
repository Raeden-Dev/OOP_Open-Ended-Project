package com.raeden.lab_reports.ui;

import com.raeden.lab_reports.managers.DungeonManager;
import com.raeden.lab_reports.managers.GlobalManager;
import com.raeden.lab_reports.managers.PlayerManager;

import static com.raeden.lab_reports.Main.menuID;
import static com.raeden.lab_reports.ui.printStrings.*;
import static com.raeden.lab_reports.Main.programRunning;

public class MenuNavigator {
    private DungeonManager dungeonManager;
    private PlayerManager playerManager;
    private GlobalManager globalManager;

    public MenuNavigator(DungeonManager d, PlayerManager p, GlobalManager g) {
        this.dungeonManager = d;
        this.playerManager = p;
        this.globalManager = g;
    }

    public void menuNavListener(String inputStr) {
        switch (inputStr) {
            case "1":
                printSimulate();
                menuID = "s";
                break;
            case "2":
                printCreateDungeon();
                menuID = "cd";
                break;
            case "3":
                printCreatePlayer();
                menuID = "cp";
                break;
            case "4":
                printManage();
                menuID = "m";
                break;
            case "5":
                programRunning = false;
                printInfo("Exiting Dungeon Manager...");
                break;
            default:
                menuID = "mm";
                printInvalidSelected(inputStr);
        }
    }

    public void simulateNavListener(String inputStr) {
        switch (inputStr) {
            case "0":
                menuID = "mm";
                printMenu();
                break;
            default:
                menuID = "s";
                printInvalidSelected(inputStr);
        }
    }

    public void createDungeonNavListener(String inputStr) {
        if (inputStr.equals("0")) {
            menuID = "mm";
            printMenu();
        } else {
            dungeonManager.parseDungeonCreationInput(inputStr);
        }
    }

    public void createPlayerNavListener(String inputStr) {
        if (inputStr.equals("0")) {
            menuID = "mm";
            printMenu();
        } else {
            playerManager.parsePlayerCreationInput(inputStr);
        }
    }

    public void editDungeonNavListener(String inputStr) {
        String cleanInput = inputStr.trim();
        if (cleanInput.equals("0")) {
            menuID = "m";
            printManage();
        }
        else if(cleanInput.startsWith("-d")) {
            globalManager.deleteDungeon(cleanInput);
        }
        else {
            globalManager.editDungeon(cleanInput);
        }
    }

    public void editPlayerNavListener(String inputStr) {
        String cleanInput = inputStr.trim();
        if (cleanInput.equals("0")) {
            menuID = "m";
            printManage();
        }
        else if(cleanInput.startsWith("-d")) {
            globalManager.deletePlayer(inputStr);
        }
        else {
            globalManager.editPlayer(inputStr);
        }
    }


    public void manageNavListener(String inputStr) {
        switch (inputStr) {
            case "0":
                menuID = "mm";
                printMenu();
                break;
            case "1":
                globalManager.showAvailableDungeons();
                break;
            case "2":
                globalManager.showAvailablePlayers();
                break;
            case "3":
                menuID = "ed";
                printEditDungeon();
                break;
            case "4":
                menuID = "ep";
                printEditPlayer();
                break;
            default:
                menuID = "m";
                printInvalidSelected(inputStr);
        }
    }

}
