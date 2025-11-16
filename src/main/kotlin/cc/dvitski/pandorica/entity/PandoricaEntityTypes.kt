package cc.dvitski.pandorica.entity

import cc.dvitski.pandorica.Pandorica
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.SpawnPlacementTypes
import net.minecraft.world.entity.SpawnPlacements
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.monster.AbstractSkeleton
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.level.levelgen.Heightmap

object PandoricaEntityTypes {
    val LIQUFIED_SKELETON = registerLiving("liquefied_skeleton",
        EntityType.Builder.of(::LiquefiedSkeletonEntity, MobCategory.MONSTER)
            .fireImmune()
            .sized(0.7f, 2.4f)
            .eyeHeight(2.1f)
            .ridingOffset(-0.875f)
            .clientTrackingRange(8)
            .notInPeaceful(),
        AbstractSkeleton.createAttributes(),
    )

    init {
        SpawnPlacements.register(LIQUFIED_SKELETON, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules)
    }

    private fun <T : Entity> register(id: String, builder: EntityType.Builder<T>): EntityType<T> {
        val location = ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id)
        val key = ResourceKey.create(Registries.ENTITY_TYPE, location)
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key))
    }

    private fun <T : LivingEntity> registerLiving(id: String, builder: EntityType.Builder<T>, attributes: AttributeSupplier.Builder): EntityType<T> {
        val type = register(id, builder)
        FabricDefaultAttributeRegistry.register(type, attributes)
        return type
    }
}
