package cc.dvitski.pandorica

import cc.dvitski.pandorica.block.PandoricaBlocks
import cc.dvitski.pandorica.entity.PandoricaEntityTypes
import cc.dvitski.pandorica.item.PandoricaItemGroups
import cc.dvitski.pandorica.item.PandoricaItems
import cc.dvitski.pandorica.particle.PandoricaParticleTypes
import cc.dvitski.pandorica.world.gen.PandoricaWorldGen
import cc.dvitski.pandorica.world.loot.PandoricaLootModifications
import net.fabricmc.api.ModInitializer
import net.minecraft.core.Registry
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Pandorica : ModInitializer {
    const val MOD_ID = "pandorica"
    const val MOD_NAME = "Pandorica"

    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    @Suppress("UnusedExpression")
    override fun onInitialize() {
        logger.info("Initializing $MOD_NAME")

        PandoricaParticleTypes
        PandoricaBlocks
        PandoricaItems
        PandoricaItemGroups
        PandoricaEntityTypes

        PandoricaWorldGen.initialize()
        PandoricaLootModifications.initialize()
    }

    fun <T : Any?> Registry<T>.filterPandoricaMod(): List<T> {
        return filter { obj ->
            val location = getKey(obj)
            location?.namespace == MOD_ID
        }
    }
}
