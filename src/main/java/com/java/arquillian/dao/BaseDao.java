package com.java.arquillian.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.java.arquillian.entity.BaseEntity;

public abstract class BaseDao<T extends BaseEntity> {

	@PersistenceContext(unitName = "testing-h2")
	private EntityManager entityManager;

	private Class<T> persistentClass;

	public T persist(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}

	public void persistAndFlush(T entity) {
		persist(entity);
		entityManager.flush();
	}

	public void removeAndFlush(T entity) {
		remove(entity);
		entityManager.flush();
	}

	public T merge(T entity) {
		return entityManager.merge(entity);
	}

	public void mergeAndFlush(T entity) {
		merge(entity);
		entityManager.flush();
	}

	public void remove(T entity) {
		entityManager.remove(entityManager.merge(entity));
	}

	public void remove(Long id) {
		entityManager.remove(find(id));
	}

	/**
	 * @return the persistentClass
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getPersistentClass() {
		if (persistentClass == null) {
			persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}
		return persistentClass;
	}

	public Long getMaxId(String idColumnName) {
		long result = 0;
		Class<T> entityClass = getPersistentClass();
		String strQuery = "SELECT MAX(e." + idColumnName + ") FROM " + entityClass.getSimpleName() + " e";
		Object obj = entityManager.createQuery(strQuery).getSingleResult();
		if (obj != null) {
			result = (Long) obj;
		}
		return result;
	}

	public Long getNextId(String idColumnName) {
		return getMaxId(idColumnName) + 1;
	}

	public T find(Long id) {
		T persistent = entityManager.find(getPersistentClass(), id);
		if (persistent == null) {
			return null;
		}

		// get fresh data of entity from db
		entityManager.refresh(persistent);

		return persistent;
	}

	public List<T> findAll() {
		Class<T> entityClass = getPersistentClass();
		String sqlQuery = "SELECT e FROM " + entityClass.getSimpleName() + " e ";
		sqlQuery += " ORDER BY e.id";
		return entityManager.createQuery(sqlQuery, entityClass).getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findByName(String name) {
		if (name == null) {
			return null;
		}
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery cq = builder.createQuery(getPersistentClass());
		Root<T> c = cq.from(getPersistentClass());
		cq.select(c).where(builder.like(builder.trim(c.get("name")), "%" + name.toLowerCase().trim() + "%"));
		return entityManager.createQuery(cq).getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findRange(int[] range) {
		CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		cq.select(cq.from(getPersistentClass()));
		javax.persistence.Query q = entityManager.createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int count() {
		CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(getPersistentClass());
		cq.select(entityManager.getCriteriaBuilder().count(rt));
		javax.persistence.Query q = entityManager.createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int countById(Long id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(getPersistentClass());
		cq.where(builder.equal(rt.get("id"), id));
		cq.select(entityManager.getCriteriaBuilder().count(rt));
		javax.persistence.Query q = entityManager.createQuery(cq);
		int count = ((Long) q.getSingleResult()).intValue();
		return count;
	}

	public void persistFlushAndClear(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.clear();
	}

	/**
	 * Gets the entityManager
	 *
	 * @return Returns the entityManager
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * Clearing the entity manager empties its associated cache.
	 * The state held by the cache doesn't reflect what is in the
	 * database because of the queries, so you want to clear the cache to avoid
	 * the inconsistency
	 */
    public void clear() {
    	getEntityManager().clear();
    }
    
    public void flush() {
    	getEntityManager().flush();
    }

}
