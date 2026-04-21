package com.raeden.lab_reports.ui;

import com.raeden.lab_reports.managers.DungeonManager;
import com.raeden.lab_reports.managers.PlayerManager;

import static com.raeden.lab_reports.Main.menuID;
import static com.raeden.lab_reports.ui.printStrings.*;
import static com.raeden.lab_reports.Main.programRunning;

public class MenuNavigator {
    private DungeonManager dungeonManager;
    private PlayerManager playerManager;

    public MenuNavigator(DungeonManager d, PlayerManager p) {
        this.dungeonManager = d;
        this.playerManager = p;
    }

    public void menuNavListener(int sel) {
        switch (sel) {
            case 1:
                printSimulate();
                menuID = "s";
                break;
            case 2:
                printCreateDungeon();
                menuID = "cd";
                break;
            case 3:
                printCreatePlayer();
                menuID = "cp";
                break;
            case 4:
                printManage();
                menuID = "m";
                break;
            case 5:
                programRunning = false;
                printInfo("Exiting Dungeon Manager...");
                break;
            default:
                menuID = "mm";
                printInvalidSelected(String.valueOf(sel));
        }
    }

    public void simulateNavListener(int sel) {
        switch (sel) {
            case 0:
                menuID = "mm";
                printMenu();
                break;
            default:
                menuID = "s";
                printInvalidSelected(String.valueOf(sel));
        }
    }

    public void createDungeonNavListener(String inputStr) {
        if (inputStr.equals("0")) {
            menuID = "mm";
            printMenu();
            return;
        }
    }

    public void createPlayerNavListener(String inputStr) {
        if (inputStr.equals("0")) {
            menuID = "mm";
            printMenu();
            return;
        }
    }

    public void manageNavListener(int sel) {
        switch (sel) {
            case 0:
                menuID = "mm";
                printMenu();
                break;
            case 1:
                dungeonManager.showDungeons();
                break;
            case 2:
                playerManager.showCharacters();
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                menuID = "m";
                printInvalidSelected(String.valueOf(sel));
        }
    }

}
