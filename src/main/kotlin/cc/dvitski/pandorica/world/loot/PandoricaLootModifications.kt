package cc.dvitski.pandorica.world.loot

import cc.dvitski.pandorica.item.PandoricaItems
import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Items

object PandoricaLootModifications {
    fun initialize() {
        val witherSkeletonTable = EntityType.WITHER_SKELETON.defaultLootTable.orElseThrow()
        LootTableEvents.MODIFY_DROPS.register { entry, _, drops ->
            if (entry.`is`(witherSkeletonTable)) {
                drops.replaceAll { stack ->
                    if (stack.`is`(Items.BONE)) {
                        stack.transmuteCopy(PandoricaItems.WITHERED_BONE)
                    } else {
                        stack
                    }
                }
            }
        }
    }
}
