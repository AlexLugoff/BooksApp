# BooksApp

Приложение для поиска книг, построенное на современных архитектурных принципах. Проект демонстрирует работу с Google Books API, реактивное программирование и декларативный UI.

## 📱 Особенности
* **Динамический поиск:** Интегрированный Search Bar с мгновенным обновлением результатов.
* **Реактивный UI:** Полностью написан на Jetpack Compose с поддержкой Material 3.
* **Оффлайн-обработка:** Замена небезопасных HTTP ссылок на HTTPS "на лету" при загрузке изображений.
* **Интеграция:** Возможность перехода к просмотру книги в браузере через Intent.

## 🏗 Архитектура и технологии
Проект строго следует принципам **Clean Architecture** и **SOLID**:

* **UI Layer:** Jetpack Compose, ViewModel (с использованием State-хоистинга), Material 3.
* **Domain Layer:** Понятные Use Cases, абстрактные интерфейсы репозиториев, чистые модели данных.
* **Data Layer:** Реализация репозиториев, маппинг DTO -> Domain моделей, работа с Retrofit.

### Стек технологий:
* **Language:** Kotlin.
* **DI:** Hilt (Dagger-Hilt).
* **Async & Streams:** Coroutines (viewModelScope, suspend functions).
* **Networking:** Retrofit 2 + OkHttp (с настроенными таймаутами и логированием).
* **JSON Parsing:** Gson.
* **Image Loading:** Coil (с поддержкой кроссфейда и обработкой состояний загрузки/ошибки).

## 📸 Как это выглядит
<p align="left">
  <img src="screenshots/Screenshot_20260317_215419.png" width="300" />
  <img src="screenshots/Screenshot_20260317_215518.png" width="300" />
</p>
