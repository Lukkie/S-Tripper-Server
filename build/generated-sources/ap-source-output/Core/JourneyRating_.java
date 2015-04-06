package Core;

import Core.Gebruiker;
import Core.Journey;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-03T13:44:31")
@StaticMetamodel(JourneyRating.class)
public class JourneyRating_ { 

    public static volatile SingularAttribute<JourneyRating, Integer> score;
    public static volatile SingularAttribute<JourneyRating, Journey> journey;
    public static volatile SingularAttribute<JourneyRating, Gebruiker> rater;
    public static volatile SingularAttribute<JourneyRating, Long> id;

}