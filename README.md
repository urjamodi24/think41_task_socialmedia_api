Here's a well-structured, beginner-friendly `README.md` file for your **Post Likes API** project using Spring Boot and MySQL:

---

````markdown
# 📌 Post Likes API

A simple RESTful API built with Spring Boot and MySQL that allows users to create posts, like/unlike posts, and retrieve analytics such as the number of likes and top liked posts.

---

## 🚀 Features

- 📄 Create new posts
- 👍 Like and 👎 Unlike posts by users
- 🔢 Get like count for each post
- 🏆 Fetch top liked posts
- 🧍 View posts liked by a specific user

---

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot
- **Database:** MySQL
- **Build Tool:** Maven
- **IDE:** IntelliJ IDEA / VS Code

---

## 📦 Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/urjamodi24/think41_task_socialmedia_api.git
cd think41_task_socialmedia_api
````

### 2. Configure Database

Create a MySQL database named `postlikesdb` (or change name in application.properties).

```sql
CREATE DATABASE postlikesdb;
```

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/social_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Build and Run the App

Use Maven:

```bash
mvn clean install
mvn spring-boot:run
```

---

## 🧪 API Endpoints

Base URL: `http://localhost:8080/posts`

| Method | Endpoint                            | Description                        |
| ------ | ----------------------------------- | ---------------------------------- |
| POST   | `/posts`                            | Create a new post                  |
| POST   | `/posts/{postStrId}/like`           | Like a post                        |
| DELETE | `/posts/{postStrId}/like`           | Unlike a post                      |
| GET    | `/posts/{postStrId}/likes`          | Get like count for a post          |
| GET    | `/posts/top?limit=N`                | Get top N liked posts              |
| GET    | `/posts/users/{userId}/liked-posts` | Get posts liked by a specific user |

---

## 📬 Sample Requests (Postman)

### ➕ Create a Post

```http
POST /posts
```

```json
{
  "postStrId": "post123",
  "content": "This is my first post"
}
```

### 👍 Like a Post

```http
POST /posts/post123/like
```

```json
{
  "user_id_str": "user001"
}
```

### 👎 Unlike a Post

```http
DELETE /posts/post123/like
```

```json
{
  "user_id_str": "user001"
}
```

---

## 🧠 How It Works

* **PostController:** Handles all HTTP requests.
* **PostService:** Contains business logic for liking/unliking and analytics.
* **Repositories:** Interact with the database.
* **Entities:** Define Post and Like models.

---

## 📁 Folder Structure

```
src
└── main
    ├── java
    │   └── com.example.think41
    │       ├── controller
    │       ├── model
    │       ├── repository
    │       └── service
    └── resources
        └── application.properties
```

---

```
