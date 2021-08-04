package thebeyond.init;
import thebeyond.ModItemGroup;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {

    public static final RegistryObject<Block> EXAMPLE_BLOCK = register("example_block", () -> new Block(AbstractBlock.Properties.of(Material.DECORATION).strength(2).harvestTool(ToolType.AXE).sound(SoundType.ANCIENT_DEBRIS)));


    private static <T extends Block>RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return thebeyond.init.InitContent.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        thebeyond.init.InitContent.ITEMS.register(name, () ->new BlockItem(ret.get(), new Item.Properties().tab(ModItemGroup.THE_BEYOND)));
        return ret;
    }

    public static void register() {}
}
