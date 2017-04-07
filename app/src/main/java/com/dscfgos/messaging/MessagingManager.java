package com.dscfgos.messaging;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by David on 15-03-2017.
 */

public class MessagingManager
{
    private static HashMap<String, ArrayList<MessageProcessor>> mapMessages = new HashMap<>();
    public static void addDSMessageListener(String messageType, MessageProcessor listener)
    {
        if(listener != null && messageType != null && messageType.length()>0)
        {
            ArrayList<MessageProcessor> listeners = mapMessages.remove(messageType);
            if(listeners == null)
            {
                listeners = new ArrayList<>();
            }

            listeners.add(listener);

            mapMessages.put(messageType,listeners);
        }
    }

    public static void removeDSMessageListener(String messageType, MessageProcessor listener)
    {
        if(listener != null && messageType != null && messageType.length()>0)
        {
            ArrayList<MessageProcessor> listeners = mapMessages.remove(messageType);
            if(listeners != null)
            {
                listeners.remove(listener);
                mapMessages.put(messageType,listeners);
            }
        }
    }

    public static void sendMessage(DSMessage message)
    {
        if(message != null && message.isValid())
        {
            ArrayList<MessageProcessor> listeners = mapMessages.get(message.getMessageType());
            if(listeners != null && listeners.size() >0)
            {
                for (MessageProcessor processor: listeners)
                {
                    processor.processMessage(message);
                }
            }
        }
    }
}
