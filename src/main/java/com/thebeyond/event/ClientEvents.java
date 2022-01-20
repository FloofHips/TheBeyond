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

    /*    BlockColors colors = event.getBlockColors();
        colors.register((state, reader, pos, tintIndex) -> {
            if (pos != null) {
                    //int value = (int) ((noise.getValue(pos.getX() * 0.1F, pos.getY() * 0.1F, pos.getZ() * 0.1F) + 0.999F) * 128);
                int value = (int) ((Mth.sin(0.3F * pos.getX() + 0.1F * pos.getY() + 0.4F * pos.getZ() + (float) noise.getValue(pos.getX() * 0.1F, pos.getY() * 0.1F, pos.getZ() * 0.1F) * 1.5F) + 0.999) * 9);
                    //int value = (int) ((tri(0.048F * pos.getX() + 0.016F * pos.getY() + 0.064F * pos.getZ() + (float) noise.getValue(pos.getX() * 0.1F, pos.getY() * 0.1F, pos.getZ() * 0.1F) * 0.32F)) * 256);
                return (237 + value) << 16 | (237 + value) << 8 | (237 + value);
            }
            return 0xFFFFFF;
        }, TBBlocks.PEARL_BLOCK.get());
    }*/

        BlockColors colors = event.getBlockColors();
        colors.register((state, reader, pos, tintIndex) -> {
         if (pos != null) {
            double NoiseValue = (double) ((Mth.sin(0.3F * pos.getX() + 0.1F * pos.getY() + 0.4F * pos.getZ() + (float) noise.getValue(pos.getX() * 0.1F, pos.getY() * 0.1F, pos.getZ() * 0.1F) * 3F)));
            double XRLimit = 1.0;
            double XLLimit = 0.5;

            int RedRLimit = 200;
            int GreenRLimit = 200;
            int BlueRLimit = 200;

            int RedLLimit = 100;
            int GreenLLimit = 100;
            int BlueLLimit = 100;

            if (NoiseValue>=(-1) && NoiseValue<=(-0.5))
            {
                XRLimit = -0.5;
                XLLimit = -1.0;

                RedRLimit =      168;
                GreenRLimit =    200;
                BlueRLimit =     207;

                RedLLimit =      202;
                GreenLLimit =    222;
                BlueLLimit =     234;
            }
            else if (NoiseValue>(-0.5) && NoiseValue<=(0))
            {
                XRLimit = 0.0;
                XLLimit = -0.5;

                RedRLimit =     255;
                GreenRLimit =   227;
                BlueRLimit =    248;

                RedLLimit =     168;
                GreenLLimit =   200;
                BlueLLimit =    207;
            }
             else if (NoiseValue>(0) && NoiseValue<=(0.7))
             {
                 XRLimit = 0.5;
                 XLLimit = 0.0;

                 RedRLimit =    202;
                 GreenRLimit =  234;
                 BlueRLimit =   221;

                 RedLLimit =    255;
                 GreenLLimit =  227;
                 BlueLLimit =   248;
             }
             else if (NoiseValue>(0.7) && NoiseValue<=(1))
             {
                 XRLimit = 1.0;
                 XLLimit = 0.5;

                 RedRLimit =    239;
                 GreenRLimit =  250;
                 BlueRLimit =   218;

                 RedLLimit =    202;
                 GreenLLimit =  234;
                 BlueLLimit =   221;
             }

             int Rvalue = (int) ((( (NoiseValue - XRLimit) * (RedLLimit - RedRLimit))*1.0 / (XLLimit - XRLimit)*1.0) + RedRLimit);
             int Gvalue = (int) ((( (NoiseValue - XRLimit) * (GreenLLimit - GreenRLimit))*1.0 / (XLLimit - XRLimit)*1.0) + GreenRLimit);
             int Bvalue = (int) ((( (NoiseValue - XRLimit) * (BlueLLimit - BlueRLimit))*1.0 / (XLLimit - XRLimit)*1.0) + BlueRLimit);

             return (Rvalue) << 16 | (Gvalue) << 8 | (Bvalue);
         }
         return 0xFFFFFF;
        }, TBBlocks.PEARL_BLOCK.get());
     }

    public static float tri(float f) {

        return Math.abs(Mth.frac(f) - 0.5F) * 2.0F;
    }

}
