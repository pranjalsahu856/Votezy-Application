# 🗳️ Online Voting Application 🚀

A Full-Stack Web Application built using **Java Spring Boot** that simulates a secure and structured online voting system.

✨ This project allows voters and candidates to register, enables secure vote casting, and displays real-time election results while enforcing the **One-Vote-Per-Voter** rule.

---

## 📖 About The Project

The **Online Voting Application** demonstrates full-stack development using RESTful architecture and clean backend design principles.

💡 The system supports:

- 🧑‍💼 Candidate Registration  
- 🧑 Voter Registration  
- 🗳️ Secure Vote Casting  
- 📊 Real-Time Result Display  
- ✅ Input Validation  
- ⚠️ Global Exception Handling  
- 🏗️ Layered Architecture  

---

## 🛠️ 💻 Tech Stack

### 🔹 Backend
- ☕ Java  
- 🌱 Spring Boot  
- 🔗 Spring Data JPA  
- 🌐 REST APIs  
- ✅ Bean Validation  
- ⚠️ Global Exception Handling  

### 🔹 Frontend
- 🌐 HTML  
- 🎨 CSS  
- ⚡ JavaScript  

### 🔹 Database
- 🗄️ MySQL  

### 🔹 Tools
- 📦 Maven  
- 📮 Postman (API Testing)  
- 🐙 Git & GitHub  

---

## 🏗️ 🧱 Project Architecture

This project follows a **Layered Architecture Pattern**:

```
Controller → Service → Repository → Database
```

🔹 **Controller Layer** – Handles HTTP requests & responses  
🔹 **Service Layer** – Contains business logic  
🔹 **Repository Layer** – Communicates with the database using JPA  

---

## ✨ 🔑 Core Features

- 📝 Voter Registration  
- 📝 Candidate Registration  
- 🗳️ One Vote Per Voter Logic  
- 📊 View Election Results  
- 🔄 CRUD Operations  
- ✅ Input Validation using `@Valid`  
- ⚠️ Global Exception Handling using `@ControllerAdvice`  
- 🔗 REST API Integration with Frontend  

---

## 🧠 📜 Business Rules Implemented

✔️ A voter can cast only **one vote**  
✔️ Each vote must be linked to a registered candidate  
✔️ Proper validation for all inputs  
✔️ Structured error responses for invalid requests  

---

## 📂 📁 Project Structure

```
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── entity/
 ├── exception/
 └── VotingApplication.java
```
---

## 📈 🎯 Learning Outcomes

- 🚀 REST API Development using Spring Boot  
- 🗄️ Database relationship management with JPA  
- 🧠 Implementation of real-world business logic  
- 🔗 Frontend & backend integration  
- ⚠️ Validation and global exception handling  
- 🧱 Clean code structuring using layered architecture  

---

## 🔮 🌟 Future Enhancements

- 🔐 Spring Security Integration  
- 🪪 JWT Authentication  
- 🛠️ Admin Dashboard  
- 📄 Pagination & Filtering  
