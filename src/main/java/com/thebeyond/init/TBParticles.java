package com.thebeyond.init;


import com.thebeyond.TheBeyond;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Supplier;

public class TBParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TheBeyond.MOD_ID);

    public static final RegistryObject<SimpleParticleType> VOID_SMOKE = PARTICLE_TYPES.register("void_smoke", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> DISPLACED_EYE = PARTICLE_TYPES.register("displaced_eye", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> FIRE = PARTICLE_TYPES.register("fire", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) { PARTICLE_TYPES.register(eventBus); }
}
