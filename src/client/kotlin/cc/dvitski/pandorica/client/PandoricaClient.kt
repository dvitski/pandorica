package cc.dvitski.pandorica.client

import cc.dvitski.pandorica.Pandorica.MOD_ID
import cc.dvitski.pandorica.Pandorica.MOD_NAME
import net.fabricmc.api.ClientModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object PandoricaClient : ClientModInitializer {
    val logger: Logger = LoggerFactory.getLogger("$MOD_ID-client")

	override fun onInitializeClient() {
        logger.info("Initializing $MOD_NAME client")
	}
}
