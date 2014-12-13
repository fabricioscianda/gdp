package gdp.model;

import gdp.model.Persona;
import gdp.model.TipoEstadoFormacion;
import gdp.model.TipoFormacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-12T23:59:13")
@StaticMetamodel(FormacionAcademica.class)
public class FormacionAcademica_ { 

    public static volatile SingularAttribute<FormacionAcademica, Long> id;
    public static volatile SingularAttribute<FormacionAcademica, TipoEstadoFormacion> estado;
    public static volatile SingularAttribute<FormacionAcademica, TipoFormacion> tipoFormacion;
    public static volatile SingularAttribute<FormacionAcademica, Persona> persona;

}