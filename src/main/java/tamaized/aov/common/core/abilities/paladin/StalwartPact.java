package tamaized.aov.common.core.abilities.paladin;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tamaized.aov.AoV;
import tamaized.aov.client.ParticleHelper;
import tamaized.aov.common.capabilities.CapabilityList;
import tamaized.aov.common.capabilities.aov.IAoVCapability;
import tamaized.aov.common.core.abilities.Ability;
import tamaized.aov.common.core.abilities.AbilityBase;
import tamaized.aov.registry.AoVPotions;
import tamaized.aov.registry.SoundEvents;

import java.util.List;
import java.util.Objects;

public class StalwartPact extends AbilityBase {

	private final static String name = "aov.spells.stalwart.name";
	private final static int charges = 3;
	private final static double range = 3;

	public StalwartPact() {
		super(

				new TranslationTextComponent(name),

				new TranslationTextComponent(""),

				new TranslationTextComponent("aov.spells.global.charges", charges),

				new TranslationTextComponent("aov.spells.global.range", range),

				new TranslationTextComponent(""),

				new TranslationTextComponent("aov.spells.stalwart.desc")

		);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return I18n.format(name);
	}

	@Override
	public double getMaxDistance() {
		return range;
	}

	@Override
	public int getMaxCharges() {
		return charges;
	}

	@Override
	public boolean usesInvoke() {
		return true;
	}

	@Override
	public boolean isCastOnTarget(PlayerEntity caster, IAoVCapability cap, LivingEntity target) {
		return IAoVCapability.canBenefit(caster, cap, target);
	}

	protected int getParticleColor() {
		return 0xFFFFFFFF;
	}

	@Override
	public boolean cast(Ability ability, PlayerEntity caster, LivingEntity e) {
		IAoVCapability cap = CapabilityList.getCap(caster, CapabilityList.AOV);
		if (cap == null)
			return false;
		if (cap.getInvokeMass())
			castAsMass(caster, cap);
		else if (e == null) {
			addPotionEffects(caster);
		} else {
			if (IAoVCapability.canBenefit(caster, cap, e))
				addPotionEffects(e);
		}
		SoundEvents.playMovingSoundOnServer(SoundEvents.cast_2, caster);
		cap.addExp(caster, 20, this);
		return true;
	}

	private void addPotionEffects(LivingEntity entity) {
		entity.addPotionEffect(new EffectInstance(Objects.requireNonNull(AoVPotions.stalwartPact.get()), 20 * (60 * 5)));
	}

	private void castAsMass(LivingEntity caster, IAoVCapability cap) {
		int range = (int) (getMaxDistance() * 2);
		ParticleHelper.spawnParticleMesh(ParticleHelper.MeshType.BURST, ParticleHelper.ParticleType.Fluff, caster.world, caster.getPositionVector(), range, getParticleColor());
		List<LivingEntity> list = caster.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(caster.getPosition().add(-range, -range, -range), caster.getPosition().add(range, range, range)));
		for (LivingEntity entity : list) {
			if (IAoVCapability.canBenefit(caster, cap, entity)) {
				addPotionEffects(entity);
				cap.addExp(caster, 20, this);
			}
		}
	}

	@Override
	public int getChargeCost() {
		return 1;
	}

	@Override
	public int getCoolDown() {
		return 12;
	}

	@Override
	public ResourceLocation getIcon() {
		return new ResourceLocation(AoV.MODID, "textures/spells/stalwart.png");
	}
}
