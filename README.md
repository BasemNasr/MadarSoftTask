# MadarSoft Android Task

A modern Android application built as a technical task for MadarSoft, demonstrating user data management (Save/List/Delete) using a Clean Architecture approach with the latest Android technologies.

## 📝 Task Description

The goal of this project is to provide a seamless user experience for entering and viewing user profiles. Users can:
- View a splash animation on app startup.
- Input personal details: Name, Job Title, Age, and Gender.
- Save information locally to a database.
- View a list of all saved users.
- Delete user entries from the list.

## 🖼️ Screens

The application consists of three main screens:

1.  **Splash Screen**: A branded entry point that transitions automatically to the input screen.
2.  **Input Screen**: A form-based interface for collecting user data with field validation (e.g., ensuring all fields are filled and age is a valid number).
3.  **Display Screen**: A list view displaying cards of all saved users with the ability to delete any entry.

## 🏗️ Architecture

The project follows **Clean Architecture** principles to ensure maintainability, scalability, and testability. It is divided into three layers:

-   **Data Layer**:
    -   `Local`: Room database implementation, Entities, and DAOs.
    -   `Repository Impl`: Concrete implementation of the repository interfaces using local data sources.
-   **Domain Layer**:
    -   `Model`: Pure Kotlin data classes representing the business entities (e.g., `User`).
    -   `Repository`: Interfaces defining the data operations required by the domain.
    -   `Use Case`: Single-responsibility classes for specific business logic (e.g., `SaveUserUseCase`, `GetAllUsersUseCase`).
-   **Presentation Layer**:
    -   Uses **MVI/MVVM** pattern with Jetpack Compose.
    -   `ViewModel`: Manages UI state using `StateFlow` and handles user interactions by calling Use Cases.
    -   `Screen`: Composable functions that define the UI and observe state changes.

## 📂 Project Structure

```text
com.bn.madarsofttaskbassemnar
├── data
│   ├── local          # Room database, Dao, Entities
│   └── repository     # Repository implementation
├── di                 # Hilt Dependency Injection modules
├── domain
│   ├── model          # Business models
│   ├── repository     # Repository interfaces
│   └── usecase        # Business logic / Use cases
├── presentation
│   ├── components     # Reusable UI components
│   ├── display        # Display screen UI & ViewModel
│   ├── input          # Input screen UI & ViewModel
│   ├── navigation     # Jetpack Navigation setup
│   └── splash         # Splash screen UI
├── ui.theme           # Application theme & Typography
└── util               # UI helpers (e.g., UiText)
```

## 🛠️ Libraries Used

-   **Jetpack Compose**: Modern toolkit for building native UI.
-   **Hilt**: Dependency injection library for Android.
-   **Room**: Persistence library providing an abstraction layer over SQLite.
-   **Jetpack Navigation**: For navigating between composables.
-   **Kotlin Coroutines & Flow**: For asynchronous programming and reactive state management.
-   **Material 3**: Google's latest design system for Android.
-   **JUnit, Mockk, Turbine**: For comprehensive unit testing of ViewModels, Use Cases, and Repositories.

## 🧪 Testing

The project includes unit tests for all core logic components.
To run the tests, use the following command:

```bash
./gradlew testDebugUnitTest
```
