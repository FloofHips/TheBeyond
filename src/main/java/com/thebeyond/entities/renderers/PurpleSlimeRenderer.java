package com.thebeyond.entities.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thebeyond.entities.PurpleSlimeEntity;
import com.thebeyond.entities.models.PurpleSlimeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PurpleSlimeRenderer extends MobRenderer<PurpleSlimeEntity, PurpleSlimeModel<PurpleSlimeEntity>> {

    private static final ResourceLocation PURPLESLIME_LOCATION=new ResourceLocation("textures/entity/livid_corruption/purple_slime_naked.png");

    public PurpleSlimeRenderer(EntityRendererProvider.Context p_174298_){
        super(p_174298_,new PurpleSlimeModel<>(p_174298_.bakeLayer(PurpleSlimeModel.PURPLE_SLIME_MLL)),0.25F);
    }

    protected int getBlockLightLevel(PurpleSlimeEntity pEntity,BlockPos pPos){
        return 15;
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(PurpleSlimeEntity pEntity){
        return PURPLESLIME_LOCATION;
    }

    protected void scale(PurpleSlimeEntity pLivingEntity,PoseStack pMatrixStack,float pPartialTickTime){
        int i=pLivingEntity.getSize();
        float f=Mth.lerp(pPartialTickTime,pLivingEntity.oSquish,pLivingEntity.squish)/((float)i*0.5F+1.0F);
        float f1=1.0F/(f+1.0F);
        pMatrixStack.scale(f1*(float)i,1.0F/f1*(float)i,f1*(float)i);
    }
}
