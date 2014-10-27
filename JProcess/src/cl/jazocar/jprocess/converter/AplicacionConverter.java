package cl.jazocar.jprocess.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import cl.jazocar.jprocess.common.connection.JProcessFactoryService;
import cl.jazocar.jprocess.dao.service.IConfigJProcessService;

@FacesConverter(value = "aplicacionConverter")
public class AplicacionConverter implements Converter {

	private IConfigJProcessService configJProcessService;

	public AplicacionConverter() {
		configJProcessService = JProcessFactoryService.getInstance()
				.getService("CONF_JPROCESS_SERVICE",
						IConfigJProcessService.class);
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value)
			throws ConverterException {
		// TODO Auto-generated method stub
		return configJProcessService.selectAplicacionById(Integer.parseInt(value.toString())).getAplicacion();	
	}

}
