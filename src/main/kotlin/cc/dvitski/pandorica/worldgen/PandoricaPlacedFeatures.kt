package cc.dvitski.pandorica.worldgen

import cc.dvitski.pandorica.Pandorica
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.placement.RarityFilter

object PandoricaPlacedFeatures {
    val PATCH_MAGMA_TONGUE = create("patch_magma_tongue")

    fun bootstrap(context: BootstrapContext<PlacedFeature>) {
        val configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE)
        PlacementUtils.register(context, PATCH_MAGMA_TONGUE,
            configuredFeatures.getOrThrow(PandoricaConfiguredFeatures.PATCH_MAGMA_TONGUE),
            listOf(
                CountOnEveryLayerPlacement.of(6),
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(3),
            )
        )
    }

    private fun create(id: String): ResourceKey<PlacedFeature> {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id))
    }
}
