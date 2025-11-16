package cc.dvitski.pandorica.client.model

import cc.dvitski.pandorica.Pandorica
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.model.SkeletonModel
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.renderer.entity.ArmorModelSet
import net.minecraft.client.renderer.entity.state.SkeletonRenderState
import net.minecraft.resources.ResourceLocation

object PandoricaModelLayers {
    val LIQUEFIED_SKELETON = register("liquefied_skeleton", SkeletonModel<SkeletonRenderState>::createBodyLayer)
    val LIQUEFIED_SKELETON_ARMOR = registerArmor("liquefied_skeleton", ::createArmorLayerDefinitions)

    private fun create(id: String, layer: String): ModelLayerLocation {
        return ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id), layer)
    }

    private fun createArmorLocations(id: String): ArmorModelSet<ModelLayerLocation> {
        return ArmorModelSet(
            create(id, "helmet"),
            create(id, "chestplate"),
            create(id, "leggings"),
            create(id, "boots")
        )
    }

    private fun createArmorLayerDefinitions(): ArmorModelSet<LayerDefinition> {
        return HumanoidModel.createArmorMeshSet(CubeDeformation(0.5f), CubeDeformation(1.0f))
            .map { LayerDefinition.create(it, 64, 32) }
    }

    private fun register(id: String, provider: EntityModelLayerRegistry.TexturedModelDataProvider, layer: String = "main"): ModelLayerLocation {
        return create(id, layer).also {
            EntityModelLayerRegistry.registerModelLayer(it, provider)
        }
    }

    private fun registerArmor(id: String, provider: EntityModelLayerRegistry.TexturedEquipmentModelDataProvider): ArmorModelSet<ModelLayerLocation> {
        return createArmorLocations(id).also {
            EntityModelLayerRegistry.registerEquipmentModelLayers(it, provider)
        }
    }
}
