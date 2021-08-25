package za.ac.nwu.repo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("za.ac.nwu.repo.persistence")
@EntityScan("za.ac.nwu.domain.persistence")
@PropertySource(value = "classpath:application-db.properties")
public class RepositoryConfig {
    /*private static final String[] ENTITY_PACKAGES_TO_SCAN = {"za.ac.nwu.domain.persistence"};
    private static final String PERSISTENCE_UNIT_NAME = "discovery.account.system.persistence";

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(buildJpaRepository());
        entityManagerFactoryBean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource(){
        try{
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setUrl(datasourceUrl);
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Properties buildJpaRepository(){
        Properties properties = new Properties();
        properties.setProperty("javax.persistence.transactionType", "jta");
        properties.setProperty("hibernate.Integercode.use_reflection_optimizer", "true");
        properties.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");
        properties.setProperty("hibernate.query.factory_class", "org.hibernate.hql.internal.ClassicQueryTranslatorFactory");
        properties.setProperty("hibernate.transaction.jpa.platform", "org.hibernate.service.jta.platform.internal.SunOneJtaPlatform");
        properties.setProperty("hibernate.dialect", "org.hibernate..dialect.MySQL5InnoDBDialect");
        properties.setProperty("hibernate.generate_statistics", "false");
        properties.setProperty("hibernate.use_sql_comments", "false");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.jdbc.batch_size", "500");
        properties.setProperty("hibernate.order_inserts", "true");
        properties.setProperty("hibernate.order_updates", "true");
        properties.setProperty("hibernate.batch_versioned_data", "true");
        return properties;
    }*/
}