package com.thebeyond.dimension;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;

abstract class TheEndSpecialEffects extends DimensionSpecialEffects {
    public TheEndSpecialEffects(float pCloudLevel, boolean pHasGround, SkyType pSkyType, boolean pForceBrightLightmap, boolean pConstantAmbientLight) {
        super(pCloudLevel, pHasGround, pSkyType, pForceBrightLightmap, pConstantAmbientLight);
    }

    public static final int CLOUD_LEVEL = 192;

    public TheEndSpecialEffects() {
        super(192.0F, true, DimensionSpecialEffects.SkyType.NORMAL, false, false);
    }

    public Vec3 getBrightnessDependentFogColor(Vec3 p_108908_, float p_108909_) {
        return p_108908_.multiply((double)(p_108909_ * 0.94F + 0.06F), (double)(p_108909_ * 0.94F + 0.06F), (double)(p_108909_ * 0.91F + 0.09F));
    }

    public boolean isFoggyAt(int p_108905_, int p_108906_) {
        return false;
    }
}
