package cc.dvitski.pandorica.world.gen

import cc.dvitski.pandorica.Pandorica
import cc.dvitski.pandorica.block.MagmaTongueBlock
import cc.dvitski.pandorica.block.PandoricaBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.random.WeightedList
import net.minecraft.util.valueproviders.BiasedToBottomInt
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest

object PandoricaConfiguredFeatures {
    val PATCH_MAGMA_TONGUE = create("patch_magma_tongue")
    val WITHERMEAL_DIRT = create("withermeal_dirt")
    val ORE_CRUMBLED_BASALT = create("ore_crumbled_basalt")

    fun bootstrap(context: BootstrapContext<ConfiguredFeature<*, *>>) {
        val tongueProperty = MagmaTongueBlock.TONGUES
        FeatureUtils.register(context, PATCH_MAGMA_TONGUE,
            Feature.NETHER_FOREST_VEGETATION,
            NetherForestVegetationConfig(
                RandomizedIntStateProvider(
                    BlockStateProvider.simple(PandoricaBlocks.MAGMA_TONGUE),
                    tongueProperty,
                    BiasedToBottomInt.of(tongueProperty.min, tongueProperty.max),
                ),
                4, 4
            )
        )

        FeatureUtils.register(context, WITHERMEAL_DIRT,
            Feature.SIMPLE_BLOCK,
            SimpleBlockConfiguration(
                WeightedStateProvider(
                    WeightedList.builder<BlockState>()
                        .add(Blocks.NETHERRACK.defaultBlockState(), 16)
                        .add(PandoricaBlocks.CRUMBLED_BASALT.defaultBlockState(), 3)
                        .add(Blocks.NETHER_WART_BLOCK.defaultBlockState(), 2)
                        .add(Blocks.MAGMA_BLOCK.defaultBlockState(), 2)
                        .add(Blocks.SOUL_SAND.defaultBlockState(), 1)
                        .build()
                )
            )
        )

        FeatureUtils.register(context, ORE_CRUMBLED_BASALT,
            Feature.ORE,
            OreConfiguration(
                BlockMatchTest(Blocks.NETHERRACK),
                PandoricaBlocks.CRUMBLED_BASALT.defaultBlockState(),
                45
            )
        )
    }

    private fun create(id: String): ResourceKey<ConfiguredFeature<*, *>> {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id))
    }
}
