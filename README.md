Наполнение бд из скриптов
sql_tables.sql
TEST_PUBLIC_DISH.sql
TEST_PUBLIC_MENU.sql
TEST_PUBLIC_RESTAURANT.sql
TEST_PUBLIC_USER.sql

http://localhost:8080

У всех запросов Content-Type: application/json;charset=UTF-8

Неавторизованный доступ только к /api/user/login

/api/user/login
method: POST
Авторизует юзера, проверяет логин/пароль, если все ок - выдает токен
тело запроса {"name":"имя юзера", "password":"пароль"}, например, {"name":"vasya", "password":"password"}
ответ: {"token":JWT-токен}, например {"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YXN5YSIsInNjb3BlcyI6IlJPTEVfVVNFUiIsImlhdCI6MTUzODIwMDU2OCwiZXhwIjoxNTM4MjE4NTY4fQ.RJF383m0f1mNhOR7i305Kw1dwGPby0Gg3z6-EsRGWyo"}

Все остальные запросы требуют авторизации, без авторизации 401 ошибка.
Для авторизации добавить в headers запроса header Authorization: Bearer токен-из-предыдущего-пункта
например Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YXN5YSIsInNjb3BlcyI6IlJPTEVfVVNFUiIsImlhdCI6MTUzODIwMDU2OCwiZXhwIjoxNTM4MjE4NTY4fQ.RJF383m0f1mNhOR7i305Kw1dwGPby0Gg3z6-EsRGWyo

/api/dishes
method: GET
Отдает список всех блюд
пример ответа
[{"id":1,"name":"Свинина","price":100},{"id":2,"name":"Говядина","price":200},{"id":33,"name":"salad","price":100},{"id":34,"name":"Чай","price":100},{"id":35,"name":"Плюшки","price":200},{"id":36,"name":"Чай","price":100},{"id":37,"name":"Печенья","price":200},{"id":38,"name":"Варенье","price":200},{"id":39,"name":"Кофе","price":100}]

/api/dishes/{id}
method: GET
Отдает блюдо по айди
пример ответа
{"id":1,"name":"Свинина","price":100}

/api/restaurants
method: GET
Отдает список всех ресторанов (вместе с меню)
пример ответа
[{"id":1,"name":"У Васи","menu":[{"id":1,"name":"Свинина","price":100},{"id":2,"name":"Говядина","price":200}]},{"id":2,"name":"у Гоги","menu":[{"id":37,"name":"Печенья","price":200},{"id":36,"name":"Чай","price":100}]},{"id":3,"name":"у Пети","menu":[{"id":39,"name":"Кофе","price":100},{"id":38,"name":"Варенье","price":200}]}]

/api/restaurants/{id}
method: GET
Отдает ресторан по айди (вместе с меню)
пример ответа:
{"id":1,"name":"У Васи","menu":[{"id":1,"name":"Свинина","price":100},{"id":2,"name":"Говядина","price":200}]}

/api/restaurants/update
method: POST
Доступно только юзерам с ролью ROLE_ADMIN
Обновляет меню ресторана. Ответ - обновленный ресторан
Формат запроса: {"restId":идентификатор ресторана, "menu": [{"name":название блюда, "price":цена},{"name":название блюда, "price":цена}]}
пример ответа:
{"id":1,"name":"У Васи","menu":[{"id":1,"name":"Свинина","price":100},{"id":2,"name":"Говядина","price":200}]}

/api/user/vote/{restID}
method: GET
Текущий юзер голосует за ресторан c айди = restId
В ответ приходит обновленный юзер
пример ответа
{"id":2,"name":"vasya","passSalt":"sdf3HJRC32dsf","passHash":"d7a03e7d12f50c3bc73b09437fda76e9","role":"ROLE_USER","alreadyVotedFor":2,"latestVote":"2018-09-29T09:21:30.942"}
