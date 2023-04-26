package de.humansareweak.digt.item;

import cpw.mods.fml.common.registry.GameRegistry;
import de.humansareweak.digt.Tags;
import de.humansareweak.digt.item.aoe_tool.DIG_AOETool;
import de.humansareweak.digt.item.overrides.DIG_Tool_Axe;
import de.humansareweak.digt.item.overrides.DIG_Tool_DoubleAxe;
import de.humansareweak.digt.item.aoe_tool.DIG_Tool_MiningHammer;
import de.humansareweak.digt.item.aoe_tool.DIG_Tool_Excavator;
import de.humansareweak.digt.item.overrides.DIG_Tool_Pickaxe;
import gregapi.code.ICondition;
import gregapi.code.IItemContainer;
import gregapi.data.*;
import gregapi.item.IItemEnergy;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.IToolStats;
import gregapi.item.prefixitem.PrefixItem;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.recipes.AdvancedCraftingTool;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static de.humansareweak.digt.item.DIG_Tools.*;
import static de.humansareweak.digt.item.Items.toolHeadExcavator;
import static de.humansareweak.digt.item.Items.toolHeadMiningHammer;
import static gregapi.data.CS.*;
import static gregapi.data.OP.*;
import static gregapi.data.TD.Atomic.ANTIMATTER;

public class Registry {

    public static final MultiItemTool DIGT_AOE_Tools = new DIG_AOETool();

    private static void registerPrefixItem(String nameInternal, OreDictPrefix itemPrefix) {
        new PrefixItem(Tags.MODID, Tags.MODID, "digt." + nameInternal, itemPrefix);
    }

    public static void registerItems() {
        registerPrefixItem("aoe." + TOOL_miningHammer, toolHeadMiningHammer);
        registerPrefixItem("aoe." + TOOL_excavator, toolHeadExcavator);

        CS.ToolsGT.add(
            DIGT_AOE_Tools.addTool(
                DIG_Tools.MiningHammer,
                "Mining Hammer",
                "Swing that hammer",
                DIG_Tool_MiningHammer.INSTANCE.setMaterialAmount(toolHeadMiningHammer.mAmount),
                miningHammer,
                OreDictToolNames.pickaxe
            ), TOOL_miningHammer
        );

        CS.ToolsGT.add(
            DIGT_AOE_Tools.addTool(
                Excavator,
                "Excavator",
                "Terraform the world!",
                DIG_Tool_Excavator.INSTANCE.setMaterialAmount(Items.toolHeadExcavator.mAmount),
                excavator,
                OreDictToolNames.shovel
            ), TOOL_excavator
        );
    }

