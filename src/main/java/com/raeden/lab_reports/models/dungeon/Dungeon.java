package com.raeden.lab_reports.models.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Dungeon {
    private final String dungeonID = UUID.randomUUID().toString();
    private final DungeonPointers generationLogic;

    private final String dungeonName;
    private final int dungeonSizeX;
    private final int dungeonSizeY;

    private List<Room> roomList;
    private List<Mob> mobList;
    private List<Reward> rewardList;
    private List<Modifier> modifierList;

    private int totalRaids;
    private int successfulRaids;
    private int failedRaids;

    public Dungeon(String dungeonName, int dungeonSizeX, int dungeonSizeY, DungeonPointers generationLogic) {
        this.dungeonName = dungeonName;
        this.dungeonSizeX = dungeonSizeX;
        this.dungeonSizeY = dungeonSizeY;
        if(generationLogic.equals(DungeonPointers.BFS_GEN) || generationLogic.equals(DungeonPointers.DFS_GEN) || generationLogic.equals(DungeonPointers.A_STAR_GEN)) {
            this.generationLogic = generationLogic;
        } else {
            this.generationLogic = DungeonPointers.BFS_GEN;
        }

        roomList = new ArrayList<>();
        mobList = new ArrayList<>();
        rewardList = new ArrayList<>();
        modifierList = new ArrayList<>();

        totalRaids = 0;
        successfulRaids = 0;
        failedRaids = 0;
    }

    public String getDungeonName() {
        return dungeonName;
    }

    public int getDungeonSizeX() {
        return dungeonSizeX;
    }

    public int getDungeonSizeY() {
        return dungeonSizeY;
    }

    public List<Mob> getMobList() {
        return mobList;
    }

    public void setMobList(List<Mob> mobList) {
        this.mobList = mobList;
    }

    public void addMob(Mob mob) {
        this.mobList.add(mob);
    }

    public List<Reward> getRewardList() {
        return rewardList;
    }

    public void setRewardList(List<Reward> rewardList) {
        this.rewardList = rewardList;
    }

    public void addReward(Reward reward) {
        this.rewardList.add(reward);
    }

    public List<Modifier> getModifierList() {
        return modifierList;
    }

    public void setModifierList(List<Modifier> modifierList) {
        this.modifierList = modifierList;
    }

    public void addModifier(Modifier modifier) {
        this.modifierList.add(modifier);
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

    public DungeonPointers getGenerationLogic() {
        return generationLogic;
    }

    public String getDungeonID() {
        return dungeonID;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
}
