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

    /** User id attribute, associated with id column */
    public static volatile SingularAttribute<User, Long> id;

    /** User alias attribute, associated with alias column */
    public static volatile SingularAttribute<User, String> alias;

    /** User name attribute, associated with name column */
    public static volatile SingularAttribute<User, String> name;

    /** User email attribute, associated with email column */
    public static volatile SingularAttribute<User, String> email;
}
