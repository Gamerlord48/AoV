package Tamaized.AoV.core.skills.healer.tier1;

import net.minecraft.util.ResourceLocation;
import Tamaized.AoV.AoV;
import Tamaized.AoV.core.abilities.AbilityBase;
import Tamaized.AoV.core.abilities.healer.Healing.CureModWounds;
import Tamaized.AoV.core.skills.AoVSkill;
import Tamaized.AoV.core.skills.healer.cores.HealerSkillCore1;

import com.mojang.realmsclient.gui.ChatFormatting;

public class HealerSkillT1S1 extends AoVSkill{
	
	private static final ResourceLocation icon = new ResourceLocation(AoV.modid+":textures/skills/HealerT1S1.png");

	public HealerSkillT1S1() {
		super(getUnlocalizedName(), AoVSkill.getSkillFromName(HealerSkillCore1.getUnlocalizedName()), 1, 0, 1, false,
				new AbilityBase[]{
					AbilityBase.fromName(CureModWounds.getStaticName())
				},
				ChatFormatting.AQUA+"Cure Moderate Wounds",
				ChatFormatting.RED+"Requires: 1 Point Spent in Tree",
				"",
				ChatFormatting.YELLOW+"Added Spell: Cure Moderate Wounds"
				);
	}

	@Override
	protected void setupBuffs() {
		buffs = new Buffs(0, 0, 0, 0, false);
	}

	@Override
	public ResourceLocation getIcon() {
		return icon;
	}
	
	public static String getUnlocalizedName(){
		return "HealerSkillT1S1";
	}

}