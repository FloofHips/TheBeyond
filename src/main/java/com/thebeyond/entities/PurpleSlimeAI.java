package com.thebeyond.entities;

import com.thebeyond.init.TBEntities;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;

public class PurpleSlimeAI {
    static class SlimeAttackGoal extends Goal {
        private final PurpleSlimeEntity purpleSlime;
        private int growTiredTimer;

        public SlimeAttackGoal(PurpleSlimeEntity p_33648_) {
            this.purpleSlime = p_33648_;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            LivingEntity livingentity = this.purpleSlime.getTarget();
            if (livingentity == null) {
                return false;
            } else {
                return !this.purpleSlime.canAttack(livingentity) ? false : this.purpleSlime.getMoveControl() instanceof PurpleSlimeEntity.SlimeMoveControl;
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.growTiredTimer = reducedTickDelay(300);
            super.start();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            LivingEntity livingentity = this.purpleSlime.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!this.purpleSlime.canAttack(livingentity)) {
                return false;
            } else {
                return --this.growTiredTimer > 0;
            }
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            LivingEntity livingentity = this.purpleSlime.getTarget();
            if (livingentity != null) {
                this.purpleSlime.lookAt(livingentity, 10.0F, 10.0F);
            }

            ((PurpleSlimeEntity.SlimeMoveControl)this.purpleSlime.getMoveControl()).setDirection(this.purpleSlime.getYRot(), this.purpleSlime.isDealsDamage());
        }
    }

    static class SlimeFloatGoal extends Goal {
        private final PurpleSlimeEntity purpleSlime;

        public SlimeFloatGoal(PurpleSlimeEntity p_33655_) {
            this.purpleSlime = p_33655_;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
            p_33655_.getNavigation().setCanFloat(true);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return (this.purpleSlime.isInWater() || this.purpleSlime.isInLava()) && this.purpleSlime.getMoveControl() instanceof PurpleSlimeEntity.SlimeMoveControl;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.purpleSlime.getRandom().nextFloat() < 0.8F) {
                this.purpleSlime.getJumpControl().jump();
            }

