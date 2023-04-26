package de.humansareweak.digt.item;

import de.humansareweak.digt.Tags;
import gregapi.item.multiitem.MultiItemTool;

public class DIGMultiItemTool extends MultiItemTool {
    /**
     * Creates the Item using these Parameters.
     */
    public DIGMultiItemTool(String internalName) {
        super(Tags.MODID, "digt." + internalName);
    }
}
