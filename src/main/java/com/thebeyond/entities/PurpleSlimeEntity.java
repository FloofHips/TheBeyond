package com.thebeyond.entities;

import com.thebeyond.init.TBEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class PurpleSlimeEntity extends Slime {
    public PurpleSlimeEntity(EntityType<? extends Slime> p_33588_, Level p_33589_) {
        super(p_33588_, p_33589_);
        this.moveControl = new PurpleSlimeEntity.SlimeMoveControl(this);
    }
    private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(PurpleSlimeEntity.class, EntityDataSerializers.BOOLEAN);
    private boolean charged = false;
    public static final EntityDataAccessor<Boolean> DATA_ARMORED = SynchedEntityData.defineId(PurpleSlimeEntity.class, EntityDataSerializers.BOOLEAN);

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ARMORED, false);
    }

    public boolean isCharged() {
        return this.entityData.get(DATA_ARMORED);
    }

    public void setCharged(boolean charged) {
        this.entityData.set(DATA_ARMORED, charged);
    }

    protected void registerGoals() {

        if(this.entityData.get(DATA_ARMORED)==false){
            this.goalSelector.addGoal(6, new PurpleSlimeAI.SlimeRangedAttackGoal(this));
        }
//        if(isCharged()){
            this.goalSelector.addGoal(1, new PurpleSlimeAI.SlimeFloatGoal(this));
            this.goalSelector.addGoal(2, new PurpleSlimeAI.SlimeAttackGoal(this));
            this.goalSelector.addGoal(3, new PurpleSlimeAI.SlimeRandomDirectionGoal(this));
            this.goalSelector.addGoal(5, new PurpleSlimeAI.SlimeKeepOnJumpingGoal(this));
            this.goalSelector.addGoal(2, new PurpleSlimeAI.SlimeChargeGoal(this));
//        }


        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (p_33641_) -> {
            return Math.abs(p_33641_.getY() - this.getY()) <= 4.0D;
        }));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    protected boolean doPlayJumpSound() {
        return this.getSize() > 0;
    }
    protected float getSoundVolume() {
        return 0.4F * (float)this.getSize();
    }
    float getSoundPitch() {
        float f = this.isTiny() ? 1.4F : 0.8F;
        return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * f;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public static boolean checkPurpleSlimeSpawnRules(EntityType<MagmaCube> p_32981_, LevelAccessor p_32982_, MobSpawnType p_32983_, BlockPos p_32984_, Random p_32985_) {
        return p_32982_.getDifficulty() != Difficulty.PEACEFUL;
    }

    public boolean checkSpawnObstruction(LevelReader pLevel) {
        return pLevel.isUnobstructed(this) && !pLevel.containsAnyLiquid(this.getBoundingBox());
    }

    protected void setSize(int pSize, boolean pResetHealth) {
        super.setSize(pSize, pResetHealth);
        this.getAttribute(Attributes.ARMOR).setBaseValue((double)(pSize * 3));
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness() {
        return 1.0F;
    }

    protected ParticleOptions getParticleType() {
        return ParticleTypes.PORTAL;
    }

    protected ResourceLocation getDefaultLootTable() {
        return this.isTiny() ? BuiltInLootTables.EMPTY : this.getType().getDefaultLootTable();
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    public boolean isOnFire() {
        return false;
    }

    /**
     * Gets the amount of time the slime needs to wait between jumps.
     */
    protected int getJumpDelay() {
        return super.getJumpDelay() * 4;
    }

    protected void decreaseSquish() {
        this.targetSquish *= 0.9F;
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jumpFromGround() {
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x, (double)(this.getJumpPower() + (float)this.getSize() * 0.1F), vec3.z);
        this.hasImpulse = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(this);
    }

    protected void jumpInLiquid(TagKey<Fluid> pFluidTag) {
        if (pFluidTag == FluidTags.LAVA) {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.x, (double)(0.22F + (float)this.getSize() * 0.05F), vec3.z);
            this.hasImpulse = true;
        } else {
            super.jumpInLiquid(pFluidTag);
        }

    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.FOLLOW_RANGE, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    /**
     * Indicates weather the slime is able to damage the player (based upon the slime's size)
     */
    protected boolean isDealsDamage() {
        return this.isEffectiveAi();
    }

    protected float getAttackDamage() {
        return super.getAttackDamage() + 2.0F;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return this.isTiny() ? SoundEvents.MAGMA_CUBE_HURT_SMALL : SoundEvents.MAGMA_CUBE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return this.isTiny() ? SoundEvents.MAGMA_CUBE_DEATH_SMALL : SoundEvents.MAGMA_CUBE_DEATH;
    }

    protected SoundEvent getSquishSound() {
        return this.isTiny() ? SoundEvents.MAGMA_CUBE_SQUISH_SMALL : SoundEvents.MAGMA_CUBE_SQUISH;
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.MAGMA_CUBE_JUMP;
    }

//    private void shoot(LivingEntity pTarget) {
//        LlamaSpit llamaSpit = new LlamaSpit(EntityType.LLAMA_SPIT, this.level);
//        double d0 = pTarget.getX() - this.getX();
//        double d1 = pTarget.getY(0.3333333333333333D) - llamaSpit.getY();
//        double d2 = pTarget.getZ() - this.getZ();
//        double d3 = Math.sqrt(d0 * d0 + d2 * d2) * (double)0.2F;
//        llamaSpit.shoot(d0, d1 + d3, d2, 1.5F, 10.0F);
//        if (!this.isSilent()) {
//            this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.LLAMA_SPIT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
//        }
//
//        this.level.addFreshEntity(llamaSpit);
//    }

    public class SlimeMoveControl extends MoveControl {
        private float yRot;
        private int jumpDelay;
        private final PurpleSlimeEntity purpleSlime;
        private boolean isAggressive;

        public SlimeMoveControl(PurpleSlimeEntity p_33668_) {
            super(p_33668_);
            this.purpleSlime = p_33668_;
            this.yRot = 180.0F * p_33668_.getYRot() / (float)Math.PI;
        }

        public void setDirection(float pYRot, boolean pAggressive) {
            this.yRot = pYRot;
            this.isAggressive = pAggressive;
        }

        public void setWantedMovement(double pSpeed) {
            this.speedModifier = pSpeed;
            this.operation = MoveControl.Operation.MOVE_TO;
        }

        public void tick() {
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90.0F));
            this.mob.yHeadRot = this.mob.getYRot();
            this.mob.yBodyRot = this.mob.getYRot();
            if (this.operation != MoveControl.Operation.MOVE_TO) {
                this.mob.setZza(0.0F);
            } else {
                this.operation = MoveControl.Operation.WAIT;
                if (this.mob.isOnGround()) {
                    this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                    if (this.jumpDelay-- <= 0) {
                        this.jumpDelay = this.purpleSlime.getJumpDelay();
                        if (this.isAggressive) {
                            this.jumpDelay /= 3;
                        }

                        this.purpleSlime.getJumpControl().jump();
                        if (this.purpleSlime.doPlayJumpSound()) {
                            this.purpleSlime.playSound(this.purpleSlime.getJumpSound(), this.purpleSlime.getSoundVolume(), this.purpleSlime.getSoundPitch());
                        }
                    } else {
                        this.purpleSlime.xxa = 0.0F;
                        this.purpleSlime.zza = 0.0F;
                        this.mob.setSpeed(0.0F);
                    }
                } else {
                    this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                }

            }
        }
    }
}
