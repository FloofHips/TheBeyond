package com.thebeyond.blocks.TBBlockstates;

import net.minecraft.util.StringRepresentable;

public enum Imbalance implements StringRepresentable {
    NONE("none"),
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high"),
    SEEKING("seeking");

    private final String name;

    private Imbalance(String imbalance) {
        this.name = imbalance;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}