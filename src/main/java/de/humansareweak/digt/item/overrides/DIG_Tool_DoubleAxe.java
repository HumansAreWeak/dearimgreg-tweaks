package de.humansareweak.digt.item.overrides;

import gregtech.items.tools.early.GT_Tool_AxeDouble;

public class DIG_Tool_DoubleAxe extends GT_Tool_AxeDouble {
    public static final DIG_Tool_DoubleAxe INSTANCE = new DIG_Tool_DoubleAxe();

    private DIG_Tool_DoubleAxe() {}

    @Override
    public float getSpeedMultiplier() {
        return 2.5F;
    }
}
