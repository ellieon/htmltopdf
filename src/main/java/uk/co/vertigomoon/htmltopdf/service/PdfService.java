package uk.co.vertigomoon.htmltopdf.service;

import com.openhtmltopdf.DOMBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.ByteArrayOutputStream;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;



@Service
public class PdfService {
    public byte[] convertHtmlToPdf(String content) {
        try {
            Jsoup.parse(content);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfRendererBuilder builder = new PdfRendererBuilder();
            
            builder.withW3cDocument(html5ParseDocument(content), "");
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private org.w3c.dom.Document html5ParseDocument(String content) {
        return DOMBuilder.jsoup2DOM(Jsoup.parse(content));
    }
}
