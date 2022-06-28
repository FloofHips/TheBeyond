package com.thebeyond.items;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.List;
import java.util.function.Predicate;

public class MagnetItem extends Item {
    public MagnetItem(Properties pProperties) {
        super(pProperties);
    }


    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);

    }

    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {

    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BLOCK;
    }

    private void stopUsing(LivingEntity pLivingEntity) {

        float pitch = 2 - ((pLivingEntity.xRotO + 90) / (90)) -0.2F;

        pLivingEntity.playSound(SoundEvents.GHAST_SCREAM, 0.01F, pitch);
    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        this.stopUsing(pLivingEntity);
        return pStack;
    }

    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pCount) {
        List<Entity> areaList = pLivingEntity.level.getEntities(pLivingEntity, pLivingEntity.getBoundingBox().inflate(3), e -> e instanceof ItemEntity || e instanceof IronGolem);
        Vec3 speedVec = new Vec3(1,1,1);
//        areaList.forEach(e -> {
//            Vec3 diff = e.position().subtract(pLivingEntity.position());
//            diff = diff.reverse();
//            diff = diff.normalize();
//            e.setDeltaMovement(diff.multiply(speedVec));
//        });
        areaList.forEach(e -> {
            Vec3 diff = pLivingEntity.position().subtract(e.position());
            double d0 = diff.lengthSqr();
            double d1 = 1.0D - Math.sqrt(d0) / 8.0D;
            diff = diff.normalize().scale(d1 * d1 * 0.1D);
            e.setDeltaMovement(e.getDeltaMovement().add(diff));
        });

    }

    public int getUseDuration(ItemStack pStack) { return 1000; }

}
