package com.thebeyond.init;

import com.thebeyond.TheBeyond;
import com.thebeyond.entities.SentientVoid;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TBEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, TheBeyond.MOD_ID);

//    public static final RegistryObject<EntityType<SentientVoid>> EXAMPLE = ENTITY_TYPES.register("example",
//            () -> EntityType.Builder.create(ExampleEntity::new, EntityClassification.MONSTER).size(1.0f, 1.0f)
//                    .build(new ResourceLocation(TutorialMod.MOD_ID, "example").toString()));
}
