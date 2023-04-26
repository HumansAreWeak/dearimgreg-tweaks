package de.humansareweak.digt.item.aoe_tool;

import de.humansareweak.digt.item.Items;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.render.IIconContainer;
import gregtech.items.tools.early.GT_Tool_Pickaxe;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;

public class DIG_Tool_MiningHammer extends GT_Tool_Pickaxe {
    public static final DIG_Tool_MiningHammer INSTANCE = new DIG_Tool_MiningHammer();

    @Override public int getToolDamagePerBlockBreak()   { return  25; }
    @Override public int getToolDamagePerEntityAttack() { return 150; }
    @Override public float getBaseDamage()              { return 4.5F; }
    @Override public float getMaxDurabilityMultiplier() { return 2.5f; }

    private DIG_Tool_MiningHammer() {}

    @Override
    public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(Items.toolHeadMiningHammer.mIconIndexItem) : MultiItemTool.getSecondaryMaterial(aStack, MT.WOODS.Spruce).mTextureSetsItems.get(OP.stick.mIconIndexItem);
    }

    @Override
    public float getMiningSpeed(Block aBlock, byte aMetaData) {
        return super.getMiningSpeed(aBlock, aMetaData);
    }

    @Override
    public float getMiningSpeed(Block aBlock, byte aMetaData, float aDefault, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
        return super.getMiningSpeed(aBlock, aMetaData, aDefault, aPlayer, aWorld, aX, aY, aZ);
    }

    @Override
    public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer) {
        super.onToolCrafted(aStack, aPlayer);
        aPlayer.triggerAchievement(AchievementList.buildPickaxe);
        aPlayer.triggerAchievement(AchievementList.buildBetterPickaxe);
    }

    @Override
    public String getDeathMessage() {
        return "[VICTIM] got boinked by [KILLER]";
    }
}
