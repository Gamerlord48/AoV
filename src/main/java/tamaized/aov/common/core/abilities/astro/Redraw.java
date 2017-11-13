package tamaized.aov.common.core.abilities.astro;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.aov.AoV;
import tamaized.aov.common.capabilities.CapabilityList;
import tamaized.aov.common.capabilities.aov.IAoVCapability;
import tamaized.aov.common.capabilities.astro.IAstroCapability;
import tamaized.aov.common.core.abilities.Abilities;
import tamaized.aov.common.core.abilities.Ability;
import tamaized.aov.common.core.abilities.AbilityBase;

public class Redraw extends AbilityBase {

	private static final ResourceLocation icon = new ResourceLocation(AoV.modid, "textures/spells/redraw.png");

	private static final int charges = -1;

	public Redraw() {
		super(

				new TextComponentTranslation(getStaticName()),

				new TextComponentTranslation(""),

				new TextComponentTranslation("aov.spells.redraw.desc")

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
	@SideOnly(Side.CLIENT)
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
	public boolean cast(Ability ability, EntityPlayer caster, EntityLivingBase target) {
		if (!caster.hasCapability(CapabilityList.ASTRO, null) || !caster.hasCapability(CapabilityList.AOV, null))
			return false;
		IAstroCapability astro = caster.getCapability(CapabilityList.ASTRO, null);
		IAoVCapability aov = caster.getCapability(CapabilityList.AOV, null);
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
