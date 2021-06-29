package nz.govt.mbie;

import org.concordion.cubano.config.Config;
import org.concordion.cubano.config.PropertyLoader;
import org.concordion.cubano.driver.web.config.WebDriverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);
    private final PropertyLoader propertyLoader;

    private String baseUrl;
    private int defaultTimeout;


    private static class Holder {
        static final AppConfig INSTANCE = new AppConfig();
    }

    public static AppConfig getInstance() {
        return Holder.INSTANCE;
    }

    private AppConfig() {
        propertyLoader = Config.getInstance().getPropertyLoader();
        loadProperties();
    }

    public void logSettings() {
        LOGGER.info("Environment:        " + Config.getInstance().getEnvironment());
        LOGGER.info("url:                " + baseUrl);

        WebDriverConfig webDriverConfig = WebDriverConfig.getInstance();
        LOGGER.info("Browser:            " + webDriverConfig.getBrowserProvider());

        if (!webDriverConfig.getBrowserDimension().isEmpty()) {
            LOGGER.info("browserSize:        " + webDriverConfig.getBrowserDimension());
        }

        LOGGER.info("Default Timeout:        " + getDefaultTimeout());
    }

    private void loadProperties() {
        baseUrl = propertyLoader.getProperty("baseUrl");
        defaultTimeout = propertyLoader.getPropertyAsInteger("webdriver.defaultTimeout", "10");

    }

    // Application properties
    public String getBaseUrl() {
        return baseUrl;
    }
  
    public int getDefaultTimeout() {
        return defaultTimeout;
    }

}
