package com.thebeyond.blocks;

import com.thebeyond.blocks.TBBlockstates.Imbalance;
import com.thebeyond.init.TBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.Random;

public class Ferropillar extends Block {
    public Ferropillar(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.defaultBlockState().setValue(ENERGIZED, true));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ENERGIZED);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(ENERGIZED, false);
    }

    public static final BooleanProperty ENERGIZED = BlockStateProperties.POWERED;


    private static void activateCatalyst(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.setBlockAndUpdate(pPos, pState.setValue(FerrousCatalyst.AGE, 0));
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.DOWN ? pState.setValue(ENERGIZED, Boolean.valueOf(true)) : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {

        if (!pLevel.isAreaLoaded(pPos, 1)) return;
        if (!pLevel.isClientSide) {
            if ((pState.getValue(ENERGIZED) == true)){
                pLevel.setBlockAndUpdate(pPos, pState.setValue(ENERGIZED, false));
            }
        }
    }
}