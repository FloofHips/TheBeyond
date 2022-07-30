package com.thebeyond;

import com.thebeyond.init.TBItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TBCreativeTab {
    public static final CreativeModeTab THE_BEYOND = new CreativeModeTab("The Beyond") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(TBItems.MAGNET.get());
        }
    };
}