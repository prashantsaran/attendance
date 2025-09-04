**Attendance Management System (Spring Boot)**
📌 Overview

This is a Spring Boot CRUD-based Attendance Management System that allows employees to punch in and punch out multiple times per day and retrieve attendance reports for a given duration.

The system ensures alternating Punch In / Punch Out actions and provides daily & overall summaries of work hours.

🚀 Features

Mark Attendance

Employees can punch in and out multiple times per day.

Ensures proper sequence (no double punch-ins or punch-outs).

Get Attendance Report

Fetch records for an employee within a given date-time range.

Provides:

Attendance breakdown per day.

Daily summary (total work hours).

Overall summary for the entire duration.

🛠️ Tech Stack

Java 17+

Spring Boot 3.x

Spring Web

Spring Data JPA

Lombok

Database: H2 (default, in-memory) or MySQL (optional)

⚙️ Deployment Steps
1. Clone Repository
git clone https://github.com/prashantsaran/attendance.git
cd attendance-system

2. Build Project

Using Maven:

mvn clean install

3. Run Application
mvn spring-boot:run


Application will start on http://localhost:8080

🗄️ Database Setup
Option 1: Default (H2 In-Memory)

H2 is already included.


Default config in application.properties:

spring.datasource.url=jdbc:h2:mem:attendancedb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true

Option 2: MySQL

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/attendance_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


Create database in MySQL:

CREATE DATABASE attendance_db;

📡 API Endpoints
1. Mark Attendance
POST api/attendance/punch
payload :
{
    "employeeId": 125
}
Response :

{
    "id": 2177,
    "employeeId": 125,
    "punchType": "OUT",
    "timestamp": "2025-09-04T11:34:17.8865948"
}


Toggles Punch In / Punch Out automatically.

2. Get Attendance Report
GET /api/attendance/126?from=2025-09-01&to=2025-09-03


Response Example

{
    "dailyRecords": {
        "2025-09-03": [
            {
                "punchType": "IN",
                "timestamp": "2025-09-03T15:45:11.559012"
            },
            {
                "punchType": "OUT",
                "timestamp": "2025-09-03T15:45:54.086459"
            },
            {
                "punchType": "IN",
                "timestamp": "2025-09-03T15:45:55.295838"
            },
            {
                "punchType": "OUT",
                "timestamp": "2025-09-03T16:07:19.774374"
            },
            {
                "punchType": "IN",
                "timestamp": "2025-09-03T16:12:31.355327"
            },
            {
                "punchType": "OUT",
                "timestamp": "2025-09-03T16:12:34.134582"
            },
            {
                "punchType": "IN",
                "timestamp": "2025-09-03T16:12:44.233956"
            },
            {
                "punchType": "OUT",
                "timestamp": "2025-09-03T16:54:02.945"
            },
            {
                "punchType": "IN",
                "timestamp": "2025-09-03T16:54:04.918744"
            }
        ]
    },
    "dailySummary": {
        "2025-09-03": "62 minutes"
    },
    "totalSummary": "62 minutes"
}

🧪 Testing

Use Postman or cURL for API testing.

Example:

curl --location 'http://localhost:8080/api/attendance/126?from=2025-09-01&to=2025-09-04'
