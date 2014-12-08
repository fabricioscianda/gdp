package gdp.model;

import gdp.model.Persona;
import gdp.model.TipoContacto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-07T13:40:29")
@StaticMetamodel(Contacto.class)
public class Contacto_ { 

    public static volatile SingularAttribute<Contacto, Long> id;
    public static volatile SingularAttribute<Contacto, String> valor;
    public static volatile SingularAttribute<Contacto, TipoContacto> tipoContacto;
    public static volatile SingularAttribute<Contacto, Persona> persona;

}