package tr.com.agem.metadata.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import tr.com.agem.core.dao.BaseDao;
import tr.com.agem.metadata.persistence.EntityManagerService;

public class BaseDaoImpl<T> implements BaseDao<T> {
	
	private Class<T> type;
	
	public BaseDaoImpl(Class<T> type) {
		this.type = type;
	}

	public T save(T o) {
		EntityManager em = EntityManagerService.getInstance();
		em.getTransaction().begin();
		em.persist(o);
		em.flush();
		em.getTransaction().commit();
		return o;
	}

	public void delete(Object id) {
		EntityManager em = EntityManagerService.getInstance();
		em.getTransaction().begin();
		em.remove(em.getReference(type, id));
		em.flush();
		em.getTransaction().commit();
		
	}

	public T find(Object id) {
		EntityManager em = EntityManagerService.getInstance();
		T t = (T) em.find(type, id);
		return t;
	}

	public T update(T o) {
		EntityManager em = EntityManagerService.getInstance();
		em.getTransaction().begin();
		em.merge(o);
		em.flush();
		em.getTransaction().commit();
		return o;
	}

	public List<? extends T> findAll(Class<? extends T> obj) {
		EntityManager em = EntityManagerService.getInstance();
		return em.createQuery(
                "select model from " + obj.getName() + " model", obj).getResultList();
	}

}
