package com.raeden.lab_reports.models.dungeon.types;

import com.raeden.lab_reports.models.dungeon.Dungeon;
import com.raeden.lab_reports.models.dungeon.DungeonActions;
import com.raeden.lab_reports.models.Pointers;

import static com.raeden.lab_reports.ui.printStrings.printDungeonAction;

public class BFSDungeon extends Dungeon implements DungeonActions {
    public BFSDungeon(String dungeonName, int dungeonSizeX, int dungeonSizeY, Pointers generationLogic) {
        super(dungeonName, dungeonSizeX, dungeonSizeY, generationLogic);
    }

    @Override
    public void crawl() {
        printDungeonAction("Crawling through the hallways of " + getDungeonName());
    }

    @Override
    public void loot() {
        printDungeonAction("Looted something at " + getDungeonName());
    }

    @Override
    public void discover() {
        printDungeonAction("Discovered something at " + getDungeonName());
    }

    @Override
    public void treasure() {
        printDungeonAction("Found treasure at " + getDungeonName());
    }

    @Override
    public void encounter() {
        printDungeonAction("Encountered in " + getDungeonName());
    }
}
