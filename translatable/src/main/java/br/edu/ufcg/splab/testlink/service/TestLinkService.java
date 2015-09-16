package br.edu.ufcg.splab.testlink.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PushbackInputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.interfaces.Traceable;
import splab.ufcg.edu.br.trace.interfaces.Translatable;
import br.edu.ufcg.splab.util.TestLinkProperties;

public class TestLinkService implements Translatable, Traceable {

	@Inject
	private TestLinkProperties properties;

	// FEFF because this is the Unicode char represented by the UTF-8 byte order
	// mark (EF BB BF).
	public static final String UTF8_BOM = "\uFEFF";

	@Override
	public TraceLinkList read(File file) {
		return null;
	}

	@Override
	public void write(File file, TraceLinkList tracelinks) {
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(TraceLinkList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(tracelinks, file);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public TraceLinkList read() {
		properties.loadProperties();
		TraceLinkList traceLinkList = new TraceLinkList();
		try {
			System.out.println();
			System.out
					.println("--------------------------------------------------------------");
			System.out.println();

			File folder = new File(properties.getReaderDirectory());
			for (File fileEntry : folder.listFiles()) {

				FileInputStream inputStream = new FileInputStream(fileEntry);

				InputStream inputStreamBOM = checkForUtf8BOMAndDiscardIfAny(inputStream);

				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docBuilderFactory
						.newDocumentBuilder();
				Document doc = docBuilder.parse(inputStreamBOM);

				// normalize text representation
				doc.getDocumentElement().normalize();

				NodeList testcases = doc.getElementsByTagName("testcase");
				for (int i = 0; i < testcases.getLength(); i++) {
					Node testCaseNode = testcases.item(i);
					if (testCaseNode.getNodeType() == Node.ELEMENT_NODE) {
						Element testCaseElement = (Element) testCaseNode;
						String testCaseName = testCaseElement
								.getAttribute("name");
						System.out.println(testCaseName);

						NodeList keywordList = testCaseElement
								.getElementsByTagName("keyword");
						for (int j = 0; j < keywordList.getLength(); j++) {
							Element keywordElement = (Element) keywordList
									.item(j);
							String keywordName = keywordElement
									.getAttribute("name");
							System.out.println("	" + keywordName);

							TraceLink traceLink = new TraceLink(keywordName,
									"Dependency", "Test_Case", testCaseName);
							traceLinkList.add(traceLink);
						}
					}
				}
			}
			System.out.println();
			System.out
					.println("--------------------------------------------------------------");
			System.out.println();

		} catch (SAXException | IOException | ParserConfigurationException ex) {
			
			ex.printStackTrace();
			
			throw new RuntimeException(ex);
		}
		return traceLinkList;
	}

	public void write(TraceLinkList traceLinks) throws Exception {
		File file = new File(properties.getWriterDirectory());
		this.write(file, traceLinks);
	}

	@Override
	public TraceLinkList getTraceLinks() {
		return null;
	}

	private InputStream checkForUtf8BOMAndDiscardIfAny(
			InputStream inputStream) throws IOException {
		PushbackInputStream pushbackInputStream = new PushbackInputStream(
				new BufferedInputStream(inputStream), 3);
		byte[] bom = new byte[3];
		if (pushbackInputStream.read(bom) != -1) {
			if (!(bom[0] == (byte) 0xEF && bom[1] == (byte) 0xBB && bom[2] == (byte) 0xBF)) {
				pushbackInputStream.unread(bom);
			}
		}
		return pushbackInputStream;
	}

}
