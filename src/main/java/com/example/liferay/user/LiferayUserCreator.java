package com.example.liferay.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
            createUser(options);
            System.out.println("User created successfully in the database.");
        } catch (SQLException e) {
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

    private static void createUser(Map<String, String> options) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                options.get("dbUrl"), options.get("dbUser"), options.get("dbPassword"))) {
            connection.setAutoCommit(false);
            try {
                long userId = nextId(connection, options.getOrDefault(
                        "classNameUser", "com.liferay.portal.kernel.model.User"));
                long contactId = nextId(connection, options.getOrDefault(
                        "classNameContact", "com.liferay.portal.kernel.model.Contact"));

                insertUser(connection, options, userId, contactId);
                insertContact(connection, options, userId, contactId);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    private static void insertUser(Connection connection, Map<String, String> options, long userId, long contactId)
            throws SQLException {

        Timestamp now = Timestamp.from(ZonedDateTime.now(ZoneId.of("UTC")).toInstant());
        String screenName = options.getOrDefault("screenName", options.get("email"));
        String passwordHash = sha256(options.get("userPassword"));
        String greeting = "Welcome, " + options.get("firstName") + "!";
        boolean male = Boolean.parseBoolean(options.getOrDefault("male", "true"));

        String sql = "INSERT INTO User_ " +
                "(uuid_, userId, companyId, createDate, modifiedDate, defaultUser, contactId, password_, " +
                "passwordEncrypted, passwordReset, passwordModifiedDate, digest, reminderQueryQuestion, reminderQueryAnswer, " +
                "graceLoginCount, screenName, emailAddress, facebookId, ldapServerId, openId, portraitId, languageId, timeZoneId, " +
                "greeting, comments, firstName, middleName, lastName, jobTitle, status, lockout, lockoutDate, " +
                "agreedToTermsOfUse, emailAddressVerified, statusDate, male) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "");
            ps.setLong(2, userId);
            ps.setLong(3, Long.parseLong(options.get("companyId")));
            ps.setTimestamp(4, now);
            ps.setTimestamp(5, now);
            ps.setBoolean(6, false);
            ps.setLong(7, contactId);
            ps.setString(8, passwordHash);
            ps.setBoolean(9, true);
            ps.setBoolean(10, false);
            ps.setTimestamp(11, now);
            ps.setString(12, null);
            ps.setString(13, "question");
            ps.setString(14, "answer");
            ps.setInt(15, 0);
            ps.setString(16, screenName);
            ps.setString(17, options.get("email"));
            ps.setLong(18, 0);
            ps.setLong(19, 0);
            ps.setString(20, "");
            ps.setLong(21, 0);
            ps.setString(22, options.getOrDefault("locale", "en_US"));
            ps.setString(23, "UTC");
            ps.setString(24, greeting);
            ps.setString(25, "");
            ps.setString(26, options.get("firstName"));
            ps.setString(27, options.getOrDefault("middleName", ""));
            ps.setString(28, options.get("lastName"));
            ps.setString(29, options.getOrDefault("jobTitle", ""));
            ps.setInt(30, 0);
            ps.setBoolean(31, false);
            ps.setTimestamp(32, null);
            ps.setBoolean(33, true);
            ps.setBoolean(34, true);
            ps.setTimestamp(35, now);
            ps.setBoolean(36, male);
            ps.executeUpdate();
        }
    }

    private static void insertContact(Connection connection, Map<String, String> options, long userId, long contactId)
            throws SQLException {
        Timestamp now = Timestamp.from(ZonedDateTime.now(ZoneId.of("UTC")).toInstant());
        LocalDate birthday = LocalDate.of(
                Integer.parseInt(options.getOrDefault("birthdayYear", "1990")),
                Integer.parseInt(options.getOrDefault("birthdayMonth", "1")),
                Integer.parseInt(options.getOrDefault("birthdayDay", "1")));

        long classNameId = findClassNameId(connection, options.getOrDefault(
                "classNameUser", "com.liferay.portal.kernel.model.User"));

        String sql = "INSERT INTO Contact_ " +
                "(uuid_, contactId, companyId, userId, userName, createDate, modifiedDate, classNameId, classPK, accountId, " +
                "parentContactId, emailAddress, firstName, middleName, lastName, prefixId, suffixId, male, birthday, smsSn, aimSn, " +
                "facebookSn, icqSn, jabberSn, msnSn, mySpaceSn, skypeSn, twitterSn, ymSn, employeeStatusId, employeeNumber, jobTitle, " +
                "jobClass, hoursOfOperation) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "");
            ps.setLong(2, contactId);
            ps.setLong(3, Long.parseLong(options.get("companyId")));
            ps.setLong(4, userId);
            ps.setString(5, options.get("firstName"));
            ps.setTimestamp(6, now);
            ps.setTimestamp(7, now);
            ps.setLong(8, classNameId);
            ps.setLong(9, userId);
            ps.setLong(10, 0);
            ps.setLong(11, 0);
            ps.setString(12, options.get("email"));
            ps.setString(13, options.get("firstName"));
            ps.setString(14, options.getOrDefault("middleName", ""));
            ps.setString(15, options.get("lastName"));
            ps.setInt(16, 0);
            ps.setInt(17, 0);
            ps.setBoolean(18, Boolean.parseBoolean(options.getOrDefault("male", "true")));
            ps.setDate(19, Date.valueOf(birthday));
            ps.setString(20, "");
            ps.setString(21, "");
            ps.setString(22, "");
            ps.setString(23, "");
            ps.setString(24, "");
            ps.setString(25, "");
            ps.setString(26, "");
            ps.setString(27, "");
            ps.setString(28, "");
            ps.setString(29, "");
            ps.setString(30, "");
            ps.setString(31, options.getOrDefault("jobTitle", ""));
            ps.setString(32, "");
            ps.setString(33, "");
            ps.setString(34, "");
            ps.executeUpdate();
        }
    }

    private static long nextId(Connection connection, String counterName) throws SQLException {
        String selectSql = "SELECT currentId FROM Counter WHERE name = ? FOR UPDATE";
        String insertSql = "INSERT INTO Counter (name, currentId) VALUES (?, ?)";
        String updateSql = "UPDATE Counter SET currentId = ? WHERE name = ?";

        try (PreparedStatement select = connection.prepareStatement(selectSql)) {
            select.setString(1, counterName);
            try (ResultSet rs = select.executeQuery()) {
                if (rs.next()) {
                    long next = rs.getLong(1) + 1;
                    try (PreparedStatement update = connection.prepareStatement(updateSql)) {
                        update.setLong(1, next);
                        update.setString(2, counterName);
                        update.executeUpdate();
                    }
                    return next;
                }
            }
        }

        long startingValue = 10000L;
        try (PreparedStatement insert = connection.prepareStatement(insertSql)) {
            insert.setString(1, counterName);
            insert.setLong(2, startingValue);
            insert.executeUpdate();
        }
        return startingValue;
    }

    private static long findClassNameId(Connection connection, String className) throws SQLException {
        String query = "SELECT classNameId FROM ClassName_ WHERE value = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, className);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        throw new SQLException("classNameId not found for " + className + " in ClassName_ table");
    }

    private static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}
