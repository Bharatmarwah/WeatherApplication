WeatherApp
=========

Small Spring Boot backend that fetches current weather from WeatherAPI, caches responses in Redis, and exposes a simple REST endpoint. This repo also includes a tiny static frontend (served from Spring Boot's static resources) to query the API.

Quick overview
--------------
- Backend: Spring Boot (Java 21)
- HTTP client: Spring WebFlux WebClient
- Cache: Redis (spring-data-redis)
- DTOs in `com.bm.WeatherApp.DTOS`
- Controller: `POST /weather/get/{city}` returns a JSON WeatherResponse

What I added
------------
- README.md (this file)
- A minimal single-page frontend at `src/main/resources/static/index.html` plus `app.js` and `styles.css` to call the backend and display results.

How to run (development)
------------------------
Prerequisites:
- Java 21 JDK
- Maven
- Redis running locally (default host/port: localhost:6379) or adjust `spring.redis.*` in `src/main/resources/application.properties`.

From project root (F:\\WeatherApp\\WeatherApp):

1) Build the app (skip tests if you prefer faster builds):
   mvn -DskipTests package

2) Run the app:
   mvn spring-boot:run

3) Open the frontend in your browser:
   http://localhost:8080/

API details
-----------
- POST /weather/get/{city}
  - Path parameter: city (e.g. London)
  - Returns: WeatherResponse JSON with fields `city` and `temperature`.
  - Example (curl):
    curl -X POST http://localhost:8080/weather/get/London

Configuration
-------------
The app reads configuration from `src/main/resources/application.properties`.
Important properties:
- spring.redis.host, spring.redis.port — connection for Redis
- weather.api.key — API key for WeatherAPI

You can override properties via command line or environment variables. Example:

mvn spring-boot:run -Dspring-boot.run.arguments="--weather.api.key=YOUR_KEY"

Minimal Frontend
----------------
The frontend is a tiny static page that calls the backend endpoint and displays temperature. It sends a POST request to `/weather/get/{city}` and displays the JSON response.

Notes & Next steps
------------------
- The embedded API key in `application.properties` may be a placeholder. Rotate or move it to a secrets store in production.
- Add input validation and error handling on backend when calling external API.
- Optionally add CORS config if you serve frontend from a different origin.

If you'd like, I can:
- Implement CORS and security
- Improve the frontend UX
- Add unit/integration tests around the controller and client

