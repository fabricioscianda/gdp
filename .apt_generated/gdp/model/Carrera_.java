package gdp.model;

import gdp.model.Asignatura;
import gdp.model.Sede;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-21T21:13:11")
@StaticMetamodel(Carrera.class)
public class Carrera_ { 

    public static volatile SingularAttribute<Carrera, Long> id;
    public static volatile SingularAttribute<Carrera, String> nombre;
    public static volatile SingularAttribute<Carrera, Sede> sede;
    public static volatile ListAttribute<Carrera, Asignatura> asignaturas;

}