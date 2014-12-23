package gdp.model;

import gdp.model.Carrera;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-22T01:36:58")
@StaticMetamodel(Asignatura.class)
public class Asignatura_ { 

    public static volatile SingularAttribute<Asignatura, Integer> anio;
    public static volatile SingularAttribute<Asignatura, Carrera> carrera;
    public static volatile SingularAttribute<Asignatura, Long> id;
    public static volatile SingularAttribute<Asignatura, String> nombre;

}