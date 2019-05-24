/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2019.
 */
package me.rexlmanu.suro.database.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.rexlmanu.suro.SuroPlugin;
import me.rexlmanu.suro.database.OnlineSuroPlayer;
import me.rexlmanu.suro.database.SuroDatabase;
import me.rexlmanu.suro.database.SuroPlayer;

import java.io.*;
import java.util.List;
import java.util.UUID;

/******************************************************************************************
 *    Urheberrechtshinweis                                                                *
 *    Copyright © Emmanuel Lampe 2019                                                  *
 *    Erstellt: 24.05.2019 / 15:50                                               *
 *                                                                                        *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,            *
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                                        *
 *                                                                                        * 
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,                 *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                                  *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.             *
 ******************************************************************************************/

public final class FileSuroDatabase implements SuroDatabase {

    private final JsonParser jsonParser;
    private final Gson gson;
    private final File databaseFile;

    private JsonObject cachedJsonObject;

    public FileSuroDatabase() {
        this.jsonParser = new JsonParser();
        this.gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        this.databaseFile = new File(SuroPlugin.getInstance().getDataFolder(), "database.json");
        if (this.databaseFile.exists())
            try {
                this.cachedJsonObject = jsonParser.parse(new FileReader(this.databaseFile)).getAsJsonObject();
            } catch (FileNotFoundException ignored) {
            }
        else this.cachedJsonObject = new JsonObject();
    }

    private void saveCacheToFile() {
        try {
            FileWriter fileWriter = new FileWriter(this.databaseFile);
            fileWriter.write(gson.toJson(this.cachedJsonObject));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ignored) {
        }
    }

    public List<SuroPlayer> getPlayers() {
        return null;
    }

    public void registerNewPlayer(OnlineSuroPlayer suroPlayer) {

    }

    public List<UUID> getWhitelist() {
        return null;
    }

    public void addToWhitelist(UUID uuid) {

    }

    public void removeFromWhitelist(UUID uuid) {

    }
}
