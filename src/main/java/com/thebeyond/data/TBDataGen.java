package com.thebeyond.data;

import com.thebeyond.TheBeyond;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

//@Mod.EventBusSubscriber(modid = TheBeyond.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//public class TBDataGen {
//    @SubscribeEvent
//    public static void gatherData(GatherDataEvent event) {
//        DataGenerator generator = event.getGenerator();
//        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
//
//        generator.addProvider(new TBItemModels(generator, existingFileHelper));
//    }
//}