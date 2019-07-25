# File Upload Practice

A NuxtJS/VueJS Application to upload files and gather metadata. Uses Spring Boot + RESTful Services.

## Backend Usage
### Configure

1. Set your preferred log file location at application.yml
2. Set your preferred file upload directory in application.yml

### Run

- Compile your File Upload Practice Backend application in your preferred IDE (Netbeans, IntelliJ, Eclipse, etc.). 
- Once you have successfully build your application. You can run the JAR file created at your target folder using the commands indicated below.
- By default, backend module runs at port 9007 (http://localhost:9007/)

```sh
cd <install_directory>/fileUploadPractice/fileUploadPractice-backend/target
java -jar fileUploadPractice-backend-0.0.1-SNAPSHOT.jar
```

### Endpoints

- POST http://localhost:9007/file/upload
- GET http://localhost:9007/file/list
- DELETE http://localhost:9007/file/reset

## Frontend Usage
### Run

```sh
cd <install_directory>/fileUploadPractice/fileUploadPractice-frontend
npm install
npm run dev
```

By default, frontend module runs at port 3000 (http://localhost:3000)
