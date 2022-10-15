package com.thebeyond.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.ColorHandlerEvent;

public class ClamBlock extends TBDirectionalBlock {
    public ClamBlock(Properties p_52591_) {
        super(p_52591_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }
    public SoundType getSoundType(BlockState pState) {
        if(pState.getValue(FACING)== Direction.UP)
            return SoundType.SLIME_BLOCK;
        else
            return SoundType.DEEPSLATE_TILES;
    }

    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float f) {
        if(pState.getValue(FACING)== Direction.UP) {
            if (pEntity.isSuppressingBounce()) {
                super.fallOn(pLevel, pState, pPos, pEntity, f);
            } else {
                pEntity.causeFallDamage(f, 0.0F, DamageSource.FALL);
            }
        }
    }

//    public void updateEntityAfterFallOn(BlockGetter pLevel, Entity pEntity) {
//        if(pEntity.level.getBlockState(this.).getValue(FACING)==Direction.UP) {
//            if (pEntity.isSuppressingBounce()) {
//                super.updateEntityAfterFallOn(pLevel, pEntity);
//            } else {
//                this.bounceUp(pEntity);
//            }
//        }
//    }
//
//    private void bounceUp(Entity pEntity) {
//        if(pEntity.level.getBlockState().getValue(FACING)== Direction.UP) {
//            Vec3 vec3 = pEntity.getDeltaMovement();
//            if (vec3.y < 0.0D) {
//                double d0 = pEntity instanceof LivingEntity ? 1.0D : 0.8D;
//                pEntity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
//            }
//        }
//    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pState.getValue(FACING) == Direction.UP) {
            double d0 = Math.abs(pEntity.getDeltaMovement().y);
            if (d0 < 0.1D && !pEntity.isSteppingCarefully()) {
                double d1 = 0.4D + d0 * 0.2D;
                pEntity.setDeltaMovement(pEntity.getDeltaMovement().multiply(d1, 1.0D, d1));
            }

            super.stepOn(pLevel, pPos, pState, pEntity);
        }
    }
}
