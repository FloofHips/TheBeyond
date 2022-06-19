package com.thebeyond.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class PseudoFluidBlock extends PowderSnowBlock {
    public PseudoFluidBlock(Properties p_154253_) {
        super(p_154253_);
        this.registerDefaultState(this.stateDefinition.any().setValue(TOP, Boolean.TRUE));
    }

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SURFACE_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(TOP) == Boolean.FALSE)
            return SHAPE;
        else
            return SURFACE_SHAPE;
    }

    public static final BooleanProperty TOP = BlockStateProperties.UP;

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TOP);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {

        if(pContext.getLevel().getBlockState(pContext.getClickedPos().above(1)).getBlock() instanceof PseudoFluidBlock)
            return this.defaultBlockState().setValue(TOP, false);
        else
            return this.defaultBlockState().setValue(TOP, true);

    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if(pLevel.getBlockState(pCurrentPos.above()).getBlock() instanceof PseudoFluidBlock)
            return pState.setValue(TOP, false);
        if(!(pLevel.getBlockState(pCurrentPos.above()).getBlock() instanceof PseudoFluidBlock))
            return pState.setValue(TOP, true);
        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (!(pEntity instanceof LivingEntity) || pEntity.getFeetBlockState().is(this)) {
            pEntity.makeStuckInBlock(pState, new Vec3((double)0.9F, 1.5D, (double)0.9F));
            if (pLevel.isClientSide) {
                Random random = pLevel.getRandom();
                boolean flag = pEntity.xOld != pEntity.getX() || pEntity.zOld != pEntity.getZ();
                if (flag && random.nextBoolean()) {
                    pLevel.addParticle(ParticleTypes.SMOKE, pEntity.getX(), (double)(pPos.getY() + 1), pEntity.getZ(), (double)(Mth.randomBetween(random, -1.0F, 1.0F) * 0.083333336F), (double)0.05F, (double)(Mth.randomBetween(random, -1.0F, 1.0F) * 0.083333336F));
                }
            }
        }
    }
}
