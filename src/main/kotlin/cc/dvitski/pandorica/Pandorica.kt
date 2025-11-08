package cc.dvitski.pandorica

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Pandorica : ModInitializer {
    const val MOD_ID = "pandorica"
    const val MOD_NAME = "Pandorica"

    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        logger.info("Initializing $MOD_NAME")
    }
}
