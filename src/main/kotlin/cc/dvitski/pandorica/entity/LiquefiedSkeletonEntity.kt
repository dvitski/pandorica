package cc.dvitski.pandorica.entity

import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.monster.AbstractSkeleton
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.PathType

class LiquefiedSkeletonEntity(type: EntityType<LiquefiedSkeletonEntity>, level: Level) : AbstractSkeleton(type, level) {
    init {
        setPathfindingMalus(PathType.LAVA, 8.0f)
    }

    override fun canHoldItem(stack: ItemStack): Boolean {
        return super.canHoldItem(stack)
    }

    override fun populateDefaultEquipmentSlots(source: RandomSource, difficultyInstance: DifficultyInstance) {
        setItemSlot(EquipmentSlot.MAINHAND, ItemStack(Items.COPPER_SWORD))
    }

    override fun populateDefaultEquipmentEnchantments(level: ServerLevelAccessor, source: RandomSource, difficultyInstance: DifficultyInstance) {
    }

    override fun finalizeSpawn(level: ServerLevelAccessor, difficultyInstance: DifficultyInstance, reason: EntitySpawnReason, groupData: SpawnGroupData?): SpawnGroupData? {
        val newGroupData = super.finalizeSpawn(level, difficultyInstance, reason, groupData)
        getAttribute(Attributes.ATTACK_DAMAGE)?.baseValue = 4.0
        reassessWeaponGoal()
        return newGroupData
    }

    override fun doHurtTarget(level: ServerLevel, entity: Entity): Boolean {
        return if (!super.doHurtTarget(level, entity)) {
            false
        } else {
            if (entity is LivingEntity) {
                entity.remainingFireTicks = 20 * 10
            }

            true
        }
    }

    override fun getArrow(stack: ItemStack, damage: Float, weaponStack: ItemStack?): AbstractArrow {
        val entity = super.getArrow(stack, damage, weaponStack)
        entity.igniteForSeconds(100.0f)
        return entity
    }

    override fun getPreferredWeaponType(): TagKey<Item>? {
        return null
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.WITHER_SKELETON_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.WITHER_SKELETON_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.WITHER_SKELETON_DEATH
    }

    override fun getStepSound(): SoundEvent {
        return SoundEvents.WITHER_SKELETON_STEP
    }
}
