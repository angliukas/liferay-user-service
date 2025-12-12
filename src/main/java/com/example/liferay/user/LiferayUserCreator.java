package com.example.liferay.user;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LiferayUserCreator {

    public static void main(String[] args) {
        Map<String, String> options = parseArgs(args);

        if (!hasRequired(options)) {
            printUsage();
            System.exit(1);
        }

        try {
            String response = createUser(options);
            System.out.println("User creation response:\n" + response);
        } catch (IOException | InterruptedException e) {
            System.err.println("Failed to create user: " + e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        }
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
        return options.containsKey("serverUrl")
                && options.containsKey("adminUser")
                && options.containsKey("adminPassword")
                && options.containsKey("companyId")
                && options.containsKey("email")
                && options.containsKey("firstName")
                && options.containsKey("lastName")
                && options.containsKey("userPassword");
    }

    private static void printUsage() {
        System.out.println("Usage: java -jar liferay-user-service.jar \\");
        System.out.println("  --serverUrl=<http(s)://host:port> \\");
        System.out.println("  --adminUser=<portal-admin-login> \\");
        System.out.println("  --adminPassword=<portal-admin-password> \\");
        System.out.println("  --companyId=<company-id> \\");
        System.out.println("  --email=<user-email> \\");
        System.out.println("  --firstName=<first-name> \\");
        System.out.println("  --lastName=<last-name> \\");
        System.out.println("  --userPassword=<password> [optional parameters]\n");
        System.out.println("Optional parameters:\n" +
                "  --screenName=<desired-screen-name> (leave empty to let the portal generate it)\n" +
                "  --jobTitle=<job-title>\n" +
                "  --locale=<e.g. en_US>\n" +
                "  --male=<true|false>\n" +
                "  --birthdayMonth=<0-11>\n" +
                "  --birthdayDay=<1-31>\n" +
                "  --birthdayYear=<e.g. 1990>\n" +
                "  --groupIds=<comma-separated-ids>\n" +
                "  --organizationIds=<comma-separated-ids>\n" +
                "  --roleIds=<comma-separated-ids>\n" +
                "  --userGroupIds=<comma-separated-ids>\n" +
                "  --sendEmail=<true|false>\n" +
                "  --middleName=<middle-name>");
    }

    private static String createUser(Map<String, String> options) throws IOException, InterruptedException {
        String serverUrl = options.get("serverUrl");
        if (serverUrl.endsWith("/")) {
            serverUrl = serverUrl.substring(0, serverUrl.length() - 1);
        }

        String targetUrl = serverUrl + "/api/jsonws/user/add-user";

        Map<String, String> body = new LinkedHashMap<>();
        String screenName = options.getOrDefault("screenName", "");
        boolean autoScreenName = screenName.isBlank();

        body.put("companyId", options.get("companyId"));
        body.put("autoPassword", "false");
        body.put("password1", options.get("userPassword"));
        body.put("password2", options.get("userPassword"));
        body.put("autoScreenName", Boolean.toString(autoScreenName));
        body.put("screenName", screenName);
        body.put("emailAddress", options.get("email"));
        body.put("facebookId", "0");
        body.put("openId", "");
        body.put("locale", options.getOrDefault("locale", "en_US"));
        body.put("firstName", options.get("firstName"));
        body.put("middleName", options.getOrDefault("middleName", ""));
        body.put("lastName", options.get("lastName"));
        body.put("prefixId", "0");
        body.put("suffixId", "0");
        body.put("male", options.getOrDefault("male", "true"));
        body.put("birthdayMonth", options.getOrDefault("birthdayMonth", "0"));
        body.put("birthdayDay", options.getOrDefault("birthdayDay", "1"));
        body.put("birthdayYear", options.getOrDefault("birthdayYear", "1990"));
        body.put("jobTitle", options.getOrDefault("jobTitle", ""));
        body.put("groupIds", options.getOrDefault("groupIds", ""));
        body.put("organizationIds", options.getOrDefault("organizationIds", ""));
        body.put("roleIds", options.getOrDefault("roleIds", ""));
        body.put("userGroupIds", options.getOrDefault("userGroupIds", ""));
        body.put("sendEmail", options.getOrDefault("sendEmail", "false"));

        String credentials = options.get("adminUser") + ":" + options.get("adminPassword");
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(targetUrl))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", authHeader)
                .POST(HttpRequest.BodyPublishers.ofString(toFormUrlEncoded(body)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static String toFormUrlEncoded(Map<String, String> body) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : body.entrySet()) {
            if (builder.length() > 0) {
                builder.append('&');
            }
            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            builder.append('=');
            builder.append(URLEncoder.encode(Objects.toString(entry.getValue(), ""), StandardCharsets.UTF_8));
        }
        return builder.toString();
    }
}
