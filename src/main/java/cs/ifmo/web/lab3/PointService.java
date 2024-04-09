package cs.ifmo.web.lab3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
@ApplicationScoped
public class PointService {
    @PersistenceContext
    private EntityManager em;

    public List<Point> getAll() {
        return em.createNamedQuery("Point.selectAll", Point.class).getResultList();
    }

    public void add(Point point) {
        em.getTransaction().begin();
        em.persist(point);
        em.getTransaction().commit();
    }
}
