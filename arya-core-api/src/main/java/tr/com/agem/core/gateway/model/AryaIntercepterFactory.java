package tr.com.agem.core.gateway.model;


public class AryaIntercepterFactory 
{
	private IAryaIntercepter[] intercepters;

	public AryaIntercepterFactory(
			IAryaIntercepter[] intercepters) {
		this.intercepters = intercepters;
	}

	public IAryaIntercepter[] getIntercepters() {
		return intercepters;
	}

	public void setIntercepters(IAryaIntercepter[] intercepters) {
		this.intercepters = intercepters;
	}
}
