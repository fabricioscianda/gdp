package gdp.model;

import gdp.model.Carrera;
import gdp.model.Instituto;
import gdp.model.Localidad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-07T13:40:29")
@StaticMetamodel(Sede.class)
public class Sede_ { 

    public static volatile SingularAttribute<Sede, Long> id;
    public static volatile SingularAttribute<Sede, String> nombre;
    public static volatile SingularAttribute<Sede, Localidad> localidad;
    public static volatile ListAttribute<Sede, Carrera> carreras;
    public static volatile SingularAttribute<Sede, Instituto> instituto;

}