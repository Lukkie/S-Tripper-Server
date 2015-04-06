package Core;

import Core.Gebruiker;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-03T13:44:31")
@StaticMetamodel(UserRating.class)
public class UserRating_ { 

    public static volatile SingularAttribute<UserRating, Integer> score;
    public static volatile SingularAttribute<UserRating, Gebruiker> rater;
    public static volatile SingularAttribute<UserRating, Long> id;
    public static volatile SingularAttribute<UserRating, Gebruiker> wordtGerate;

}