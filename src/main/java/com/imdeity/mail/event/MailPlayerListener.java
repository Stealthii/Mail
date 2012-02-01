package com.imdeity.mail.event;

//~--- non-JDK imports --------------------------------------------------------

import com.imdeity.mail.Mail;
import com.imdeity.mail.sql.MailSQL;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MailPlayerListener implements Listener {
    public MailPlayerListener(Mail instance) {}

    @EventHandler(priority = EventPriority.MONITOR)
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("mail.player.join")) {
            MailSQL.sendUnreadCount(player.getName());
        }
    }
}
