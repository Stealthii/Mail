package com.imdeity.mail.object;

//~--- non-JDK imports --------------------------------------------------------

import com.imdeity.mail.util.StringMgmt;

//~--- JDK imports ------------------------------------------------------------

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;

public class MailObject {
    private int    id,
                   index    = 0;
    private Date   sendDate = null;
    private String sender, receiver,
                   message  = "";

    public MailObject(int id, int index, String sender, String receiver, String message) {
        this.id       = id;
        this.index    = index;
        this.sender   = sender;
        this.receiver = receiver;
        this.message  = message;
    }

    public MailObject(int id, int index, String sender, String receiver, String message, Date sendDate) {
        this.id       = id;
        this.index    = index;
        this.sender   = sender;
        this.receiver = receiver;
        this.message  = message;
        this.sendDate = sendDate;
    }

    public int getId() {
        return this.id;
    }

    public int getIndex() {
        return this.index;
    }

    public String getSender() {
        return this.sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public String getMessage() {
        return this.message;
    }

    public String[] toShortString() {
        return this.preformReplacement(Language.getMailShortMessage());
    }

    public String[] toLongString() {
        return this.preformReplacement(Language.getMailLongMessage());
    }

    public String getSendDate() {
        DateFormat df = DateFormat.getDateInstance();
        return df.format(this.sendDate);
    }

    public String[] preformReplacement(String msg) {
        msg = msg.replaceAll("%messageId", this.getId() + "").replaceAll("%messageLocalIndex",
                             this.getIndex() + "").replaceAll("%messageSender",
                                 this.getSender()).replaceAll("%messageLongMessage",
                                     this.message).replaceAll("%messageShortMessage",
                                         StringMgmt.maxLength(this.message, 30)).replaceAll("%messageReceiver",
                                             this.getReceiver()).replaceAll("%messageSendDate", this.getSendDate());

        String[] tmpMsg = msg.split("%newline");

        return tmpMsg;
    }
}
