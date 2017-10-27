package tamaized.aov.common.core.abilities.caster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import tamaized.aov.AoV;
import tamaized.aov.common.capabilities.CapabilityList;
import tamaized.aov.common.capabilities.aov.IAoVCapability;
import tamaized.aov.common.core.abilities.Ability;
import tamaized.aov.common.core.abilities.AbilityBase;
import tamaized.aov.registry.AoVDamageSource;
import tamaized.aov.registry.SoundEvents;

public class Destruction extends AbilityBase {

	private static final ResourceLocation icon = new ResourceLocation(AoV.modid, "textures/spells/destruction.png");

	private static final int charges = 2;
	private static final int distance = 20;

	public Destruction() {
		super(

				new TextComponentTranslation(getStaticName()),

				new TextComponentTranslation(""),

				new TextComponentTranslation("aov.spells.global.charges", charges),

				new TextComponentTranslation("aov.spells.global.range", distance),

				new TextComponentTranslation(""),

				new TextComponentTranslation("aov.spells.destruction.desc")

		);
	}

	public static String getStaticName() {
		return "aov.spells.destruction.name";
	}

	@Override
	public ResourceLocation getIcon() {
		return icon;
	}

	@Override
	public String getName() {
		return getStaticName();
	}

	@Override
	public int getCoolDown() {
		return 15;
	}

	@Override
	public int getMaxCharges() {
		return charges;
	}

	@Override
	public int getChargeCost() {
		return 1;
	}

	@Override
	public double getMaxDistance() {
		return distance;
	}

	@Override
	public boolean usesInvoke() {
		return false;
	}

	@Override
	public void cast(Ability ability, EntityPlayer caster, EntityLivingBase target) {
		if (target == null)
			return;
		IAoVCapability cap = caster.getCapability(CapabilityList.AOV, null);
		if (cap != null && target.isNonBoss()) {
			target.attackEntityFrom(AoVDamageSource.destruction, Integer.MAX_VALUE);
			target.world.playSound(null, target.posX, target.posY, target.posZ, SoundEvents.destruction, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			cap.addExp(caster, 20, AbilityBase.destruction);
		}
	}

}
