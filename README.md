# Spring Boot REST API – User Task Manager

This is a simple RESTful API built with Spring Boot that allows you to manage tasks for multiple users. It's ideal as a learning project or a starting point for more advanced back-end applications.

## 🚀 Features

- Create tasks associated with a specific user
- List all tasks for a user
- Delete all tasks for a user
- Uses in-memory storage (`Map<Integer, List<Task>>`)
- JSON request/response with automatic serialization via Jackson

## 🛠 Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Jackson (JSON mapper)
- Maven

---

## 📦 Installation

1. **Clone the repository:**

```bash
git clone https://github.com/your-username/springboot-rest-api.git
cd springboot-rest-api
```

2. **Build the project:**

```bash
./mvnw clean install
```

3. **Run the application:**

```bash
./mvnw spring-boot:run
```

The API will be available at:  
👉 `http://localhost:8080`

---

## 📘 API Endpoints

### 🔹 Create a Task

`POST /users/{userId}/tasks`

**Request Body:**
```json
{
  "description": "Learn Spring Boot",
  "done": false
}
```

**Response:**
```json
{
  "description": "Learn Spring Boot",
  "done": false,
  "userId": 1,
  "userName": "Usuário 1"
}
```

---

### 🔹 Get Tasks by User

`GET /users/{userId}/tasks`

**Response:**
```json
[
  {
    "description": "Learn Spring Boot",
    "done": false,
    "userId": 1,
    "userName": "Usuário 1"
  }
]
```

---

### 🔹 Delete All Tasks for User

`DELETE /users/{userId}/tasks`

**Response:** `204 No Content`

---

## 🧪 Testing

You can test the API using tools like:

- [Postman](https://www.postman.com/)
- [HTTPie](https://httpie.io/)
- `curl`

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 👨‍💻 Author

Developed by [Mitalo Ammon](https://github.com/seu-usuario-aqui)