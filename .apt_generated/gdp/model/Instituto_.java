package gdp.model;

import gdp.model.Sede;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-07T13:40:29")
@StaticMetamodel(Instituto.class)
public class Instituto_ { 

    public static volatile SingularAttribute<Instituto, Long> id;
    public static volatile SingularAttribute<Instituto, String> nombre;
    public static volatile ListAttribute<Instituto, Sede> sedes;

}