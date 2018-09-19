//package com.mustafaergan.microservis.admin.conf;
//
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//
//@Configuration
//@EnableJpaRepositories( entityManagerFactoryRef = "paketv1EntityManager",
//						transactionManagerRef = "paketv1TransactionManager",
//						basePackages = {"com.mustafaergan.microservis.paket1" }
//					  )
//class DataSourceAgencyV1Config {
//
//	@Bean(name = "paket1EntityManager")
//	public LocalContainerEntityManagerFactoryBean getAgencyv1EntityManager(EntityManagerFactoryBuilder builder,
//																			@Qualifier("dsv1") DataSource agv1DataSource) {
//		return builder
//				.dataSource(agv1DataSource)
//				.packages("com.mustafaergan.microservis.paket1")
//				.persistenceUnit("paketv1PersUnit")
//				.build();
//	}
//
//	@Bean("dspv1")
//	@ConfigurationProperties("spring.second.datasource")
//	public DataSourceProperties paketv2DataSourceProperties() {
//		return new DataSourceProperties();
//	}
//
//	@Bean(name = "dsv1")
//	@ConfigurationProperties("spring.second.datasource")
//	public DataSource paketv1DataSource(@Qualifier("dspv1") DataSourceProperties paketv1DataSourceProperties) {
//		return paketv1DataSourceProperties.initializeDataSourceBuilder().build();
//	}
//
//	@Bean(name = "paketv1TransactionManager")
//	public JpaTransactionManager transactionManager(@Qualifier("paket1EntityManager") EntityManagerFactory paketv1EntityManager) {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(paketv1EntityManager);
//		return transactionManager;
//	}
//
//}