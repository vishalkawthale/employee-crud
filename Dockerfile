FROM openjdk:21
EXPOSE 8080
ADD target/employee-crud.jar employee-crud.jar
ENTRYPOINT ["java", "-jar", "employee-crud.jar"]