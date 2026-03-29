package com.miracsclient;

import com.miracsclient.Modules.AutoFish;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Miracsclient implements ModInitializer {
    public static final String MOD_ID = "miracsclient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        AutoFish.INSTANCE.toString();
    }
}