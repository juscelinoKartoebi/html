package sr.unasat.Admin.DAO;

import sr.unasat.Admin.Entities.Add;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AddDAO {
    private EntityManager entityManager;
    public AddDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Add> retrieveAddList(){
        entityManager.getTransaction().begin();
        String jpql = "select a from add a";
        TypedQuery<Add> query = entityManager.createQuery(jpql, Add.class);
        List<Add> addList = query.getResultList();
        entityManager.getTransaction().commit();
        return addList;
    }

    public Add findAddById(long id) {
        entityManager.getTransaction().begin();
        String jpql = "select a from add a where a.id = :id";
        TypedQuery<Add> query = entityManager.createQuery(jpql, Add.class);
        query.setParameter("id", id);
        Add account = query.getSingleResult();
        entityManager.getTransaction().commit();
        return account;
    }

    public Add insertAdd(Add account) {
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
        return account;
    }

    public void deleteAdd(Add account) {
        entityManager.getTransaction().begin();
        entityManager.remove(account);
        entityManager.getTransaction().commit();
    }
}
