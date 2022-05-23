package June.Spring.demo.clientrepository;

import June.Spring.demo.clientdomain.clientmemeber;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JPAmemberrepository implements memberrepository{

    private final EntityManager em;

    public JPAmemberrepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public clientmemeber save(clientmemeber member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<clientmemeber> findByID(Long id) {
        clientmemeber member = em.find(clientmemeber.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<clientmemeber> findByName(String name) {
        List<clientmemeber> result1 = em.createQuery("select m from clientmember m where m.name = :name",
                clientmemeber.class).setParameter("name", name).getResultList();
        return result1.stream().findAny();
    }

    @Override
    public List<clientmemeber> findAll() {
        List<clientmemeber> result2 = em.createQuery("select m from clientmember m",
                clientmemeber.class).getResultList();
        return result2;
    }
}
