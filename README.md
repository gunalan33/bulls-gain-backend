🐂 Bulls Gain — Stock Portfolio Tracker (Backend)
A secure, full-featured backend for tracking stock investments — built with Spring Boot, Spring Security, and JWT authentication.
🚀 Features
JWT Authentication — Secure register/login with BCrypt password hashing
Portfolio Management — Add, update, delete stock holdings
Buy More / Sell — Weighted average price calculation on additional purchases, realized P&L on sell
Target Price & Stop Loss — Set price alerts per stock
Auto Profit/Loss Calculation — Real-time P&L tracking based on current price
Role-based Access Control — User-owned data isolation via Spring Security
🛠️ Tech Stack
Java 17
Spring Boot 3.2
Spring Security + JWT (jjwt)
Spring Data JPA / Hibernate
MySQL
Maven
📁 Project Structure
```
src/main/java/com/bullsgain/
├── controller/     # REST API endpoints
├── service/        # Business logic
├── repository/     # Data access layer (JPA)
├── model/          # Entity classes (User, Stock)
├── dto/            # Request/Response objects
└── security/       # JWT filter, security config, user details service
```
🔑 API Endpoints
Method	Endpoint	Description
POST	`/api/auth/register`	Register new user
POST	`/api/auth/login`	Login, returns JWT token
GET	`/api/stocks/my`	Get user's portfolio
POST	`/api/stocks/add`	Add new stock
PUT	`/api/stocks/update-price/{id}`	Update current price
PUT	`/api/stocks/buy-more/{id}`	Buy additional shares (weighted avg)
PUT	`/api/stocks/sell/{id}`	Sell shares (realized P&L)
DELETE	`/api/stocks/delete/{id}`	Delete a stock
⚙️ Setup & Run
Clone the repo
```bash
   git clone https://github.com/gunalan33/bulls-gain-backend.git
   ```
Create a MySQL database:
```sql
   CREATE DATABASE stock_tracker;
   ```
Update `src/main/resources/application.properties` with your MySQL credentials
Run the application:
```bash
   ./mvnw spring-boot:run
   ```
Server runs on `http://localhost:8080`
🔗 Related Repo
Frontend (React): bulls-gain-frontend
👤 Author
Gunalan Thangaraj
LinkedIn
