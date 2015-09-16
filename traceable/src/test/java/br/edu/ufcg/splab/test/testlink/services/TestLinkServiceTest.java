package br.edu.ufcg.splab.test.testlink.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import br.edu.ufcg.splab.testlink.data.TestSuite;

@RunWith(Arquillian.class)
public class TestLinkServiceTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		File[] files = Maven.resolver().loadPomFromFile("pom.xml")
				.importRuntimeDependencies().resolve().withTransitivity()
				.asFile();

		// Create deploy file
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, "br.edu.ufcg.splab.testlink.services",
						"br.edu.ufcg.splab.testlink.data")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("test-link.xml", "test-link.xml")

				.addAsLibraries(
						Maven.resolver()
								.resolve(
										"org.apache.httpcomponents:httpcore:4.2.2")
								.withTransitivity().asFile())
				.addAsLibraries(files);

		// Show the deploy structure
		// System.out.println(war.toString(true));
		return war;
	}

	@Test
	public void testReadTestSuite() {
		JAXBContext jaxbContext;
		try {
			// jaxbContext = JAXBContext.newInstance(TestSuite.class);
			InputStream inputStream = Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream("test-link.xml");
			// Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			// TestSuite testSuite = (TestSuite) jaxbUnmarshaller
			// .unmarshal(inputStream);
			//
			// Assert.assertNotNull(testSuite);
			//
			
			// System.out.println(testSuite.toString());
			 System.out.println();
			 System.out.println("--------------------------------------------------------------");
			 System.out.println();
			 
			 TraceLinkList traceLinkList = new TraceLinkList();

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputStream);

			// normalize text representation
			doc.getDocumentElement().normalize();
			
			NodeList testcases = doc.getElementsByTagName("testcase");
			for(int i = 0; i < testcases.getLength(); i++){
				Node testCaseNode = testcases.item(i);
				if(testCaseNode.getNodeType() == Node.ELEMENT_NODE){
                    Element testCaseElement = (Element) testCaseNode;
                    String testCaseName = testCaseElement.getAttribute("name");
                    System.out.println(testCaseName);

                    List<String>  requirements = new ArrayList<String>();
                    NodeList keywordList = testCaseElement.getElementsByTagName("keyword");
                    for (int j = 0; j < keywordList.getLength(); j++){
                    	Element keywordElement = (Element) keywordList.item(j);
                    	String keywordName = keywordElement.getAttribute("name");
                        System.out.println("	" +keywordName);
                        requirements.add(keywordName);
                        
                    }
                    TraceLink traceLink = new TraceLink(requirements, "Dependency",
                    		testCaseElement.getNodeName(), testCaseName);
                    traceLinkList.add(traceLink);
				}
			}
			System.out.println();
			System.out.println();
			System.out.println("--------------------------------------------------------------");
			System.out.println();
			System.out.println(traceLinkList.toString());
			System.out.println();
			System.out.println("--------------------------------------------------------------");
			System.out.println();
			
			
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
}
