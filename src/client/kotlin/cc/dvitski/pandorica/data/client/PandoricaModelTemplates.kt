package cc.dvitski.pandorica.data.client

import cc.dvitski.pandorica.Pandorica
import net.minecraft.client.data.models.model.ModelTemplate
import net.minecraft.client.data.models.model.TextureSlot
import net.minecraft.resources.ResourceLocation
import java.util.Optional

object PandoricaModelTemplates {
    val MAGMA_TONGUE_1 = create("magma_tongue", "_1", TextureSlot.TEXTURE)
    val MAGMA_TONGUE_2 = create("two_magma_tongues", "_2", TextureSlot.TEXTURE)
    val MAGMA_TONGUE_3 = create("three_magma_tongues", "_3", TextureSlot.TEXTURE)
    val MAGMA_TONGUE_4 = create("four_magma_tongues", "_4", TextureSlot.TEXTURE)
    val MAGMA_TONGUE_5 = create("five_magma_tongues", "_5", TextureSlot.TEXTURE)

    private fun create(id: String, vararg slots: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id)), Optional.empty(), *slots)
    }

    private fun create(id: String, suffix: String, vararg slots: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id)), Optional.of(suffix), *slots)
    }
}
