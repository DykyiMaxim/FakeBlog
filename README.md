# FakeBlog

A blog feed app built to practise offline-first paging and a strict Clean Architecture split.

Posts come from [DummyAPI](https://dummyapi.io). The feed pages through them with Paging 3 and a `RemoteMediator` that caches every page in Room, so the list keeps working without a network connection. Tapping a post opens its details.

## Architecture

The project is split into Gradle modules by layer:

- `domain`: models, repository interfaces and use cases (`GetBlogsUseCase`, `GetBlogDetailsUseCase`)
- `data`: Retrofit API, Room database, DTO mappers, `BlogRemoteMediator` and repository implementations
- `app`: Jetpack Compose UI and ViewModels (MVVM)
- `common`: shared helpers

Dependencies are wired with Hilt.

## Stack

Kotlin, Jetpack Compose, Paging 3, Room, Retrofit, Coroutines, Hilt, Navigation
