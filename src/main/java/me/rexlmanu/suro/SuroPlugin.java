/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2019.
 */
package me.rexlmanu.suro;

import lombok.Getter;
import me.rexlmanu.suro.command.SuroCommand;
import me.rexlmanu.suro.database.SuroDatabase;
import me.rexlmanu.suro.database.SuroPlayer;
import me.rexlmanu.suro.database.file.FileSuroDatabase;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/******************************************************************************************
 *    Urheberrechtshinweis                                                                *
 *    Copyright © Emmanuel Lampe 2019                                                  *
 *    Erstellt: 24.05.2019 / 15:36                                               *
 *                                                                                        *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,            *
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                                        *
 *                                                                                        * 
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,                 *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                                  *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.             *
 ******************************************************************************************/

@Getter
public final class SuroPlugin extends JavaPlugin {

    @Getter
    private static SuroPlugin instance;

    private final List<SuroPlayer> onlinePlayers;

    private SuroDatabase suroDatabase;

    public SuroPlugin() {
        this.onlinePlayers = new ArrayList<>();
    }

    @Override
    public void onEnable() {
        instance = this;

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        this.getCommand("suro").setExecutor(new SuroCommand());

        String database = this.getConfig().getString("Database");
        switch (database.toLowerCase()) {
            case "file":
                this.suroDatabase = new FileSuroDatabase();
                break;
            default:
                Bukkit.getConsoleSender().sendMessage("§4The database " + database + " could not be found...");
                Bukkit.getConsoleSender().sendMessage("§7Returning to default database 'file'");
                this.suroDatabase = new FileSuroDatabase();
                break;
        }
    }

    public String getMessage(String key) {
        return this.getConfig().getString("Message." + key).replace('&', '§');
    }

    public boolean isServerOpen() {
        String time = this.getConfig().getString("Time");
        String[] splitedTime = time.split("-");
        int from = Integer.parseInt(splitedTime[0]);
        int to = Integer.parseInt(splitedTime[1]);
        int currentHour = this.getCurrentHour();
        return from >= currentHour && to > currentHour;
    }

    private int getCurrentHour() {
        return ZonedDateTime.now(ZoneId.of(Objects.requireNonNull(this.getConfig().getString("ZoneId")))).getHour();
    }
}