            ((PurpleSlimeEntity.SlimeMoveControl)this.purpleSlime.getMoveControl()).setWantedMovement(1.2D);
        }
    }

    static class SlimeKeepOnJumpingGoal extends Goal {
        private final PurpleSlimeEntity purpleSlime;

        public SlimeKeepOnJumpingGoal(PurpleSlimeEntity p_33660_) {
            this.purpleSlime = p_33660_;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return !this.purpleSlime.isPassenger();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            ((PurpleSlimeEntity.SlimeMoveControl)this.purpleSlime.getMoveControl()).setWantedMovement(1.0D);
        }
    }

    static class SlimeMoveControl extends MoveControl {
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

    static class SlimeRandomDirectionGoal extends Goal {
        private final PurpleSlimeEntity purpleSlime;
        private float chosenDegrees;
        private int nextRandomizeTime;

        public SlimeRandomDirectionGoal(PurpleSlimeEntity p_33679_) {
            this.purpleSlime = p_33679_;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return this.purpleSlime.getTarget() == null && (this.purpleSlime.isOnGround() || this.purpleSlime.isInWater() || this.purpleSlime.isInLava() || this.purpleSlime.hasEffect(MobEffects.LEVITATION)) && this.purpleSlime.getMoveControl() instanceof PurpleSlimeEntity.SlimeMoveControl;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (--this.nextRandomizeTime <= 0) {
                this.nextRandomizeTime = this.adjustedTickDelay(40 + this.purpleSlime.getRandom().nextInt(60));
                this.chosenDegrees = (float)this.purpleSlime.getRandom().nextInt(360);
            }

            ((PurpleSlimeEntity.SlimeMoveControl)this.purpleSlime.getMoveControl()).setDirection(this.chosenDegrees, false);
        }
    }

    static class SlimeRangedAttackGoal extends Goal {
        private final PurpleSlimeEntity purpleSlime;
        public int chargeTime;

        public SlimeRangedAttackGoal(PurpleSlimeEntity p_32776_) {
            this.purpleSlime = p_32776_;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return this.purpleSlime.getTarget() != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.chargeTime = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            LivingEntity livingentity = this.purpleSlime.getTarget();
            if (livingentity != null) {
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.purpleSlime) < 4096.0D && this.purpleSlime.hasLineOfSight(livingentity)) {
                    Level level = this.purpleSlime.level;
                    ++this.chargeTime;
                    if (this.chargeTime == 10 && !this.purpleSlime.isSilent()) {
                        level.levelEvent((Player)null, 1015, this.purpleSlime.blockPosition(), 0);
                    }

                    if (this.chargeTime == 20) {
                        double d1 = 4.0D;
                        Vec3 vec3 = this.purpleSlime.getViewVector(1.0F);
                        double d2 = livingentity.getX() - (this.purpleSlime.getX() + vec3.x * 4.0D);
                        double d3 = livingentity.getY(0.5D) - (0.5D + this.purpleSlime.getY(0.5D));
                        double d4 = livingentity.getZ() - (this.purpleSlime.getZ() + vec3.z * 4.0D);
                        if (!this.purpleSlime.isSilent()) {
                            level.levelEvent((Player)null, 1016, this.purpleSlime.blockPosition(), 0);
                        }

                        ArmorBall armorBall = new ArmorBall(level, this.purpleSlime, d2, d3, d4, 1);
                        armorBall.setPos(this.purpleSlime.getX() + vec3.x * 4.0D, this.purpleSlime.getY(0.5D) + 0.5D, armorBall.getZ() + vec3.z * 4.0D);
                        level.addFreshEntity(armorBall);
                        this.chargeTime = -40;
                        this.purpleSlime.setCharged(false);
                    }
                } else if (this.chargeTime > 0) {
                    --this.chargeTime;
                }
            }
        }
    }

    static class SlimeChargeGoal extends Goal {
        public int chargingTime;
        public boolean finishedCharging=false;
        private final PurpleSlimeEntity purpleSlime;

        public void start() {
            this.chargingTime = 0;
        }

        public SlimeChargeGoal(PurpleSlimeEntity p_32776_) {
            this.purpleSlime = p_32776_;
        }

        public void CurrentlyCharging(){
            for(this.chargingTime=100;this.chargingTime==0;this.chargingTime--){

            }
            this.finishedCharging=true;
        }

        public boolean canUse() {
            return this.purpleSlime.getTarget() != null;
        }
        public void tick() {
            if (this.purpleSlime.getTarget() != null && this.purpleSlime.isCharged()==false)
                {List<Entity> areaList = purpleSlime.level.getEntities(purpleSlime, purpleSlime.getBoundingBox().inflate(10), e -> e instanceof LivingEntity );
                Vec3 speedVec = new Vec3(1, 1, 1);

                areaList.forEach(e -> {
                    Vec3 diff = purpleSlime.position().subtract(e.position());
                    double d0 = diff.lengthSqr();
                    double d1 = 1.0D - Math.sqrt(d0) / 8.0D;
                    diff = diff.normalize().scale(d1 * d1 * 0.1D);
                    e.setDeltaMovement(e.getDeltaMovement().add(diff));});
                this.purpleSlime.playSound(this.purpleSlime.getSquishSound(), this.purpleSlime.getSoundVolume(), this.purpleSlime.getSoundPitch());
                chargingTime++;
                if(chargingTime==40)
                {
                    this.purpleSlime.playSound(this.purpleSlime.getDeathSound(), this.purpleSlime.getSoundVolume(), this.purpleSlime.getSoundPitch());
                    this.purpleSlime.setCharged(true);
                    stop();
                }
            }
        }
    }
}


