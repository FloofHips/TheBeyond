package com.thebeyond.blocks;

import com.thebeyond.init.TBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PillarBlock extends Block {
    public PillarBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(TIP, Boolean.TRUE));
    }

    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public static final BooleanProperty TIP = BlockStateProperties.UP;

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TIP);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {

        if(pContext.getLevel().getBlockState(pContext.getClickedPos().above(1)).getBlock() instanceof AirBlock)
            return this.defaultBlockState().setValue(TIP, true);
        else
            return this.defaultBlockState().setValue(TIP, false);

    }

    public BlockBehaviour.OffsetType getOffsetType() {
        return BlockBehaviour.OffsetType.XZ;
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return canSupportCenter(pLevel, pPos.below(), Direction.UP) ;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if(pLevel.getBlockState(pCurrentPos.below()).isAir())
            return Blocks.AIR.defaultBlockState();
        if(pLevel.getBlockState(pCurrentPos.above()).isAir())
            return pState.setValue(TIP, true);
        if(!pLevel.getBlockState(pCurrentPos.above()).isAir())
            return pState.setValue(TIP, false);

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }
}