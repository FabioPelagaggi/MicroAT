# Usar uma imagem base do JDK
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o JAR da aplicação para o diretório de trabalho
COPY target/veiculos-1.0-SNAPSHOT.jar /app/veiculos.jar

# Expor a porta 8090
EXPOSE 8090

# Comando para iniciar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "veiculos.jar"]