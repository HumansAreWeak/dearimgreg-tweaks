package de.humansareweak.digt.item.overrides;

import gregtech.items.tools.early.GT_Tool_Axe;

public class DIG_Tool_Axe extends GT_Tool_Axe {
    public static final DIG_Tool_Axe INSTANCE = new DIG_Tool_Axe();

    private DIG_Tool_Axe() {}

    @Override
    public float getSpeedMultiplier() { return 1.5f; }

}
