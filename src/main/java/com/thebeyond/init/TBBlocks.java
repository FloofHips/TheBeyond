package com.thebeyond.init;

import com.thebeyond.TheBeyond;
import com.thebeyond.blocks.PolarAntenna;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.thebeyond.TheBeyond.creativeTab;
import static com.thebeyond.init.TBItems.ITEMS;

public class TBBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheBeyond.MOD_ID);

    public static final RegistryObject<Block> PEARL_BLOCK = registerBlockAndItem("pearl_block",
            BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).strength(1.5F, 1.5F).sound(SoundType.STONE).requiresCorrectToolForDrops(),
            new Item.Properties().tab(creativeTab));
    public static final RegistryObject<Block> FERROUS_CATALYST = registerBlockAndItem("ferrous_catalyst", BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 1.5F).sound(SoundType.LODESTONE).requiresCorrectToolForDrops(),new Item.Properties().tab(creativeTab));
    public static final RegistryObject<Block> FERROPILLAR = registerBlockAndItem("ferropillar", BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 1.5F).sound(SoundType.BONE_BLOCK).requiresCorrectToolForDrops(),new Item.Properties().tab(creativeTab));
    public static final RegistryObject<Block> PLATE_BLOCK = registerBlockAndItem("plate_block", BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 1.5F).sound(SoundType.LANTERN).requiresCorrectToolForDrops(),new Item.Properties().tab(creativeTab));
    public static final RegistryObject<Block> PLATED_END_STONE = registerBlockAndItem("plated_end_stone", BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 1.5F).sound(SoundType.ANCIENT_DEBRIS).requiresCorrectToolForDrops(),new Item.Properties().tab(creativeTab));
    public static final RegistryObject<Block> POLAR_ANTENNA = registerComplexBlockAndItem("polar_antenna", () -> new PolarAntenna(BlockBehaviour.Properties.of(Material.GRASS).noCollission().strength(1.5F, 1.5F)), () -> new Item(new Item.Properties().tab(creativeTab)));

    public static RegistryObject<Block> registerComplexBlockAndItem(String id, Supplier<Block> blockSupp, Supplier<Item> itemSupp) {

        ITEMS.register(id, itemSupp);
        return BLOCKS.register(id, blockSupp);
    }

    public static RegistryObject<Block> registerBlockAndItem(String id, Supplier<Block> blockSupp, Item.Properties itemProps) {

        RegistryObject<Block> block = BLOCKS.register(id, blockSupp);
        ITEMS.register(id, () -> new BlockItem(block.get(), itemProps));
        return block;
    }

    public static RegistryObject<Block> registerBlockAndItem(String id, BlockBehaviour.Properties blockProps, Item.Properties itemProps) {

        return registerBlockAndItem(id, () -> new Block(blockProps), itemProps);
    }
}