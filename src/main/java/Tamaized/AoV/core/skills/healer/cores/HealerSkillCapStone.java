package Tamaized.AoV.core.skills.healer.cores;

import java.util.ArrayList;
import java.util.List;

import Tamaized.AoV.AoV;
import Tamaized.AoV.core.abilities.AbilityBase;
import Tamaized.AoV.core.skills.AoVSkill;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class HealerSkillCapStone extends AoVSkill {

	private static final ResourceLocation icon = new ResourceLocation(AoV.modid + ":textures/skills/test.png");

	private static final List<AbilityBase> spells = new ArrayList<AbilityBase>();

	static {

	}

	public HealerSkillCapStone() {
		super(spells,

				TextFormatting.AQUA + "Capstone: Healer",

				TextFormatting.RED + "Requires: Healer Core 4",

				TextFormatting.RED + "Requires: Level 15",

				"",

				TextFormatting.GREEN + "+50 Spell Power",

				TextFormatting.GREEN + "+3 Charges"

		);
	}

	@Override
	public String getName() {
		return "HealerSkillCapStone";
	}

	@Override
	protected Buffs setupBuffs() {
		return new Buffs(3, 50, false);
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
		return AoVSkill.healer_core_4;
	}

	@Override
	public int getCost() {
		return 1;
	}

	@Override
	public int getLevel() {
		return 15;
	}

	@Override
	public int getSpentPoints() {
		return 0;
	}

}
