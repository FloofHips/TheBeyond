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

    public static int hexToRed(String hex){
        int Red = Integer.valueOf(hex.substring(0, 2), 16);
        //Red = (Red << 16) & 0xFF0000;
        return Red;
    }

    public static int hexToGreen(String hex){
        int Green = Integer.valueOf(hex.substring(2, 4), 16);
        //Green = (Green << 8) & 0x00FF00;
        return Green;
    }

    public static int hexToBlue(String hex){
        int Blue = Integer.valueOf(hex.substring(4, 6), 16);
        //Blue = Blue & 0x0000FF;
        return Blue;
    }

    @SubscribeEvent
    public static void colorSetupBlock(final ColorHandlerEvent.Block event) {

        BlockColors colors = event.getBlockColors();
        colors.register((state, reader, pos, tintIndex) -> {
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
                    //blue
                    RedLLimit =   hexToRed("CADEEA");
                    GreenLLimit = hexToGreen("CADEEA");
                    BlueLLimit =  hexToBlue("CADEEA");
                    //purple
                    RedRLimit =   hexToRed("A8C8CF");
                    GreenRLimit = hexToGreen("A8C8CF");
                    BlueRLimit =  hexToBlue("A8C8CF");
                }
                else if (NoiseValue>(0.22) && NoiseValue<=(0.5))
                {
                    XRLimit = 0.5;
                    XLLimit = 0.22;
                    //purple
                    RedLLimit =   hexToRed("A8C8CF");
                    GreenLLimit = hexToGreen("A8C8CF");
                    BlueLLimit =  hexToBlue("A8C8CF");
                    //pink
                    RedRLimit =   hexToRed("FFE3F8");
                    GreenRLimit = hexToGreen("FFE3F8");
                    BlueRLimit =  hexToBlue("FFE3F8");
                }
                else if (NoiseValue>(0.5) && NoiseValue<=(0.72))
                {
                    XRLimit = 0.72;
                    XLLimit = 0.5;
                    //pink
                    RedLLimit =    hexToRed("FFE3F8");
                    GreenLLimit =  hexToGreen("FFE3F8");
                    BlueLLimit =   hexToBlue("FFE3F8");
                    //green
                    RedRLimit =    hexToRed("CAEADD");
                    GreenRLimit =  hexToGreen("CAEADD");
                    BlueRLimit =   hexToBlue("CAEADD");
                }
                else if (NoiseValue>(0.72) && NoiseValue<=(1))
                {
                    XRLimit = 1.0;
                    XLLimit = 0.72;
                    //green
                    RedLLimit =    hexToRed("CAEADD");
                    GreenLLimit =  hexToGreen("CAEADD");
                    BlueLLimit =   hexToBlue("CAEADD");
                    //yellow
                    RedRLimit =    hexToRed("EFFADA");
                    GreenRLimit =  hexToGreen("EFFADA");
                    BlueRLimit =   hexToBlue("EFFADA");
                }

                int Rvalue = (int) ((( (NoiseValue - XRLimit) * (RedLLimit - RedRLimit))*1.0 / (XLLimit - XRLimit)*1.0) + RedRLimit);
                int Gvalue = (int) ((( (NoiseValue - XRLimit) * (GreenLLimit - GreenRLimit))*1.0 / (XLLimit - XRLimit)*1.0) + GreenRLimit);
                int Bvalue = (int) ((( (NoiseValue - XRLimit) * (BlueLLimit - BlueRLimit))*1.0 / (XLLimit - XRLimit)*1.0) + BlueRLimit);

                return (Rvalue) << 16 | (Gvalue) << 8 | (Bvalue);
            }
            return 0xFFFFFF;


        }, TBBlocks.PEARL_BLOCK.get());
        colors.register((state, reader, pos, tintIndex) -> {
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
                    //blue
                    RedLLimit =   hexToRed("FFE597");
                    GreenLLimit = hexToGreen("FFE597");
                    BlueLLimit =  hexToBlue("FFE597");
                    //purple
                    RedRLimit =   hexToRed("FFC197");
                    GreenRLimit = hexToGreen("FFC197");
                    BlueRLimit =  hexToBlue("FFC197");
                }
                else if (NoiseValue>(0.22) && NoiseValue<=(0.5))
                {
                    XRLimit = 0.5;
                    XLLimit = 0.22;
                    //purple
                    RedLLimit =   hexToRed("FFC197");
                    GreenLLimit = hexToGreen("FFC197");
                    BlueLLimit =  hexToBlue("FFC197");
                    //pink
                    RedRLimit =   hexToRed("FF97FD");
                    GreenRLimit = hexToGreen("FF97FD");
                    BlueRLimit =  hexToBlue("FF97FD");
                }
                else if (NoiseValue>(0.5) && NoiseValue<=(0.72))
                {
                    XRLimit = 0.72;
                    XLLimit = 0.5;
                    //pink
                    RedLLimit =    hexToRed("FF97FD");
                    GreenLLimit =  hexToGreen("FF97FD");
                    BlueLLimit =   hexToBlue("FF97FD");
                    //green
                    RedRLimit =    hexToRed("A897FF");
                    GreenRLimit =  hexToGreen("A897FF");
                    BlueRLimit =   hexToBlue("A897FF");
                }
                else if (NoiseValue>(0.72) && NoiseValue<=(1))
                {
                    XRLimit = 1.0;
                    XLLimit = 0.72;
                    //green
                    RedLLimit =    hexToRed("A897FF");
                    GreenLLimit =  hexToGreen("A897FF");
                    BlueLLimit =   hexToBlue("A897FF");
                    //yellow
                    RedRLimit =    hexToRed("97E4FF");
                    GreenRLimit =  hexToGreen("97E4FF");
                    BlueRLimit =   hexToBlue("97E4FF");
                }

                int Rvalue = (int) ((( (NoiseValue - XRLimit) * (RedLLimit - RedRLimit))*1.0 / (XLLimit - XRLimit)*1.0) + RedRLimit);
                int Gvalue = (int) ((( (NoiseValue - XRLimit) * (GreenLLimit - GreenRLimit))*1.0 / (XLLimit - XRLimit)*1.0) + GreenRLimit);
                int Bvalue = (int) ((( (NoiseValue - XRLimit) * (BlueLLimit - BlueRLimit))*1.0 / (XLLimit - XRLimit)*1.0) + BlueRLimit);

                return (Rvalue) << 16 | (Gvalue) << 8 | (Bvalue);
            }
            return 0xFFFFFF;


        }, TBBlocks.BISMUTH.get());
    }

    public static float tri(float f) {

        return Math.abs(Mth.frac(f) - 0.5F) * 2.0F;
    }

}