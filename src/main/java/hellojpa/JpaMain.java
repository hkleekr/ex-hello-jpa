package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();  // EntityManager를 통해서 호출해야 한다.

        EntityTransaction tx = em.getTransaction(); // 트랜젝션을 얻을 수 있다.
        tx.begin();

        try{

//            Member findMember = em.find(Member.class, 1L);
            List <Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name ="+member.getName());
            }


            tx.commit();  // 커밋 꼭 해줘야 한다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();  // 사용 다하면 꼭 닫아줘야 함
        }

        emf.close();   // 사용 다하면 꼭 닫아줘야 함
    }
}
