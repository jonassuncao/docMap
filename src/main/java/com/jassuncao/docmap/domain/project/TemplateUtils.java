package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.infra.InternalException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 16/09/2021
 */
public abstract class TemplateUtils {

    public static String processFile(String fileName, Map<String, Object> params) {
        final String entity = TemplateUtils.getTemplateFile(fileName);
        try {
            Template template = new Template(fileName, entity, cfg());
            try (StringWriter writer = new StringWriter()) {
                template.process(params, writer);
                return writer.toString();
            }
        } catch (TemplateException | IOException ex) {
            throw new InternalException(ex);
        }
    }

    public static String getTemplateFile(String path) {
        final Map<String, String> templates = new ConcurrentHashMap<>();
        return templates.computeIfAbsent(path, TemplateUtils::loadFile);
    }

    private static Configuration cfg() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        cfg.setLogTemplateExceptions(false);
        return cfg;
    }

    private static String loadFile(String path) {
        try {
            try (InputStream stream = safeFile(path)) {
                return IOUtils.toString(stream, StandardCharsets.UTF_8);
            }
        } catch (IOException ex) {
            throw new InternalException(ex);
        }
    }

    private static InputStream safeFile(String value) throws IOException {
        String path = StringUtils.prependIfMissing(value, "/templates/");
        path = StringUtils.appendIfMissing(path, ".java");
        ClassPathResource resource = new ClassPathResource(path, TemplateUtils.class);
        return resource.getInputStream();
    }
}
