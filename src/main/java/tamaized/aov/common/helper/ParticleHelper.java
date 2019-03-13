package tamaized.aov.common.helper;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;
import tamaized.aov.AoV;
import tamaized.aov.network.client.ClientPacketHandlerParticleMesh;
import tamaized.aov.proxy.CommonProxy;

public class ParticleHelper {

	public static void spawnVanillaParticleOnServer(World world, BasicParticleType particle, double x, double y, double z, double xS, double yS, double zS) {
		world.spawnParticle(particle, x, y, z, xS, yS, zS);
	}

	public static void spawnParticleMesh(MeshType type, CommonProxy.ParticleType particle, World world, Vec3d target, int range, int color) {
		if (!world.isRemote) {
			AoV.network.send(PacketDistributor.TRACKING_CHUNK.with(() -> world.getChunk(new BlockPos(target.x, target.y, target.z))), new ClientPacketHandlerParticleMesh(type, particle, target, range, color));
		} else {
			switch (type) {
				case BURST:
					burstParticles(particle, world, target, range, color);
					break;
				default:
					break;
			}
		}
	}

	private static void burstParticles(CommonProxy.ParticleType particle, World world, Vec3d pos, int range, int color) {
		int amount = range * 4;
		for (int i = -amount; i <= amount; i++) {
			double dx = (world.rand.nextFloat() * 0.2F) - 0.1F;
			double dz = (world.rand.nextFloat() * 0.2F) - 0.1F;
			AoV.proxy.spawnParticle(particle, world, pos, new Vec3d(dx, 0, dz), 20 * (world.rand.nextInt(6)), -0.01F, (world.rand.nextFloat() * 0.9F) - 0.25F, color);
		}
	}

	public enum MeshType {
		BURST
	}

}
