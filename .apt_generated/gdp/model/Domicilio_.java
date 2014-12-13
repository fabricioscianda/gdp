package gdp.model;

import gdp.model.Localidad;
import gdp.model.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-12T23:59:13")
@StaticMetamodel(Domicilio.class)
public class Domicilio_ { 

    public static volatile SingularAttribute<Domicilio, Long> id;
    public static volatile SingularAttribute<Domicilio, Localidad> localidad;
    public static volatile SingularAttribute<Domicilio, String> domicilio;
    public static volatile SingularAttribute<Domicilio, Boolean> actual;
    public static volatile SingularAttribute<Domicilio, Persona> persona;

}