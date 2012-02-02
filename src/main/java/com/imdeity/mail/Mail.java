package com.imdeity.mail;

//~--- non-JDK imports --------------------------------------------------------

import com.imdeity.mail.cmd.MailCommand;
import com.imdeity.mail.event.MailPlayerListener;
import com.imdeity.mail.object.Language;
import com.imdeity.mail.sql.MailSQL;
import com.imdeity.mail.sql.MySQLConnection;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

//~--- JDK imports ------------------------------------------------------------

import java.sql.SQLException;

import java.util.logging.Logger;

public class Mail extends JavaPlugin {
    public static MySQLConnection database    = null;
    public static Mail            mail        = null;
    public static boolean         configError = false;
    public Language               language    = null;
    public final Logger           log         = Logger.getLogger("Minecraft");
    private Settings              settings    = null;

    @Override
    public void onDisable() {
        language.save();
        out("Disabled");
    }

    @Override
    public void onEnable() {
        this.language = null;
        this.settings = null;
        this.language = new Language();
        this.language.loadDefaults();
        this.settings = new Settings(this);
        this.settings.loadSettings("config.yml", "/config.yml");
        Mail.mail = this;
        getCommand("mail").setExecutor(new MailCommand(this));

        try {
            database = new MySQLConnection();
        } catch (Exception ex) {
            out("Database is set up incorrectly. Please configure the config.yml before procedeing");
            configError = true;
        }

        if (!configError) {
            getServer().getPluginManager().registerEvents(new MailPlayerListener(this), this);
        }

        out("Enabled");
    }

    public Player getPlayer(String playername) {
        Player player = null;

        for (Player checkPlayer : this.getServer().getOnlinePlayers()) {
            if (checkPlayer.getName().equalsIgnoreCase(playername)) {
                player = checkPlayer;

                return player;
            }
        }

        return null;
    }

    public void notifyReceiver(String playername) {
        Player receiver = this.getPlayer(playername);

        if (receiver != null) {
            this.sendPlayerMessage(receiver, Language.getInboxNew());
            this.sendPlayerMessage(receiver, Language.getInboxCheck());
        }
    }

    public void sendPlayerMessage(Player player, String message) {
        player.sendMessage(Language.getHeader() + message);
    }

    public void out(String message) {
        PluginDescriptionFile pdfFile = this.getDescription();

        log.info("[" + pdfFile.getName() + "] " + message);
    }

    public static void sendMailToPlayer(String sender, String receiver, String message) throws SQLException {
        MailSQL.sendMail(sender, receiver, message);
    }

    public static void sendUnreadMailCountMessage(String player) {
        MailSQL.sendUnreadCount(player);
    }
}
