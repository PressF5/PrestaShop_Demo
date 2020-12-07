package com.PrestaShop.AllureXML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XML {

	private Document document;
	private DocumentBuilder builder;
	private Element RootElement;

	private File file = new File("src/test/resources/AllureFiles/environment.xml");

	public File getEnvironmentAllureFile() {

		return file;
	}

	public XML createXMLDocument() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return this;
	}

	public XML changeXMLDocument() {

		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = builder.parse(file);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public XML createRootXML() {

		document = builder.newDocument();
		RootElement = document.createElement("environment");
		document.appendChild(RootElement);

		return this;
	}

	private Element getRootXML() {

		return document.getDocumentElement();
	}

	public XML createXMLParameter(String key, String value) {

		Element Parameter = document.createElement("parameter");
		RootElement.appendChild(Parameter);

		Element Key = document.createElement("key");
		Element Value = document.createElement("value");

		Key.appendChild(document.createTextNode(key));
		Value.appendChild(document.createTextNode(value));

		Parameter.appendChild(Key);
		Parameter.appendChild(Value);

		return this;
	}

	public XML addNewParametersToXML(String key, String value) {

		RootElement = getRootXML();
		createXMLParameter(key, value);

		return this;
	}

	public XML writeToXMLFile() {

		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return this;
	}
}
