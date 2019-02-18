package uk.co.vertigomoon.htmltopdf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.vertigomoon.htmltopdf.model.RenderTemplate;
import uk.co.vertigomoon.htmltopdf.service.PdfService;
import uk.co.vertigomoon.htmltopdf.service.TemplateService;

@RestController
public class PdfController {

    private final PdfService pdfService;
    private final TemplateService templateService;

    @Autowired
    public PdfController(
            PdfService pdfService,
            TemplateService templateService) {
        this.pdfService = pdfService;
        this.templateService = templateService;
    }

    @PostMapping(
            value = "/create-from-template",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> createPdfFromTemplate(
            @RequestBody RenderTemplate template
    ) {
        String html = templateService.resolveTemplate(
                template.getTemplate(),
                template.getContent());
        return ResponseEntity.ok(new ByteArrayResource(pdfService.convertHtmlToPdf(html)));
    }

    @PostMapping(
            value = "/create-from-html",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<ByteArrayResource> createPdfFromHtml(@RequestBody String html) {
        return ResponseEntity.ok(new ByteArrayResource(pdfService.convertHtmlToPdf(html)));
    }

    @PostMapping(
            value = "/create-html-from-template",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<ByteArrayResource> createHtmlFromTemplate(
            @RequestBody RenderTemplate template
    ) {
        return ResponseEntity.ok(new ByteArrayResource(
                templateService.resolveTemplate(template.getTemplate(),
                        template.getContent()).getBytes()));
    }

}
