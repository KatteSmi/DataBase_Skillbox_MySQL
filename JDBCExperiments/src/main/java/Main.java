import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.PrimaryKey;

import javax.persistence.Id;
import java.security.PrivateKey;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Hibernate.initConnection();

        Transaction transaction = Hibernate.getSession().beginTransaction();
        Student student = Hibernate.getSession().get(Student.class, 25);

        List<Subscription> subscriptions = student.getSubscriptions();
        for (Subscription subscription : subscriptions) {
            System.out.println(subscription.getStudent().getName());
            System.out.println(subscription.getCourse().getName());
            System.out.println(subscription.getSubscriptionDate());
        }

        fillTable(Hibernate.getSession());

        transaction.commit();
        Hibernate.getSession().close();
    }

    private static void fillTable(Session session) {
        List<LinkedPurchaseList> purchaseList = session.createQuery("from LinkedPurchaseList")
                .getResultList();
        for (LinkedPurchaseList var : purchaseList) {

            DetachedCriteria studentsCriteria = DetachedCriteria.forClass(Student.class)
                    .add(Restrictions.eq("name", var.getStudentName()));
            DetachedCriteria coursesCriteria = DetachedCriteria.forClass(Course.class)
                    .add(Restrictions.eq("name", var.getCourseName()));
            Student student = (Student) studentsCriteria.getExecutableCriteria(session).list().stream()
                    .findFirst().get();
            Course course = (Course) coursesCriteria.getExecutableCriteria(session).list().stream()
                    .findFirst().get();
            System.out.println(course.getName());

            StudentsCourses sc = new StudentsCourses(
                    new StudentsCourses.Id(student.getId(), course.getId()), student, course,
                    course.getPrice(), var.getSubscriptionDate());
            session.save(sc);
        }
    }
}
