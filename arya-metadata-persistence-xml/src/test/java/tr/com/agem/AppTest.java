package tr.com.agem;

import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import tr.com.agem.arya.metadata.arya.impl.AryaType;
import tr.com.agem.arya.metadata.arya.impl.ButtonType;
import tr.com.agem.arya.metadata.arya.impl.ObjectFactory;
import tr.com.agem.arya.metadata.arya.impl.WindowType;
import tr.com.agem.arya.metadata.persistence.impl.xml.MetadataPersistenceImplXml;
import tr.com.agem.arya.metadata.persistence.impl.xml.MetadataXml;
import tr.com.agem.core.utils.AryaUtils;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	@SuppressWarnings("unchecked")
	public void testApp() {

		MetadataPersistenceImplXml xml = new MetadataPersistenceImplXml();

		MetadataXml mdxml = (MetadataXml) xml.findMetadata("arya", "master");

		System.out.println(mdxml.getMetadata());

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(AryaType.class, ObjectFactory.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(mdxml.getMetadata());

			AryaType zk = ((JAXBElement<AryaType>) ((JAXBElement<AryaType>) jaxbUnmarshaller
					.unmarshal(reader))).getValue();

			List<Object> comp = zk.getContent();

			for (Object o : comp) {
				if (o instanceof JAXBElement) {
					if (((JAXBElement<?>) o).getName().getLocalPart()
							.equalsIgnoreCase("window")) {
						WindowType window = (WindowType) ((JAXBElement<?>) o)
								.getValue();

						for (Object oo : window.getContent()) {
							if (oo instanceof JAXBElement<?>) {
								JAXBElement<?> j = (JAXBElement<?>) oo;
								System.out.println(j.getDeclaredType());
								if (((JAXBElement<?>) j).getName()
										.getLocalPart()
										.equalsIgnoreCase("button")) {
									ButtonType button = (ButtonType) ((JAXBElement<?>) j)
											.getValue();
									System.out.println(button.getId()
											+ button.getOnClick());
								}
							} else
								System.out.println("---> " + oo);
						}
					}
				}

			}
		} catch (JAXBException e) {
			AryaUtils.logException(null,e);
		}

		assertTrue(true);
	}
}
