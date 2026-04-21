package com.raeden.lab_reports;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raeden.lab_reports.managers.DungeonManager;
import com.raeden.lab_reports.managers.PlayerManager;
import com.raeden.lab_reports.ui.MenuNavigator;

import java.util.Scanner;

import static com.raeden.lab_reports.ui.printStrings.*;

public class Main {
    public static boolean programRunning = true;
    public static String menuID  = "mm"; // mm, s, cd, cp, m

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static void main(String[] args) {
        DungeonManager dungeonManager = new DungeonManager();
        PlayerManager playerManager = new PlayerManager();
        MenuNavigator mNav = new MenuNavigator(dungeonManager, playerManager);

        Scanner sc = new Scanner(System.in);

        printMenu();
        while (programRunning) {
            int selected = -1;
            String stringInput = "";
            boolean validInput = false;


            while (!validInput) {
                if (menuID.equals("cd") || menuID.equals("cp")) {
                    printInput();
                    stringInput = sc.nextLine();
                    validInput = true;
                } else {
                    printSelection();
                    try {
                        selected = sc.nextInt();
                        validInput = true;
                    } catch (Exception e) {
                        printInfo("You must input a number to navigate menus!");
                        sc.nextLine();
                    }
                }

            }

            switch (menuID) {
                case "s":
                    mNav.simulateNavListener(selected);
                    break;
                case "cd":
                    mNav.createDungeonNavListener(stringInput);
                    break;
                case "cp":
                    mNav.createPlayerNavListener(stringInput);
                    break;
                case "m":
                    mNav.manageNavListener(selected);
                    break;
                default:
                    mNav.menuNavListener(selected);
            }
        }
    }
}