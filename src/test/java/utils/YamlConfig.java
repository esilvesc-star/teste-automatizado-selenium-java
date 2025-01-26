package utils;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class YamlConfig {

    @SuppressWarnings("unchecked")
    public static String readConfig(String doc, String parameter) {
        Yaml yaml = new Yaml();
        Map<String, Object> ymlConfig = null;
        try {
            ymlConfig = yaml.load(new FileInputStream("./src/test/resources/"+doc+".yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> configPath = Arrays.asList(parameter.split("\\."));
        Object config = ymlConfig.get(configPath.get(0));
        for (int i = 1; i < configPath.size(); i++) {
            config = ((Map<String, Object>) config).get(configPath.get(i));
        }
        if (Objects.isNull(config)) config = "";
        return String.valueOf(config);
    }
}