    public static void registerRecipes() {
        for (Object[] tHandle : new Object[][] {{ANY.Wood, OD.stickAnyWood}, {MT.PetrifiedWood, stick.dat(MT.PetrifiedWood)}, {MT.Bamboo, OD.bamboo}, {MT.Bone, net.minecraft.init.Items.bone}, {MT.Plastic, stick.dat(MT.Plastic)}}) {
            CR.shaped(DIGT_AOE_Tools.getToolWithStats(DIG_Tools.MiningHammer, MT.Obsidian, (OreDictMaterial) tHandle[0]), CR.DEF, "XX ", "XXS", "XX ", 'X', rockGt.dat(MT.Obsidian), 'S', tHandle[1]);
            CR.shaped(DIGT_AOE_Tools.getToolWithStats(DIG_Tools.MiningHammer, MT.PetrifiedWood, (OreDictMaterial) tHandle[0]), CR.DEF, "XX ", "XXS", "XX ", 'X', rockGt.dat(MT.PetrifiedWood), 'S', tHandle[1]);

            CR.shaped(DIGT_AOE_Tools.getToolWithStats(Excavator, MT.Obsidian, (OreDictMaterial) tHandle[0]), CR.DEF, " X ", "XSX", " S ", 'X', rockGt.dat(MT.Obsidian), 'S', tHandle[1]);
            CR.shaped(DIGT_AOE_Tools.getToolWithStats(Excavator, MT.PetrifiedWood, (OreDictMaterial) tHandle[0]), CR.DEF, " X ", "XSX", " S ", 'X', rockGt.dat(MT.PetrifiedWood), 'S', tHandle[1]);

            // Alternate Double Axe Recipe
            CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.DOUBLE_AXE, MT.Obsidian, (OreDictMaterial) tHandle[0]), CR.DEF, "XSX", "XSX", " S ", 'X', rockGt.dat(MT.Obsidian), 'S', tHandle[1]);
            CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.DOUBLE_AXE, MT.PetrifiedWood, (OreDictMaterial) tHandle[0]), CR.DEF, "XSX", "XSX", " S ", 'X', rockGt.dat(MT.PetrifiedWood), 'S', tHandle[1]);

            for(OreDictMaterial tRock : ANY.Stone.mToThis) {
                CR.shaped(DIGT_AOE_Tools.getToolWithStats(DIG_Tools.MiningHammer, tRock, (OreDictMaterial) tHandle[0]), CR.DEF, "XX ", "XXS", "XX ", 'X', rockGt.dat(tRock), 'S', tHandle[1]);
                CR.shaped(DIGT_AOE_Tools.getToolWithStats(Excavator, tRock, (OreDictMaterial) tHandle[0]), CR.DEF, " X ", "XSX", " S ", 'X', rockGt.dat(tRock), 'S', tHandle[1]);

                // Alternate Double Axe Recipe
                CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.DOUBLE_AXE, tRock, (OreDictMaterial) tHandle[0]), CR.DEF, "XSX", "XSX", " S ", 'X', rockGt.dat(tRock), 'S', tHandle[1]);
            }
        }

        final String category = "";

        toolHeadMiningHammer.addListener(new OreProcessing_Tool(MiningHammer, category + "MiningHammer", T, F, 0, 0, null, new String[][] {{" BH", "PII", "hBf"}, {" CH", "CGG", " Cf"}}, new String[][] {{" B ", "PII", "hBf"}, {" C ", "CGG", " Cf"}}, null, null, null, null, null, new ICondition.And<>(ANTIMATTER.NOT)));
        toolHeadExcavator.addListener(new OreProcessing_Tool(Excavator, category + "Spade", T, F, 0, 0, null, new String[][] {{" PH", "PIP", "hIf"}, {" CH", "CGC", " Gf"}}, new String[][] {{" P ", "PIP", "hIf"}, {" C ", "CGC", " Gf"}}, null, null, null, null, null, new ICondition.And<>(ANTIMATTER.NOT)));

        GameRegistry.addRecipe(new AdvancedCraftingTool(DIGT_AOE_Tools, MiningHammer, toolHeadMiningHammer, MT.Bronze));
        GameRegistry.addRecipe(new AdvancedCraftingTool(DIGT_AOE_Tools, Excavator, toolHeadExcavator, MT.Bronze));

        replaceGtMetaToolStats(ToolsGT.DOUBLE_AXE, DIG_Tool_DoubleAxe.INSTANCE);
        replaceGtMetaToolStats(ToolsGT.AXE, DIG_Tool_Axe.INSTANCE);
        replaceGtMetaToolStats(ToolsGT.PICKAXE, DIG_Tool_Pickaxe.INSTANCE);
    }

    private static void replaceGtMetaToolStats(short aItemId, IToolStats stats) {
        ToolsGT.sMetaTool.mToolStats.put(aItemId, stats);
        ToolsGT.sMetaTool.mToolStats.put((short)(aItemId+1), stats);
    }

    public static class OreProcessing_Tool implements IOreDictListenerEvent {
        private final ICondition<OreDictMaterial> mCondition;
        private final String[][] mToolRecipes, mToolHeadRecipes;
        private final String mCategoryName;
        private final Object mSpecialObjectV, mSpecialObjectW, mSpecialObjectX, mSpecialObjectY, mSpecialObjectZ;
        private final int mToolID;
        private final long mCapacity, mVoltage;
        private final boolean mUseNormalHandle, mDismantleable;
        private final OreDictMaterial mHandleOverride;

        /**
         * I = ingot
         * P = plate
         * G = gem
         * B = plateCurved
         * C = plateGem
         * T = screw
         * O = ring
         * R = stone
         * S = stick
         * H = stick, made of Handle Material
         * A = the tool head
         * XYZVW
         */
        public OreProcessing_Tool(int aToolID, String aCategoryName, boolean aUseNormalHandle, boolean aDismantleable, long aCapacity, long aVoltage, OreDictMaterial aHandleOverride, String[][] aToolRecipes, String[][] aToolHeadRecipes, Object aSpecialObjectX, Object aSpecialObjectY, Object aSpecialobjectZ, Object aSpecialObjectV, Object aSpecialObjectW, ICondition<OreDictMaterial> aCondition) {
            mSpecialObjectV = aSpecialObjectV;
            mSpecialObjectW = aSpecialObjectW;
            mSpecialObjectX = aSpecialObjectX;
            mSpecialObjectY = aSpecialObjectY;
            mSpecialObjectZ = aSpecialobjectZ;
            mToolHeadRecipes = aToolHeadRecipes;
            mToolRecipes = aToolRecipes;
            mCondition = aCondition;
            mToolID = aToolID;
            mCategoryName = aCategoryName;
            mHandleOverride = aHandleOverride;
            mUseNormalHandle = aUseNormalHandle;
            mDismantleable = aDismantleable;
            mCapacity = aCapacity;
            mVoltage = aVoltage;
        }

        @Override
        public void onOreRegistration(OreDictRegistrationContainer aEvent) {
            if (aEvent.mNotAlreadyRegisteredName && aEvent.mMaterial.mToolTypes > 0 && mCondition.isTrue(aEvent.mMaterial)) {
                long tCapacity = mCapacity;
                if (tCapacity < 0) {
                    tCapacity = 0;
                    if (mSpecialObjectV instanceof IItemContainer) {
                        ItemStack tBattery = ((IItemContainer)mSpecialObjectV).get(1);
                        if (tBattery != null && tBattery.getItem() instanceof IItemEnergy) {
                            tCapacity += ((IItemEnergy)tBattery.getItem()).getEnergyCapacity(TD.Energy.EU, tBattery);
                        }
                    } else if (mSpecialObjectV instanceof ItemStack) {
                        if (((ItemStack)mSpecialObjectV).getItem() instanceof IItemEnergy) {
                            tCapacity += ((IItemEnergy)(((ItemStack)mSpecialObjectV).getItem())).getEnergyCapacity(TD.Energy.EU, (ItemStack)mSpecialObjectV);
                        }
                    }
                    if (mSpecialObjectW instanceof IItemContainer) {
                        ItemStack tBattery = ((IItemContainer)mSpecialObjectW).get(1);
                        if (tBattery != null && tBattery.getItem() instanceof IItemEnergy) {
                            tCapacity += ((IItemEnergy)tBattery.getItem()).getEnergyCapacity(TD.Energy.EU, tBattery);
                        }
                    } else if (mSpecialObjectW instanceof ItemStack) {
                        if (((ItemStack)mSpecialObjectW).getItem() instanceof IItemEnergy) {
                            tCapacity += ((IItemEnergy)(((ItemStack)mSpecialObjectW).getItem())).getEnergyCapacity(TD.Energy.EU, (ItemStack)mSpecialObjectW);
                        }
                    }
                }
                ItemStack tTool = DIGT_AOE_Tools.getToolWithStats(mToolID, 1, aEvent.mMaterial, mHandleOverride==null?mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial:mHandleOverride, tCapacity, mVoltage), tStack = aEvent.mPrefix.mat(aEvent.mMaterial, 1);
                if (tTool != null) {
                    if (mToolRecipes != null && mToolRecipes.length > 0) {
                        for (int i = 0; i < mToolRecipes.length; i++) if (mToolRecipes[i] != null && mToolRecipes[i].length > 0 && (mCategoryName == null || CS.ConfigsGT.RECIPES.get(mCategoryName + ".toolrecipes."+i, aEvent.mMaterial.mNameInternal, T))) {
                            if (mToolRecipes[i].length == 1)       CR.shaped(tTool, CR.DEF_NCC | (mDismantleable?CR.DISMANTLE:0), new Object[] {mToolRecipes[i][0]
                                , 'G', gem.dat(aEvent.mMaterial)
                                , 'I', aEvent.mMaterial==MT.Wood?OD.plankWood:ingot.dat(aEvent.mMaterial)
                                , 'P', aEvent.mMaterial==MT.Wood?OD.plankWood:plate.dat(aEvent.mMaterial)
                                , 'B', plateCurved.dat(aEvent.mMaterial)
                                , 'C', plateGem.dat(aEvent.mMaterial)
                                , 'S', stick.dat(aEvent.mMaterial)
                                , 'R', aEvent.mMaterial==MT.Stone? ST.make(Blocks.stone, 1, W):stone.dat(aEvent.mMaterial)
                                , 'T', screw.dat(aEvent.mMaterial)
                                , 'O', ring.dat(aEvent.mMaterial)
                                , 'N', nugget.dat(aEvent.mMaterial)
                                , 'H', stick.dat(mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial)
                                , 'A', aEvent.mPrefix.dat(aEvent.mMaterial)
                                , 'V', mSpecialObjectV instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectV).dat(aEvent.mMaterial) : mSpecialObjectV==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectV
                                , 'W', mSpecialObjectW instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectW).dat(aEvent.mMaterial) : mSpecialObjectW==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectW
                                , 'X', mSpecialObjectX instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectX).dat(aEvent.mMaterial) : mSpecialObjectX==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectX
                                , 'Y', mSpecialObjectY instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectY).dat(aEvent.mMaterial) : mSpecialObjectY==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectY
                                , 'Z', mSpecialObjectZ instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectZ).dat(aEvent.mMaterial) : mSpecialObjectZ==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectZ
                            });
                            else if (mToolRecipes[i].length == 2)       CR.shaped(tTool, CR.DEF_NCC | (mDismantleable?CR.DISMANTLE:0), new Object[] {mToolRecipes[i][0], mToolRecipes[i][1]
                                , 'G', gem.dat(aEvent.mMaterial)
                                , 'I', aEvent.mMaterial==MT.Wood?OD.plankWood:ingot.dat(aEvent.mMaterial)
                                , 'P', aEvent.mMaterial==MT.Wood?OD.plankWood:plate.dat(aEvent.mMaterial)
                                , 'B', plateCurved.dat(aEvent.mMaterial)
                                , 'C', plateGem.dat(aEvent.mMaterial)
                                , 'S', stick.dat(aEvent.mMaterial)
                                , 'R', aEvent.mMaterial==MT.Stone?ST.make(Blocks.stone, 1, W):stone.dat(aEvent.mMaterial)
                                , 'T', screw.dat(aEvent.mMaterial)
                                , 'O', ring.dat(aEvent.mMaterial)
                                , 'N', nugget.dat(aEvent.mMaterial)
                                , 'H', stick.dat(mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial)
                                , 'A', aEvent.mPrefix.dat(aEvent.mMaterial)
                                , 'V', mSpecialObjectV instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectV).dat(aEvent.mMaterial) : mSpecialObjectV==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectV
                                , 'W', mSpecialObjectW instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectW).dat(aEvent.mMaterial) : mSpecialObjectW==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectW
                                , 'X', mSpecialObjectX instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectX).dat(aEvent.mMaterial) : mSpecialObjectX==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectX
                                , 'Y', mSpecialObjectY instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectY).dat(aEvent.mMaterial) : mSpecialObjectY==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectY
                                , 'Z', mSpecialObjectZ instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectZ).dat(aEvent.mMaterial) : mSpecialObjectZ==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectZ
                            });
                            else                                        CR.shaped(tTool, CR.DEF_NCC | (mDismantleable?CR.DISMANTLE:0), new Object[] {mToolRecipes[i][0], mToolRecipes[i][1], mToolRecipes[i][2]
                                    , 'G', gem.dat(aEvent.mMaterial)
                                    , 'I', aEvent.mMaterial==MT.Wood?OD.plankWood:ingot.dat(aEvent.mMaterial)
                                    , 'P', aEvent.mMaterial==MT.Wood?OD.plankWood:plate.dat(aEvent.mMaterial)
                                    , 'B', plateCurved.dat(aEvent.mMaterial)
                                    , 'C', plateGem.dat(aEvent.mMaterial)
                                    , 'S', stick.dat(aEvent.mMaterial)
                                    , 'R', aEvent.mMaterial==MT.Stone?ST.make(Blocks.stone, 1, W):stone.dat(aEvent.mMaterial)
                                    , 'T', screw.dat(aEvent.mMaterial)
                                    , 'O', ring.dat(aEvent.mMaterial)
                                    , 'N', nugget.dat(aEvent.mMaterial)
                                    , 'H', stick.dat(mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial)
                                    , 'A', aEvent.mPrefix.dat(aEvent.mMaterial)
                                    , 'V', mSpecialObjectV instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectV).dat(aEvent.mMaterial) : mSpecialObjectV==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectV
                                    , 'W', mSpecialObjectW instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectW).dat(aEvent.mMaterial) : mSpecialObjectW==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectW
                                    , 'X', mSpecialObjectX instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectX).dat(aEvent.mMaterial) : mSpecialObjectX==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectX
                                    , 'Y', mSpecialObjectY instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectY).dat(aEvent.mMaterial) : mSpecialObjectY==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectY
                                    , 'Z', mSpecialObjectZ instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectZ).dat(aEvent.mMaterial) : mSpecialObjectZ==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectZ
                                });
                        }
                    }
                    if (mToolHeadRecipes != null && mToolHeadRecipes.length > 0) {
                        for (int i = 0; i < mToolHeadRecipes.length; i++) if (mToolHeadRecipes[i] != null && mToolHeadRecipes[i].length > 0 && (mCategoryName == null || ConfigsGT.RECIPES.get(mCategoryName + ".toolheadrecipe."+i, aEvent.mMaterial.mNameInternal, T))) {
                            if (mToolHeadRecipes[i].length == 1)   CR.shaped(tStack, CR.DEF_NCC | (mDismantleable?CR.DISMANTLE:0), new Object[] {mToolHeadRecipes[i][0]
                                , 'G', gem.dat(aEvent.mMaterial)
                                , 'I', aEvent.mMaterial==MT.Wood?OD.plankWood:ingot.dat(aEvent.mMaterial)
                                , 'P', aEvent.mMaterial==MT.Wood?OD.plankWood:plate.dat(aEvent.mMaterial)
                                , 'B', plateCurved.dat(aEvent.mMaterial)
                                , 'C', plateGem.dat(aEvent.mMaterial)
                                , 'S', stick.dat(aEvent.mMaterial)
                                , 'R', aEvent.mMaterial==MT.Stone?ST.make(Blocks.stone, 1, W):stone.dat(aEvent.mMaterial)
                                , 'T', screw.dat(aEvent.mMaterial)
                                , 'O', ring.dat(aEvent.mMaterial)
                                , 'N', nugget.dat(aEvent.mMaterial)
                                , 'H', stick.dat(mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial)
                                , 'A', aEvent.mPrefix.dat(aEvent.mMaterial)
                                , 'V', mSpecialObjectV instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectV).dat(aEvent.mMaterial) : mSpecialObjectV==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectV
                                , 'W', mSpecialObjectW instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectW).dat(aEvent.mMaterial) : mSpecialObjectW==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectW
                                , 'X', mSpecialObjectX instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectX).dat(aEvent.mMaterial) : mSpecialObjectX==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectX
                                , 'Y', mSpecialObjectY instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectY).dat(aEvent.mMaterial) : mSpecialObjectY==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectY
                                , 'Z', mSpecialObjectZ instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectZ).dat(aEvent.mMaterial) : mSpecialObjectZ==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectZ
                            });
                            else if (mToolHeadRecipes[i].length == 2)   CR.shaped(tStack, CR.DEF_NCC | (mDismantleable?CR.DISMANTLE:0), new Object[] {mToolHeadRecipes[i][0], mToolHeadRecipes[i][1]
                                , 'G', gem.dat(aEvent.mMaterial)
                                , 'I', aEvent.mMaterial==MT.Wood?OD.plankWood:ingot.dat(aEvent.mMaterial)
                                , 'P', aEvent.mMaterial==MT.Wood?OD.plankWood:plate.dat(aEvent.mMaterial)
                                , 'B', plateCurved.dat(aEvent.mMaterial)
                                , 'C', plateGem.dat(aEvent.mMaterial)
                                , 'S', stick.dat(aEvent.mMaterial)
                                , 'R', aEvent.mMaterial==MT.Stone?ST.make(Blocks.stone, 1, W):stone.dat(aEvent.mMaterial)
                                , 'T', screw.dat(aEvent.mMaterial)
                                , 'O', ring.dat(aEvent.mMaterial)
                                , 'N', nugget.dat(aEvent.mMaterial)
                                , 'H', stick.dat(mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial)
                                , 'A', aEvent.mPrefix.dat(aEvent.mMaterial)
                                , 'V', mSpecialObjectV instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectV).dat(aEvent.mMaterial) : mSpecialObjectV==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectV
                                , 'W', mSpecialObjectW instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectW).dat(aEvent.mMaterial) : mSpecialObjectW==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectW
                                , 'X', mSpecialObjectX instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectX).dat(aEvent.mMaterial) : mSpecialObjectX==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectX
                                , 'Y', mSpecialObjectY instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectY).dat(aEvent.mMaterial) : mSpecialObjectY==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectY
                                , 'Z', mSpecialObjectZ instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectZ).dat(aEvent.mMaterial) : mSpecialObjectZ==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectZ
                            });
                            else                                        CR.shaped(tStack, CR.DEF_NCC | (mDismantleable?CR.DISMANTLE:0), new Object[] {mToolHeadRecipes[i][0], mToolHeadRecipes[i][1], mToolHeadRecipes[i][2]
                                    , 'G', gem.dat(aEvent.mMaterial)
                                    , 'I', aEvent.mMaterial==MT.Wood?OD.plankWood:ingot.dat(aEvent.mMaterial)
                                    , 'P', aEvent.mMaterial==MT.Wood?OD.plankWood:plate.dat(aEvent.mMaterial)
                                    , 'B', plateCurved.dat(aEvent.mMaterial)
                                    , 'C', plateGem.dat(aEvent.mMaterial)
                                    , 'S', stick.dat(aEvent.mMaterial)
                                    , 'R', aEvent.mMaterial==MT.Stone?ST.make(Blocks.stone, 1, W):stone.dat(aEvent.mMaterial)
                                    , 'T', screw.dat(aEvent.mMaterial)
                                    , 'O', ring.dat(aEvent.mMaterial)
                                    , 'N', nugget.dat(aEvent.mMaterial)
                                    , 'H', stick.dat(mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial)
                                    , 'A', aEvent.mPrefix.dat(aEvent.mMaterial)
                                    , 'V', mSpecialObjectV instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectV).dat(aEvent.mMaterial) : mSpecialObjectV==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectV
                                    , 'W', mSpecialObjectW instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectW).dat(aEvent.mMaterial) : mSpecialObjectW==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectW
                                    , 'X', mSpecialObjectX instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectX).dat(aEvent.mMaterial) : mSpecialObjectX==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectX
                                    , 'Y', mSpecialObjectY instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectY).dat(aEvent.mMaterial) : mSpecialObjectY==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectY
                                    , 'Z', mSpecialObjectZ instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectZ).dat(aEvent.mMaterial) : mSpecialObjectZ==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectZ
                                });
                        }
                    }
                }
            }
        }
    }
}
