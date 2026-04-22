package com.raeden.lab_reports.models.dungeon;

import com.raeden.lab_reports.models.Pointers;

import java.util.UUID;

public abstract class Dungeon {
    private final String dungeonID = UUID.randomUUID().toString();
    private final Pointers generationLogic;

    private String dungeonName;
    private int dungeonSizeX;
    private int dungeonSizeY;

    private int totalRaids;
    private int successfulRaids;
    private int failedRaids;

    public Dungeon(String dungeonName, int dungeonSizeX, int dungeonSizeY, Pointers generationLogic) {
        this.dungeonName = dungeonName;
        this.dungeonSizeX = dungeonSizeX;
        this.dungeonSizeY = dungeonSizeY;
        if(generationLogic.equals(Pointers.BFS_GEN) || generationLogic.equals(Pointers.DFS_GEN) || generationLogic.equals(Pointers.A_STAR_GEN)) {
            this.generationLogic = generationLogic;
        } else {
            this.generationLogic = Pointers.BFS_GEN;
        }

        totalRaids = 0;
        successfulRaids = 0;
        failedRaids = 0;
    }

    public int getTotalRaids() {
        return totalRaids;
    }

    public void setTotalRaids(int totalRaids) {
        this.totalRaids = totalRaids;
    }

    public int getSuccessfulRaids() {
        return successfulRaids;
    }

    public void setSuccessfulRaids(int successfulRaids) {
        this.successfulRaids = successfulRaids;
    }

    public int getFailedRaids() {
        return failedRaids;
    }

    public void setFailedRaids(int failedRaids) {
        this.failedRaids = failedRaids;
    }

    public Pointers getGenerationLogic() {
        return generationLogic;
    }

    public String getDungeonID() {
        return dungeonID;
    }

    public String getDungeonName() {
        return dungeonName;
    }

    public void setDungeonName(String dungeonName) {
        this.dungeonName = dungeonName;
    }

    public int getDungeonSizeX() {
        return dungeonSizeX;
    }

    public void setDungeonSizeX(int dungeonSizeX) {
        this.dungeonSizeX = dungeonSizeX;
    }

    public int getDungeonSizeY() {
        return dungeonSizeY;
    }

    public void setDungeonSizeY(int dungeonSizeY) {
        this.dungeonSizeY = dungeonSizeY;
    }
}
