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
    /*     if (pos != null) {
         //   double NoiseValue = (double) ((Mth.sin(0.3F * pos.getX() + 0.1F * pos.getY() + 0.4F * pos.getZ() + (float) noise.getValue(pos.getX() * 0.1F, pos.getY() * 0.1F, pos.getZ() * 0.1F) * 3F)));
                double NoiseValue = (tri((0.048F * pos.getX() + 0.016F * pos.getY() + 0.064F * pos.getZ()) +
                                    ((float) noise.getValue(pos.getX() * 0.05F, pos.getY() * 0.05F, pos.getZ() * 0.05F)) *
                                    ((float) noise.getValue(pos.getX() * 0.1F, pos.getY() * 0.1F, pos.getZ() * 0.1F))));
            double XRLimit = 1.0;
            double XLLimit = 0.5;

            int LeftColor = 0xFFFFFF;
            int RightColor = 0xFFFFFF;

            int PearlYellow = 	0xE1EDD8;
            int PearlGreen =	0xBBD9C5;
            int PearlPink =	    0xD6CBD2;
            int PearlPurple =	0xAEB8D2;
            int PearlBlue = 	0xA9CAD1;

            int RedRLimit;
            int GreenRLimit;
            int BlueRLimit;

            int RedLLimit;
            int GreenLLimit;
            int BlueLLimit;

            if (NoiseValue>=(0) && NoiseValue<=(0.22))
            {
                XRLimit = 0.22;
                XLLimit = 0.0;
                LeftColor = PearlYellow;
                RightColor = PearlGreen;
            }
            else if (NoiseValue>(0.22) && NoiseValue<=(0.5))
            {
                XRLimit = 0.5;
                XLLimit = 0.22;
                LeftColor = PearlGreen;
                RightColor = PearlPink;
            }
             else if (NoiseValue>(0.5) && NoiseValue<=(0.72)) {
                XRLimit = 0.72;
                XLLimit = 0.5;
                LeftColor = PearlPink;
                RightColor = PearlPurple;
            }
             else if (NoiseValue>(0.72) && NoiseValue<=(1)) {
                XRLimit = 1.0;
                XLLimit = 0.72;
                LeftColor = PearlPurple;
                RightColor = PearlBlue;
             }

             RedRLimit =    RightColor / 10000;
             GreenRLimit =  (RightColor / 100) % 100;
             BlueRLimit =   RightColor % 100;

             RedLLimit =    LeftColor / 10000;
             GreenLLimit =  (LeftColor / 100) % 100;
             BlueLLimit =   LeftColor % 100;

             int Rvalue = (int) ((( (NoiseValue - XRLimit) * (RedLLimit - RedRLimit))*1.0 / (XLLimit - XRLimit)) + RedLLimit);
             int Gvalue = (int) ((( (NoiseValue - XRLimit) * (GreenLLimit - GreenRLimit))*1.0 / (XLLimit - XRLimit)) + GreenLLimit);
             int Bvalue = (int) ((( (NoiseValue - XRLimit) * (BlueLLimit - BlueRLimit))*1.0 / (XLLimit - XRLimit)) + BlueLLimit);

             return (Rvalue) << 16 | (Gvalue) << 8 | (Bvalue);
         }
         return 0xFFFFFF;*/


            if (pos != null) {
                //   double NoiseValue = (double) ((Mth.sin(0.3F * pos.getX() + 0.1F * pos.getY() + 0.4F * pos.getZ() + (float) noise.getValue(pos.getX() * 0.1F, pos.getY() * 0.1F, pos.getZ() * 0.1F) * 3F)));
                double NoiseValue = (tri((0.048F * pos.getX() + 0.016F * pos.getY() + 0.064F * pos.getZ()) +
                        ((float) noise.getValue(pos.getX() * 0.05F, pos.getY() * 0.05F, pos.getZ() * 0.05F)) *
                        ((float) noise.getValue(pos.getX() * 0.1F, pos.getY() * 0.1F, pos.getZ() * 0.1F))));
                double XRLimit = 1.0;
                double XLLimit = 0.5;

                int RedRLimit = 200;
                int GreenRLimit = 200;
                int BlueRLimit = 200;

                int RedLLimit = 100;
                int GreenLLimit = 100;
                int BlueLLimit = 100;

                if (NoiseValue>=(0) && NoiseValue<=(0.22))
                {
                    XRLimit = 0.22;
                    XLLimit = 0.0;
                    //purple
                    RedRLimit =      /*174;*/    168;
                    GreenRLimit =    /*184;*/    200;
                    BlueRLimit =     /*210;*/    207;
                    //blue
                    RedLLimit =      /*169;*/    202;
                    GreenLLimit =    /*202;*/    222;
                    BlueLLimit =     /*209;*/    234;
                }
                else if (NoiseValue>(0.22) && NoiseValue<=(0.5))
                {
                    XRLimit = 0.5;
                    XLLimit = 0.22;
                    //pink
                    RedRLimit =     /*214;*/    255;
                    GreenRLimit =   /*203;*/    227;
                    BlueRLimit =    /*210;*/    248;
                    //purple
                    RedLLimit =     /*174;*/    168;
                    GreenLLimit =   /*184;*/    200;
                    BlueLLimit =    /*210;*/    207;
                }
                else if (NoiseValue>(0.5) && NoiseValue<=(0.72))
                {
                    XRLimit = 0.72;
                    XLLimit = 0.5;
                    //green
                    RedRLimit =    /*187;*/     202;
                    GreenRLimit =  /*217;*/     234;
                    BlueRLimit =   /*197;*/     221;
                    //pink
                    RedLLimit =    /*214;*/     255;
                    GreenLLimit =  /*203;*/     227;
                    BlueLLimit =   /*210;*/     248;
                }
                else if (NoiseValue>(0.72) && NoiseValue<=(1))
                {
                    XRLimit = 1.0;
                    XLLimit = 0.72;
                    //yellow
                    RedRLimit =    /*225;*/     239;
                    GreenRLimit =  /*237;*/     250;
                    BlueRLimit =   /*216;*/     218;
                    //green
                    RedLLimit =    /*187;*/     202;
                    GreenLLimit =  /*217;*/     234;
                    BlueLLimit =   /*197;*/     221;
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
