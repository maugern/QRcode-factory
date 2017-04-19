package fr.epsi.config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan({ "fr.epsi.*" })
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class AppConfig {

    private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("fr.epsi.users.model").addProperties(getHibernateProperties());
        return builder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        return prop;
    }

    /**
     * Return a dataSource based on Postgresql environment variable.
     * Use the following environment variables :
     * <ul>
     *  <li>PGHOST or "localhost" if null</li>
     *  <li>PGPORT or "5432" if null</li>
     *  <li>PGDATABASE or "postgres" if null</li>
     *  <li>PGUSER or "postgres" if null</li>
     *  <li>PGPASSWORD or "" if null</li>
     * </ul>
     * @return a new dataSource
     */
    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        StringBuilder url = new StringBuilder("jdbc:postgresql://");
        url.append(System.getenv("PGHOST") == null ? "localhost" : System.getenv("PGHOST"));
        url.append(":");
        url.append(System.getenv("PGPORT") == null ? "5432" : System.getenv("PGPORT"));
        url.append("/");
        url.append(System.getenv("PGDATABASE") == null ? "postgres" : System.getenv("PGDATABASE"));
        url.append("?sslmode=require");
        driverManagerDataSource.setUrl(url.toString());
        logger.info("Postgres URI : " + url);
        driverManagerDataSource.setSchema(System.getenv("POSTGRES_DB") == null ? "postgres" : System.getenv("POSTGRES_DB"));
        driverManagerDataSource.setUsername(System.getenv("PGUSER") == null ? "postgres" : System.getenv("PGUSER"));
        driverManagerDataSource.setPassword(System.getenv("PGPASSWORD") == null ? "postgres" : System.getenv("PGUSER"));
        return driverManagerDataSource;
    }

    @Bean
    public HibernateTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
