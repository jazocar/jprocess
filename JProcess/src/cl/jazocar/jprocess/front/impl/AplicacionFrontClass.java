/**
 * 
 */
package cl.jazocar.jprocess.front.impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.icefaces.ace.event.SelectEvent;

import cl.jazocar.jprocess.common.connection.JProcessFactoryService;
import cl.jazocar.jprocess.dao.service.IConfigJProcessService;
import cl.jazocar.jprocess.dto.AplicacionDTO;
import cl.jazocar.jprocess.front.AplicationAbstract;

/**
 * @author Diablo
 * 
 */
@ManagedBean(name = AplicacionFrontClass.APLICACION_BEAN)
@SessionScoped
public class AplicacionFrontClass extends AplicationAbstract{

	public static final String APLICACION_BEAN = "aplicacionFrontBean";

	private List<AplicacionDTO> listaAplicacion;
	private AplicacionDTO aplicacion;

	private String inputAplicacion;
	private String inputDescripcion;
	private String inputVersion;

	private boolean selected;
	private int     idAplicacion;

	public AplicacionFrontClass() {
	
		listaAplicacion = configJProcessService.selectAllAplicacion();
	}

	public void insertAplicacionListener(ActionEvent e) {
		
	}

	public void updateAplicacionListener(ActionEvent e) {
		
	}

	public void deleteAplicacionListener(ActionEvent e) {
		
	}

	public void resetAplicacionListener(ActionEvent e) {
		resetAplicacion();
	}
	
	public void resetAplicacion(){
		
	}
	
	public void aplicacionSelectListener(SelectEvent event) {
		
	}

	/**
	 * @return the listaAplicacion
	 */
	public List<AplicacionDTO> getListaAplicacion() {
		return listaAplicacion;
	}

	/**
	 * @param listaAplicacion the listaAplicacion to set
	 */
	public void setListaAplicacion(List<AplicacionDTO> listaAplicacion) {
		this.listaAplicacion = listaAplicacion;
	}

	/**
	 * @return the aplicacion
	 */
	public AplicacionDTO getAplicacion() {
		return aplicacion;
	}

	/**
	 * @param aplicacion the aplicacion to set
	 */
	public void setAplicacion(AplicacionDTO aplicacion) {
		this.aplicacion = aplicacion;
	}

	/**
	 * @return the inputAplicacion
	 */
	public String getInputAplicacion() {
		return inputAplicacion;
	}

	/**
	 * @param inputAplicacion the inputAplicacion to set
	 */
	public void setInputAplicacion(String inputAplicacion) {
		this.inputAplicacion = inputAplicacion;
	}

	/**
	 * @return the inputDescripcion
	 */
	public String getInputDescripcion() {
		return inputDescripcion;
	}

	/**
	 * @param inputDescripcion the inputDescripcion to set
	 */
	public void setInputDescripcion(String inputDescripcion) {
		this.inputDescripcion = inputDescripcion;
	}

	/**
	 * @return the inputVersion
	 */
	public String getInputVersion() {
		return inputVersion;
	}

	/**
	 * @param inputVersion the inputVersion to set
	 */
	public void setInputVersion(String inputVersion) {
		this.inputVersion = inputVersion;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the configJProcessService
	 */
	public IConfigJProcessService getConfigJProcessService() {
		return configJProcessService;
	}

	/**
	 * @param configJProcessService the configJProcessService to set
	 */
	public void setConfigJProcessService(
			IConfigJProcessService configJProcessService) {
		this.configJProcessService = configJProcessService;
	}

	/**
	 * @return the aplicacionBean
	 */
	public static String getAplicacionBean() {
		return APLICACION_BEAN;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertObjectListener(ActionEvent e) {
		// TODO Auto-generated method stub
		aplicacion = new AplicacionDTO();
		aplicacion.setAplicacion(inputAplicacion);
		aplicacion.setDescripcion(inputDescripcion);
		aplicacion.setVersion(inputVersion);
		configJProcessService.insertAplicacion(aplicacion);
		listaAplicacion = configJProcessService.selectAllAplicacion();
	}

	@Override
	public void updateObjectListener(ActionEvent e) {
		// TODO Auto-generated method stub
		aplicacion = new AplicacionDTO();
		aplicacion.setId(idAplicacion);
		aplicacion.setAplicacion(inputAplicacion);
		aplicacion.setDescripcion(inputDescripcion);
		aplicacion.setVersion(inputVersion);
		configJProcessService.updateAplicacion(aplicacion);
		listaAplicacion = configJProcessService.selectAllAplicacion();
	}

	@Override
	public void deleteObjectListener(ActionEvent e) {
		// TODO Auto-generated method stub
		aplicacion = new AplicacionDTO();
		aplicacion.setId(idAplicacion);
		configJProcessService.deleteAplicacion(aplicacion);
		listaAplicacion = configJProcessService.selectAllAplicacion();
	}

	@Override
	public void resetObjectListener(ActionEvent e) {
		// TODO Auto-generated method stub
		resetObject();
	}

	@Override
	public void searchObjectListener(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetObject() {
		// TODO Auto-generated method stub
		inputAplicacion  = "";
		inputDescripcion = "";
		inputVersion     = "";
		idAplicacion     = 0;
		listaAplicacion = configJProcessService.selectAllAplicacion();
		selected = false;
	}

	@Override
	public void objectSelectListener(SelectEvent event) {
		// TODO Auto-generated method stub
        aplicacion = (AplicacionDTO) event.getObject();			
		
		idAplicacion     = aplicacion.getId();
		inputAplicacion  = aplicacion.getAplicacion();
		inputDescripcion = aplicacion.getDescripcion();
		inputVersion     = aplicacion.getVersion();
		selected         = true;
	}	
}
