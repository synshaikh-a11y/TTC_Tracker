# TTC Transit Tracker

Real-time Toronto Transit Commission (TTC) transit tracking REST API built with Spring Boot.

##  Features

- Real-time vehicle location tracking
- Route information and stops
- Arrival time predictions
- RESTful API architecture
- Interactive API documentation

##  Tech Stack

- **Java 17**
- **Spring Boot 3.2**
- **Spring MVC**
- **Maven**
- **REST API**

##  API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/` | GET | API Documentation |
| `/api/test` | GET | Health check |
| `/api/routes` | GET | Get all TTC routes |
| `/api/routes/{routeTag}/stops` | GET | Get stops for a route |
| `/api/stops/{stopId}/predictions` | GET | Get arrival predictions |
| `/api/routes/{routeTag}/vehicles` | GET | Get vehicle locations |

## 🏃 Running Locally

### Prerequisites
- JDK 17 or higher
- Maven 3.6+