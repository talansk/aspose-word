package com.aspose.asposewords.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aspose.asposewords.bean.RequestBean;
import com.aspose.asposewords.bean.ResponseBean;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.LoadFormat;
import com.aspose.words.LoadOptions;
import com.aspose.words.Orientation;
import com.aspose.words.PageSetup;
import com.aspose.words.PaperSize;

@RestController
public class DownloadController {
	
	@PostMapping("/download")
	public ResponseBean download(@RequestBody RequestBean requestBean) throws Exception {
		String fileName 	= requestBean.getFileName();
		String storagePath 	= requestBean.getStorageUrl();
		
		URL url = new URL(requestBean.getRemoteUrl());
		
		// The easiest way to load our document from the internet is make use of the URLConnection class.
		URLConnection webClient = url.openConnection();

		// Download the bytes from the location referenced by the URL.
		InputStream inputStream = webClient.getInputStream();

		// Convert the input stream to a byte array.
		int pos;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((pos = inputStream.read()) != -1) bos.write(pos);

		byte[] dataBytes = bos.toByteArray();

		// Wrap the bytes representing the document in memory into a stream object.
		ByteArrayInputStream byteStream = new ByteArrayInputStream(dataBytes);

		// The baseUri property should be set to ensure any relative img paths are retrieved correctly.
		
//		String html = "<html style=\"margin:0;padding:0;\"><head></head><body style=\"margin:0;padding:0;\"></body></html>";
		
		LoadOptions options = new LoadOptions(LoadFormat.HTML, "", url.getPath());
		
		// Load the HTML document from stream and pass the LoadOptions object.
		Document doc = new Document(byteStream, options);
		
		DocumentBuilder builder = new DocumentBuilder(doc);

		PageSetup ps = builder.getPageSetup();
		ps.setPaperSize(PaperSize.LEGAL);
		ps.setOrientation(Orientation.PORTRAIT);
		
		if(requestBean.getTemplateCD().equals("int13_a")) {
			ps.setTopMargin(0);
			ps.setBottomMargin(0);

			ps.setLeftMargin(0);
			ps.setRightMargin(0);
		} else {
			ps.setTopMargin(20);
			ps.setBottomMargin(20);
			
			ps.setLeftMargin(30);
			ps.setRightMargin(30);
		}
		
		ps.setHeaderDistance(0);
		ps.setFooterDistance(0);
		
		
//		ps.setTopMargin(ConvertUtil.inchToPoint(0));
//		ps.setBottomMargin(ConvertUtil.inchToPoint(0));
//		ps.setLeftMargin(ConvertUtil.inchToPoint(0));
//		ps.setHeaderDistance(ConvertUtil.inchToPoint(0.2));
//		ps.setFooterDistance(ConvertUtil.inchToPoint(0.2));
		
//		builder.writeln("Hello world.");
//		builder.setDocument(doc);2
		builder.getDocument().save(storagePath+"/"+fileName);
		
		// Save the document to disk.
		// The extension of the filename can be changed to save the document into other formats. e.g PDF, DOCX, ODT, RTF.
		
//		doc.save(storagePath+"/"+fileName);
		
		ResponseBean response = new ResponseBean();
		response.setMessage("success");
		
		return response;
	} 
}