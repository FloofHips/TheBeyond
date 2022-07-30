package com.thebeyond.data;

import com.thebeyond.TheBeyond;
import com.thebeyond.init.TBItems;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.thebeyond.init.TBItems.ITEMS;

public class TBItemModels extends ItemModelProvider {

    public TBItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TheBeyond.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(TBItems.MAGNET.get());
        simpleItem(TBItems.FERRO_PETAL.get());
        withExistingParent(TBItems.PURPLE_SLIME_EGG.get().getRegistryName().getPath(), mcLoc("item/template_spawn_egg"));

        Set<RegistryObject<Item>> items = new HashSet<>(ITEMS.getEntries());
        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem).forEach(this::blockItem);
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TheBeyond.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(TheBeyond.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }

    private void blockItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(prefix("block/" + name)));
    }

    private ResourceLocation prefix(String s) {
        return new ResourceLocation(TheBeyond.MOD_ID, s);
    }


}
