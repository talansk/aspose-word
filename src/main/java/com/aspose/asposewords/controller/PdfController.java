package com.aspose.asposewords.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspose.pdf.Document;

@RestController
public class PdfController {

	@PostMapping("/pdf")
	public String updatePDF() throws Exception {
			
		Document doc = new Document("/home/yogendra/Downloads/resume_blue_modified.pdf");
		
		int pageCount = doc.getPages().size();
		
		if(pageCount > 0) {
			doc.getPages().delete(pageCount);
			
			doc.save("/home/yogendra/Downloads/resume_blue_modified_new.pdf");
		}
		
		return "success";
	}
}
