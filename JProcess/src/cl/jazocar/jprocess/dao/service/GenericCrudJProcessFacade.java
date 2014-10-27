package cl.jazocar.jprocess.dao.service;

import java.util.List;
import java.util.Map;

import cl.jazocar.jprocess.exception.JProcessBusinessException;

public interface GenericCrudJProcessFacade 
{
	@SuppressWarnings("rawtypes")
	public Map  processRequest(String action, String idTable, String[] dataArray, 
							   String[] deleteArray, String[] newArray, String pagina,
							   String[] primaryKeyArray, String[] filterArray) throws JProcessBusinessException;

	
}
