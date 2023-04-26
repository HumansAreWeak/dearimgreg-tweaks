package de.humansareweak.digt.item.overrides;

import gregtech.items.tools.early.GT_Tool_Pickaxe;

public class DIG_Tool_Pickaxe extends GT_Tool_Pickaxe {
    public static final DIG_Tool_Pickaxe INSTANCE = new DIG_Tool_Pickaxe();

    private DIG_Tool_Pickaxe() {}

    @Override
    public float getSpeedMultiplier() { return 1.5f; }
}
