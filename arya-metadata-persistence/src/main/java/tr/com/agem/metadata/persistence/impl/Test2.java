package tr.com.agem.metadata.persistence.impl;

import javax.persistence.EntityManager;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tr.com.agem.core.metadata.persistence.IMetadataPersistence;
import tr.com.agem.metadata.persistence.EntityManagerService;
import tr.com.agem.metadata.persistence.model.MetadataImpl;

public class Test2 {

	
	public static void main(String[] args) {
		
//		EntityManager em = EntityManagerService.getInstance();
//		MetadataImpl impl = new MetadataImpl();
//		impl.setMetadataXml("tirilaylay lom");
//		em.getTransaction().begin();
//		em.persist(impl);
//		em.flush();
//		em.getTransaction().commit();
//		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		IMetadataPersistence bean = appContext.getBean(IMetadataPersistence.class);
//		MetadataImpl impl = new MetadataImpl();
//		impl.setMetadataXml("<window><textfield></textfield></window>");
//		bean.saveMetadata(impl);
		DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:metadata", "--user", "sa", "--password", "" });
		
		
	}

}
