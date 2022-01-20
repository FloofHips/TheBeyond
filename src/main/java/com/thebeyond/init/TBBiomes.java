package com.thebeyond.init;

import com.thebeyond.TheBeyond;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TBBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, TheBeyond.MOD_ID);

//    public static final RegistryObject<Item> MAGNETITE_FIELD = BIOMES.register("magnetite_field", TBBiomes.createMagneticField());

//    private static Biome createMagneticField()
 //   {
 //       MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
 //       BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
 //       return Biome(Biome.Precipitation.NONE, Biome.BiomeCategory.THEEND, 0.6F, 0.6F, spawnSettings, generationSettings);
  //  }


}