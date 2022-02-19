package com.thebeyond.blocks;

import com.thebeyond.blocks.TBBlockstates.Imbalance;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.chunk.ChunkAccess;

import javax.annotation.Nullable;
import java.util.Random;

public class PolarAntenna extends Block {
    public static final EnumProperty IMBALANCE = EnumProperty.create("imbalance", Imbalance.class);
    public static final BooleanProperty CARRIER = BlockStateProperties.ENABLED;
    public static final BooleanProperty COOLDOWN = BlockStateProperties.DISARMED;
    private static final Object2IntMap<Imbalance> DELAY_UNTIL_NEXT_IMBALANCE_STATE = Util.make(new Object2IntArrayMap<>(), (Array) -> {
        Array.defaultReturnValue(-1);
        Array.put(Imbalance.LOW, 20);
        Array.put(Imbalance.MEDIUM, 20);
        Array.put(Imbalance.HIGH, 20);
        Array.put(Imbalance.SEEKING, 3);
    });

    public PolarAntenna(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(IMBALANCE, Imbalance.NONE).setValue(CARRIER, true).setValue(COOLDOWN, false));

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(IMBALANCE, CARRIER, COOLDOWN);
    }

    //updates state if entity inside
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (!pLevel.isClientSide) {
            if ((pState.getValue(IMBALANCE) == Imbalance.NONE) && (pState.getValue(COOLDOWN) == false)) {
                this.setImbalanceAndScheduleTick(pState, pLevel, pPos, Imbalance.LOW, SoundEvents.BIG_DRIPLEAF_TILT_DOWN);
            } else if ((pState.getValue(IMBALANCE) == Imbalance.LOW) && (pState.getValue(COOLDOWN) == false)) {
                this.setImbalanceAndScheduleTick(pState, pLevel, pPos, Imbalance.MEDIUM, SoundEvents.BIG_DRIPLEAF_TILT_DOWN);
            } else if ((pState.getValue(IMBALANCE) == Imbalance.MEDIUM) && (pState.getValue(COOLDOWN) == false)) {
                this.setImbalanceAndScheduleTick(pState, pLevel, pPos, Imbalance.HIGH, SoundEvents.BIG_DRIPLEAF_TILT_DOWN);
            } else if ((pState.getValue(IMBALANCE) == Imbalance.HIGH) && (pState.getValue(COOLDOWN) == false)) {
                this.setImbalanceAndScheduleTick(pState, pLevel, pPos, Imbalance.SEEKING, SoundEvents.BIG_DRIPLEAF_TILT_DOWN);
            }
        }
    }
    //resets cooldown after 20 ticks
    public void resetCoolDown(BlockState pState, ServerLevel pLevel, BlockPos pPos){
        pLevel.setBlockAndUpdate(pPos, pState.setValue(COOLDOWN, false));
    }

    //scheduled ticks
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        this.resetCoolDown(pState,pLevel,pPos);
        if (pState.getValue(IMBALANCE) == Imbalance.SEEKING){
            startSeeking(pState, pLevel, pPos);
        }
    }

    //sets the imbalance and schedules tick
    private void setImbalanceAndScheduleTick(BlockState pState, Level pLevel, BlockPos pPos, Imbalance pImbalance, @Nullable SoundEvent pSound) {
        if (pSound != null) {
            playImbalanceSound(pLevel, pPos, pSound);
        }

        int i = DELAY_UNTIL_NEXT_IMBALANCE_STATE.getInt(pImbalance);
        if (i != -1) {
            pLevel.scheduleTick(pPos, this, i);
        }
        setImbalance(pState, pLevel, pPos, pImbalance);
    }

    //plays a sound
    private static void playImbalanceSound(Level pLevel, BlockPos pPos, SoundEvent pSound) {
        float f = Mth.randomBetween(pLevel.random, 0.8F, 1.2F);
        pLevel.playSound((Player)null, pPos, pSound, SoundSource.BLOCKS, 1.0F, f);
    }

    //sets the state
    private static void setImbalance(BlockState pState, Level pLevel, BlockPos pPos, Imbalance pImbalance) {
        pLevel.setBlockAndUpdate(pPos, pState.setValue(COOLDOWN, true).setValue(IMBALANCE, pImbalance));
    }
    //not done yet, supposed to reset the states after a while
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        this.tick(pState, pLevel, pPos, pRandom);
    }

    //sets a random nearby antenna to seeking; not done
    private void startSeeking(BlockState pState, Level pLevel, BlockPos pPos) {

        if (pState.getValue(IMBALANCE) == Imbalance.SEEKING){
            if (pState.getValue(IMBALANCE) != Imbalance.NONE) {
                playImbalanceSound(pLevel, pPos, SoundEvents.BIG_DRIPLEAF_TILT_UP);
            }
            int x;
            int y;
            int z;

            ChunkAccess cachedChunk = pLevel.getChunk(pPos);

            BlockPos neighborPos = pPos.relative(Direction.Axis.X, 1);
            BlockState neighborState = cachedChunk.getBlockState(neighborPos);

            if ((neighborState.getBlock() instanceof PolarAntenna) && !(neighborState.getValue(IMBALANCE) == Imbalance.SEEKING)) {
                this.setImbalanceAndScheduleTick(pState, pLevel, neighborPos, Imbalance.SEEKING, SoundEvents.BIG_DRIPLEAF_TILT_DOWN);
            }

//            for (x = -1; x < 2; x++) {
//                for (y = -1; y < 2; y++) {
//                    for (z = -1; z < 2; z++) {
//
//                    }
//                }
//            }
        }
    }
}
