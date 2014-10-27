package cl.jazocar.jprocess.front;

import cl.jazocar.jprocess.common.connection.JProcessFactoryService;
import cl.jazocar.jprocess.dao.service.IConfigJProcessService;


public abstract class AplicationAbstract implements AplicationInterface {

	public IConfigJProcessService configJProcessService;
	
	public AplicationAbstract(){		
		configJProcessService = JProcessFactoryService.getInstance()
				.getService("CONF_JPROCESS_SERVICE",
						IConfigJProcessService.class);
	}
}
