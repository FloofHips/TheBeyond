package com.thebeyond.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Tilt;

import javax.annotation.Nullable;

public class PolarAntenna extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;


    public PolarAntenna(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)).setValue(TRIGGERED, Boolean.valueOf(false)));

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, TRIGGERED);
    }

    @Override
    public void entityInside(BlockState thisState, Level level, BlockPos ThisPos, Entity entity) {

        int Age = thisState.getValue(AGE);

        if (!level.isClientSide) {
            if (canEntityTrigger(ThisPos, entity)) {
                this.setNewBlockstate(thisState, level, ThisPos, Age, (SoundEvent)null);
            }
        }
    }

    public void setNewBlockstate(BlockState thisState, Level level, BlockPos ThisPos, Integer age, @Nullable SoundEvent soundEvent){

        if(age==0){
            thisState.setValue(AGE, Integer.valueOf(1)).setValue(TRIGGERED, Boolean.valueOf(false));
        }

        else if(age==1){
            thisState.setValue(AGE, Integer.valueOf(2)).setValue(TRIGGERED, Boolean.valueOf(false));
        }

        else if(age==2){
            thisState.setValue(AGE, Integer.valueOf(3)).setValue(TRIGGERED, Boolean.valueOf(true));
        }

    }

    public int UpdateNeighbour(BlockState ThisState, BlockState neighborState, BlockPos ThisPos, BlockPos neighborPos){
        return 0;
    }

    private static boolean canEntityTrigger(BlockPos ThisPos, Entity entity) {

        return entity.isOnGround() && entity.position().y > (double)((float)ThisPos.getY() + 0.6875F);
    }

}
