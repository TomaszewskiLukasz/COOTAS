# COOTAS - Customer Oriented One-Time Token Access Service

COOTAS is a Spring Boot backend service that enables secure, temporary file exchange using **one-time access links**. Users do not need accounts or passwords — access is granted through time-limited tokens sent by email.

---

## 🚀 Key Features

- One-time access links (magic tokens)
- No user accounts required for end users
- Admin creates and manages upload sessions
- Users can upload multiple files per session
- Automatic expiration and revocation of access
- In-memory storage (MVP mode)
- Email-based delivery of access links

---

## 🔄 Simple Flow

1. Admin creates an upload session for a user email  
2. System generates a one-time access link and sends it via email  
3. User opens the link and gains temporary access  
4. User uploads one or more files  
5. Session is automatically closed or expires  

---

## ⚙️ Tech Stack

- Java 25+
- Spring Boot
- Spring Security
- Spring Mail
- REST API
- In-memory storage (ConcurrentHashMap)

---

## 🧪 In-Memory Mode (MVP)

COOTAS runs without a database in MVP mode.

All data (sessions, tokens, uploads metadata) is stored in memory and is lost on restart.

---

## 📧 Email Configuration (SMTP Example)

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your@email.com
spring.mail.password=APP_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

---

## 🔒 Security Model

- Tokens are single-use and time-limited
- User access is temporary and scoped to a session
- Sessions are automatically invalidated after completion or timeout
- Admin endpoints are protected via Spring Security

---

## 📦 Future Improvements

- Redis-based session storage
- File scanning and validation
- Rate limiting and audit logs
