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
//            team.getMembers().add(member);
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("=====================");
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
            System.out.println("=====================");


            tx.commit();  // 정상이면 commit해주고
        } catch (Exception e) {
            tx.rollback(); // 문제가 생기면 rollback 해주기
        } finally {
            em.close();  // 작업이 다 끝나면 EntityManager를 닫아주기
        }
        emf.close();    // 전체 애플리케이션이 끝나면 EntityManagerFactory까지 닫아주기
    }
}
