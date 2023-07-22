package ysoserial.payloads.util;

import ysoserial.payloads.ObjectPayload;

import java.util.ArrayList;
import java.util.List;

public class ParseArg {
    public static Class getTargetPayload(String arg) {
        String payloadClass = arg.split(":")[0];
        Class payloadClazz = null;
        List<Class<? extends ObjectPayload>> payloadClasses =
            new ArrayList<Class<? extends ObjectPayload>>(ObjectPayload.Utils.getPayloadClasses());
        for (Class<?> clazz : payloadClasses) {
            if ((("ysoserial.payloads."+payloadClass).toLowerCase()).equals(clazz.getName().toLowerCase())) {
                payloadClazz = clazz;
                break;
            }
        }
        return payloadClazz;
    }

}
