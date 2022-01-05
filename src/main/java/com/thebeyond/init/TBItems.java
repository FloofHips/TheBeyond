package com.thebeyond.init;

import com.thebeyond.TheBeyond;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TBItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheBeyond.MOD_ID);

    public static final RegistryObject<Item> MAGNETITE = ITEMS.register("magnetite", () -> new Item(new Item.Properties().tab(TheBeyond.creativeTab)));
}
