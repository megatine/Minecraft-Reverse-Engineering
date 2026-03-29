package com.miracsclient;

import com.miracsclient.Modules.Fly;
import com.miracsclient.Modules.Reach;
import net.fabricmc.api.ClientModInitializer;

public class MiracsClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Fly.init();
        Reach.init();
    }
}