package com.thebeyond.init;

import com.thebeyond.TheBeyond;
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

    public static RegistryObject<Block> registerBlockAndItem(String id, Supplier<Block> blockSupp, Supplier<Item> itemSupp) {

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
