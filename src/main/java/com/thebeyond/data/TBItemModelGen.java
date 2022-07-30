package com.thebeyond.data;

import com.thebeyond.TheBeyond;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

import static com.thebeyond.init.TBItems.ITEMS;
import static com.thebeyond.init.TBItems.PURPLE_SLIME_EGG;
import static net.minecraft.Util.prefix;

public class TBItemModelGen extends net.minecraftforge.client.model.generators.ItemModelProvider {
    public TBItemModelGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TheBeyond.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<RegistryObject<Item>> items = new HashSet<>(ITEMS.getEntries());

        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem).forEach(this::blockItem);
        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof WallBlock).forEach(this::wallBlockItem);
//        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof FenceBlock).forEach(this::fenceBlockItem);
//        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof DoorBlock).forEach(this::generatedItem);
//        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof TrapDoorBlock).forEach(this::trapdoorBlockItem);
//        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof PressurePlateBlock).forEach(this::pressurePlateBlockItem);
//        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof ButtonBlock).forEach(this::buttonBlockItem);
        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof BushBlock && !(((BlockItem) i.get()).getBlock() instanceof DoublePlantBlock)).forEach(this::blockGeneratedItem);
        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof DoublePlantBlock).forEach(this::generatedItem);
        DataHelper.takeAll(items, i -> i.get() instanceof SignItem).forEach(this::generatedItem);
        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem).forEach(this::blockItem);
        //DataHelper.takeAll(items, i -> i.get() instanceof DiggerItem).forEach(this::handheldItem);
        withExistingParent(PURPLE_SLIME_EGG.get().getRegistryName().getPath(), mcLoc("item/template_spawn_egg"));
        items.forEach(this::generatedItem);
    }

    private static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    private static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");

    private void handheldItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, HANDHELD).texture("layer0", prefix("item/" + name));
    }

    private ResourceLocation prefix(String s) {
        return new ResourceLocation(TheBeyond.MOD_ID, s);
    }

    private void generatedItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, GENERATED).texture("layer0", prefix("item/" + name));
    }

    private void blockGeneratedItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, GENERATED).texture("layer0", prefix("block/" + name));
    }

    private void blockItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(prefix("block/" + name)));
    }

    private void wallBlockItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        String baseName = name.substring(0, name.length() - 5);
        wallInventory(name, prefix("block/" + baseName));
    }
}
