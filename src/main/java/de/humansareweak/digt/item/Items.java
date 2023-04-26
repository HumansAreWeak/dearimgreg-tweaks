package de.humansareweak.digt.item;

import gregapi.code.ICondition;
import gregapi.data.CS;
import gregapi.oredict.OreDictPrefix;
import static gregapi.data.TD.Prefix.*;
import static gregapi.oredict.OreDictMaterialCondition.typemin;

public class Items {
    private static OreDictPrefix createPrefix(String prefix, String category, String postMaterial) {
        return OreDictPrefix
            .createPrefix(prefix)
            .setCategoryName(category)
            .setLocalPrefixName(category)
            .setLocalItemName("", postMaterial)
            .setCondition(ICondition.TRUE);
    }

    // UNIFICATABLE, BURNABLE, TOOLTIP_ENCHANTS, RECYCLABLE, SCANNABLE, TOOL_HEAD, NEEDS_HANDLE
    public static final OreDictPrefix
        toolHeadMiningHammer = createPrefix("toolHeadMiningHammer", "Mining Hammer Heads", " Mining Hammer Head")
            .setMaterialStats(CS.U * 6 - CS.U9)
            .setCondition(typemin(2))
            .add(UNIFICATABLE, BURNABLE, TOOLTIP_ENCHANTS, RECYCLABLE, SCANNABLE, TOOL_HEAD, NEEDS_HANDLE)
            .setStacksize(16),
        toolHeadExcavator = createPrefix("toolHeadExcavator", "Excavator Heads", " Excavator Head")
            .setMaterialStats(CS.U * 3 - CS.U9)
            .setCondition(typemin(2))
            .add(UNIFICATABLE, BURNABLE, TOOLTIP_ENCHANTS, RECYCLABLE, SCANNABLE, TOOL_HEAD, NEEDS_HANDLE)
            .setStacksize(16);
}
