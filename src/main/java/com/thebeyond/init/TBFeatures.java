package com.thebeyond.init;

import com.thebeyond.TheBeyond;
import com.thebeyond.features.BismuthFormationFeature;
import com.thebeyond.features.LongIslandFeature;
import com.thebeyond.features.OverLookerTreeFeature;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class TBFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TheBeyond.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LONG_ISLAND = createFeature("long_island", () -> new LongIslandFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BISMUTH_FORMATION = createFeature("bismuth_formation", () -> new BismuthFormationFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> OVERLOOKER_TREE = createFeature("overlooker_tree", () -> new OverLookerTreeFeature(NoneFeatureConfiguration.CODEC));

    public static <C extends FeatureConfiguration, F extends Feature<C>> RegistryObject<F> createFeature(String id, Supplier<? extends F> feature) {
        return FEATURES.register(id, feature);
    }

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
