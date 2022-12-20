package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam.getName() = " + findTeam.getName());


            tx.commit();  // 정상이면 commit해주고
        } catch (Exception e) {
            tx.rollback(); // 문제가 생기면 rollback 해주기
        } finally {
            em.close();  // 작업이 다 끝나면 EntityManager를 닫아주기
        }
        emf.close();    // 전체 애플리케이션이 끝나면 EntityManagerFactory까지 닫아주기
    }
}
