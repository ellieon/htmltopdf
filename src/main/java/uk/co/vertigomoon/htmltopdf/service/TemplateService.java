package uk.co.vertigomoon.htmltopdf.service;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.StringLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {
    public String resolveTemplate(String template, Map<String, Object> values) {
        PebbleEngine engine = new PebbleEngine.Builder().loader(new StringLoader()).build();


        StringWriter writer = new StringWriter();
        try {
            PebbleTemplate pebbleTemplate = engine.getTemplate(template);
            pebbleTemplate.evaluate(writer, values);
        } catch (PebbleException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
        return writer.toString();
    }
}
