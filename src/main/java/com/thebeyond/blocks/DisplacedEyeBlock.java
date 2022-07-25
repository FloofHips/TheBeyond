package com.thebeyond.blocks;

import com.thebeyond.init.TBParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class DisplacedEyeBlock extends Block {
    public DisplacedEyeBlock(Properties p_49795_) {
        super(p_49795_);
    }

    private static final VoxelShape SHAPE = Block.box(5.0D, 5.0D, 5.0D, 11.0D, 11.0D, 11.0D);

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRand) {
        double d0 = (double)pPos.getX() + 0.5D;
        double d1 = (double)pPos.getY() + 0.5D;
        double d2 = (double)pPos.getZ() + 0.5D;

        double d6 = pRand.nextDouble() * 6.0D / 16.0D;

        if (pRand.nextDouble() < 0.05D) {
            pLevel.playLocalSound(d0, d1, d2, SoundEvents.CONDUIT_AMBIENT_SHORT, SoundSource.BLOCKS, 0.5F, 1.0F, false);
        }
        if (pRand.nextDouble() < 0.25D) {
            pLevel.playLocalSound(d0, d1, d2, SoundEvents.CONDUIT_AMBIENT, SoundSource.BLOCKS, 0.1F, 1.0F, false);
        }
        pLevel.addParticle(TBParticles.DISPLACED_EYE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        pLevel.addParticle(ParticleTypes.PORTAL, d0 + d6, d1 + d6, d2 + d6, 0.0D, 0.0D, 0.0D);
    }
}
