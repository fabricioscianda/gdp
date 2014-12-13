package gdp.model;

import gdp.model.Localidad;
import gdp.model.Provincia;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-12T23:59:13")
@StaticMetamodel(Partido.class)
public class Partido_ { 

    public static volatile SingularAttribute<Partido, Long> id;
    public static volatile SingularAttribute<Partido, String> nombre;
    public static volatile ListAttribute<Partido, Localidad> localidades;
    public static volatile SingularAttribute<Partido, Provincia> provincia;

}