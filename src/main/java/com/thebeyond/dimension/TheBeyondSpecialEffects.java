package com.thebeyond.dimension;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class TheBeyondSpecialEffects extends DimensionSpecialEffects {
    public TheBeyondSpecialEffects(float pCloudLevel, boolean pHasGround, SkyType pSkyType, boolean pForceBrightLightmap, boolean pConstantAmbientLight) {
        super(pCloudLevel, pHasGround, pSkyType, pForceBrightLightmap, pConstantAmbientLight);
    }
    public TheBeyondSpecialEffects() {
        super(Float.NaN, true, SkyType.NONE, false, false);
    }

    public Vec3 getBrightnessDependentFogColor(Vec3 p_108894_, float p_108895_) {
        return p_108894_.scale((double)0.15F);
    }

    public boolean isFoggyAt(int p_108891_, int p_108892_) {
        return false;
    }

    @Nullable
    public float[] getSunriseColor(float p_108888_, float p_108889_) {
        return null;
    }
}
