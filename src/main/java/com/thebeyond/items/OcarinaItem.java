package com.thebeyond.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class OcarinaItem extends Item {
    public OcarinaItem(Item.Properties p_41188_) {
        super(p_41188_);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        float pitch = 2 - ((pPlayer.xRotO + 90) / (90));

        //pPlayer.playSound(SoundEvents.NOTE_BLOCK_FLUTE, 1.0F, pitch);
        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }

    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        this.stopUsing(pLivingEntity);
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
        if (!pLevel.isClientSide) {

            float pitch = 2 - ((pLivingEntity.xRotO + 90) / (90));

            pLevel.playSound((Player)null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), SoundEvents.NOTE_BLOCK_FLUTE, SoundSource.PLAYERS, 0.5F, pitch);
        }

    }

    public int getUseDuration(ItemStack pStack) { return 1000; }

}
