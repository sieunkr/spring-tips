package com.example.demo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class FreemarkerTemplateEngine implements TemplateEngine {

    private final Configuration config;

    public FreemarkerTemplateEngine(Configuration config) {
        this.config = config;
    }
    
    @Override
    public String template(String templateName, Object data) {
        try {
            config.setDirectoryForTemplateLoading(new ClassPathResource("/templates").getFile());
            return templating(config.getTemplate(templateName, "UTF-8"), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String templating(Template template, Object data){

        try {
            StringWriter writer = new StringWriter();
            Map<String, Object> map = new HashMap<>();

            map.put("id","1");
            map.put("data",data);
            template.process(map, writer);
            return writer.toString();

        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
