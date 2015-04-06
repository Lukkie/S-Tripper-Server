package Core;

import Core.Interesse;
import Core.Locatie;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-03T13:44:31")
@StaticMetamodel(ToDo.class)
public class ToDo_ { 

    public static volatile SingularAttribute<ToDo, Locatie> locatie;
    public static volatile SingularAttribute<ToDo, String> description;
    public static volatile SingularAttribute<ToDo, Long> id;
    public static volatile SingularAttribute<ToDo, String> title;
    public static volatile ListAttribute<ToDo, Interesse> tags;

}