FROM gradle:7.6.1-focal AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM amazoncorretto:17.0.6 AS jdk-runtime
RUN yum install -y unzip
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/ /app/
WORKDIR /app
CMD ["java", "-jar", "thenewboston-tutorial-1.0.jar"]