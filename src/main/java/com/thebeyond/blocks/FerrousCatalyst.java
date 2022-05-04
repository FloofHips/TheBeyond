package com.thebeyond.blocks;

import com.thebeyond.blocks.TBBlockstates.Imbalance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class FerrousCatalyst extends Block {
    public FerrousCatalyst(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.DOWN && pState.getValue(AGE)==3 ? pState.setValue(AGE, 0) : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }
    
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {

        if (!pLevel.isAreaLoaded(pPos, 1)) return;
        if (!pLevel.isClientSide) {
            if ((pState.getValue(AGE) == 0)) {
                this.setAge(pState, pLevel, pPos, 1);
            } else if ((pState.getValue(AGE) == 1)) {
                this.setAge(pState, pLevel, pPos, 2);
            } else if ((pState.getValue(AGE) == 2)) {
                this.setAge(pState, pLevel, pPos, 3);
            }
            // else if ((pState.getValue(AGE) == 3)) {
            //  this.setAge(pState, pLevel, pPos, 0);
            //    activateCatalyst(pLevel, pPos);
            //}
        }
    }

    private static void setAge(BlockState pState, Level pLevel, BlockPos pPos, Integer age) {
        pLevel.setBlockAndUpdate(pPos, pState.setValue(AGE, age));
    }

    public void activateCatalyst(ServerLevel pLevel, BlockPos pPos){
        Slime slime = EntityType.SLIME.create(pLevel);
        slime.moveTo((double)pPos.getX(), (double)pPos.getY() + 0.5D, (double)pPos.getZ(), 0.0F, 0.0F);
        pLevel.addFreshEntity(slime);
        slime.spawnAnim();
    }
}