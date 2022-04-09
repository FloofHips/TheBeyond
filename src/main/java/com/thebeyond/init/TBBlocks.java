package com.thebeyond.init;

import com.thebeyond.TBCreativeTab;
import com.thebeyond.TheBeyond;
import com.thebeyond.blocks.PolarAntenna;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TBBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheBeyond.MOD_ID);

    public static final RegistryObject<Block> PEARL_BLOCK = registerBlock("pearl_block", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> COMPRESSED_SAND = registerBlock("compressed_sand", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.NETHERRACK).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> BIOLUMINESCENT_SAND = registerBlock("bioluminescent_sand", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.NETHERRACK).strength(1f).lightLevel(state -> 13).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);


    public static final RegistryObject<Block> FERROUS_CATALYST = registerBlock("ferrous_catalyst", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(1.5f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> FERROPILLAR = registerBlock("ferropillar", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.BONE_BLOCK).strength(2f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> PLATE_BLOCK = registerBlock("plate_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.LANTERN).strength(2f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> PLATED_END_STONE = registerBlock("plated_end_stone", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.ANCIENT_DEBRIS).strength(1.5f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> POLAR_ANTENNA = registerBlock("polar_antenna", () -> new PolarAntenna(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.ANCIENT_DEBRIS).noCollission().strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return TBItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}