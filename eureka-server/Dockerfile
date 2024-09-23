# Usar uma imagem base do JDK
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o JAR da aplicação para o diretório de trabalho
COPY target/eureka-server-1.0-SNAPSHOT.jar /app/eureka-server.jar

# Expor a porta 8761
EXPOSE 8761

# Comando para iniciar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]

# Comando para construir a imagem
# docker build -t eureka-server .
# Comando para executar o contêiner
# docker run -p 8761:8761 eureka-server
