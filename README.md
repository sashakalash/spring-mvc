# Migration

### Легенда
Первая задача достаточно простая: вам нужно смигрировать ваше приложение на сервлетах, написанное в предыдущих ДЗ на Spring Web MVC с Embed Tomcat.

### Задача
Создайте новый проект на базе Spring MVC и Embed Tomcat и перенесите реализованную в предыдущих ДЗ функциональность.

### Pull request
### Легенда
Самое плохое, что можно сделать с пользовательскими данными - это безвозвратно их удалить (они потом всегда звонят с просьбой восстановить и утверждают, что они-то точно ничего сами не удаляли). Поэтому чаще всего их не удаляют, а помечают на удаление (т.е. добавляют какое-то поле, например, removed).

Для простоты мы будем считать, что /api/posts - это API для клиентов, где они не смогут реализовать "восстановление" удалённых постов и даже просмотреть удалённые посты. Для этого будет отдельное API (позже).

Соответственно, получается, что removeById всего лишь выставляет это поле. Но это не всё, работа остальных методов тоже кардинально меняется:

all возвращает все посты, кроме тех, у которых выставлен флаг removed
getById возвращает пост только если у него не выставлен флаг removed, в противном случае кидает NotFoundException*
save обновляет существующий пост только если у него не выставлен флаг removed, в противном случае кидает NotFoundException*
Примечание*: здесь нет идеального решения, разные люди могут вам говорить, что так можно, так нельзя и т.д. Мы же лишь скажем, что любая категоричность - это всегда плохо и вы должны понимать, что бывает по-разному, всё зависит от того, какое решение примет проектировщик API или команда.

Единственный вопрос остаётся со статус кодами. По логике, NotFoundException должен приводить к статус коду 404. Изучите документацию на @ResponseStatus и подумайте, как её применить для выставления статуса кода 404 при возникновении указанного нами Exception'а.
