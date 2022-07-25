package com.thebeyond.entities;

import com.google.common.base.MoreObjects;
import com.thebeyond.init.TBEntities;
import com.thebeyond.init.TBParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ArmorBall extends Fireball {
    private int explosionPower = 1;

    public ArmorBall(EntityType<? extends ArmorBall> p_37199_, Level p_37200_) {
        super(p_37199_, p_37200_);
    }

    public ArmorBall(Level p_181151_, LivingEntity p_181152_, double p_181153_, double p_181154_, double p_181155_, int p_181156_) {
        super(TBEntities.ARMOR_BALL.get(), p_181152_, p_181153_, p_181154_, p_181155_, p_181151_);
        this.explosionPower = p_181156_;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onHitBlock(BlockHitResult p_37239_) {
        super.onHitBlock(p_37239_);
        if (!this.level.isClientSide) {
            this.discard();
        }

    }

    protected boolean shouldBurn() {
        return false;
    }

    protected ParticleOptions getTrailParticle() {
        return TBParticles.VOID_SMOKE.get();
    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        Entity entity1 = this.getOwner();
        LivingEntity livingentity = entity1 instanceof LivingEntity ? (LivingEntity)entity1 : null;
        boolean flag = entity.hurt(DamageSource.indirectMobAttack(this, livingentity).setProjectile(), 4.0F);
        if (flag) {
            this.doEnchantDamageEffects(livingentity, entity);
            if (entity instanceof LivingEntity) {
                ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200), MoreObjects.firstNonNull(entity1, this));
            }
        }
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putByte("ExplosionPower", (byte)this.explosionPower);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("ExplosionPower", 99)) {
            this.explosionPower = pCompound.getByte("ExplosionPower");
        }

    }
}

//    private int explosionPower = 1;
//    public ArmorBall(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
//        super(p_36833_, p_36834_);
//    }
//
//    public ArmorBall(Level p_181151_, LivingEntity p_181152_, double p_181153_, double p_181154_, double p_181155_, int p_181156_) {
//        super(TBEntities.ARMOR_BALL.get(), p_181152_, p_181153_, p_181154_, p_181155_, p_181151_);
//        this.explosionPower = p_181156_;
//    }
//
//    protected void onHitEntity(EntityHitResult pResult) {
//        super.onHitEntity(pResult);
//        Entity entity = pResult.getEntity();
//        Entity entity1 = this.getOwner();
//        LivingEntity livingentity = entity1 instanceof LivingEntity ? (LivingEntity)entity1 : null;
//        boolean flag = entity.hurt(DamageSource.indirectMobAttack(this, livingentity).setProjectile(), 4.0F);
//        if (flag) {
//            this.doEnchantDamageEffects(livingentity, entity);
//            if (entity instanceof LivingEntity) {
//                ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200), MoreObjects.firstNonNull(entity1, this));
//            }
//        }
//    }
//
//    protected void onHitBlock(BlockHitResult p_37239_) {
//        super.onHitBlock(p_37239_);
//        if (!this.level.isClientSide) {
//            this.discard();
//        }
//
//    }
//
//    protected void defineSynchedData() {
//    }
//
//    protected ParticleOptions getTrailParticle() {
//        return ParticleTypes.CLOUD;
//    }
//
//    protected boolean shouldBurn() {
//        return false;
//    }

//    public ArmorBall(EntityType<? extends ArmorBall> p_37224_, Level p_37225_)  {
//        super(p_37224_, p_37225_);
//    }
//
//    public ArmorBall(Level p_181151_, LivingEntity p_181152_, double p_181153_, double p_181154_, double p_181155_, int p_181156_) {
//        super(TBEntities.ARMOR_BALL.get(), p_181152_, p_181153_, p_181154_, p_181155_, p_181151_);
//        this.explosionPower = p_181156_;
//    }
//
//    /**
//     * Called to update the entity's position/logic.
//     */
//    public void tick() {
//        super.tick();
//        Vec3 vec3 = this.getDeltaMovement();
//        HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
//        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult))
//            this.onHit(hitresult);
//        double d0 = this.getX() + vec3.x;
//        double d1 = this.getY() + vec3.y;
//        double d2 = this.getZ() + vec3.z;
//        this.updateRotation();
//        float f = 0.99F;
//        float f1 = 0.06F;
//        if (this.level.getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) {
//            this.discard();
//        } else if (this.isInWaterOrBubble()) {
//            this.discard();
//        } else {
//            this.setDeltaMovement(vec3.scale((double)0.99F));
//            if (!this.isNoGravity()) {
//                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, (double)-0.06F, 0.0D));
//            }
//
//            this.setPos(d0, d1, d2);
//        }
//    }
//
//    /**
//     * Called when the arrow hits an entity
//     */
//    protected void onHitEntity(EntityHitResult pResult) {
//        super.onHitEntity(pResult);
//        Entity entity = pResult.getEntity();
//        Entity entity1 = this.getOwner();
//        LivingEntity livingentity = entity1 instanceof LivingEntity ? (LivingEntity)entity1 : null;
//        boolean flag = entity.hurt(DamageSource.indirectMobAttack(this, livingentity).setProjectile(), 4.0F);
//        if (flag) {
//            this.doEnchantDamageEffects(livingentity, entity);
//            if (entity instanceof LivingEntity) {
//                ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200), MoreObjects.firstNonNull(entity1, this));
//            }
//        }
//
//    }
//
//    protected void onHitBlock(BlockHitResult p_37239_) {
//        super.onHitBlock(p_37239_);
//        if (!this.level.isClientSide) {
//            this.discard();
//        }
//
//    }
//
//    protected void defineSynchedData() {
//    }
//
//    public void recreateFromPacket(ClientboundAddEntityPacket pPacket) {
//        super.recreateFromPacket(pPacket);
//        double d0 = pPacket.getXa();
//        double d1 = pPacket.getYa();
//        double d2 = pPacket.getZa();
//
//        for(int i = 0; i < 7; ++i) {
//            double d3 = 0.4D + 0.1D * (double)i;
//            this.level.addParticle(ParticleTypes.SPIT, this.getX(), this.getY(), this.getZ(), d0 * d3, d1, d2 * d3);
//        }
//
//        this.setDeltaMovement(d0, d1, d2);
//    }

