package com.raeden.lab_reports.models.dungeon;

import java.util.UUID;

public abstract class Reward {
    private final String mobID = UUID.randomUUID().toString();
    private final String rewardName;
    private final String rewardQuality;
    private final String rewardDescription;
    private int minAmount;
    private int maxAmount;

    protected Reward(String rewardName, String rewardQuality, String rewardDescription) {
        this.rewardName = rewardName;
        this.rewardQuality = rewardQuality;
        this.rewardDescription = rewardDescription;
    }

    public String getRewardName() {
        return rewardName;
    }

    public String getRewardQuality() {
        return rewardQuality;
    }

    public String getRewardDescription() {
        return rewardDescription;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }
}
