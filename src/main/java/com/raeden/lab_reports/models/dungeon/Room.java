package com.raeden.lab_reports.models.dungeon;

import java.util.UUID;

public abstract class Room {
    private final String roomID = UUID.randomUUID().toString();
    private final String roomType;
    private final String roomName;
    private final char roomSymbol;
    private int roomSize;
    private final String roomColor;
    private double spawnChance;
    private boolean cleared;

    public Room(String roomName, String roomType, char roomSymbol, String roomColor, int roomSize) {
        this.roomName = roomName;
        this.roomType = roomType;
        this.roomSymbol = roomSymbol;
        this.roomColor = roomColor;
        this.roomSize = roomSize;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public String getRoomColor() {
        return roomColor;
    }

    public double getSpawnChance() {
        return spawnChance;
    }

    public void setSpawnChance(double spawnChance) {
        this.spawnChance = spawnChance;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }

    public char getRoomSymbol() {
        return roomSymbol;
    }

    public String getRoomType() {
        return roomType;
    }
}
