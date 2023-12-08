package services;

import models.Employee;
import models.Good;
import models.Report;

public class GoodService extends ServiceBase{

    public void create(Good good){
        em.getTransaction().begin();
        em.persist(good);
        em.getTransaction().commit();
    }

    public void destroy(Good good) {
        em.getTransaction().begin();
        em.remove(good);       // データ削除
        em.getTransaction().commit();
    }

    public Good findOne(Employee emp, Report rep) {
        Good good = em.createNamedQuery("findOne", Good.class)
                .setParameter("emp", emp)
                .setParameter("rep", rep)
                .getSingleResult();
        return good;
    }

    public long isGood(Employee emp, Report rep) {
        long count = (long) em.createNamedQuery("isGood", Long.class)
                .setParameter("emp", emp)
                .setParameter("rep", rep)
                .getSingleResult();
        return count;
    }

}