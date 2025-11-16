package cc.dvitski.pandorica.client

import cc.dvitski.pandorica.Pandorica.MOD_ID
import cc.dvitski.pandorica.Pandorica.MOD_NAME
import cc.dvitski.pandorica.client.model.PandoricaModelLayers
import cc.dvitski.pandorica.client.particle.SizzleParticle
import cc.dvitski.pandorica.client.render.entity.LiquefiedSkeletonEntityRenderer
import cc.dvitski.pandorica.entity.PandoricaEntityTypes
import cc.dvitski.pandorica.particle.PandoricaParticleTypes
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.minecraft.client.renderer.entity.EntityRenderers
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object PandoricaClient : ClientModInitializer {
    val logger: Logger = LoggerFactory.getLogger("$MOD_ID-client")

    @Suppress("UnusedExpression")
    override fun onInitializeClient() {
        logger.info("Initializing $MOD_NAME client")

        PandoricaModelLayers

        ParticleFactoryRegistry.getInstance().register(PandoricaParticleTypes.SIZZLE) { SizzleParticle.Provider(it) }

        EntityRenderers.register(PandoricaEntityTypes.LIQUFIED_SKELETON, ::LiquefiedSkeletonEntityRenderer)
    }
}
