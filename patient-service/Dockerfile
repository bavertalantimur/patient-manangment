# 1. Aşama: Build (derleme aşaması)
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Çalışma dizini
WORKDIR /app

# Sadece pom.xml'i kopyalayarak bağımlılıkları önceden indir
COPY pom.xml .

# Bağımlılıkları önceden indir (offline cache için)
RUN mvn dependency:go-offline -B

# Kaynak kodları kopyala
COPY src ./src

# Testleri atlayarak build işlemi (isteğe bağlı -DskipTests kaldırılabilir)
RUN mvn clean package -DskipTests

# 2. Aşama: Çalıştırma (sadece jar içeren hafif imaj)
FROM openjdk:21-jdk AS runner

# Uygulama çalışma dizini
WORKDIR /app

# Builder aşamasından çıkan jar dosyasını kopyala
COPY --from=builder /app/target/patient-service-0.0.1-SNAPSHOT.jar ./app.jar

# Uygulamanın çalıştığı port (application.properties ile eşleşmeli)
EXPOSE 4000

# Uygulama giriş noktası
ENTRYPOINT ["java", "-jar", "app.jar"]
