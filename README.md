# Liferay 6.2 User Creator (Standalone)

This small Java console application writes directly to the Liferay database to create a new user without using any Liferay portal APIs or SDK dependencies. It uses plain JDBC to update the `Counter`, `User_`, and `Contact_` tables.

## Build

The project uses Gradle. With Gradle available locally:

```bash
gradle build
```

The assembled JAR will be in `build/libs/`.

## Run

Pass user details, database connection information, and optional metadata as `--key=value` pairs. Required parameters are marked below.

```bash
java -jar build/libs/liferay-user-service.jar \
  --dbUrl=jdbc:mysql://localhost:3306/lportal \
  --dbUser=liferay \
  --dbPassword=secret \
  --companyId=20116 \
  --email=new.user@example.com \
  --firstName=New \
  --lastName=User \
  --userPassword=changeit \
  --screenName=newuser \
  --jobTitle=Developer
```

The app increments the `Counter` table to reserve identifiers, inserts a row into `User_`, and then adds the corresponding `Contact_` entry. It defaults the password to a SHA-256 hash, marks the account as approved, and fills optional metadata with safe defaults.

## Notes

- The `companyId` must match values from your target portal. `Counter`, `User_`, and `Contact_` schema requirements can vary by Liferay release; adjust the SQL if your deployment differs.
- Birth date defaults to 1 January 1990. Override with `--birthdayMonth`, `--birthdayDay`, and `--birthdayYear` if needed.
- The application intentionally avoids any Liferay libraries or helpers; it relies on standard JDBC and SQL. Include your database driver in the runtime classpath if different from MySQL.
