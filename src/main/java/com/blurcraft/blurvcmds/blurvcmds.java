package com.blurcraft.blurvcmds;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.blurcraft.blurvcmds.commands.*;
import net.kyori.adventure.text.format.TextColor;
import org.slf4j.Logger;

import javax.inject.Inject;

@Plugin(id = "blurvcmds", name="blurvcmds", version="1.0-SNAPSHOT", description="BlurCraft Velocity Commands!")
public class blurvcmds {
    private final ProxyServer server;

    public static final TextColor COLOR_RED = TextColor.fromCSSHexString("FF5555");
    public static final TextColor COLOR_YELLOW = TextColor.fromCSSHexString("FFFF55");

    @Inject
    public blurvcmds(ProxyServer server, Logger logger) {
        this.server = server;
    }


    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getCommandManager().register("send", new CommandSend(server));
        server.getCommandManager().register("sendall", new CommandSendall(server));
        server.getCommandManager().register("broadcast", new CommandBroadcast(server), "bc", "alert");
        server.getCommandManager().register("find", new CommandFind(server), "search");
        server.getCommandManager().register("restart", new CommandRestart(server));
        server.getCommandManager().register("servers", new CommandServers(server), "allservers");
    }

}
