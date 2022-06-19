package com.thebeyond.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HangingEndBushBlock extends Block {

    public HangingEndBushBlock(Properties p_49795_) {
        super(p_49795_);
    }

    protected static final VoxelShape SHAPE = Block.box(2.0D, 3.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.UP && !this.canSurvive(pState, pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public BlockBehaviour.OffsetType getOffsetType() {
        return BlockBehaviour.OffsetType.XZ;
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return canSupportCenter(pLevel, pPos.above(), Direction.UP);
    }
}
