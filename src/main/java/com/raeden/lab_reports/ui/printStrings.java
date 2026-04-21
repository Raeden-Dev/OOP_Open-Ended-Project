package com.raeden.lab_reports.ui;

public class printStrings {
    public static void printSeparator() {
        System.out.println("==================================================================");
    }

    public static void printSubMenuSeparator() {
        System.out.println("                  ------------------------------                  ");
    }

    public static void printTitle(String title) {
        System.out.println("                  <<<     " + title + "     >>>                   ");
    }

    public static void printOptionDash(String name) {
        System.out.println("   - " + name);
    }

    public static void printOption(int num, String name) {
        System.out.println("   " + num + ". " + name);
    }

    public static void printSelected(String name) {
        System.out.println("   [!] Selected: " + name);
    }

    public static void printInvalidSelected(String name) {
        System.out.println("   [x] Invalid selection '" + name + "', make sure, selection exists in option menu!");
    }


    public static void printSelection() {
        System.out.print("  | Select >>>  ");
    }

    public static void printInput() {
        System.out.print("");
    }

    public static void printInput(String of) {
        String toPrint = (of.isEmpty() ? "  | ":"[" + of + "] ") + "Input >>>  ";
        System.out.print(toPrint);
    }

    public static void printInfo(String description) {
        System.out.println("  [i] " + description);
    }

    public static void printWarning(String description) {
        System.out.println("   [x] Warning: " + description);
    }

    // Interface
    public static void printDungeonAction(String description) {
        System.out.println("         <#> Dungeon Action: " + description);
    }

    public static void printCharacterAction(String name, String description) {
        System.out.println("         <%> Character Action (" + name + "): " + description);
    }

    // File management
    public static void printDirCreate(String dirName, String location) {
        System.out.println("  [i] Created " + dirName + " directory at " + location);
    }
    public static void printDirCreateFail(String dirName, String location) {
        System.out.println("  [x] Failed to create " + dirName + " directory at " + location);
    }
    public static void printLoadSuccess(String fileName) {
        System.out.println("  [i] Loaded " + fileName + " successfully.");
    }
    public static void printLoadFailure(String fileName) {
        System.out.println("  [x] Failed to load " + fileName + ".");
    }
    public static void printSaveSuccess(String fileName) {
        System.out.println("  [i] Saved " + fileName + " successfully.");
    }
    public static void printSaveFailure(String fileName) {
        System.out.println("  [x] Failed to save " + fileName + ".");
    }

    // Creation problems
    public static void errorDungeonCreation() {
        errorDungeonCreation("Unknown");
    }
    public static void errorDungeonCreation(String reason) {
        System.out.println("  [x] Something went wrong while creating the dungeon. Make sure you inputted the correct variables in right format! [Reason: " + reason + "]");
    }

    public static void errorPlayerCreation() {
        System.out.println("  [x] Something went wrong while creating the character. Make sure you inputted the correct variables in right format!");
    }

    // Menus
    public static void printMenu() {
        int option = 1;
        printSeparator();
        printTitle("Dungeon Manager");
        printInfo("Navigate by picking an option. (e.g: 1, 2, 3...)");
        printOption(option++, "Simulate");
        printOption(option++,"Create Dungeon");
        printOption(option++,"Create Character");
        printOption(option++,"Manage...");
        printOption(option++,"Exit");
        printSeparator();
    }

    public static void printSimulate() {
        int option = 1;

        printSubMenuSeparator();
        printTitle("Simulate");
        printInfo("Input 0 to go back to Main Menu.");
        printWarning("Simulation is currently unavailable.");
        printOption(option++, "Dungeon");
        printOption(option++, "Raid");
        printOption(option++, "Multiple Raid");
        printSubMenuSeparator();
    }

    public static void printCreateDungeon() {
        printSubMenuSeparator();
        printTitle("Create Dungeon");
        printInfo("Input 0 to go back to Management Menu.");
        printInfo("Input these variables in order: {dungeon_name}, {size_x}, {size_z}, {generation_logic}");
        printInfo("          e.g: The Gloomy Corridors, 5, 5, BFS_GEN");
        printInfo("Available generation logic: BFS_GEN (ASTAR & DFS isn't available)");
        printSubMenuSeparator();
    }

    public static void printEditDungeon() {
        printSubMenuSeparator();
        printTitle("Edit Dungeon");
        printInfo("Input 0 to go back to Management Menu.");
        printInfo("Input '-d' to delete Dungeon.");
        printInfo("Input these variables in order: {dungeon_name}, {size_x}, {size_z}, {generation_logic}");
        printInfo("          e.g: The Gloomy Corridors, 5, 5, BFS_GEN");
        printInfo("Available generation logic: BFS_GEN (ASTAR & DFS isn't available)");
        printSubMenuSeparator();
    }

    public static void printEditPlayer() {
        printSubMenuSeparator();
        printTitle("Edit Character");
        printInfo("Input 0 to go back to Main Menu.");
        printInfo("Input '-d' to delete Character.");
        printInfo("Input these variables in order: {character_name}, {description}, {health}, {damage}, {type}");
        printInfo("          e.g: Sadman, BEST CODE MONKEY!, 100, 2, PLAYER");
        printInfo("Available character type: PLAYER, ENCOUNTER");
        printSubMenuSeparator();
    }

    public static void printCreatePlayer() {
        int option = 1;

        printSubMenuSeparator();
        printTitle("Create Character");
        printInfo("Input 0 to go back to Main Menu.");
        printInfo("Input these variables in order: {character_name}, {description}, {health}, {damage}, {type}");
        printInfo("          e.g: Sadman, BEST CODE MONKEY!, 100, 2, PLAYER");
        printInfo("Available character type: PLAYER, ENCOUNTER");
        printSubMenuSeparator();
    }

    public static void printManage() {
        int option = 1;

        printSubMenuSeparator();
        printTitle("Manage");
        printInfo("Input 0 to go back to Main Menu.");
        printOption(option++, "Show active Dungeons");
        printOption(option++, "Show active Characters");
        printOption(option++, "Edit Dungeon...");
        printOption(option++, "Edit Character...");
        printSubMenuSeparator();
    }
}
