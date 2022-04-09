package com.thebeyond.blocks;

import com.thebeyond.blocks.TBBlockstates.Imbalance;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;

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
            startSeeking(pState, pLevel, pPos, pRandom);
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

    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    //not done yet, supposed to reset the states after a while
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {

        if (!pLevel.isAreaLoaded(pPos, 1)) return;
        if (!pLevel.isClientSide) {
            if ((pState.getValue(IMBALANCE) == Imbalance.LOW) && (pState.getValue(COOLDOWN) == false)) {
                this.setImbalanceAndScheduleTick(pState, pLevel, pPos, Imbalance.NONE, SoundEvents.BIG_DRIPLEAF_TILT_DOWN);
            } else if ((pState.getValue(IMBALANCE) == Imbalance.MEDIUM) && (pState.getValue(COOLDOWN) == false)) {
                this.setImbalanceAndScheduleTick(pState, pLevel, pPos, Imbalance.LOW, SoundEvents.BIG_DRIPLEAF_TILT_DOWN);
            } else if ((pState.getValue(IMBALANCE) == Imbalance.HIGH) && (pState.getValue(COOLDOWN) == false)) {
                this.setImbalanceAndScheduleTick(pState, pLevel, pPos, Imbalance.MEDIUM, SoundEvents.BIG_DRIPLEAF_TILT_DOWN);
            } else if ((pState.getValue(IMBALANCE) == Imbalance.SEEKING) && (pState.getValue(COOLDOWN) == false)) {
                this.setImbalanceAndScheduleTick(pState, pLevel, pPos, Imbalance.HIGH, SoundEvents.BIG_DRIPLEAF_TILT_DOWN);
            }
        }
    }

    public BlockBehaviour.OffsetType getOffsetType() {
        return OffsetType.XZ;
    }

    //sets a random nearby antenna to seeking; not done
    private void startSeeking(BlockState pState, Level pLevel, BlockPos pPos, Random pRandom) {

        if (pState.getValue(IMBALANCE) == Imbalance.SEEKING){
            if (pState.getValue(IMBALANCE) != Imbalance.NONE) {
                playImbalanceSound(pLevel, pPos, SoundEvents.BIG_DRIPLEAF_TILT_UP);
            }
            //int x;
            //int y;
            //int z;

            //ChunkAccess cachedChunk = pLevel.getChunk(pPos);

            //BlockPos neighborPos = pPos.relative(Direction.Axis.X, 1);
            //BlockState neighborState = cachedChunk.getBlockState(neighborPos);

            //if ((neighborState.getBlock() instanceof PolarAntenna) && !(neighborState.getValue(IMBALANCE) == Imbalance.SEEKING)) {
            //     this.setImbalanceAndScheduleTick(pState, pLevel, neighborPos, Imbalance.SEEKING, SoundEvents.BIG_DRIPLEAF_TILT_DOWN);
            // }

            if (!pLevel.isAreaLoaded(pPos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading

                BlockState blockstate = this.defaultBlockState();

                for(int i = 0; i < 20; ++i) {
                    BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);

                    if (pLevel.getBlockState(blockpos).getBlock() instanceof PolarAntenna && !(pLevel.getBlockState(blockpos).getValue(IMBALANCE) == Imbalance.SEEKING)) {
                        this.setImbalanceAndScheduleTick(pState, pLevel, blockpos, Imbalance.SEEKING, SoundEvents.BIG_DRIPLEAF_TILT_DOWN);
                    }
                }

        }
    }
}
