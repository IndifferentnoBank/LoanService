# Используем официальный образ OpenJDK для Kotlin
FROM openjdk:17-jdk-slim AS build

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /loan-service

# Копируем файлы сборки Gradle
COPY gradlew .
COPY gradle gradle
COPY settings.gradle.kts .
COPY build.gradle.kts .

# Копируем все модули проекта
COPY app app
COPY buildSrc buildSrc
COPY data data
COPY domain domain

# Делаем Gradle-скрипт исполняемым и собираем проект
RUN chmod +x gradlew && ./gradlew build -x test

# Второй этап: запуск приложения
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /loan-service

# Копируем собранный JAR-файл из предыдущего этапа
COPY --from=build /loan-service/build/libs/loan-service.jar app.jar

# Указываем порт, на котором запускается приложение
EXPOSE 8080

# Запускаем приложение
CMD ["java", "-jar", "app.jar"]