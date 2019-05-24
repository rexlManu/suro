/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2019.
 */
package me.rexlmanu.suro.listener;

import me.rexlmanu.suro.SuroPlugin;
import me.rexlmanu.suro.database.OnlineSuroPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/******************************************************************************************
 *    Urheberrechtshinweis                                                                *
 *    Copyright © Emmanuel Lampe 2019                                                  *
 *    Erstellt: 24.05.2019 / 16:04                                               *
 *                                                                                        *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,            *
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                                        *
 *                                                                                        * 
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,                 *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                                  *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.             *
 ******************************************************************************************/

public final class SuroListener implements Listener {

    @EventHandler
    public void handle(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SuroPlugin instance = SuroPlugin.getInstance();
        if (! instance.isServerOpen() && ! player.hasPermission("suro.admin")) {
            player.kickPlayer(instance.getMessage("NotTheTime"));
            return;
        }
        if (! instance.getSuroDatabase().getWhitelist().contains(player.getUniqueId())) {
            player.kickPlayer(instance.getMessage("NotWhitelisted"));
            return;
        }
        instance.getOnlinePlayers().add(new OnlineSuroPlayer(player.getName(), player.getUniqueId()));
    }

    @EventHandler
    public void handle(PlayerQuitEvent event) {

    }

    @EventHandler
    public void handle(PlayerDeathEvent event) {

    }

}
