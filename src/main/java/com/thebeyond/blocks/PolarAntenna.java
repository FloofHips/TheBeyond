package com.thebeyond.blocks;

import com.thebeyond.blocks.TBBlockstates.Imbalance;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;

import javax.annotation.Nullable;

public class PolarAntenna extends Block {
    public static final EnumProperty IMBALANCE = EnumProperty.create("imbalance", Imbalance.class);
    public static final BooleanProperty CARRIER = BlockStateProperties.ENABLED;
    public static final BooleanProperty COOLDOWN = BlockStateProperties.DISARMED;

    public PolarAntenna(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(IMBALANCE, Imbalance.NONE).setValue(CARRIER, true).setValue(COOLDOWN, false));

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(IMBALANCE, CARRIER, COOLDOWN);
    }

    public void entityInside(BlockState thisState, Level level, BlockPos ThisPos, Entity entity) {
        super.entityInside(thisState, level, ThisPos, entity);
        if(!level.isClientSide && canEntityTrigger(ThisPos, entity)){
            if (!(entity instanceof EnderMan)) {
                level.setBlockAndUpdate(ThisPos, setNewBlockstate(thisState, level, ThisPos));
            }
        }
    }

    public BlockState setNewBlockstate(BlockState thisState, Level level, BlockPos ThisPos){

        if(thisState.getValue(IMBALANCE) == Imbalance.NONE){
            return thisState.setValue(IMBALANCE, Imbalance.LOW);
        }

        else if(thisState.getValue(IMBALANCE) == Imbalance.LOW){
            return thisState.setValue(IMBALANCE, Imbalance.MEDIUM);
        }

        else if(thisState.getValue(IMBALANCE) == Imbalance.MEDIUM){
            return thisState.setValue(IMBALANCE, Imbalance.HIGH);
        }
        return thisState.setValue(IMBALANCE, Imbalance.HIGH);
    }

    //public boolean CoolDownTime(BlockState thisState, Level level, BlockPos ThisPos){
    //        level.scheduleTick(ThisPos,this, 20);
    //    return false;
    //}

    //public int UpdateNeighbour(BlockState ThisState, BlockState neighborState, BlockPos ThisPos, BlockPos neighborPos){
    //    return 0;
    //}

    private static boolean canEntityTrigger(BlockPos ThisPos, Entity entity) {

        return !(entity.position().x == ThisPos.getX());
    }

}
