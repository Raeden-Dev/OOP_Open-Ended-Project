package com.raeden.lab_reports.models.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GeneratedDungeon {
    private final String dungeonID = UUID.randomUUID().toString();
    private final String blueprint;
    private final String size; // 4x5, 6x6

    private final List<String> dungeonMap;
    private final List<Room> dungeonData;

    public GeneratedDungeon(String blueprint, String size, List<String> dungeonMap, List<Room> dungeonData) {
        this.blueprint = blueprint;
        this.size = size;
        this.dungeonMap = dungeonMap;
        this.dungeonData = dungeonData;
    }

    public String getBlueprint() {
        return blueprint;
    }

    public String getSize() {
        return size;
    }

    public List<String> getDungeonMap() {
        return dungeonMap;
    }

    public List<Room> getDungeonData() {
        return dungeonData;
    }

    public String getDungeonID() {
        return dungeonID;
    }
}
