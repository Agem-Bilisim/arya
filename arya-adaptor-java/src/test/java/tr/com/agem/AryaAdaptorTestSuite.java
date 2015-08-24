package tr.com.agem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	AryaRestMapperTest.class, 
	AryaRestAdaptorTest.class,
	AryaJarMapperTest.class,
	AryaJarAdaptorTest.class
})
public class AryaAdaptorTestSuite {
	
}
