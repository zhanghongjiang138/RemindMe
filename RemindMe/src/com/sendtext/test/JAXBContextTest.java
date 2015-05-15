package com.sendtext.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.ValidationException;
import javax.xml.namespace.QName;

public class JAXBContextTest {
	private String path;
	private String filename;
	public JAXBContextTest(String path,String filename){
		this.path=path;
		this.filename=filename;
	}
	public synchronized String toXML(String strPackage, JAXBElement objJXElement) {
		Marshaller marshaller = null;
		JAXBContext objJC = null;
		StringWriter strWriter = new StringWriter();
		File file = new File(path + "/" + filename + ".xml");

		try {
			objJC = JAXBContext.newInstance(strPackage);
			marshaller = objJC.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// marshaller.marshal(objJXElement, strWriter);

			try {
				marshaller.marshal(objJXElement, new FileOutputStream(file));
			} catch (FileNotFoundException e) {
				// LOGGER.error("File not found Exception while marshalling ! ",
				// e);
				System.out.print("Exception:" + e);
			}

		} catch (MarshalException eMarshal) {
			// LOGGER.error("Exception while marshalling ! ", eMarshal);
			// throw new Exception(eMarshal);
			System.out.print("Exception:" + eMarshal);
		} catch (ValidationException eValidation) {
			// LOGGER.error("ValidationException while marshalling ! ",
			// eValidation);
			// throw new Exception(eValidation);
			System.out.print("Exception:" + eValidation);
		} catch (JAXBException eJAXB) {
			// LOGGER.error("JAXBException while marshalling ! ", eJAXB);
			// throw new Exception(eJAXB);
			System.out.print("Exception:" + eJAXB);
		}
		return strWriter.toString();
	}
	
	
	
	public static void main(String [] args){
		
		JAXBContextTest jc=new JAXBContextTest("./","jaxb");
		JAXBElement objJXElement=new JAXBElement(new QName("qname"),JAXBContextTest.class,jc);
		jc.toXML("test", objJXElement);
		
	}

}
