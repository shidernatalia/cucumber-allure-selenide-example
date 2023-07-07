package helpers;
/*
 * application.conf @ file:/home/natalia/IdeaProjects/CucumberSelenideEx/target/test-classes/application.conf: 5:
 * Expecting close brace } or a comma, got '!' (Reserved character '!' is not allowed outside quotes) (if you intended '!' (Reserved character '!' is not allowed outside quotes) to be part of a key or string value, try enclosing the key or value in double quotes, or you may be able to rename the file .properties rather than .conf)*/

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("application.conf");
    }

    String URL = readConfig().getString("url");
    String USER_1_LOGIN = readConfig().getString("usersParams.user1.login");
    String USER_1_PASSWORD = readConfig().getString("usersParams.user1.password");
    Boolean IS_USER_1_ADMIN = readConfig().getBoolean("usersParams.user1.isAdmin");
    Boolean IS_USER_2_ADMIN = readConfig().getBoolean("usersParams.user2.isAdmin");
}