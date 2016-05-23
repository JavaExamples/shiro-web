package demo.shiro.tool;

import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    private static final Properties config = new Properties();

    static {
        try {
            config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        }
    }

    public static String getProp(String key) {
        String prop = "";
        if (config.containsKey(key)) {
            prop = config.getProperty(key);
        }
        return prop;
    }
}
