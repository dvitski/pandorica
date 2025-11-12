package cc.dvitski.pandorica

import cc.dvitski.pandorica.block.PandoricaBlocks
import cc.dvitski.pandorica.item.PandoricaItemGroups
import cc.dvitski.pandorica.item.PandoricaItems
import cc.dvitski.pandorica.worldgen.PandoricaPlacedFeatures
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.core.Registry
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.levelgen.GenerationStep
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Pandorica : ModInitializer {
    const val MOD_ID = "pandorica"
    const val MOD_NAME = "Pandorica"

    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    @Suppress("UnusedExpression")
    override fun onInitialize() {
        logger.info("Initializing $MOD_NAME")

        PandoricaBlocks
        PandoricaItems
        PandoricaItemGroups

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.CRIMSON_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, PandoricaPlacedFeatures.PATCH_MAGMA_TONGUE)
    }

    fun <T : Any?> Registry<T>.filterPandoricaMod(): List<T> {
        return filter { obj ->
            val location = getKey(obj)
            location?.namespace == MOD_ID
        }
    }
}
