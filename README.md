# Choose my snooze
___
This project is a fake one focused on improving DevOps skills and multi-tenancy. The project has:
- A main app database
- An API
- A service handling authentication (Keycloak)
  - that also has a backing database

Ideally, I'd like to add in:
- SMTP server for sending mail
- A front end to drive the API
- Prometheus instance for metrics
- Redis for caching stuff (not sure what needs caching yet)

The idea is to build deployment pipelines and upload docker images to a container registry (AWS ECR) and use these in deployment builds to whatever infrastructure I want to use (not wholly settled yet, but will likely be AWS EC2 instances)

## Usage
The project uses Gradle, so using the gradle wrapper will allow you to run the project's API. The project also requires a .env file in the root of the project in order to run. A template for this and an example are included in the root.

Before running the project, ensure that this has been correctly filled in. When it has, run `docker compose up` in the root of the project to bring up the backing services.

If you ever want to bring up a specific service, use the following:

```bash
$ docker compose [SERVICE_NAME] up -d
```
This will also allow you to continue using the shell used to initiate the command. 

Once the services have all come up, use the gradle wrapper to bring up the main API.

```bash
$ ./gradlew bootRun
```

The API uses Liquibase and a custom configuration to produce versioned migrations. To apply all the changelogs (which are found in resources/db/changelogs) run:
```bash
$ ./gradlew update
```

If this does not work, confirm that your app database is up and that the connection to it is possible. It may be that the .env is not correct. This will be picked up automatically by docker when ran if saved correctly. You can confirm this by running:

```bash
$ docker compose config
```

## Development
The migration system will auto generate migrations based on the entities present in the project. If you want to handwrite a migration, please use the command:

```bash
$ ./gradlew diffChangeLog
```

first to generate the migration with the correct format then change it to your needs. The root migration file includes all the migrations inside the changelog directory automatically. There is no need to do anything else aside from running the migration.

To rollback a migration, run the command:
```bash
$ ./gradlew rollbackCount -PliquibaseCommandValue=1 
```

This will roll back the last migration ran against the database. You can roll back as many migrations as you want this way by changing the command value.

