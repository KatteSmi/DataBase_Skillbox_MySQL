import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private int age;

  @Column(name = "registration_date")
  private Date registrationDate;

  @OneToMany(mappedBy = "student")
  private List<Subscription> subscriptions;

  @OneToMany(mappedBy = "student")
  private List<StudentsCourses> studentsCourses;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public List<Subscription> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(List<Subscription> subscriptions) {
    this.subscriptions = subscriptions;
  }

  public List<StudentsCourses> getStudentsCourses() {
    return studentsCourses;
  }

  public void setStudentsCourses(List<StudentsCourses> studentsCourses) {
    this.studentsCourses = studentsCourses;
  }
}
