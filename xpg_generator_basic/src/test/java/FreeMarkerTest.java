import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerTest {
    @Test
    public void test () throws IOException {

        // new Configuration object,argument is freemarker version
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // specify path of template file
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        // assign character set UTF-8 for template file
        configuration.setDefaultEncoding("utf-8");

        // new template object for load template file
        Template template = configuration.getTemplate("myweb.html.ftl");

        // cancel "." in number
        template.setNumberFormat("0.#");

        // creat data model
        Map<String,Object> dataModel = new HashMap<>();
        dataModel.put("currentYear",2024);

        // creat List of menuItems
        List<Map<String,Object>> menuItems = new ArrayList<>();
        Map<String,Object> menuItem1 = new HashMap<>(); //Map is a interface
        Map<String,Object> menuItem2 = new HashMap<>();
        menuItem1.put("url","https://codefather.cn");
        menuItem1.put("label","代码教父");
        menuItem2.put("url","https://baidu.cn");
        menuItem2.put("label","代码mama");
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        dataModel.put("menuItems",menuItems);

        try (Writer out = new FileWriter("myweb1.html")) {
            template.process(dataModel,out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }




    }
}
