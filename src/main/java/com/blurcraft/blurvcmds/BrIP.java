package com.blurcraft.blurvcmds.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.event.player.KickedFromServerEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.blurcraft.blurvcmds.blurvcmds.COLOR_RED;
import static com.blurcraft.blurvcmds.blurvcmds.COLOR_YELLOW;

public class BrIP implements SimpleCommand {
    private final ProxyServer server;

    @Subscribe
    public void kickFromServer(KickedFromServerEvent event) {
        Optional<Component> serverKickReason = event.getServerKickReason();
        if(serverKickReason.isPresent()) {
            Player player = event.getPlayer();
            Optional<RegisteredServer> authServer = plugin.getServer().getServer("auth");
            event.setResult(KickedFromServerEvent.RedirectPlayer.create(authServer.get()));
            Component reason = serverKickReason.get();

            scheduler.schedule(() -> {
                clearChat(player);
                player.sendMessage(LimboHub.getSerializer().deserialize("You kicked. Reason:" + ((TextComponent) reason).content()));
                plugin.sendToHub(player);
            }, 150, TimeUnit.MILLISECONDS);
        } else {
            Player player = event.getPlayer();
            Optional<RegisteredServer> authServer = plugin.getServer().getServer("auth");
            event.setResult(KickedFromServerEvent.RedirectPlayer.create(authServer.get()));
            scheduler.schedule(() -> {
                clearChat(player);
                player.sendMessage(LimboHub.getSerializer().deserialize("You kicked");
                plugin.sendToHub(player);
            }, 150, TimeUnit.MILLISECONDS);
        }
    }
