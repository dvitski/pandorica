package cc.dvitski.pandorica.client.render.entity

import cc.dvitski.pandorica.Pandorica
import cc.dvitski.pandorica.client.model.PandoricaModelLayers
import cc.dvitski.pandorica.entity.LiquefiedSkeletonEntity
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.SkeletonRenderState
import net.minecraft.resources.ResourceLocation

class LiquefiedSkeletonEntityRenderer(context: EntityRendererProvider.Context) : AbstractSkeletonRenderer<LiquefiedSkeletonEntity, SkeletonRenderState>(context,
    PandoricaModelLayers.LIQUEFIED_SKELETON,
    PandoricaModelLayers.LIQUEFIED_SKELETON_ARMOR
) {
    override fun getTextureLocation(state: SkeletonRenderState): ResourceLocation {
        return TEXTURE_LOCATION
    }

    override fun createRenderState(): SkeletonRenderState {
        return SkeletonRenderState()
    }

    companion object {
        val TEXTURE_LOCATION: ResourceLocation = ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, "textures/entity/skeleton/liquefied_skeleton.png")
    }
}
