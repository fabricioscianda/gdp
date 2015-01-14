package mseg.erp.spring.factoryBeans;

import mseg.erp.vomodel.VOCarrera;
import mseg.erp.vomodel.VOContacto;
import mseg.erp.vomodel.VODomicilio;
import mseg.erp.vomodel.VOInstituto;
import mseg.erp.vomodel.VOPartido;
import mseg.erp.vomodel.VOProvincia;
import mseg.erp.vomodel.VOSede;
import mseg.erp.utils.jsonUtils.strategies.CustomExclusionStrategy;

import org.springframework.beans.factory.FactoryBean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Factory Bean for Gson Serializer/Deserializer
 * 
 */
public class GsonSpringFactoryBean implements FactoryBean<Gson> {

	/**
	 * Singleton Instance.
	 */
	private Gson singleton = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public Gson getObject() throws Exception {
		if (this.singleton == null) {
			// we create the GsonBuilder
			GsonBuilder gsb = new GsonBuilder();

			gsb.setDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOProvincia.class, false, "partidos"));
			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOPartido.class, false, "localidades"));
			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOSede.class, false, "carreras"));
			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOInstituto.class, false, "sedes"));
			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOCarrera.class, false, "asignaturas"));
			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VODomicilio.class, false, "persona"));
			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOContacto.class, false, "persona"));

			// returns the created builder
			this.singleton = gsb.create();
		}

		// returns the singleton
		return this.singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		return Gson.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

}
