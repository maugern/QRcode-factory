package fr.epsi.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan({"fr.epsi.users.dao","fr.epsi.users.model","fr.epsi.users.service","fr.epsi.web.controller"})
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("fr.epsi.entity").addProperties(getHibernateProperties());
        builder.scanPackages("fr.epsi.users").addProperties(getHibernateProperties());
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
     * Return a dataSource based on PostgreSQL environment variable.
     * Use DATABASE_URL environment variables.
     * This method is copy/pasted from
     * <a href="https://devcenter.heroku.com/articles/heroku-postgresql#connecting-in-java">
     * heroku dev blog</a>.
     * @return dataSource
     */
    @Bean(name = "dataSource")
    public BasicDataSource dataSource() {

        String env = System.getenv("DATABASE_URL");
        if (env == null) {
            logger.error("Environment variable \"DATABASE_URL\" is not found. " +
                            "Maybe you forget to define this variable (not caused by a wrong URL).",
                    new NullPointerException("ENVIRONMENT VARIABLE \"DATABASE_URL\" NOT FOUND"));
        }

        URI dbUri = null;
        try {
            dbUri = new URI(env);
        } catch (URISyntaxException e) {
            logger.error("Environment variable \"DATABASE_URL\" had a malformed URL",e);
        }

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
