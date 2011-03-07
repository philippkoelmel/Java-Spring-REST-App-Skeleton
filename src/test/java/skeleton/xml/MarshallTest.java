package skeleton.xml;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import skeleton.skull.Skull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class MarshallTest {
	
	@Autowired
	Marshaller marshaller;
	
	private static final String XML_SKULL_RESULT="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><skull><id>1</id></skull>";
	
	@Test
	public void marshallSkull() throws XmlMappingException, IOException {

		Skull skull = new Skull();
		skull.setId(1);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		
		marshaller.marshal(skull, result);
		
		assertEquals(
				XML_SKULL_RESULT,
				writer.toString());
	}
}
