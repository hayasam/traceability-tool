package splab.ufcg.edu.br.trace.writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import splab.ufcg.edu.br.trace.entities.TraceLink;
import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.interfaces.Translatable;

public class TraceLinkWriter implements Translatable {

	public static final String UTF8_BOM = "\uFEFF";

	private static String removeUTF8BOM(String s) {
        if (s.startsWith(UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }

	@Override
	public TraceLinkList read(File file) throws Exception {

		TraceLinkList result = null;

		try {
			
			
//		       boolean firstLine = true;
//            FileInputStream fis = new FileInputStream(file);
//            BufferedReader r = new BufferedReader(new InputStreamReader(fis,
//                    "UTF8"));
//            
//            FileOutputStream fos = new FileOutputStream("/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/traceability-tool/src/main/resources/writer/parser.xml");
//            Writer w = new BufferedWriter(new OutputStreamWriter(fos, "Cp1252"));
//            for (String s = ""; (s = r.readLine()) != null;) {
//                if (firstLine) {
//                    s = removeUTF8BOM(s);
//                    firstLine = false;
//                }
//                w.write(s + System.getProperty("line.separator"));
//                w.flush();
//            }
//
//            w.close();
//            r.close();
//            
//            System.out.println("UTF-8 Tratado com sucesso !!!!");
//		    			

			JAXBContext jaxbContext = JAXBContext
					.newInstance(TraceLinkList.class);
			
			file = new File("/Users/Arthur/Dropbox/Mestrado-ArthurMarques-Shared/Traceability-Tool/traceability-tool/src/main/resources/writer/parser.xml");

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			result = (TraceLinkList) jaxbUnmarshaller.unmarshal(file);

		} catch (Exception ex) {
//			ex.printStackTrace();
			throw ex;
		}
		
		if (result != null){
			for (TraceLink tracelink: result.getTraceLinks()) {
				tracelink.checkParameters();				
			}
		}

		return result;
	}

	@Override
	public void write(File file, TraceLinkList tracelinks) throws Exception {

		try {
			
			if (!file.exists()){
				file.createNewFile();				
			}

			JAXBContext jaxbContext = JAXBContext
					.newInstance(TraceLinkList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(tracelinks, file);
			// jaxbMarshaller.marshal(tracelinks, System.out);

		} catch (Exception ex) {
//			ex.printStackTrace();
			throw ex;
		}

	}

}
