package de.humansareweak.digt.item.aoe_tool;

import de.humansareweak.digt.item.DIGMultiItemTool;
import de.humansareweak.digt.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

/**
 * Main class for tools that are meant to break multiple blocks at once.
 */
public class DIG_AOETool extends DIGMultiItemTool {
    protected int mineRadius = 1;
    protected int mineDepth = 0;

    /**
     * Creates the Item using these Parameters.
     *
     */
    public DIG_AOETool() {
        super("aoe");
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
        MovingObjectPosition mop = Utils.raytraceFromEntity(player.worldObj, player, false, 4.5);

        if(mop == null) {
            return super.onBlockStartBreak(itemstack, x, y, z, player);
        }

        int sideHit = mop.sideHit;

        int yDist = mineRadius;
        int xDist = mineRadius;
        int zDist = mineRadius;

        //Block destroyed, now for AOE
        switch (sideHit) {
            case 0, 1 ->
                //Facing up/down
                yDist = mineDepth;
            case 2, 3 ->
                //Facing along z axis
                zDist = mineDepth;
            case 4, 5 ->
                //Facing along x axis
                xDist = mineDepth;
        }

        for (int xPos = x - xDist; xPos <= x + xDist; xPos++)
            for (int yPos = y - yDist; yPos <= y + yDist; yPos++)
                for (int zPos = z - zDist; zPos <= z + zDist; zPos++) {
                    // don't break the originally already broken block, duh
                    if (xPos == x && yPos == y && zPos == z)
                        continue;

                    if(!super.onBlockStartBreak(itemstack, xPos, yPos, zPos, player)) {
                        Block targetBlock = player.worldObj.getBlock(x, y, z);
                        Utils.breakExtraBlock(player.worldObj, xPos, yPos, zPos,sideHit,player,x,y,z,canHarvestBlock(targetBlock, itemstack));
                    }
                }

        return super.onBlockStartBreak(itemstack, x, y, z, player);
    }
}
