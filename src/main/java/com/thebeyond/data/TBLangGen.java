package com.thebeyond.data;

import com.thebeyond.TheBeyond;
import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

import static com.thebeyond.init.TBBlocks.BLOCKS;
import static com.thebeyond.init.TBEntities.ENTITIES;
import static com.thebeyond.init.TBItems.ITEMS;

public class TBLangGen extends LanguageProvider {
    public TBLangGen(DataGenerator gen) {
        super(gen, TheBeyond.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        Set<RegistryObject<Block>> blocks = new HashSet<>(BLOCKS.getEntries());
        Set<RegistryObject<Item>> items = new HashSet<>(ITEMS.getEntries());
//        Set<RegistryObject<SoundEvent>> sounds = new HashSet<>(SOUNDS.getEntries());
//        Set<RegistryObject<Enchantment>> enchantments = new HashSet<>(ENCHANTMENTS.getEntries());
//        Set<RegistryObject<MobEffect>> effects = new HashSet<>(EFFECTS.getEntries());
        Set<RegistryObject<EntityType<?>>> entities = new HashSet<>(ENTITIES.getEntries());

        blocks.forEach(b ->
        {
            String name = b.get().getDescriptionId().replaceFirst("block.the_beyond.", "");
            name = DataHelper.toTitleCase(correctBlockItemName(name), "_").replaceAll("Of", "of");
            add(b.get().getDescriptionId(), name);
        });

        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem);
        items.forEach(i ->
        {
            String name = i.get().getDescriptionId().replaceFirst("item.the_beyond.", "");
            name = DataHelper.toTitleCase(correctBlockItemName(name), "_").replaceAll("Of", "of");
            add(i.get().getDescriptionId(), name);
        });
        entities.forEach(e -> {
            String name = DataHelper.toTitleCase(e.getId().getPath(), "_");
            add("entity.the_beyond." + e.get().getRegistryName().getPath(), name);
        });

    }

    public String correctBlockItemName(String name) {
        if ((!name.endsWith("_bricks"))) {
            if (name.contains("bricks")) {
                name = name.replaceFirst("bricks", "brick");
            }
        }
        if (name.contains("_fence") || name.contains("_button")) {
            if (name.contains("planks")) {
                name = name.replaceFirst("_planks", "");
            }
        }
        return name;
    }
}
