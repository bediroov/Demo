# Use OpenJDK 21 as the base image
FROM openjdk:21-jdk-slim

# Tətbiqin JAR faylını konteynerə kopyalayın
COPY build/libs/in-nakhchivan-backend-0.0.1-SNAPSHOT.jar in-nakhchivan-backend.jar

# Portu açın (tətbiq burada işləyəcək)
EXPOSE 8081

# JAR faylını işə salın
ENTRYPOINT ["java", "-jar", "in-nakhchivan-backend.jar"]