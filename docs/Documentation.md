The 12-factor app methodology is a set of best practices for building modern web applications. Here is a detailed explanation of how each factor is covered in this project:

1. **Codebase**: The project has a single codebase tracked in version control (GitHub) and can be deployed to multiple environments. This ensures consistency and easy collaboration among team members.

2. **Dependencies**: Dependencies are managed using Maven, which is specified in the `pom.xml` file. This allows for easy management and versioning of libraries and frameworks used in the project.

3. **Config**: Configuration is externalized using environment variables, as seen in the `compose.yaml` file for database connection details. This allows for different configurations in different environments without changing the code.

4. **Backing services**: The database is treated as an attached resource, configured via environment variables and managed in the `compose.yaml` file. This decouples the application from the database, making it easier to swap out or scale the database independently.

5. **Build, release, run**: The project uses Docker for containerization, ensuring consistent environments across build, release, and run stages. This helps in maintaining a consistent deployment process and reduces the chances of environment-specific bugs.

6. **Processes**: The application is stateless and any data that needs to persist is stored in the database. This makes the application more scalable and easier to manage.

7. **Port binding**: The application self-contains and exposes services via port binding, as specified in the `compose.yaml` file (`8080:8080`). This allows the application to be easily accessible and configurable.

8. **Concurrency**: The application can scale out by running multiple instances of the service, managed by Docker. This ensures that the application can handle increased load by adding more instances.

9. **Disposability**: The application is designed to start up and shut down quickly, as managed by Docker containers. This improves the resilience and flexibility of the application, allowing it to handle changes in demand more effectively.

10. **Dev/prod parity**: The development, staging, and production environments are as similar as possible, facilitated by Docker. This reduces the chances of environment-specific issues and ensures that the application behaves consistently across different stages.

11. **Logs**: The application uses logging libraries (e.g., SLF4J) to generate logs, which can be aggregated and monitored. This helps in tracking the application's behavior and diagnosing issues.

12. **Admin processes**: Administrative tasks can be run as one-off processes in the same environment as the application, facilitated by Docker. This ensures that administrative tasks are performed in a consistent and controlled manner.

This project adheres to the 12-factor app methodology, ensuring it is scalable, maintainable, and portable across different environments.