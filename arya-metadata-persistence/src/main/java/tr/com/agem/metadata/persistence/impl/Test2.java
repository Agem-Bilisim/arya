package tr.com.agem.metadata.persistence.impl;

import javax.persistence.EntityManager;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tr.com.agem.core.metadata.persistence.IMetaDataPersistence;
import tr.com.agem.metadata.persistence.EntityManagerService;
import tr.com.agem.metadata.persistence.model.MetaDataImpl;

public class Test2 {

	
	public static void main(String[] args) {
		
//		EntityManager em = EntityManagerService.getInstance();
//		MetaDataImpl impl = new MetaDataImpl();
//		impl.setMetaDataXml("tirilaylay lom");
//		em.getTransaction().begin();
//		em.persist(impl);
//		em.flush();
//		em.getTransaction().commit();
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		IMetaDataPersistence bean = appContext.getBean(IMetaDataPersistence.class);
		MetaDataImpl impl = new MetaDataImpl();
		impl.setMetaDataXml("tirilaylay lom2 ");
		bean.saveMetaData(impl);
		DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:metadata", "--user", "sa", "--password", "" });
		
		
	}

}
