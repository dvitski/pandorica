package cc.dvitski.pandorica.worldgen

import cc.dvitski.pandorica.Pandorica
import cc.dvitski.pandorica.block.MagmaTongueBlock
import cc.dvitski.pandorica.block.PandoricaBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.valueproviders.BiasedToBottomInt
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider

object PandoricaConfiguredFeatures {
    val PATCH_MAGMA_TONGUE = create("patch_magma_tongue")

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
    }

    private fun create(id: String): ResourceKey<ConfiguredFeature<*, *>> {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id))
    }
}
