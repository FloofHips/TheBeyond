package com.thebeyond.dimension;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.thebeyond.TheBeyond;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ISkyRenderHandler;

import javax.annotation.Nullable;

public class TheBeyondSpecialEffects extends DimensionSpecialEffects {

    private static final ResourceLocation B0 = new ResourceLocation(TheBeyond.MOD_ID, "textures/environment/0.png");
    private static final ResourceLocation B1 = new ResourceLocation(TheBeyond.MOD_ID, "textures/environment/1.png");
    private static final ResourceLocation B2 = new ResourceLocation(TheBeyond.MOD_ID, "textures/environment/2.png");
    private static final ResourceLocation B3 = new ResourceLocation(TheBeyond.MOD_ID, "textures/environment/3.png");
    private static final ResourceLocation TEST = new ResourceLocation(TheBeyond.MOD_ID, "textures/environment/beyond_horizon.png");

    public TheBeyondSpecialEffects() {
        super(Float.NaN, true, SkyType.NORMAL, false, false);
        setSkyRenderHandler(TheBeyondSpecialEffects::TBrenderSky);
    }

    @Nullable
    public net.minecraftforge.client.ISkyRenderHandler getSkyRenderHandler() {
        ISkyRenderHandler skyRenderHandler = TheBeyondSpecialEffects::TBrenderSky;
        return skyRenderHandler;
    }
    public static void TBrenderSky(int ticks, float partialTicks, PoseStack pPoseStack, ClientLevel level, Minecraft minecraft) {

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);

        RenderSystem.setShaderTexture(0, TEST);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();

        for(int i = 0; i < 4; i++) {
            pPoseStack.pushPose();
            if (i == 0) { //perfect
                pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
            }

            if (i == 1) { //perfect
                pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                //pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F)); //was 180
            }

            if (i == 2) {
                pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F)); //was -90
            }

            if (i == 3) { //perfect
                pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(-90.0F));
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(90.0F)); //was -90
            }

            Matrix4f matrix4f = pPoseStack.last().pose();
            bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferbuilder.vertex(matrix4f, -100.0F, -100.0F, -100.0F).uv(0.0F, 0.0F).color(255, 255, 255, 100).endVertex();
            bufferbuilder.vertex(matrix4f, -100.0F, -100.0F, 100.0F).uv(0.0F, 1.0F).color(255, 255, 255, 100).endVertex();
            bufferbuilder.vertex(matrix4f, 100.0F, -100.0F, 100.0F).uv(1.0F, 1.0F).color(255, 255, 255, 100).endVertex();
            bufferbuilder.vertex(matrix4f, 100.0F, -100.0F, -100.0F).uv(1.0F, 0.0F).color(255, 255, 255, 100).endVertex();
            tesselator.end();
            pPoseStack.popPose();
        }

        RenderSystem.depthMask(true);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void TBrenderSkySIDE(int ticks, float partialTicks, PoseStack pPoseStack, ClientLevel level, Minecraft minecraft) {

        RenderSystem.disableTexture();
        Vec3 vec3 = level.getSkyColor(minecraft.gameRenderer.getMainCamera().getPosition(), partialTicks);
        float f10 = (float) vec3.x;
        float f = (float) vec3.y;
        float f1 = (float) vec3.z;
        FogRenderer.levelFogColor();
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.depthMask(true);
        RenderSystem.setShaderColor(f10, f, f1, 1.0F);
        ShaderInstance shaderinstance = RenderSystem.getShader();
        //skyBuffer.drawWithShader(p_202424_.last().pose(), p_202425_, shaderinstance);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
        //float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(partialTicks), partialTicks);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.disableTexture();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.pushPose();
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        //float f2 = Mth.sin(level.getSunAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F;
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(90));
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        Matrix4f matrix4f = pPoseStack.last().pose();
        bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
        bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(255, 255, 255, 255).endVertex();
        int i = 16;

        for(int j = 0; j <= 16; ++j) {
            float f6 = (float)j * ((float)Math.PI * 4.0F) / 16.0F;
            float f7 = Mth.sin(f6);
            float f8 = Mth.cos(f6);
            bufferbuilder.vertex(matrix4f, f7 * 360.0F, f8 * 100.0F, -f8 * 360.0F * 255).color(255, 255, 255, 0.0F).endVertex();
        }

        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
        pPoseStack.popPose();
    }

    public static void TrenderSky(int ticks, float partialTick, PoseStack poseStack, ClientLevel level, Minecraft minecraft){

    }



    public Vec3 getBrightnessDependentFogColor(Vec3 p_108901_, float p_108902_) {
        return p_108901_;
    }

    public boolean isFoggyAt(int p_108891_, int p_108892_) {
        return true;
    }
}





//    private net.minecraftforge.client.ISkyRenderHandler skyRenderHandler = com.thebeyond.dimension.TBSkyRenderer;
//
//    public void setSkyRenderHandler(net.minecraftforge.client.ISkyRenderHandler skyRenderHandler) {
//        this.skyRenderHandler = com.thebeyond.dimension.TBSkyRenderer;
//    }
//    public net.minecraftforge.client.ISkyRenderHandler getSkyRenderHandler() {
//        return skyRenderHandler;
//    }
//    public TheBeyondSpecialEffects() {
//        super(Float.NaN, true, DimensionSpecialEffects.SkyType.END, false, true);
//    }
//
//    public Vec3 getBrightnessDependentFogColor(Vec3 p_108901_, float p_108902_) {
//        return p_108901_;
//    }
//
//    public boolean isFoggyAt(int p_108898_, int p_108899_) {
//        return false;
//    }
//
//}
