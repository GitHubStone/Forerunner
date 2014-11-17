package com.forerunner.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.google.common.collect.Maps;

public class JAXBUtils {

	public static <T> void parseToXML(Class<T> clazz, Object object, OutputStream outputStream) {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(object, outputStream);
		} catch (JAXBException e) {
			throw new RuntimeException("Failed to generate schema : " + e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> T parseToOBJ(Class<T> clazz, InputStream inputStream) {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			T data = (T) unmarshaller.unmarshal(inputStream);
			return data;
		} catch (JAXBException e) {
			throw new RuntimeException("(TplXMLManager) Failed to parse XML : " + e.getMessage(), e);
		}
	}

	/**
	 * generate schema(XSD) by JAXB.
	 * schema will be print to the @Param outputStream
	 */
	public static void generateSchema(Class<? extends Object> clazz, OutputStream os) {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);

			Map<String, DOMResult> builtIns = Maps.newHashMap();
			List<DOMResult> schemas = org.apache.cxf.common.jaxb.JAXBUtils.generateJaxbSchemas(context, builtIns);

			DOMResult domResult = schemas.get(0);
			Document doc = (Document) domResult.getNode();

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(new DOMSource(doc), new StreamResult(os));

		} catch (JAXBException e) {
			throw new RuntimeException("Failed to generate schema : " + e.getMessage(), e);
		} catch (IOException e) {
			throw new RuntimeException("Failed to generate schema : " + e.getMessage(), e);
		} catch (TransformerException e) {
			throw new RuntimeException("Failed to generate schema : " + e.getMessage(), e);
		}
	}
}