package gdp.model;

import gdp.model.Documentacion;
import gdp.model.Persona;
import gdp.model.TipoEstadoContractual;
import gdp.model.TipoMotivo;
import gdp.model.TipoPersonal;
import gdp.model.TipoSituacion;
import gdp.model.TipoSituacionRevista;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-07T13:40:29")
@StaticMetamodel(InfoAdministrativa.class)
public class InfoAdministrativa_ { 

    public static volatile SingularAttribute<InfoAdministrativa, String> nroResol;
    public static volatile SingularAttribute<InfoAdministrativa, TipoPersonal> tipoPersonal;
    public static volatile SingularAttribute<InfoAdministrativa, String> nroExpe;
    public static volatile SingularAttribute<InfoAdministrativa, Documentacion> documentacion;
    public static volatile SingularAttribute<InfoAdministrativa, TipoEstadoContractual> estadoContrac;
    public static volatile SingularAttribute<InfoAdministrativa, Long> fechaMotivo;
    public static volatile SingularAttribute<InfoAdministrativa, TipoSituacionRevista> situRevista;
    public static volatile SingularAttribute<InfoAdministrativa, Long> id;
    public static volatile SingularAttribute<InfoAdministrativa, TipoMotivo> motivo;
    public static volatile SingularAttribute<InfoAdministrativa, String> legajo;
    public static volatile SingularAttribute<InfoAdministrativa, Long> fechaAlta;
    public static volatile SingularAttribute<InfoAdministrativa, TipoSituacion> situActual;
    public static volatile SingularAttribute<InfoAdministrativa, Persona> persona;

}