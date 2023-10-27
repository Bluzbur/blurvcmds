package com.blurcraft.blurvcmds.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

import static com.blurcraft.blurvcmds.blurvcmds.COLOR_RED;

public class CommandBroadcast implements SimpleCommand {
    private final ProxyServer server;

    public CommandBroadcast(ProxyServer server) {
        this.server = server;
    }

    @Override
    public void execute(SimpleCommand.Invocation invocation) {
        CommandSource commandSource = invocation.source();
        String[] strings = invocation.arguments();

        if (strings.length > 0) {
            String message = String.join(" ", strings).replace("&", "§");
            String alert = ("[ALERT] ");
            for (Player player : server.getAllPlayers()) {
                player.sendMessage(Component.text(alert, NamedTextColor.DARK_RED).append(Component.text(message, NamedTextColor.RED)));
            }
        } else {
            commandSource.sendMessage(Component.text("Usage: /alert <message>", NamedTextColor.RED));
        }
    }

    @Override
    public List<String> suggest(SimpleCommand.Invocation invocation) {
        return new ArrayList<String>();
    }

    @Override
    public boolean hasPermission(SimpleCommand.Invocation invocation) {
        return invocation.source().hasPermission("blurcraftproxy.broadcast");
    }
}