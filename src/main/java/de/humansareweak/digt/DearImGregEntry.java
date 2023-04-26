package de.humansareweak.digt;

import cpw.mods.fml.common.event.*;
import de.humansareweak.digt.item.Registry;
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import gregapi.code.ModData;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]", dependencies = "required-after:gregapi_post")
public class DearImGregEntry extends Abstract_Mod {
    private static final String MOD_LOG_NAME = "DIGT";

    public static ModData MOD_DATA = new ModData(Tags.MODID, Tags.MODNAME);
    @SidedProxy(modId = Tags.MODID, clientSide = "de.humansareweak.digt.ProxyClient", serverSide = "de.humansareweak.digt.ProxyServer")
    public static Abstract_Proxy PROXY;

    @Override public String getModID() { return Tags.MODID; }
    @Override public String getModName() { return Tags.MODNAME; }
    @Override public String getModNameForLog() { return MOD_LOG_NAME; }
    @Override public Abstract_Proxy getProxy() { return PROXY; }

    // Do not change these 7 Functions. Just keep them this way.
    @Mod.EventHandler public final void onPreLoad           (FMLPreInitializationEvent    aEvent) {onModPreInit(aEvent);}
    @Mod.EventHandler public final void onLoad              (FMLInitializationEvent       aEvent) {onModInit(aEvent);}
    @Mod.EventHandler public final void onPostLoad          (FMLPostInitializationEvent   aEvent) {onModPostInit(aEvent);}
    @Mod.EventHandler public final void onServerStarting    (FMLServerStartingEvent       aEvent) {onModServerStarting(aEvent);}
    @Mod.EventHandler public final void onServerStarted     (FMLServerStartedEvent        aEvent) {onModServerStarted(aEvent);}
    @Mod.EventHandler public final void onServerStopping    (FMLServerStoppingEvent       aEvent) {onModServerStopping(aEvent);}
    @Mod.EventHandler public final void onServerStopped     (FMLServerStoppedEvent        aEvent) {onModServerStopped(aEvent);}

    @Override
    public void onModPreInit2(FMLPreInitializationEvent aEvent) {
        Registry.registerItems();
    }

    @Override
    public void onModInit2(FMLInitializationEvent aEvent) {
        Registry.registerRecipes();
    }

    @Override
    public void onModPostInit2(FMLPostInitializationEvent aEvent) {

    }

    @Override
    public void onModServerStarting2(FMLServerStartingEvent aEvent) {

    }

    @Override
    public void onModServerStarted2(FMLServerStartedEvent aEvent) {

    }

    @Override
    public void onModServerStopping2(FMLServerStoppingEvent aEvent) {

    }

    @Override
    public void onModServerStopped2(FMLServerStoppedEvent aEvent) {

    }
}
