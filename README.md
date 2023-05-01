# Sample learning SpringBoot Kotlin project

Following the [Spring Boot Tutorial](https://www.youtube.com/watch?v=1D1iL824ssk&list=PL6gx4Cwl9DGDPsneZWaOFg0H2wsundyGr&index=4) to learn Spring Boot with Kotlin.

Versions used here:
- Spring Boot 3.1.0
- Kotlin 1.8
- Gradle 7.6.1
- Java 17

### Architecture

- see [Architecture](./architecture.drawio)

### Run 

- **As jar**

    ```shell
    ./gradlew build
    java -jar build/libs/thenewboston-tutorial-1.0.jar
    ```

- **With Gradle**

    ```shell
    ./gradlew bootRun
    ```
  
- **With Docker**

    ```shell
    docker build -t thenewboston-tutorial .
    docker run -p 9000:8080 thenewboston-tutorial
    ```
  
- **Letting gradle create a jar and booting it**

    ```shell
    ./gradlew bootJar
    ```
  
- **Letting gradle create a jar and booting it with Docker**

    New feature in SpringBoot, it automatically creates a Docker image for you. You can see it in the build log.
    
    ```shell
    ./gradlew bootBuildImage
    docker run -p 9000:8080 <IMAGE_NAME>
    ```

## Additional learning resources
- [Kotlin Learning Resources](https://kotlinlang.org/docs/learning-materials-overview.html)