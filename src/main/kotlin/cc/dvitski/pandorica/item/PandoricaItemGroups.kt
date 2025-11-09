package cc.dvitski.pandorica.item

import cc.dvitski.pandorica.Pandorica
import cc.dvitski.pandorica.Pandorica.filterPandoricaMod
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

object PandoricaItemGroups {
    val ITEM_GROUP = register("item_group",
        FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.${Pandorica.MOD_ID}.item_group"))
            .icon { ItemStack(PandoricaItems.LIQUEFIED_BONE) }
            .displayItems { _, entries ->
                BuiltInRegistries.ITEM.filterPandoricaMod().forEach(entries::accept)
            }
            .build()
    )

    private fun register(id: String, group: CreativeModeTab): CreativeModeTab {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id), group)
    }
}
