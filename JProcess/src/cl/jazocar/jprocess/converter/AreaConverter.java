package cl.jazocar.jprocess.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import cl.jazocar.jprocess.common.connection.JProcessFactoryService;
import cl.jazocar.jprocess.dao.service.IConfigJProcessService;
import cl.jazocar.jprocess.dto.AreaDTO;

@FacesConverter(value = "areaConverter")
public class AreaConverter implements Converter{
	
	private IConfigJProcessService configJProcessService;	

	public AreaConverter(){
		configJProcessService = JProcessFactoryService.getInstance().getService("CONF_JPROCESS_SERVICE", IConfigJProcessService.class );		
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
		AreaDTO area = configJProcessService.selectAreaById(Integer.parseInt(value.toString()));
		
		if(area == null || area.getId() == 0)
			return "N/A";
		else
			return area.getNombre();
	}
	
	
}