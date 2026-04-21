package com.raeden.lab_reports;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raeden.lab_reports.managers.DungeonManager;
import com.raeden.lab_reports.managers.GlobalManager;
import com.raeden.lab_reports.managers.PlayerManager;
import com.raeden.lab_reports.ui.MenuNavigator;

import java.io.File;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

import static com.raeden.lab_reports.models.filemanager.FileManager.createDirectory;
import static com.raeden.lab_reports.ui.printStrings.*;

public class Main {
    public static final Random rand = new Random();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static String DEFAULT_SAVE_LOCATION = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "DungeonManager";
    public static Path dungeonSavePath = Path.of(DEFAULT_SAVE_LOCATION).resolve("dungeons");
    public static Path playerSavePath = Path.of(DEFAULT_SAVE_LOCATION).resolve("players");

    public static boolean programRunning = true;
    public static String menuID  = "mm"; // mm, s, cd, cp, m

    static {
        printSeparator();
        System.out.println("Booting up dungeon manager...");
        createDirectory(Path.of(DEFAULT_SAVE_LOCATION), true);
        createDirectory(dungeonSavePath, true);
        createDirectory(playerSavePath, true);
    }

    public static void main(String[] args) {
        DungeonManager dungeonManager = new DungeonManager();
        PlayerManager playerManager = new PlayerManager();
        GlobalManager manager = new GlobalManager();
        MenuNavigator mNav = new MenuNavigator(dungeonManager, playerManager, manager);

        Scanner sc = new Scanner(System.in);

        printMenu();
        while (programRunning) {
            String stringInput;
            if (menuID.equals("cd")) {
                printInput("Creating Dungeon");
                stringInput = sc.nextLine();
            }
            else if(menuID.equals("cp")) {
                printInput("Creating Player");
                stringInput = sc.nextLine();
            }
            else if(menuID.equals("ed")) {
                printInput("Editing Dungeon");
                stringInput = sc.nextLine();
            }
            else if(menuID.equals("ep")) {
                printInput("Editing Player");
                stringInput = sc.nextLine();
            }
            else if(menuID.equals("m")) {
                printSelection();
                stringInput = sc.nextLine();
            }
            else {
                printSelection();
                stringInput = sc.nextLine();
            }

            switch (menuID) {
                case "s":
                    mNav.simulateNavListener(stringInput);
                    break;
                case "cd":
                    mNav.createDungeonNavListener(stringInput);
                    break;
                case "cp":
                    mNav.createPlayerNavListener(stringInput);
                    break;
                case "m":
                    mNav.manageNavListener(stringInput);
                    break;
                case "ed":
                    mNav.editDungeonNavListener(stringInput);
                    break;
                case "ep":
                    mNav.editPlayerNavListener(stringInput);
                    break;
                default:
                    mNav.menuNavListener(stringInput);
            }
        }
    }
}