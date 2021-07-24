package thebeyond;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModItemGroup {

    public static final ItemGroup THE_BEYOND = new ItemGroup("The Beyond") {
        @Override
        public ItemStack makeIcon() {
            return Items.ENDER_EYE.getDefaultInstance();
        }
    };
}