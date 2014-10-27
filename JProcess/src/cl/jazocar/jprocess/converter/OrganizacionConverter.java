/**
 * 
 */
package cl.jazocar.jprocess.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import cl.jazocar.jprocess.common.connection.JProcessFactoryService;
import cl.jazocar.jprocess.dao.service.IConfigJProcessService;
import cl.jazocar.jprocess.dto.OrganizacionDTO;

/**
 * @author Diablo
 *
 */
@FacesConverter(value = "organizationConverter")
public class OrganizacionConverter implements Converter {
	
	private IConfigJProcessService configJProcessService;	
	
	public OrganizacionConverter(){
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
		OrganizacionDTO org = configJProcessService.getOrganizacionById(Integer.valueOf(value.toString()));
		
		if(org == null || org.getId() == 0)
			return "N/A";
		else
			return org.getNombre();
		
	}

}
