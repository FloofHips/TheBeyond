package com.thebeyond;

import com.thebeyond.init.TBBlocks;
import com.thebeyond.init.TBEntities;
import com.thebeyond.init.TBItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraftforge.registries.ForgeRegistries.BLOCKS;

@Mod(TheBeyond.MOD_ID)
public class TheBeyond {

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "the_beyond";

    public static CreativeModeTab creativeTab;

    public TheBeyond() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        //MinecraftForge.EVENT_BUS.register(this);

        TBBlocks.BLOCKS.register(modEventBus);
        TBItems.ITEMS.register(modEventBus);
        TBEntities.ENTITIES.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        creativeTab = new CreativeModeTab(-1, MOD_ID) {

            @Override
            @OnlyIn(Dist.CLIENT)
            public ItemStack makeIcon() {

                return new ItemStack(BLOCKS.getValue(new ResourceLocation(MOD_ID,"pearl_block")));
            }
        };
    }

    ////TODO
    //private static <T extends Entity, V extends T> void registerEntityRenderingHandler(RegisterRenderers event, Supplier<EntityType<V>> type, EntityRendererProvider<T> renderer) {
    //
    //    event.registerEntityRenderer(type.get(), renderer);
    //}

}
