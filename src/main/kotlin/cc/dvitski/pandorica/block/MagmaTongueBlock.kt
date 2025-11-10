package cc.dvitski.pandorica.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.ScheduledTickAccess
import net.minecraft.world.level.block.AmethystClusterBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.VegetationBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import kotlin.math.min

class MagmaTongueBlock(properties: Properties) : VegetationBlock(properties), SimpleWaterloggedBlock {
    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(TONGUES, 1)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val state = context.level.getBlockState(context.clickedPos)
        return if (state.`is`(this)) {
            val tongues = min(TONGUES.max, state.getValue(TONGUES) + 1)
            state.setValue(TONGUES, tongues)
        } else {
            val fluidState = context.level.getFluidState(context.clickedPos)
            val waterlogged = fluidState.type == Fluids.WATER
            super.getStateForPlacement(context)
                ?.setValue(WATERLOGGED, waterlogged)
        }
    }

    override fun mayPlaceOn(state: BlockState, level: BlockGetter, pos: BlockPos): Boolean {
        return !state.getCollisionShape(level, pos).getFaceShape(Direction.UP).isEmpty
                || state.isFaceSturdy(level, pos, Direction.UP)
    }

    override fun canBeReplaced(state: BlockState, context: BlockPlaceContext): Boolean {
        return context.itemInHand.item == asItem() && state.getValue(TONGUES) < TONGUES.max
    }

    override fun updateShape(state: BlockState, level: LevelReader, tickAccess: ScheduledTickAccess, pos: BlockPos, direction: Direction, otherPos: BlockPos, otherState: BlockState, random: RandomSource): BlockState {
        if (state.getValue(AmethystClusterBlock.WATERLOGGED)) {
            tickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        }

        return super.updateShape(state, level, tickAccess, pos, direction, otherPos, otherState, random)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) {
            Fluids.WATER.getSource(false)
        } else {
            super.getFluidState(state)
        }
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(TONGUES, WATERLOGGED)
    }

    override fun codec(): MapCodec<MagmaTongueBlock> {
        return CODEC
    }

    companion object {
        val CODEC: MapCodec<MagmaTongueBlock> = simpleCodec(::MagmaTongueBlock)

        val TONGUES: IntegerProperty = IntegerProperty.create("tongues", 1, 5)
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        fun getLight(state: BlockState): Int {
            return if (state.getValue(WATERLOGGED)) {
                0
            } else {
                3 + state.getValue(TONGUES) * 2
            }
        }
    }
}
