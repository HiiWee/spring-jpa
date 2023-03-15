package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.close();
        factory.close();
    }
}
