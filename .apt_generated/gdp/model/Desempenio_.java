package gdp.model;

import gdp.model.Asignatura;
import gdp.model.Docente;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-07T13:40:29")
@StaticMetamodel(Desempenio.class)
public class Desempenio_ { 

    public static volatile SingularAttribute<Desempenio, Integer> anio;
    public static volatile SingularAttribute<Desempenio, Long> id;
    public static volatile SingularAttribute<Desempenio, Integer> mes;
    public static volatile SingularAttribute<Desempenio, Integer> hcs;
    public static volatile SingularAttribute<Desempenio, Asignatura> asignatura;
    public static volatile SingularAttribute<Desempenio, Docente> docente;

}