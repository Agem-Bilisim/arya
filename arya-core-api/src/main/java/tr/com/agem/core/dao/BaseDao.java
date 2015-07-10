package tr.com.agem.core.dao;

import java.util.List;

public interface BaseDao<T> {
	
	T save(T o);
	void delete(Object id);
	T find(Object id);
	T update(T t);
	
	List<? extends T> findAll(Class<? extends T> obj);
	T findWithName(String appName, String moduleName, String formName);
}
