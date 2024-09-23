# Usar uma imagem base do JDK
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o JAR da aplicação para o diretório de trabalho
COPY target/catfact-1.0-SNAPSHOT.jar /app/catfact.jar

# Expor a porta 8080
EXPOSE 8093

# Comando para iniciar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "catfact.jar"]

# Comando para construir a imagem
# docker build -t catfact .
# Comando para executar o contêiner
# docker run -p 8093:8093 catfact
