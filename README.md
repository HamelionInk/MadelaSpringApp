# MadelaSpringApp
Задание по Spring

Базовый уровень
Необходимо разработать приложение на Spring Boot, реализующее функционал отправки письма с содержанием «Пора на работу!» по списку адресатов каждый день с понедельника по пятницу в 8:00. Подробности:
●	Отправка письма осуществляется с помощью Spring Mail - есть
●	В качестве планировщика применяется Spring Scheduler - есть
●	Список адресатов хранится в реляционной базе данных - например, Postqresql, MySql. Для работы с ней используется Spring JDBC - есть
●	Версия Spring Boot – самая свежая стабильная - есть
●	Приложение имеет REST-интерфейс для просмотра списка адресатов, добавления и удаления из списка - есть
●	Приложение покрыто Unit-тестами
●	Конфигурация производится стандартным образом через YAML-файл
●	В исходном коде должно отчётливо прослеживаться следование паттерну MVC - есть
●	Приложение собирается с помощью Maven 3 - есть

Продвинутый уровень
Варианты усложнения:
●	Приложение должно быть автоматически собрано в Docker-образ - можно применить соответствующий плагин для Maven
●	Написать Swagger-контракт для приложения и поместить его в ресурсы приложения
●	Использовать NoSQL базу данных для хранения адресатов – например, Redis или MongoDB
●	Отправлять письмо именно каждый рабочий день в соответствии с производственным календарём. Нужно скачивать актуальный CSV-файл, парсить даты и динамически формировать расписание отправления писем для планировщика
●	Реализовать пользовательский интерфейс для удобного отображения REST API приложения в браузере. Выбор способа реализации свободный, применять ли Bootstrap, JS-фреймворки, шаблонизаторы типа Thymeleaf/Mustache – всё индивидуально
●	Заменить REST-интерфейс приложения на WebSocket, используя Spring WebFlux и Netty
●	Имитировать работу в кластерной среде. Поднять сразу несколько экземпляров сервиса в виде fat-jar, связанных между собой с помощью Service Discovery – например, Netflix Eureka или HashiCorp Consul. Заменить Spring Scheduler на Quartz
●	Использовать Message Brokers (Apache Kafka, RabbitMQ или ActiveMQ)
●	Сделать авторизацию через Spring Security
●	Перевести приложение на микросервисную архитектуру (Spring Cloud)
●	Организовать самый простой процесс CI/CD в гитлабе с помощью .gitlab-ci.yml
(например запуск тестов и деплой на докер хаб)
