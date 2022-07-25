package com.thebeyond.blocks;

import com.thebeyond.blocks.TBBlockstates.Imbalance;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class PorousReed extends Block {

    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {

        if (!pLevel.isAreaLoaded(pPos, 1)) return;
        if (!pLevel.isClientSide) {
            this.playSound(pLevel, pPos, SoundEvents.BEACON_AMBIENT);
        }
    }

    private static void playSound(Level pLevel, BlockPos pPos, SoundEvent pSound) {
        float f = Mth.randomBetween(pLevel.random, 0.0F, 2.0F);
        pLevel.playSound((Player)null, pPos, pSound, SoundSource.BLOCKS, 1.5F, f);
    }

    public PorousReed(Properties p_49795_) {
        super(p_49795_);
    }
}
