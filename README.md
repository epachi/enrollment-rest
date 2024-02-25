
# APIs supported by StudentController:
1. Add a new Student:
   POST /api/v1/students

2. Get all Students:
   GET /api/v1/students

3. Get a Student by Id:
   GET /api/v1/students/{id}

4. Update a Student:
   PUT /api/v1/students/{id}

5. Enroll a Student to a Course:
   POST /api/v1/students/enroll

6. Unenroll a Student to a Course:
   POST /api/v1/students/unenroll

7. Delete a Student by Id:
   DELETE /api/v1/students/{id}


# URLs
* API docs: http://localhost:8080/enrollmentrest/api/api-docs
* Swagger UI: http://localhost:8080/enrollmentrest/api/swagger-ui/index.html
* Actuator URL: http://localhost:8080/enrollmentrest/api/monitoring
* H2 console: http://localhost:8080/h2-console
                - User Name/Password: sa/password
                - JDBC url=jdbc:h2:mem:testdb


# Test Data
* Course test data is static and can be found in src/main/resources/data.sql
* Test data for Student and the association table (student_course) is created dynamically and can be found in the following Postman Collection


# Postman Collection
* Student.postman_collection_2.1
