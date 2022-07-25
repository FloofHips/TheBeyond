package com.thebeyond.blocks;

import com.thebeyond.init.TBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class SingularityBranch extends PipeBlock {
    public SingularityBranch(BlockBehaviour.Properties p_51707_) {
        super(0.3125F, p_51707_);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false)));
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.getStateForPlacement(pContext.getLevel(), pContext.getClickedPos());
    }

    public BlockState getStateForPlacement(BlockGetter pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos.below()); //pLevel.getBlockState(pPos.below());
        BlockState blockstate1 = pLevel.getBlockState(pPos.above());
        BlockState blockstate2 = pLevel.getBlockState(pPos.north());
        BlockState blockstate3 = pLevel.getBlockState(pPos.east());
        BlockState blockstate4 = pLevel.getBlockState(pPos.south());
        BlockState blockstate5 = pLevel.getBlockState(pPos.west());
        return this.defaultBlockState().setValue(DOWN, Boolean.valueOf(blockstate1.is(this) || blockstate.is(TBBlocks.SINGULARITY_BLOCK.get()))).setValue(UP, Boolean.valueOf(blockstate1.is(this) || blockstate1.is(TBBlocks.SINGULARITY_BLOCK.get()))).setValue(NORTH, Boolean.valueOf(blockstate2.is(this) || blockstate2.is(TBBlocks.SINGULARITY_BLOCK.get()))).setValue(EAST, Boolean.valueOf(blockstate3.is(this) || blockstate3.is(TBBlocks.SINGULARITY_BLOCK.get()))).setValue(SOUTH, Boolean.valueOf(blockstate4.is(this) || blockstate4.is(TBBlocks.SINGULARITY_BLOCK.get()))).setValue(WEST, Boolean.valueOf(blockstate5.is(this) || blockstate5.is(TBBlocks.SINGULARITY_BLOCK.get())));
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        boolean flag = pFacingState.is(this) || pFacingState.is(TBBlocks.SINGULARITY_BLOCK.get()) || pFacing == Direction.DOWN && pFacingState.is(TBBlocks.SINGULARITY_BLOCK.get());
            return pState.setValue(PROPERTY_BY_DIRECTION.get(pFacing), Boolean.valueOf(flag));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }
}
