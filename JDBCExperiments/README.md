## База данных Skillbox состоит из таблиц:

●Students(id, name, age, registration_date); 

●Courses(id, name, duration, type, description, teacher_id, students_count, price, price_per_hour);

●Teachers(id, name, salary, age); 

●Subscription(student_id, course_id, subscription_date); 

●PurchaseList(student_name, course_name, price, subscription_date, id, name, student_id, course_id).

# Задание № 1 :
 
 Создать классы @Entity для всех таблиц базы данных и связи между ними для всей базы данных Skillbox.

# Задание № 2:

Написать код новой таблицы LinkedPurchaseList, которая заполняется на основании данных таблицы PurchaseList.

### Таблица должна содержать следующие поля:

student_id

course_id

В таблице нет отдельной колонки с привычным идентификатором записи. 

В роли идентификатора здесь выступает пара student_id и course_id.  

Это значит, что пара значений student_id и course_id уникальна для каждой записи.

Связка student_id и course_id называется «составной ключ» (Composite key). 

Один из вариантов создания составного ключа — написать отдельный класс, в котором поля будут содержать значения полей, входящих в составной ключ.

### На примере класса Subscription:

public class Key implements Serializable {

@Column(name = "student_id")

    private int studentId;

@Column(name = "course_id")

    private int courseId;

 //setters, getters, equals(), hashcode()

}

### Класс составного ключа обязан:

●быть public;

●иметь публичный конструктор по умолчанию;

●реализовывать собственные equals(), hashCode(), публичные геттеры и сеттеры;

●имплементировать Serializable.


 ### Используется класс-ключ в @Entity следующим образом:

@Entity

@Table(name = "Subscriptions")

public class Subscription {

  @EmbeddedId

  private Key id;

  @Column(name = "student_id", insertable = false, updatable = false)

  private int studentId;

  @Column(name = "course_id", insertable = false, updatable = false)

  private int courseId;
