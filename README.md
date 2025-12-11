# Liferay 6.2 User Creator (Standalone)

This small Java console application calls the Liferay 6.2 JSON web service endpoint directly to create a new user without using any Liferay SDK or portal dependencies. It uses only the standard Java HTTP client (no external libraries).

## Build

The project uses Gradle. With Gradle available locally:

```bash
gradle build
```

The assembled JAR will be in `build/libs/`.

## Run

Pass user details and admin credentials as `--key=value` pairs. Required parameters are marked below.

```bash
java -jar build/libs/liferay-user-service.jar \
  --serverUrl=http://localhost:8080 \
  --adminUser=test@liferay.com \
  --adminPassword=admin \
  --companyId=20116 \
  --email=new.user@example.com \
  --firstName=New \
  --lastName=User \
  --userPassword=changeit \
  --jobTitle=Developer \
  --groupIds=20121,20122 \
  --sendEmail=false
```

The app posts to `/api/jsonws/user/add-user` using HTTP basic authentication. You can provide optional fields such as `screenName`, `jobTitle`, `locale`, or membership ids (`groupIds`, `organizationIds`, `roleIds`, `userGroupIds`). Omit `screenName` to let Liferay auto-generate it; `sendEmail` controls whether the welcome email is sent.

## Notes

- The `companyId` and membership ids must match values from your target portal.
- Birth date defaults to 1 January 1990. Override with `--birthdayMonth`, `--birthdayDay`, and `--birthdayYear` if needed.
- The application intentionally avoids any Liferay libraries or helpers; it builds the form body manually and sends it over HTTPS/HTTP.
