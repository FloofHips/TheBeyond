package com.thebeyond.init;

import com.thebeyond.TheBeyond;
import com.thebeyond.entities.ArmorBall;
import com.thebeyond.entities.PurpleSlimeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TBEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, TheBeyond.MOD_ID);

    public static final RegistryObject<EntityType<ArmorBall>> ARMOR_BALL = ENTITIES.register("armor_ball", () -> EntityType.Builder.<ArmorBall>of(ArmorBall::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("armor_ball"));
    public static final RegistryObject<EntityType<PurpleSlimeEntity>> PURPLE_SLIME = ENTITIES.register("purple_slime", () -> EntityType.Builder.of(PurpleSlimeEntity::new, MobCategory.MONSTER).sized(2.04F, 2.04F).clientTrackingRange(10).build("purple_slime"));

}