package com.dscfgos.messaging;

/**
 * Created by David on 15-03-2017.
 */

public class DSMessage
{
    private String  messageType;
    private Object  content;

    public DSMessage(String messageType, Object content)
    {
        this.messageType    = messageType ;
        this.content        = content;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public boolean isValid()
    {
        return (this.content != null && this.messageType != null && this.messageType.length() > 0)?true:false;
    }
}
