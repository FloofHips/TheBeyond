package com.thebeyond.init;

import com.thebeyond.TBCreativeTab;
import com.thebeyond.TheBeyond;
import com.thebeyond.blocks.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TBBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheBeyond.MOD_ID);

    public static final RegistryObject<Block> NULL_SHALE        = registerBlock("null_shale", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.TUFF).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> ZAROBLENDE        = registerBlock("zaroblende", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DRIPSTONE_BLOCK).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> CRYSTAL_VOID        = registerBlock("crystal_void", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(1f).lightLevel(state -> 10).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    //Pearlescent Planes
    public static final RegistryObject<Block> PEARL_BLOCK        = registerBlock("pearl_block", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> COMPRESSED_SAND    = registerBlock("compressed_sand", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.NETHERRACK).strength(1f)), TBCreativeTab.THE_BEYOND);
                                            //BIOLUMINESCENT
    public static final RegistryObject<Block> GLOW_SAND            = registerBlock("glow_sand", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.NETHERRACK).strength(1f).lightLevel(state -> 13)), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> CLAM_CHUNK           = registerBlock("clam_chunk", () -> new ClamBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE_TILES).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> MOTHER_OF_PEARL      = registerBlock("mother_of_pearl", () -> new MotherOfPearlBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.SHROOMLIGHT).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    public static final RegistryObject<Block> PEARL_BRICKS         = registerBlock("pearl_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> CHISELED_PEARL       = registerBlock("chiseled_pearl", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> SMOOTH_PEARL         = registerBlock("smooth_pearl", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> PEARL_PILLAR         = registerBlock("pearl_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> COBBLED_PEARL        = registerBlock("cobbled_pearl", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> COBBLED_PEARL_BRICKS = registerBlock("cobbled_pearl_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> SMOOTH_COBBLED_PEARL = registerBlock("smooth_cobbled_pearl", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    //Magnetic Field
    public static final RegistryObject<Block> FERROUS_CATALYST   = registerBlock("ferrous_catalyst", () -> new FerrousCatalyst(BlockBehaviour.Properties.of(Material.METAL).strength(1.5f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> FERROPILLAR        = registerBlock("ferropillar", () -> new Ferropillar(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.BONE_BLOCK).strength(2f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> PLATE_BLOCK        = registerBlock("plate_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.LANTERN).strength(2f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> PLATED_END_STONE   = registerBlock("plated_end_stone", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.ANCIENT_DEBRIS).strength(1.5f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> POLAR_ANTENNA      = registerBlock("polar_antenna", () -> new PolarAntenna(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.ANCIENT_DEBRIS).noCollission().strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> GELLID_VOID        = registerBlock("gellid_void", () -> new PseudoFluidBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.POWDER_SNOW).strength(3f).noOcclusion().requiresCorrectToolForDrops().lightLevel(state -> 10)), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> MAGNOLILLY         = registerBlock("magnolilly", () -> new MagnolillyBlock(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.ANCIENT_DEBRIS).noCollission().strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    //Orchestral Valley
    public static final RegistryObject<Block> COTTON_ROT         = registerBlock("cotton_rot", () -> new Block(BlockBehaviour.Properties.of(Material.WOOL).sound(SoundType.SNOW).strength(1f)), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> HOLLOW_REED        = registerBlock("hollow_reed", () -> new HollowReed(BlockBehaviour.Properties.of(Material.BAMBOO).sound(SoundType.SCAFFOLDING).strength(1f).noOcclusion().requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> POROUS_REED        = registerBlock("porous_reed", () -> new PorousReed(BlockBehaviour.Properties.of(Material.BAMBOO).sound(SoundType.BAMBOO).strength(1f).noOcclusion().requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> VIOLET_ROT         = registerBlock("violet_rot", () -> new Block(BlockBehaviour.Properties.of(Material.WOOL).sound(SoundType.FUNGUS).strength(1f)), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> VILE_GROWTH        = registerBlock("vile_growth", () -> new EndBushBlock(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.FUNGUS).noCollission().strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> COTTING            = registerBlock("cotting", () -> new EndBushBlock(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.SNOW).noCollission().strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> SHRYBULB           = registerBlock("shrybulb", () -> new HangingEndBushBlock(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.AMETHYST_CLUSTER).noCollission().strength(1f).requiresCorrectToolForDrops().lightLevel(state -> 13)), TBCreativeTab.THE_BEYOND);

    //Forge Of Creation
    public static final RegistryObject<Block> GOLIATH_STONE      = registerBlock("goliath_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.CALCITE).strength(2f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
                                            //DESSICaTED
    public static final RegistryObject<Block> DRY_ICHOR          = registerBlock("dry_ichor", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE_BRICKS).strength(2f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    //Great Graveyard
    public static final RegistryObject<Block> GLASS_OBSIDIAN     = registerBlock("glass_obsidian", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE_TILES).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> RUNIC_OBSIDIAN     = registerBlock("runic_obsidian", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE_TILES).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    //Disfigured Forest
    public static final RegistryObject<Block> DISFIGURED_GRASS   = registerBlock("disfigured_grass", () -> new Block(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.NYLIUM).strength(1f)), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> DISFIGURED_EARTH   = registerBlock("disfigured_earth", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.NETHERRACK).strength(1f)), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> DISFIGURED_OAK_LOG = registerBlock("disfigured_oak_log", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.BASALT).strength(2f)), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> DISFIGURED_OAK_LEAVES = registerBlock("disfigured_oak_leaves", () -> new Block(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.AZALEA_LEAVES).strength(1f)), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> DISFIGURED_VINES   = registerBlock("disfigured_vines", () -> new Block(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.AZALEA_LEAVES).strength(1f)), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> DISPLACED_EYE      = registerBlock("displaced_eye", () -> new DisplacedEyeBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GLASS).strength(3f).noOcclusion().noCollission().requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    //Bismuthian Palace
    public static final RegistryObject<Block> BISMUTH            = registerBlock("bismuth", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.BONE_BLOCK).strength(3f).noOcclusion().requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> MOLTEN_METAL       = registerBlock("molten_metal", () -> new PseudoFluidBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.POWDER_SNOW).strength(3f).noOcclusion().requiresCorrectToolForDrops().lightLevel(state -> 10)), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> BRITTLE_METAL      = registerBlock("brittle_metal", () -> new BrittleMetalBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.COPPER).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> ERODED_ENDSTONE    = registerBlock("eroded_endstone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.BASALT).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> BISMUTH_PILLAR     = registerBlock("bismuth_pillar", () -> new PillarBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.BONE_BLOCK).strength(3f).noOcclusion().dynamicShape().requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> CRYSTAL_GROWTH     = registerBlock("crystal_growth", () -> new EndBushBlock(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.BASALT).noCollission().strength(1f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    //The Beyond
    public static final RegistryObject<Block> SINGULARITY_BRANCH = registerBlock("singularity_branch", () -> new SingularityBranch(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_PURPLE).strength(0.4F).sound(SoundType.SHROOMLIGHT).lightLevel(state -> 16)), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> SINGULARITY_BLOCK  = registerBlock("singularity_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.SHROOMLIGHT).strength(3f).lightLevel(state -> 16).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    //The Farlands

    //Architecture
    public static final RegistryObject<Block> ZELLIGE_TILES     = registerBlock("zellige_tiles", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE_TILES).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> PATTERNED_ZELLIGE = registerBlock("patterned_zellige", () -> new GlazedTerracottaBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE_TILES).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    public static final RegistryObject<Block> LILIM_TILES       = registerBlock("lilim_tiles", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.CALCITE).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> CARVED_LILIM      = registerBlock("carved_lilim", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.CALCITE).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> GALVANIZED_LILIM  = registerBlock("galvanized_lilim", () -> new TBDirectionalBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.NETHERITE_BLOCK).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> LILIM_FACADE      = registerBlock("lilim_facade", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.CALCITE).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> LILIM_PILLAR      = registerBlock("lilim_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.CALCITE).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    public static final RegistryObject<Block> ZARDINE           = registerBlock("zardine", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.SCAFFOLDING).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> ZARDINE_STAIRS    = registerBlock("zardine_stairs", () -> new StairBlock(() -> ZARDINE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.SCAFFOLDING).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> PATTERNED_ZARDINE = registerBlock("patterned_zardine", () -> new GlazedTerracottaBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.SCAFFOLDING).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> ZARDINE_TILES     = registerBlock("zardine_tiles", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.SCAFFOLDING).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> ZARDINE_TRIM      = registerBlock("zardine_trim", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.SCAFFOLDING).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    //public static final RegistryObject<Block> GALVANIZED_ROOF_TILES      = registerBlock("galvanized_roof_tiles", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.LANTERN).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> GALVANIZED_RESIN  = registerBlock("galvanized_resin", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.LANTERN).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);
    public static final RegistryObject<Block> GALVANIZED_BISMUTH  = registerBlock("galvanized_bismuth", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.LANTERN).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);

    public static final RegistryObject<Block> RESIN             = registerBlock("resin", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.TUFF).strength(3f).requiresCorrectToolForDrops()), TBCreativeTab.THE_BEYOND);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return TBItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}