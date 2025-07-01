# 🍎 Fruit REST API - Sprint 4 Task 2 Level 1

This is a Spring Boot CRUD REST API to manage fruit entries. The application uses:
- Spring Boot
- Spring Data JPA
- H2 in-memory database
- Bean Validation
- Global Exception Handling
- JUnit and MockMvc for testing

---

## 🚀 Endpoints

### ➕ Add Fruit
**POST** `/fruit/add`

**Request Body:**
```json
{
  "name": "Banana",
  "kilogramQuantity": 10
}
```

**Success Response:**
- Code: `201 CREATED`
- Body: Saved fruit object

---

### 📋 Get All Fruits
**GET** `/fruit/getAll`

**Response:**
- Code: `200 OK`
- Body: List of fruits

---

### 🔍 Get Fruit by ID
**GET** `/fruit/getOne/{id}`

**Response:**
- Code: `200 OK`
- Body: Fruit object

---

### ✏️ Update Fruit
**PUT** `/fruit/update/{id}`

**Request Body:**
```json
{
  "name": "Pear",
  "kilogramQuantity": 15
}
```

**Success Response:**
- Code: `200 OK`
- Body: Updated fruit object

---

### 🗑️ Delete Fruit
**DELETE** `/fruit/delete/{id}`

**Success Response:**
- Code: `200 OK`
- Body: `"Fruit with id {id} deleted"`

---

## 🛑 Error Handling

This API implements a global exception handler to manage errors gracefully and return meaningful HTTP responses.

### Common Error Responses

| HTTP Status | Description                       | Example Message                            |
|-------------|-----------------------------------|--------------------------------------------|
| `400`       | Validation or bad request error   | `{"name": "Name can't be empty"}`          |
| `404`       | Resource not found                | `{"message": "Fruit with id 99 not found"}`|
| `500`       | Internal server error             | `{"message": "Unexpected error occurred"}` |

### Example Validation Errors

POST `/fruit/add` with invalid body:
```json
{
  "name": "",
  "kilogramQuantity": -5
}
```

Response:
```json
{
  "name": "Name can't be empty",
  "kilogramQuantity": "Kg must be positive"
}
```

---

## ✅ Test Coverage

This application is covered with unit tests using JUnit and MockMvc:

- ✅ Create fruit (valid & invalid)
- ✅ Get fruit by ID (existing & not found)
- ✅ Update fruit
- ✅ Delete fruit
- ✅ Global exception handling

---

## 🧪 H2 Console

Access the H2 web console at:

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

