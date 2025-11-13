package cc.dvitski.pandorica.particle

import cc.dvitski.pandorica.Pandorica
import com.mojang.serialization.MapCodec
import net.minecraft.core.Registry
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation

object PandoricaParticleTypes {
    val SIZZLE = register("sizzle")

    private fun register(id: String, important: Boolean = false): SimpleParticleType {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id), SimpleParticleType(important))
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : ParticleOptions> register(id: String, important: Boolean, codec: (ParticleType<T>) -> MapCodec<T>, streamCodec: (ParticleType<T>) -> StreamCodec<in RegistryFriendlyByteBuf, T>): ParticleType<T> {
        val type = object : ParticleType<T>(important) {
            override fun codec(): MapCodec<T> {
                return codec as MapCodec<T>
            }

            override fun streamCodec(): StreamCodec<in RegistryFriendlyByteBuf, T> {
                return streamCodec as StreamCodec<in RegistryFriendlyByteBuf, T>
            }

        }

        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id), type)
    }
}
