FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY build/libs/*.jar app.jar
COPY assets/ ./assets/ # 이미지 파일 복사

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
