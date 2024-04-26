FROM jdk:8
VOLUME /tmp
ENV IMG_PATH/img
EXPOSE 8080
RUN mkdir -p /img
ADD ./target/SpringUsoSesionesUsuario-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java","-war","/app.war"]



