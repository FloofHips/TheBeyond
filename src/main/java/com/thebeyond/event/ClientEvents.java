package com.thebeyond.event;

import com.thebeyond.TheBeyond;
import com.thebeyond.init.TBBlocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = TheBeyond.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    public static SimplexNoise noise = new SimplexNoise(new XoroshiroRandomSource(69420));

    @SubscribeEvent
    public static void colorSetupBlock(final ColorHandlerEvent.Block event) {

        BlockColors colors = event.getBlockColors();
        colors.register((state, reader, pos, tintIndex) -> {
            if (pos != null) {
                //int value = (int) ((noise.getValue(pos.getX() * 0.1F, pos.getY() * 0.1F, pos.getZ() * 0.1F) + 0.999F) * 128);
                int value = (int) ((Mth.sin(0.3F * pos.getX() + 0.1F * pos.getY() + 0.4F * pos.getZ() + (float) noise.getValue(pos.getX() * 0.1F, pos.getY() * 0.1F, pos.getZ() * 0.1F) * 1.5F) + 0.999) * 9);
                //int value = (int) ((tri(0.048F * pos.getX() + 0.016F * pos.getY() + 0.064F * pos.getZ() + (float) noise.getValue(pos.getX() * 0.1F, pos.getY() * 0.1F, pos.getZ() * 0.1F) * 0.32F)) * 256);
                return (237 + value) << 16 | (237 + value) << 8 | 220;
            }
            return 0xFFFFFF;
        }, TBBlocks.PEARL_BLOCK.get());
    }

    public static float tri(float f) {

        return Math.abs(Mth.frac(f) - 0.5F) * 2.0F;
    }

}
