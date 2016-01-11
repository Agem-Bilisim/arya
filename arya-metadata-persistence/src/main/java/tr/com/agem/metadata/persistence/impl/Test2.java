package tr.com.agem.metadata.persistence.impl;

import org.hsqldb.util.DatabaseManagerSwing;

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
