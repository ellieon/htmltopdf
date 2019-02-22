package uk.co.vertigomoon.htmltopdf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HomeController {

    @Value("${application.github-redirect}")
    private String redirectUrl;


    @GetMapping(value = "/")
    public RedirectView redirectToSource() {
        return new RedirectView(redirectUrl);
    }

}
