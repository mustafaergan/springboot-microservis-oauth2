//package com.mustafaergan.microservis.admin.conf;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//
//@Configuration
//@EnableJpaRepositories( entityManagerFactoryRef = "paketv2EntityManager",
//						transactionManagerRef = "paketv2TransactionManager",
//						basePackages = {"com.mustafaergan.microservis.paket2" }
//					  )
//class DataSourcepaketV2Config {
//
//
//	@Bean(name = "paket2EntityManager")
//	@Primary
//	public LocalContainerEntityManagerFactoryBean getpaketv2EntityManager(EntityManagerFactoryBuilder builder,
//																			@Qualifier("dsv2") DataSource agv2DataSource) {
//
//		return builder
//				.dataSource(agv2DataSource)
//				.packages("com.mustafaergan.microservis.paket2")
//				.persistenceUnit("paketv2PersUnit")
//				.build();
//	}
//
//
//	@Bean("dspv2")
//	@Primary
//	@ConfigurationProperties("spring.datasource")
//	public DataSourceProperties paketv2DataSourceProperties() {
//		return new DataSourceProperties();
//	}
//
//	@Bean(name = "dsv2")
//	@ConfigurationProperties("spring.datasource")
//	@Primary
//	public DataSource paketv2DataSource(@Qualifier("dspv2") DataSourceProperties paketv2DataSourceProperties) {
//
//		return paketv2DataSourceProperties.initializeDataSourceBuilder().build();
//	}
//
//	@Bean(name = "paket2TransactionManager")
//	public JpaTransactionManager transactionManager(@Qualifier("paket2EntityManager") EntityManagerFactory paketv2EntityManager) {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(paketv2EntityManager);
//
//		return transactionManager;
//	}
//}