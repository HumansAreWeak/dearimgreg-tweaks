package de.humansareweak.digt.item;

import gregapi.code.ICondition;
import gregapi.data.CS;
import gregapi.data.TD;
import gregapi.oredict.OreDictPrefix;

public class Items {
    private static OreDictPrefix createPrefix(String prefix, String category, String preMaterial, String postMaterial) {
        return OreDictPrefix
            .createPrefix(prefix)
            .setCategoryName(category)
            .setLocalPrefixName(category)
            .setLocalItemName(preMaterial, postMaterial)
            .setCondition(ICondition.TRUE);
    }

    public static final OreDictPrefix
        toolHeadMiningHammer = createPrefix("toolHeadMiningHammer", "Mining Hammer Heads", "", " Mining Hammer Head")
            .setMaterialStats(CS.U * 6 - CS.U9)
            .add(TD.Prefix.UNIFICATABLE, TD.Prefix.BURNABLE, TD.Prefix.TOOLTIP_ENCHANTS, TD.Prefix.RECYCLABLE, TD.Prefix.SCANNABLE, TD.Prefix.TOOL_HEAD, TD.Prefix.NEEDS_HANDLE)
            .setStacksize(16),
        toolHeadExcavator = createPrefix("toolHeadExcavator", "Excavator Heads", "", " Excavator Head")
            .setMaterialStats(CS.U * 3 - CS.U9)
            .add(TD.Prefix.UNIFICATABLE, TD.Prefix.BURNABLE, TD.Prefix.TOOLTIP_ENCHANTS, TD.Prefix.RECYCLABLE, TD.Prefix.SCANNABLE, TD.Prefix.TOOL_HEAD, TD.Prefix.NEEDS_HANDLE)
            .setStacksize(16);
}
