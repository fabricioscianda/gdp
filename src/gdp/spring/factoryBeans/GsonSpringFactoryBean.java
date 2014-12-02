package gdp.spring.factoryBeans;

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
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOAgenda.class, false, "recordatorios"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOAgenda.class, false, "interno"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOAgendaRecordatorio.class, false, "recordatorio"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOArtefacto.class, false, "accion"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOCliente.class, false, "convenio"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOClienteContacto.class, false, "cliente"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOClientePos.class, false, "cliente"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOEmpresaCore.class, false, "roles"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOInfo.class, false, "infoAdicional"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOInterno.class, false, "internoRecordatorios"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOInternoOficina.class, false, "usuario"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOInternoRecordatorio.class, false, "recordatorio"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOPersonaCore.class, false, "roles"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VORecordatorio.class, false, "accion"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VORecordatorio.class, false, "agendas"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VORecordatorio.class, false, "propietario"));
//			gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VORepresentante.class, false, "convenio"));
			//gsb.addSerializationExclusionStrategy(new CustomExclusionStrategy(VOCliente.class, false, "infoAdicional"));

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
