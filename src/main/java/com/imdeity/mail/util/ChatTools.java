package com.imdeity.mail.util;

//~--- non-JDK imports --------------------------------------------------------

import org.bukkit.entity.Player;

public class ChatTools {
    public static void formatAndSend(String msg, Player player) {
        player.sendMessage(msg);
    }
}
