package com.thebeyond;


import com.thebeyond.entities.models.PurpleSlimeModel;
import com.thebeyond.entities.renderers.ArmorBallRenderer;
import com.thebeyond.entities.renderers.PurpleSlimeRenderer;
import com.thebeyond.init.TBBiomes;
import com.thebeyond.init.TBBlocks;
import com.thebeyond.init.TBEntities;
import com.thebeyond.init.TBItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

@Mod(TheBeyond.MOD_ID)
public class TheBeyond {

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "the_beyond";

    public TheBeyond() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::registerEntityRenders);
        //MinecraftForge.EVENT_BUS.register(this);

        TBBlocks.BLOCKS.register(modEventBus);
        TBItems.ITEMS.register(modEventBus);
        TBEntities.ENTITIES.register(modEventBus);
        TBBiomes.BIOMES.register(modEventBus);
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

    private void clientSetup(final FMLClientSetupEvent event) {
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

    ////TODO
//    private static <T extends Entity, V extends T> void registerEntityRenderingHandler(RegisterRenderers event, Supplier<EntityType<V>> type, EntityRendererProvider<T> renderer) {
//        event.registerEntityRenderer(type.get(), renderer);
//    }

}

