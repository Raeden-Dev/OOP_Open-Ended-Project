package com.raeden.lab_reports.models.dungeon.types;

import com.raeden.lab_reports.models.dungeon.Room;

public class TreasureRoom extends Room {
    private boolean explored = false;
    public TreasureRoom(String roomName, char roomSymbol, String roomColor, int roomSize) {
        super(roomName, roomSymbol, roomColor, roomSize);
    }

    public boolean isExplored() {
        return explored;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }
}
