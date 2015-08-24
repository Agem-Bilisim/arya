package tr.com.agem;

import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class AryaDataSourceConfig extends DriverManagerDataSource {

	public AryaDataSourceConfig() {
		super();
		init();
	}

	public AryaDataSourceConfig(String url, Properties conProps) {
		super(url, conProps);
		init();
	}

	public AryaDataSourceConfig(String url, String username, String password) {
		super(url, username, password);
		init();
	}

	public AryaDataSourceConfig(String url) {
		super(url);
		init();
	}

	private void init() {
		this.setDriverClassName("org.postgresql.Driver");
		this.setUrl("jdbc:postgresql://192.168.1.53:5432/sndk");
		this.setUsername("sndk");
		this.setPassword("sndk");
	}

}
