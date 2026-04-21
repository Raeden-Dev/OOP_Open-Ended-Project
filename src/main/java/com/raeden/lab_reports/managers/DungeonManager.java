package com.raeden.lab_reports.managers;

import com.raeden.lab_reports.models.dungeon.Dungeon;
import com.raeden.lab_reports.models.dungeon.DungeonPointers;
import com.raeden.lab_reports.models.dungeon.types.AStarDungeon;
import com.raeden.lab_reports.models.dungeon.types.BFSDungeon;
import com.raeden.lab_reports.models.dungeon.types.DFSDungeon;

import java.util.ArrayList;
import java.util.List;

import static com.raeden.lab_reports.ui.printStrings.*;

public class DungeonManager {
    private final List<Dungeon> activeDungeons = new ArrayList<>();

    public void createDungeon(DungeonPointers pointer, String name, int sizeX, int sizeY) {
        Dungeon dungeon = null;
        if(pointer.equals(DungeonPointers.A_STAR_GEN)) {
            dungeon = new AStarDungeon(name, sizeX, sizeY, pointer);
        }
        else if(pointer.equals(DungeonPointers.BFS_GEN)) {
            dungeon = new BFSDungeon(name, sizeX, sizeY, pointer);
        }
        else if(pointer.equals(DungeonPointers.DFS_GEN)) {
            dungeon = new DFSDungeon(name, sizeX, sizeY, pointer);
        } else {
            dungeon = new BFSDungeon(name, sizeX, sizeY, pointer);
        }

        printInfo("Created dungeon with ID: " + dungeon.getDungeonID());
        activeDungeons.add(dungeon);
    }

    public void showDungeons() {
        if(activeDungeons.isEmpty()) {
            printInfo("There are no active dungeons. Please create a dungeon first!");
            return;
        }
        for(Dungeon d : activeDungeons) {
            printOptionDash("[" + d.getDungeonName() + "] X: " + d.getDungeonSizeX() + " | Y: "
            + d.getDungeonSizeY() + "    //    {" + d.getDungeonID() + "}");
        }
    }

    public List<Dungeon> getActiveDungeons() {return activeDungeons;}
    public void addDungeon(Dungeon dungeon) { this.activeDungeons.add(dungeon);}
    public Dungeon getDungeon(String id) {
        Dungeon d = activeDungeons.stream()
                .filter(dungeon -> dungeon.getDungeonID().equals(id))
                .findFirst()
                .orElse(null);

        if(d == null) printWarning("Could not find a dungeon with id: " + id);
        return d;
    }


}
