package gdp.model;

import gdp.model.Contacto;
import gdp.model.Domicilio;
import gdp.model.Empleo;
import gdp.model.FormacionAcademica;
import gdp.model.InfoAdministrativa;
import gdp.model.TipoDocumento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-12T23:59:13")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile SingularAttribute<Persona, Long> id;
    public static volatile SingularAttribute<Persona, String> apellido;
    public static volatile SingularAttribute<Persona, Integer> numeroDoc;
    public static volatile ListAttribute<Persona, Empleo> empleos;
    public static volatile SingularAttribute<Persona, TipoDocumento> tipoDoc;
    public static volatile SingularAttribute<Persona, Long> fechaNac;
    public static volatile ListAttribute<Persona, Domicilio> domicilios;
    public static volatile SingularAttribute<Persona, Integer> cuil;
    public static volatile SingularAttribute<Persona, InfoAdministrativa> infoAdministrativa;
    public static volatile ListAttribute<Persona, Contacto> mediosContacto;
    public static volatile ListAttribute<Persona, FormacionAcademica> formacionAcadem;

}