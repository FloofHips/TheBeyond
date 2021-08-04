package thebeyond.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import thebeyond.ModItemGroup;

public class ItemInit {
    public static final RegistryObject<Item> EXAMPLE_ITEM = InitContent.ITEMS.register("example_item", () ->
           new Item(new Item.Properties().tab(ModItemGroup.THE_BEYOND)) );

    public static void register() {}
}
