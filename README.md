# TestTask Uladzislau Mialik

## 1. Network Layer.
### Есть 2 раздельных запроса:
a. Bitbucket  
b. Github  
  
Требуется поддержать возможность выполнения обоих запросов <b>параллельно</b>.  
Для этого необходимо представить свою реализацию сетевого слоя приложения.
### Сетевой слой должен обеспечивать:
1. Построение BaseURL  
2. Добавление параметров в запрос  
3. Обработку основных ошибок удобным для пользователя образом (поп-ап).  

## 2. User Interface.
Принцип построения интерфейса - любой.  
Дизайн произвольный, но он должен учитывать следующие требования.
#### a. Приложение должно состоять из 2х экранов.
i. Список загруженных объектов в виде таблицы или коллекции.  
ii. Экран с деталями о конкретном репозитории  
(Открывается по нажатию на ячейку).  
#### b. Ячейка должна содержать:
i. Иконка пользователя.  
ii. Заголовок.  
iii. Описание репозитория.  
iv. Показатель, откуда пришли данные (Bitbucket / Github).  
## 3. Дополнительная функциональность.
### a. Навигация по проекту должна использовать панель навигации.
### b. Коллекция / таблица должна поддерживать swipe-to-refresh.
### c. Необходимо реализовать сортировку репозиториев по:
i. Источнику информации.  
ii. По алфавиту / в обратном порядке / восстановление порядка.  
### d. Необходимо реализовать фильтрацию по:
i. Имени пользователя.  
ii. Названию репозитория.  
iii. Источнику информации.  
