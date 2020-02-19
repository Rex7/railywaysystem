package com.mydemo.daoimp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import com.mydemo.dao.TransactionDao;
import com.mydemo.model.Transaction;
import com.mydemo.operations.Dbhelper;

public class TransactionImp implements TransactionDao {

	@Override
	public void initTransaction(Transaction transaction) {
		Connection con = Dbhelper.getConnection();
		try {
			PreparedStatement pst = con
					.prepareStatement("insert into transaction (customername,trainno,price) values(?,?,?)");

			pst.setString(1, transaction.getCustomerName());
			pst.setInt(2, transaction.getTrainNo());
			pst.setFloat(3, transaction.getTotalCost());
			int update = pst.executeUpdate();
			if (update > 0) {
				System.out.println("booked ticket successfully");
				TrainSeatImp seats = new TrainSeatImp();
				seats.updateCheats(transaction.getTrainNo(), transaction.getNoOfPassenger());
				File xmlFile = writeToXMl(transaction);

				ConvertXml(xmlFile);

			} else {
				System.out.println("server error try again");
			}
		} catch (SQLException e) {
			System.out.println("sql exception " + e.getMessage());
		}

	}

	private static File writeToXMl(Transaction ticket) {
		try {
			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(Transaction.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting??
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File myfile = new File("ticket.xml");
			jaxbMarshaller.marshal(ticket, myfile);

			return myfile;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void ConvertXml(File xml) {
		File xslFile = new File("./src/simple.xsl");
		File pdfDir = new File("./pdfFolder");
		File pdfFile = new File(pdfDir, "name.pdf");

		pdfDir.mkdirs();
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		try {
			OutputStream outputStream = new FileOutputStream(pdfFile);
			outputStream = new BufferedOutputStream(outputStream);
			Fop fop;
			fop = fopFactory.newFop(MimeConstants.MIME_FOP_PRINT, foUserAgent, outputStream);
			// xsl file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer former = transformerFactory.newTransformer(new StreamSource(xslFile));

			former.setParameter("name", "Indian Railway");
			former.setParameter("myaddress", "Room no 92");

			Source src = new StreamSource(xml);

			Result res = new SAXResult(fop.getDefaultHandler());
			former.transform(src, res);

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (FOPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
