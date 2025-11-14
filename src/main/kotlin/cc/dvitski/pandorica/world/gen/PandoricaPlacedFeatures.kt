package cc.dvitski.pandorica.world.gen

import cc.dvitski.pandorica.Pandorica
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.OrePlacements
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import net.minecraft.world.level.levelgen.placement.RarityFilter

object PandoricaPlacedFeatures {
    val PATCH_MAGMA_TONGUE = create("patch_magma_tongue")
    val WITHERMEAL_DIRT = create("withermeal_dirt")
    val ORE_CRUMBLED_BASALT = create("ore_crumbled_basalt")

    fun bootstrap(context: BootstrapContext<PlacedFeature>) {
        val configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE)
        fun register(key: ResourceKey<PlacedFeature>, holder: ResourceKey<ConfiguredFeature<*, *>>, vararg modifiers: PlacementModifier) {
            PlacementUtils.register(context, key, configuredFeatures.getOrThrow(holder), *modifiers)
        }

        register(PATCH_MAGMA_TONGUE, PandoricaConfiguredFeatures.PATCH_MAGMA_TONGUE,
            CountOnEveryLayerPlacement.of(6),
            BiomeFilter.biome(),
            RarityFilter.onAverageOnceEvery(3),
        )

        register(WITHERMEAL_DIRT, PandoricaConfiguredFeatures.WITHERMEAL_DIRT)
        register(ORE_CRUMBLED_BASALT, PandoricaConfiguredFeatures.ORE_CRUMBLED_BASALT,
            *OrePlacements.commonOrePlacement(
                4,
                HeightRangePlacement.uniform(
                    VerticalAnchor.bottom(),
                    VerticalAnchor.aboveBottom(82)
                )
            ).toTypedArray()
        )
    }

    private fun create(id: String): ResourceKey<PlacedFeature> {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id))
    }
}
