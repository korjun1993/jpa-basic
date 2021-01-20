package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class japMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        Member member = new Member();
        member.setId(1L);
        member.setName("hello");

        // 1. transaction 얻어오기
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // persist
        try{
            em.persist(member);
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        // find
        try{
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        // remove
        try{
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        // modify
        try{
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("helloJPA");
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        // read
        List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();


        emf.close();
    }
}
