package tamaized.aov.common.core.skills.defender.tier2;

import tamaized.aov.AoV;
import tamaized.aov.common.core.abilities.AbilityBase;
import tamaized.aov.common.core.skills.AoVSkill;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class DefenderSkillT2S5 extends AoVSkill {

	private static final ResourceLocation icon = new ResourceLocation(AoV.modid + ":textures/skills/DefenderT2S5.png");

	private static final List<AbilityBase> spells = new ArrayList<AbilityBase>();

	static {

	}

	public DefenderSkillT2S5() {
		super(spells,

				TextFormatting.AQUA + "null",

				TextFormatting.RED + "Requires: 4 Points Spent in Tree"

		);
	}

	@Override
	public String getName() {
		return "DefenderSkillT2S5";
	}

	@Override
	protected Buffs setupBuffs() {
		return new Buffs(0, 0, 0, 0, false);
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
		return AoVSkill.defender_core_1;
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
		return 4;
	}

}