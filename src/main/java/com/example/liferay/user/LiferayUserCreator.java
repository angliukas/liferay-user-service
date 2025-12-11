package com.example.liferay.user;

import com.example.liferay.user.service.LiferayUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
public class LiferayUserCreator implements CommandLineRunner {

    private final LiferayUserService liferayUserService;

    public LiferayUserCreator(LiferayUserService liferayUserService) {
        this.liferayUserService = liferayUserService;
    }

    public static void main(String[] args) {
        Map<String, String> options = parseArgs(args);

        if (!hasRequired(options)) {
            printUsage();
            System.exit(1);
        }

        SpringApplication app = new SpringApplication(LiferayUserCreator.class);
        app.setDefaultProperties(Map.of(
                "spring.datasource.url", options.get("dbUrl"),
                "spring.datasource.username", options.get("dbUser"),
                "spring.datasource.password", options.get("dbPassword"),
                "spring.jpa.hibernate.ddl-auto", "none"
        ));
        app.run(args);
    }

    @Override
    public void run(String... args) {
        Map<String, String> options = parseArgs(args);
        liferayUserService.createUser(options);
        System.out.println("User created successfully in the database using Spring Data repositories.");
    }

    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> options = new LinkedHashMap<>();
        for (String arg : args) {
            if (!arg.startsWith("--") || !arg.contains("=")) {
                continue;
            }
            int equalsIndex = arg.indexOf('=');
            String key = arg.substring(2, equalsIndex).trim();
            String value = arg.substring(equalsIndex + 1).trim();
            options.put(key, value);
        }
        return options;
    }

    private static boolean hasRequired(Map<String, String> options) {
        return options.containsKey("dbUrl")
                && options.containsKey("dbUser")
                && options.containsKey("dbPassword")
                && options.containsKey("companyId")
                && options.containsKey("email")
                && options.containsKey("firstName")
                && options.containsKey("lastName")
                && options.containsKey("userPassword");
    }

    private static void printUsage() {
        System.out.println("Usage: java -jar liferay-user-service.jar \\");
        System.out.println("  --dbUrl=<jdbc-url> \\");
        System.out.println("  --dbUser=<db-username> \\");
        System.out.println("  --dbPassword=<db-password> \\");
        System.out.println("  --companyId=<company-id> \\");
        System.out.println("  --email=<user-email> \\");
        System.out.println("  --firstName=<first-name> \\");
        System.out.println("  --lastName=<last-name> \\");
        System.out.println("  --userPassword=<password> [optional parameters]\n");
        System.out.println("Optional parameters:\n" +
                "  --screenName=<desired-screen-name> (required if your DB enforces NOT NULL)\n" +
                "  --jobTitle=<job-title>\n" +
                "  --locale=<e.g. en_US>\n" +
                "  --male=<true|false>\n" +
                "  --birthdayMonth=<1-12>\n" +
                "  --birthdayDay=<1-31>\n" +
                "  --birthdayYear=<e.g. 1990>\n" +
                "  --middleName=<middle-name>\n" +
                "  --classNameUser=<com.liferay.portal.model.User|com.liferay.portal.kernel.model.User>\n" +
                "  --classNameContact=<com.liferay.portal.model.Contact|com.liferay.portal.kernel.model.Contact>");
    }
}
