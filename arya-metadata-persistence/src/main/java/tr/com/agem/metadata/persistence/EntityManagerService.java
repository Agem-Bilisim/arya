package tr.com.agem.metadata.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EntityManagerService {
	
	private static EntityManager em;
	
	public static EntityManager getInstance(){
		
		if(em == null){
			ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
			EntityManagerFactory emf = appContext.getBean(EntityManagerFactory.class);
			em = emf.createEntityManager();
		}
		
		return em;
	}

}
