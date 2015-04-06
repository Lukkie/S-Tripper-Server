package Core;

import Core.Gebruiker;
import Core.Interesse;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-03T13:44:31")
@StaticMetamodel(Gebruiker.class)
public class Gebruiker_ { 

    public static volatile SingularAttribute<Gebruiker, String> achternaam;
    public static volatile ListAttribute<Gebruiker, Interesse> interesses;
    public static volatile ListAttribute<Gebruiker, Gebruiker> following;
    public static volatile SingularAttribute<Gebruiker, String> paswoord;
    public static volatile SingularAttribute<Gebruiker, String> voornaam;
    public static volatile SingularAttribute<Gebruiker, Long> id;
    public static volatile SingularAttribute<Gebruiker, String> email;
    public static volatile SingularAttribute<Gebruiker, String> username;

}