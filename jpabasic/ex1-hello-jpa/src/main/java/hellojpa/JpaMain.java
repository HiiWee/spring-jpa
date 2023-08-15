package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        try {

            Member member = saveMember(em);

            Team team = new Team();
            team.setName("teamA");
            // Team이 아닌 외래키가 변경되어야 함 즉, MEMBER가 업데이트 되어야 한다.
            team.getMembers().add(member);

            em.persist(team);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }
        factory.close();
    }

    private static Member saveMember(final EntityManager em) {
        Member member = new Member("member1");
        em.persist(member);
        return member;
    }
}
