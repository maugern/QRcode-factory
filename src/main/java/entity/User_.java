package entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * User MetaModel.
 * Please refer to <a href="https://docs.jboss.org/hibernate/entitymanager/3.6/reference/en/html/metamodel.html"> 
 * hibernate documentation</a> if you dont know what it is.
 */
@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> alias;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> email;
}
