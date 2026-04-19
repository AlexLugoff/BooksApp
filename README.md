# BooksApp 📚

Современное Android-приложение для поиска книг, построенное на принципах **Clean Architecture** и **Material 3**. Проект демонстрирует использование передового стека технологий (Kotlin 2.3.20, KSP, Compose) и глубокое покрытие Unit-тестами.

## 📱 Особенности
* **Динамический поиск:** Интегрированный Search Bar с мгновенным обновлением результатов через Google Books API.
* **Material 3 UI:** Полностью декларативный интерфейс на Jetpack Compose с поддержкой адаптивных цветовых схем.
* **Side Effects:** Обработка одноразовых событий (Snackbar, навигация) через паттерн `UiEffect` (Kotlin Channels).
* **Безопасность контента:** Автоматическая замена HTTP ссылок на HTTPS для безопасной загрузки обложек.
* **Оптимизация:** Использование `StandardTestDispatcher` для тестирования асинхронных операций.

## 🏗 Архитектура
Проект строго следует принципам **Clean Architecture** и разделен на слои:
* **UI Layer:** Jetpack Compose, ViewModel (StateFlow/UiEffect), State-hoisting.
* **Domain Layer:** Use Cases (GetBooks, GetBookDetails), интерфейсы репозиториев, чистые сущности.
* **Data Layer:** Реализация репозиториев, Retrofit API, маппинг DTO в Domain модели.

## 🛠 Стек технологий
* **Core:** Kotlin 2.3.20, Coroutines & Flow.
* **Build System:** Gradle 9.1.1 (Kotlin DSL), Version Catalogs (`libs.versions.toml`), **KSP**.
* **DI:** Hilt (Dagger-Hilt) с использованием KSP для быстрой кодогенерации.
* **Network:** Retrofit 2, OkHttp 5 (Logging Interceptor), Gson.
* **Image Loading:** Coil (Crossfade, Image Transformation).
* **UI:** Jetpack Compose, Material 3, Navigation Compose.

## 🧪 Тестирование
Проект покрыт Unit-тестами (более 80% логики слоев Domain и Data):
* **MockK:** Мокирование зависимостей и контекста.
* **Turbine:** Тестирование Flow (UiState и UiEffects).
* **Coroutines Test:** Использование `runTest` и `StandardTestDispatcher`.
* **JUnit 4:** Базовая библиотека для запуска тестов.

## 📸 Скриншоты
<p align="left">
  <img src="screenshots/Screenshot_20260317_215419.png" width="300" />
  <img src="screenshots/Screenshot_20260317_215518.png" width="300" />
</p>
