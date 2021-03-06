package tamaized.aov.common.core.abilities.astro;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tamaized.aov.AoV;
import tamaized.aov.common.capabilities.CapabilityList;
import tamaized.aov.common.capabilities.aov.IAoVCapability;
import tamaized.aov.common.capabilities.astro.IAstroCapability;
import tamaized.aov.common.core.abilities.Abilities;
import tamaized.aov.common.core.abilities.Ability;
import tamaized.aov.common.core.abilities.AbilityBase;

public class Redraw extends AbilityBase {

	private static final ResourceLocation icon = new ResourceLocation(AoV.MODID, "textures/spells/redraw.png");

	private static final int charges = -1;

	public Redraw() {
		super(

				new TranslationTextComponent(getStaticName()),

				new TranslationTextComponent(""),

				new TranslationTextComponent("aov.spells.redraw.desc")

		);
	}

	public static String getStaticName() {
		return "aov.spells.redraw.name";
	}


	@Override
	public ResourceLocation getIcon() {
		return icon;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return I18n.format(getStaticName());
	}

	@Override
	public int getCoolDown() {
		return 30;
	}

	@Override
	public int getMaxCharges() {
		return charges;
	}

	@Override
	public int getChargeCost() {
		return 0;
	}

	@Override
	public double getMaxDistance() {
		return 0;
	}

	@Override
	public boolean usesInvoke() {
		return false;
	}

	@Override
	public boolean isCastOnTarget(PlayerEntity caster, IAoVCapability cap, LivingEntity target) {
		return false;
	}

	@Override
	public boolean cast(Ability ability, PlayerEntity caster, LivingEntity target) {
		IAstroCapability astro = CapabilityList.getCap(caster, CapabilityList.ASTRO);
		IAoVCapability aov = CapabilityList.getCap(caster, CapabilityList.AOV);
		if (astro == null || aov == null)
			return false;
		if (astro.getDraw() != null) {
			astro.redrawCard(caster);
			for (Ability a : aov.getSlots())
				if (a != null && a.getAbility() == Abilities.draw)
					a.setTimer(30);
			aov.addExp(caster, 8, ability.getAbility());
		} else
			ability.setNextCooldown(1);
		astro.sendPacketUpdates(caster);
		return true;
	}

}
