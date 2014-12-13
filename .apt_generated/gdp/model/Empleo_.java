package gdp.model;

import gdp.model.Persona;
import gdp.model.TipoAdministracion;
import gdp.model.TipoCargo;
import gdp.model.TipoRelacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-12T23:59:13")
@StaticMetamodel(Empleo.class)
public class Empleo_ { 

    public static volatile SingularAttribute<Empleo, Long> id;
    public static volatile SingularAttribute<Empleo, TipoRelacion> relacion;
    public static volatile SingularAttribute<Empleo, String> razonSocial;
    public static volatile SingularAttribute<Empleo, TipoAdministracion> tipoAdmin;
    public static volatile SingularAttribute<Empleo, String> detalleCargo;
    public static volatile SingularAttribute<Empleo, TipoCargo> cargo;
    public static volatile SingularAttribute<Empleo, Persona> persona;

}