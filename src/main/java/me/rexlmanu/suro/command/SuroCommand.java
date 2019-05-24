/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2019.
 */
package me.rexlmanu.suro.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/******************************************************************************************
 *    Urheberrechtshinweis                                                                *
 *    Copyright © Emmanuel Lampe 2019                                                  *
 *    Erstellt: 24.05.2019 / 15:37                                               *
 *                                                                                        *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,            *
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                                        *
 *                                                                                        * 
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,                 *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                                  *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.             *
 ******************************************************************************************/

public final class SuroCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] arguments) {
        switch (arguments.length) {
            case 1:
                if ("info".equals(arguments[0].toLowerCase())) {
                    commandSender.sendMessage("§7Minecraft §aSuro §7Plugin v1 §8| §7coded by rexlManu");
                    commandSender.sendMessage("  §8» §7For more information§8:§7 https://rexl.eu/SuroPlugin");
                    return true;
                }
            default:
                this.sendHelpUsage(commandSender);
                break;
        }
        return false;
    }

    private void sendHelpUsage(CommandSender commandSender) {

    }
}
