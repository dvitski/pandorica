package cc.dvitski.pandorica.item

import cc.dvitski.pandorica.Pandorica
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block

object PandoricaItems {
    val BASALT_DUST = register("basalt_dust")
    val CRUSTED_MAGMA = register("crusted_magma")
    val LIQUEFIED_BONE = register("liquefied_bone")

    private fun register(id: String, block: Block): Item {
        return register(id) { BlockItem(block, Item.Properties()) }
    }

    private fun register(id: String, factory: (Item.Properties) -> Item = ::Item): Item {
        val location = ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id)
        val key = ResourceKey.create(Registries.ITEM, location)
        return Items.registerItem(key, factory)
    }
}
