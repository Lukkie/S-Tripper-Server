package Core;

import Core.Journey;
import Core.ToDo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-03T13:44:31")
@StaticMetamodel(Tussenstop.class)
public class Tussenstop_ { 

    public static volatile SingularAttribute<Tussenstop, ToDo> todo;
    public static volatile SingularAttribute<Tussenstop, String> beschrijving;
    public static volatile SingularAttribute<Tussenstop, Journey> journey;
    public static volatile SingularAttribute<Tussenstop, Integer> tijdsduur;
    public static volatile SingularAttribute<Tussenstop, Long> id;

}