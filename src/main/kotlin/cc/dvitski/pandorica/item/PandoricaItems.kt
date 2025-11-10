package cc.dvitski.pandorica.item

import cc.dvitski.pandorica.Pandorica
import cc.dvitski.pandorica.block.PandoricaBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block

object PandoricaItems {
    val BASALT_DUST = register("basalt_dust")
    val CRUSTED_MAGMA = register("crusted_magma")
    val LIQUEFIED_BONE = register("liquefied_bone")

    val MAGMA_TONGUE = register(PandoricaBlocks.MAGMA_TONGUE)

    private fun register(block: Block): Item {
        return Items.registerBlock(block)
    }

    private fun register(id: String, factory: (Item.Properties) -> Item = ::Item): Item {
        val location = ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id)
        val key = ResourceKey.create(Registries.ITEM, location)
        return Items.registerItem(key, factory)
    }
}
