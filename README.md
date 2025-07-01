# S04T02N01 - Fruiteria API

Spring Boot RESTful CRUD API to manage fruits, built as a backend learning exercise.  
Includes input validation, global exception handling, unit testing with `MockMvc`, and in-memory persistence with H2 database.

---

## 🧱 Technologies Used

- Java 17+
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- H2 Database
- Maven or Gradle
- JUnit + MockMvc

---

## 📦 Project Structure

```
cat.itacademy.s04.t02.n01
├── controllers           # FruitController: REST API endpoints
├── dto                  # FruitRequest: input validation model
├── exception            # ResourceNotFoundException + GlobalExceptionHandler
├── model                # FruitModel: JPA entity
├── repository           # FruitRepository: JPA interface
├── services             # FruitService: business logic
```

---

## 🚀 Running the Application

1. Clone the repository
2. Open it in IntelliJ IDEA or any compatible IDE
3. Run the `S04T02N01Application.java` class
4. Use Postman or browser to test the endpoints

---

## 🧪 Available Endpoints

| Method | Endpoint              | Description                     |
|--------|------------------------|---------------------------------|
| POST   | `/fruit/add`           | Create a new fruit              |
| GET    | `/fruit/getAll`        | Retrieve all fruits             |
| GET    | `/fruit/getOne/{id}`   | Retrieve a fruit by ID          |
| PUT    | `/fruit/update/{id}`   | Update a fruit by ID            |
| DELETE | `/fruit/delete/{id}`   | Delete a fruit by ID            |

---

## 📄 JSON Example (POST / PUT)

```json
{
  "name": "Banana",
  "kilogramQuantity": 10
}
```

---

## ✅ Input Validation

- `name`: must not be blank (`@NotBlank`)
- `kilogramQuantity`: must be a positive number (`@Positive`)

---

## 🧠 Error Handling

Input and runtime exceptions are handled globally with `@ControllerAdvice`.

**Example response for 400 Bad Request:**

```json
{
  "name": "Name can't be empty",
  "kilogramQuantity": "Kg must be positive"
}
```

---

## 🧪 Testing

- API tests implemented using `MockMvc`
- Includes tests for:
    - Success cases (`201 Created`)
    - Validation errors (`400 Bad Request`)
    - Not found errors (`404 Not Found`)

---

## 💾 H2 Database Access

- Console URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

---

## 📌 Notes

- The application uses an in-memory H2 database: all data is cleared on restart.
- Great exercise to practice MVC architecture, validation, REST APIs, and testing.

---

## 🧑‍💻 Author

Ignasi Subirachs  
Bootcamp Java — IT Academy Barcelona

---

## 🔗 Useful Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [H2 Console Guide](https://www.h2database.com/html/main.html)
- [Java Bean Validation](https://jakarta.ee/specifications/bean-validation/)
