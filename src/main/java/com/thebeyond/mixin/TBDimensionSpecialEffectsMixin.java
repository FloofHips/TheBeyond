package com.thebeyond.mixin;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DimensionSpecialEffects.class)
public class TBDimensionSpecialEffectsMixin {
    @Accessor("EFFECTS")
    public static Object2ObjectMap<ResourceLocation, DimensionSpecialEffects> the_beyond_getBY_ResourceLocation() {
        throw new UnsupportedOperationException();
    }
}