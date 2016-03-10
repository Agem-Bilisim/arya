package tr.com.agem.core.dao;

import java.util.List;

public interface IBaseDao<T> {
	
	T save(String appName, T o);
	void delete(String appName, Object id);
	T find(String appName, Object id);
	T find(String appName, String viewName);
	T update(String appName, T t);
	
	List<? extends T> findAll(String appName, Class<? extends T> obj);
}
