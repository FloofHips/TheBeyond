package com.thebeyond.features;

import com.mojang.serialization.Codec;
import com.thebeyond.init.TBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

import java.util.Random;

public class BismuthFormationFeature extends Feature<NoneFeatureConfiguration> {
    public BismuthFormationFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        Random random = pContext.random();
        BlockPos blockpos = pContext.origin();
        float f = (float)random.nextInt(5) + 20;
        SimplexNoise noise = new SimplexNoise(new XoroshiroRandomSource(69420));
        double NoiseValue = (double) ((Mth.sin(0.3F * blockpos.getX() + 0.1F * blockpos.getY() + 0.4F * blockpos.getZ() + (float) noise.getValue(blockpos.getX() * 0.1F, blockpos.getY() * 0.1F, blockpos.getZ() * 0.1F) * 3F)));

        for(int i = 0; f > 0.5F; --i) {
            for(int j = Mth.ceil(f); j >= Mth.floor(-f); --j) {
                for(int k = Mth.ceil(f); k >= Mth.floor(-f); --k) {
                    if ((float)(j * j + k * k) <= (f + 1.0F)) {
                        this.setBlock(worldgenlevel, blockpos.offset(j, i, k), TBBlocks.BISMUTH.get().defaultBlockState());
                    }
                }
            }

            f -= (float)random.nextInt(2) + 0.5F;
        }

        f = (float)random.nextInt(5) + 20;

        for(int i = 0; f > 0.5F; ++i) {
            for(int j = Mth.ceil(f); j >= Mth.floor(-f); --j) {
                for(int k = Mth.ceil(f); k >= Mth.floor(-f); --k) {
                    if ((float)(j * j + k * k) <= (f + 1.0F)) {
                        if((float)(j * j + k * k) >= (f - 2.0F)){
                            this.setBlock(worldgenlevel, blockpos.offset(j, i, k), TBBlocks.BISMUTH.get().defaultBlockState());
                        }
                    }
                }
            }

            f -= (float)random.nextInt(2) + 0.5F;
        }

        return true;
    }
}

