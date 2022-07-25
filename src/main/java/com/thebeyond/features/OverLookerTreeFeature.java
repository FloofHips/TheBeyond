package com.thebeyond.features;

import com.mojang.serialization.Codec;
import com.thebeyond.init.TBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class OverLookerTreeFeature extends Feature<NoneFeatureConfiguration> {
    public OverLookerTreeFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    private void placeEye(FeaturePlaceContext pContext){

        WorldGenLevel worldgenlevel = pContext.level();
        Random random = pContext.random();
        BlockPos blockpos = pContext.origin();

        int AXIS = random.nextInt(2);;
        this.setBlock(worldgenlevel, blockpos, TBBlocks.DISPLACED_EYE.get().defaultBlockState());
        for(int j=-1; j<=1; j++){
            if(AXIS==0) {
                this.setBlock(worldgenlevel, blockpos.offset(j, 2, 0), Blocks.END_STONE.defaultBlockState());
                this.setBlock(worldgenlevel, blockpos.offset(-2, j, 0), Blocks.END_STONE.defaultBlockState());
                this.setBlock(worldgenlevel, blockpos.offset(j, -2, 0), Blocks.END_STONE.defaultBlockState());
                this.setBlock(worldgenlevel, blockpos.offset(2, j, 0), Blocks.END_STONE.defaultBlockState());
            }
            else {
                this.setBlock(worldgenlevel, blockpos.offset(0, 2, j), Blocks.END_STONE.defaultBlockState());
                this.setBlock(worldgenlevel, blockpos.offset(0, j, -2), Blocks.END_STONE.defaultBlockState());
                this.setBlock(worldgenlevel, blockpos.offset(0, -2, j), Blocks.END_STONE.defaultBlockState());
                this.setBlock(worldgenlevel, blockpos.offset(0, j, 2), Blocks.END_STONE.defaultBlockState());
            }
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {

        SimplexNoise noise = new SimplexNoise(new XoroshiroRandomSource(69420));

        WorldGenLevel worldgenlevel = pContext.level();
        Random random = pContext.random();
        BlockPos blockpos = pContext.origin();
        double NoiseValue = 0;

        float f = (float)random.nextInt(2)+5;

        this.setBlock(worldgenlevel, blockpos, TBBlocks.DISPLACED_EYE.get().defaultBlockState());

        for(int i = -10; i <= 10; ++i) {
            for(int j = -10; j <= 10; ++j) {
                for(int k = -10; k <= 10; ++k) {
                    NoiseValue = (double) ((Mth.sin(0.3F * blockpos.offset(j, i, k).getX() + 0.1F * blockpos.offset(j, i, k).getY() + 0.4F * blockpos.offset(j, i, k).getZ() + (float) noise.getValue(blockpos.offset(j, i, k).getX() * 0.1F, blockpos.offset(j, i, k).getY() * 0.1F, blockpos.offset(j, i, k).getZ() * 0.1F) * 3F)));
                    if(i*i/3+j*j+k*k < 20) {
                        this.setBlock(worldgenlevel, blockpos.offset(j, i, k), Blocks.END_STONE.defaultBlockState());
                    }
                }
            }
        }

        for(int i = -10; i <= 10; ++i) {
            for(int j = -10; j <= 10; ++j) {
                for(int k = -10; k <= 10; ++k) {
                    NoiseValue = (double) ((Mth.sin(0.3F * blockpos.offset(j, i, k).getX() + 0.1F * blockpos.offset(j, i, k).getY() + 0.4F * blockpos.offset(j, i, k).getZ() + (float) noise.getValue(blockpos.offset(j, i, k).getX() * 0.1F, blockpos.offset(j, i, k).getY() * 0.1F, blockpos.offset(j, i, k).getZ() * 0.1F) * 3F)));
                    if(i*i/3+j*j+k*k < 10) {
                        this.setBlock(worldgenlevel, blockpos.offset(j+2, i+2, k), Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }
        return true;
    }
}
