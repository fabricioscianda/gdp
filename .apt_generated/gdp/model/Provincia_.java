package gdp.model;

import gdp.model.Partido;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-12T23:59:13")
@StaticMetamodel(Provincia.class)
public class Provincia_ { 

    public static volatile SingularAttribute<Provincia, Long> id;
    public static volatile SingularAttribute<Provincia, String> nombre;
    public static volatile ListAttribute<Provincia, Partido> partidos;

}