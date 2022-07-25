package com.thebeyond.features;

import com.mojang.serialization.Codec;
import com.thebeyond.init.TBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class LongIslandFeature extends Feature<NoneFeatureConfiguration> {
    public LongIslandFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        Random random = pContext.random();
        BlockPos blockpos = pContext.origin();
        float f = (float)random.nextInt(30) + 10.0F;

        for(int i = 0; f > 0.5F; --i) {
            for(int j = Mth.floor(-f); j <= Mth.ceil(f); ++j) {
                for(int k = Mth.floor(-f); k <= Mth.ceil(f); ++k) {
                    if ((float)(j * j + k * k) <= (f + 1.0F)) {
                        this.setBlock(worldgenlevel, blockpos.offset(j, i, k), Blocks.END_STONE.defaultBlockState());

                        if((random.nextInt(5)<3 && i>-6) || i>=-1){
                            this.setBlock(worldgenlevel, blockpos.offset(j, i, k), Blocks.MOSS_BLOCK.defaultBlockState());
                        }
                        if((random.nextInt(10)<3 && i>-9)){
                            this.setBlock(worldgenlevel, blockpos.offset(j, i, k), Blocks.MOSS_BLOCK.defaultBlockState());
                        }
                    }
                }
            }

            f -= (float)random.nextInt(2) + 0.5F;
        }

        return true;
    }
}
