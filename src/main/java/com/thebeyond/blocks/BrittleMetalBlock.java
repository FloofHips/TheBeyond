package com.thebeyond.blocks;

import com.thebeyond.blocks.TBBlockstates.Imbalance;
import com.thebeyond.init.TBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class BrittleMetalBlock extends Block {
    public BrittleMetalBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FRAIL, Boolean.valueOf(false)));
    }

    public static final BooleanProperty FRAIL = BlockStateProperties.UNSTABLE;

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FRAIL);
    }

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    public boolean useShapeForLightOcclusion(BlockState p_153159_) { return true;}

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(!pLevel.isClientSide()) {
            if(pEntity instanceof LivingEntity) {
                //LivingEntity livingEntity = ((LivingEntity) pEntity);
                //livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300));

                pLevel.setBlockAndUpdate(pPos, pState.setValue(FRAIL, true));
                float f = Mth.randomBetween(pLevel.random, 0.8F, 1.2F);
                pLevel.playSound((Player)null, pPos, SoundEvents.COPPER_HIT, SoundSource.BLOCKS, 1.0F, f);
                pLevel.scheduleTick(pPos,this , 20);
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (pState.getValue(FRAIL) == true){
            pLevel.setBlock(pPos, TBBlocks.MOLTEN_METAL.get().defaultBlockState(), 3);
            float f = Mth.randomBetween(pLevel.random, 0.8F, 1.2F);
            pLevel.playSound((Player)null, pPos, SoundEvents.LANTERN_BREAK, SoundSource.BLOCKS, 1.0F, f);
            pLevel.playSound((Player)null, pPos, SoundEvents.IRON_GOLEM_HURT, SoundSource.BLOCKS, 0.5F, f+1);
        }
    }

}
