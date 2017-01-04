package Tamaized.AoV.core.skills.healer.tier1;

import java.util.ArrayList;
import java.util.List;

import Tamaized.AoV.AoV;
import Tamaized.AoV.core.abilities.AbilityBase;
import Tamaized.AoV.core.skills.AoVSkill;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class HealerSkillT1S3 extends AoVSkill {

	private static final ResourceLocation icon = new ResourceLocation(AoV.modid + ":textures/skills/HealerT1S3.png");

	private static final List<AbilityBase> spells = new ArrayList<AbilityBase>();

	static {

	}

	public HealerSkillT1S3() {
		super(spells,

				TextFormatting.AQUA + "Spell Power I",

				TextFormatting.RED + "Requires: 1 Point Spent in Tree",

				"",

				TextFormatting.GREEN + "+10 Spell Power"

		);
	}

	@Override
	public String getName() {
		return "HealerSkillT1S3";
	}

	@Override
	protected Buffs setupBuffs() {
		return new Buffs(0, 10, false);
	}

	@Override
	public ResourceLocation getIcon() {
		return icon;
	}

	@Override
	public boolean isClassCore() {
		return false;
	}

	@Override
	public AoVSkill getParent() {
		return AoVSkill.healer_core_1;
	}

	@Override
	public int getCost() {
		return 1;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public int getSpentPoints() {
		return 1;
	}

}
