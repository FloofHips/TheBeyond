package com.thebeyond;

import com.mojang.authlib.yggdrasil.YggdrasilEnvironment;
import com.thebeyond.data.*;
import com.thebeyond.dimension.TheBeyondSpecialEffects;
import com.thebeyond.entities.PurpleSlimeEntity;
import com.thebeyond.entities.models.PurpleSlimeModel;
import com.thebeyond.entities.renderers.ArmorBallRenderer;
import com.thebeyond.entities.renderers.PurpleSlimeRenderer;
import com.thebeyond.event.ClientEvents;
import com.thebeyond.init.*;
import com.thebeyond.mixin.TBDimensionSpecialEffectsMixin;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

@Mod(TheBeyond.MOD_ID)
public class TheBeyond {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final ResourceLocation MOD_DIMENSION_ID = new ResourceLocation(TheBeyond.MOD_ID, TheBeyond.MOD_ID);
    public static final String MOD_ID = "the_beyond";

    public TheBeyond() {

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientEvents.subscribeClientEvents();
        }

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::registerEntityRenders);
        modEventBus.addListener(this::entityLayerSetup);
        modEventBus.addListener(this::onAttributeCreate);
        modEventBus.addListener(this::gatherData);

        TBBlocks.BLOCKS.register(modEventBus);
        TBItems.ITEMS.register(modEventBus);
        TBEntities.ENTITIES.register(modEventBus);
        TBParticles.PARTICLE_TYPES.register(modEventBus);
        TBFeatures.FEATURES.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(TBEntities.ARMOR_BALL.get(), ArmorBallRenderer::new);
        event.registerEntityRenderer(TBEntities.PURPLE_SLIME.get(), PurpleSlimeRenderer::new);

    }

    @SubscribeEvent
    public void entityLayerSetup(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PurpleSlimeModel.PURPLE_SLIME_MLL, PurpleSlimeModel::createBodyLayer);
    }

    @SubscribeEvent
    public void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(TBEntities.PURPLE_SLIME.get(), PurpleSlimeEntity.prepareAttributes().build());
    }

    private void clientSetup(final FMLClientSetupEvent event) {
//        event.enqueueWork(() -> {
//            TBDimensionSpecialEffectsMixin.the_beyond_getBY_ResourceLocation().put(new ResourceLocation(TheBeyond.MOD_ID, "the_beyond_sky_property"), new TheBeyondSpecialEffects());
//        });

        ItemBlockRenderTypes.setRenderLayer(TBBlocks.POLAR_ANTENNA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TBBlocks.POROUS_REED.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TBBlocks.HOLLOW_REED.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TBBlocks.VILE_GROWTH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TBBlocks.COTTING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TBBlocks.SHRYBULB.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TBBlocks.BISMUTH_PILLAR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TBBlocks.CRYSTAL_GROWTH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TBBlocks.MAGNOLILLY.get(), RenderType.cutout());

    }
    @SubscribeEvent
    public void gatherData(GatherDataEvent event)
    {
//        DataGenerator TBgen = event.getGenerator();
//        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
//
//        TBgen.addProvider(new TBItemModelGen(TBgen));

        event.getGenerator().addProvider(new TBBlockstateGen(event.getGenerator(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(new TBItemModels(event.getGenerator(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(new TBLangGen(event.getGenerator()));
        //event.getGenerator().addProvider(provider);
        //event.getGenerator().addProvider(new TBLootTableGen(event.getGenerator()));
    }
}

