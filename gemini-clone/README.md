# Gemini AI Chatbot

## Overview
This is a full-stack AI chatbot application that utilizes Google's Gemini AI model to generate responses based on user queries. The project consists of:
- **Backend**: A Spring Boot application that interacts with the Gemini API.
- **Frontend**: A React-based UI for user interaction.

## Features
- Users can ask questions via the React frontend.
- The backend forwards the queries to the Gemini API and returns responses.
- Displays AI-generated responses along with usage metadata and citations (if applicable).
- Handles API errors gracefully.
- CORS is configured to allow frontend-backend communication.

## Technologies Used
### Backend:
- **Spring Boot** (REST API)
- **Spring WebFlux** (for non-blocking API calls)
- **WebClient** (to interact with the Gemini API)
- **Lombok** (to reduce boilerplate code)
- **Maven** (dependency management)

### Frontend:
- **React.js** (UI development)
- **Axios** (for API requests)
- **Bootstrap** (styling)
- **Vite** (for development and bundling)

## Installation & Setup
### Prerequisites
- **Java 17+** (for Spring Boot backend)
- **Node.js & npm** (for React frontend)
- **Google Gemini API Key** (to access AI model)


## API Endpoints
### POST `/api/qna/ask`
- **Description**: Sends a user question to the Gemini AI model and returns the response.
- **Request Body**:
  ```json
  {
    "question": "What is AI?"
  }
  ```
- **Response**:
  ```json
  {
    "candidates": [
      {
        "content": {
          "parts": [
            { "text": "AI stands for Artificial Intelligence..." }
          ]
        }
      }
    ],
    "usageMetadata": {
      "promptTokenCount": 10,
      "candidateTokenCount": 30,
      "totalTokenCount": 40
    }
  }
  ```

## Project Structure
```
root/
│── backend/
│   ├── src/main/java/com/ragheb/chat/
│   │   ├── AIController.java  # REST API Controller
│   │   ├── QnAService.java    # Handles API calls
│   │   ├── WebConfig.java     # Configures CORS
│   ├── src/main/resources/application.properties
│   ├── pom.xml                # Maven dependencies
│
│── frontend/
│   ├── src/component/
│   │   ├── ChatInput.jsx      # User input field
│   │   ├── ChatResponse.jsx   # Displays AI response
│   ├── src/service/api.jsx    # API service functions
│   ├── src/App.jsx            # Main component
│   ├── src/index.jsx          # React app entry point
│   ├── package.json           # Dependencies
│
│── README.md
```

## Troubleshooting
1. **400 Bad Request from Gemini API**
    - Ensure your API key is correct and active.
    - Verify that the request body format matches Gemini's expected structure.
2. **CORS Errors** (Frontend can't communicate with Backend)
    - Ensure that `WebConfig.java` allows requests from `http://localhost:5173/`.
3. **React App Not Loading**
    - Check if the frontend development server is running (`npm run dev`).

## Future Enhancements
- Add authentication for API security.
- Improve error handling in UI.
- Support multiple AI models.

