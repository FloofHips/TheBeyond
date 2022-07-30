package com.thebeyond.init;

import com.thebeyond.TBCreativeTab;
import com.thebeyond.TheBeyond;
import com.thebeyond.entities.PurpleSlimeEntity;
import com.thebeyond.items.MagnetItem;
import com.thebeyond.items.OcarinaItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TBItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheBeyond.MOD_ID);

    public static final RegistryObject<Item> FERRO_PETAL = ITEMS.register("ferro_petal", () -> new Item(new Item.Properties().tab(TBCreativeTab.THE_BEYOND)));
    public static final RegistryObject<Item> MAGNET = ITEMS.register("magnet", () -> new MagnetItem(new Item.Properties().tab(TBCreativeTab.THE_BEYOND)));
    public static final RegistryObject<Item> PURPLE_SLIME_EGG = ITEMS.register("purple_slime_spawn_egg", () -> new ForgeSpawnEggItem(TBEntities.PURPLE_SLIME, 0x19181a, 0x9021f2, new Item.Properties().tab(TBCreativeTab.THE_BEYOND)));

    public static final RegistryObject<Item> OCARINA = ITEMS.register("ocarina", () -> new OcarinaItem(new Item.Properties().tab(TBCreativeTab.THE_BEYOND)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
