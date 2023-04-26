package de.humansareweak.digt.item.aoe_tool;

import de.humansareweak.digt.item.Items;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.render.IIconContainer;
import gregtech.items.tools.early.GT_Tool_Shovel;
import net.minecraft.item.ItemStack;

public class DIG_Tool_Excavator extends GT_Tool_Shovel {
    public static final DIG_Tool_Excavator INSTANCE = new DIG_Tool_Excavator();

    private DIG_Tool_Excavator() {}

    @Override public int getToolDamagePerBlockBreak()   { return  25; }

    @Override public int getToolDamagePerEntityAttack() { return 150; }

    @Override public float getBaseDamage()              { return 2.0F; }

    @Override public float getMaxDurabilityMultiplier() { return 2.5f; }

    @Override
    public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(Items.toolHeadExcavator.mIconIndexItem) : MultiItemTool.getSecondaryMaterial(aStack, MT.WOODS.Spruce).mTextureSetsItems.get(OP.stick.mIconIndexItem);
    }

    @Override
    public String getDeathMessage() {
        return "[VICTIM] has been [REDACTED] by [KILLER]";
    }
}
