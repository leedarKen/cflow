package com.csoft.resource.cflow.common.xml;

import java.lang.reflect.Method;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLParser<T> {
	private Schema schema;

	public static <T> XMLParser<T> getInstance(URL xsdUrl) throws SAXException {
		return new XMLParser<T>(xsdUrl);
	}

	protected XMLParser(URL xsdUrl) throws SAXException {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		if (schemaFactory == null) {
			throw new SAXException("Unsupported XML schema language \"" + XMLConstants.W3C_XML_SCHEMA_NS_URI + "\".");
		}

		schema = schemaFactory.newSchema(xsdUrl);
		if (schema == null)
			throw new SAXException("Unsupported XML schema.");
	}

	@SuppressWarnings("unchecked")
	public T parse(URL xmlUrl, Class<T> c) throws SAXException {
		DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
		xmlFactory.setIgnoringComments(true);
		xmlFactory.setIgnoringElementContentWhitespace(true);
		xmlFactory.setNamespaceAware(true);
		xmlFactory.setSchema(schema);

		try {
			Document document = xmlFactory.newDocumentBuilder().parse(xmlUrl.openStream());

			Method m = c.getMethod("parse", org.w3c.dom.Element.class);
			return (T) m.invoke(c, document.getDocumentElement());
		} catch (Exception e) {
			throw new SAXException(e);
		}
	}
}
