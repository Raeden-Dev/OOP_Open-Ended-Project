package com.raeden.lab_reports.managers;

import com.raeden.lab_reports.models.dungeon.Dungeon;
import com.raeden.lab_reports.models.Pointers;
import com.raeden.lab_reports.models.dungeon.Room;
import com.raeden.lab_reports.models.dungeon.types.*;

import java.util.ArrayList;
import java.util.List;

import static com.raeden.lab_reports.Main.*;
import static com.raeden.lab_reports.managers.FileManager.saveJsonFile;
import static com.raeden.lab_reports.ui.printStrings.*;

public class DungeonManager {
    private final List<Dungeon> activeDungeons = new ArrayList<>();

    public void parseDungeonCreationInput(String input) {
        String[] vars = input.trim().split(",");
        if(vars.length < 4) {
            errorDungeonCreation("Expected 4 inputs. Found " + vars.length + "!");
            return;
        }
        Pointers pointer = null;
        String name = vars[0].replace(" ", "_");
        int x, y;
        // Check pointer
        boolean isLogicValid = false;
        
        for(Pointers p : Pointers.values()) {
            if(p.name().equalsIgnoreCase(vars[3].trim())) {
                isLogicValid = true;
                pointer = p;
                break;
            }
        }
        if(pointer == null || !pointer.equals(Pointers.BFS_GEN)) {
            isLogicValid = false;
        }
        if(!isLogicValid) {
            errorDungeonCreation("Invalid generation logic!");
            return;
        }
        try {
            x = Integer.parseInt(vars[1].trim());
            y = Integer.parseInt(vars[2].trim());
        } catch (Exception e) {
            errorDungeonCreation("Invalid dungeon size!");
            return;
        }
        createDungeon(pointer, name, x, y);
    }

    public void createDungeon(Pointers pointer, String name, int sizeX, int sizeY) {
        Dungeon dungeon = null;
        if(pointer.equals(Pointers.A_STAR_GEN)) {
            dungeon = new AStarDungeon(name, sizeX, sizeY, pointer);
        }
        else if(pointer.equals(Pointers.BFS_GEN)) {
            dungeon = new BFSDungeon(name, sizeX, sizeY, pointer);
        }
        else if(pointer.equals(Pointers.DFS_GEN)) {
            dungeon = new DFSDungeon(name, sizeX, sizeY, pointer);
        } else {
            dungeon = new BFSDungeon(name, sizeX, sizeY, pointer);
        }

        // Other variables
//        int totalRoom = (int) ((sizeX * sizeY) * 0.25);
//        dungeon.setRoomList(generateRooms(totalRoom));

        printInfo("Created dungeon with ID: " + dungeon.getDungeonID());
        String dName = dungeon.getDungeonName() + "_" + dungeon.getDungeonID();
        saveJsonFile(dName, dungeonSavePath.resolve(dName + ".json"), dungeon);
        activeDungeons.add(dungeon);
    }

    private List<Room> generateRooms(int amount) {
        List<Room> r = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            int roll = roll();
            Room room;
            if(roll > 95) {
                room = new TreasureRoom("Treasure Room " + i, Pointers.TREASURE_ROOM.name().toLowerCase(), 'T', "blue", 1);
            } else {
                room = new BasicRoom("Basic Room " + i, Pointers.BASIC_ROOM.name().toLowerCase(), 'R', "gray", 1);
            }
            r.add(room);
        }
        return r;
    }

    public void showActiveDungeons() {
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

    private int roll() {
        return rand.nextInt(100) + 1;
    }
}
