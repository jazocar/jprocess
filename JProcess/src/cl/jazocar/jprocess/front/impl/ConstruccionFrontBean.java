package cl.jazocar.jprocess.front.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.icefaces.ace.event.SelectEvent;

import cl.jazocar.jprocess.common.connection.JProcessFactoryService;
import cl.jazocar.jprocess.dao.service.IConfigJProcessService;
import cl.jazocar.jprocess.dto.AplicacionDTO;
import cl.jazocar.jprocess.dto.CreacionDTO;
import cl.jazocar.jprocess.dto.TareaDTO;

@ManagedBean (name = ConstruccionFrontBean.CONSTRUCCION_BEAN)
@SessionScoped
public class ConstruccionFrontBean {

	public static final String CONSTRUCCION_BEAN = "construccionFrontBean";
	
	public List<AplicacionDTO>     listaAplicacion;	
	public List<CreacionDTO>       listaConstruccion;
	private IConfigJProcessService configJProcessService;
	
	private AplicacionDTO aplicacion;
	private CreacionDTO   creacion;
	private boolean selected;
	private boolean selectedCreacion;
	private int     idAplicacion;
	private int     idCreacion;
	
	private List<SelectItem> tareasInicio;
	private List<SelectItem> tareasDestino;
	private List<SelectItem> tareasRechazo;
	
	private String tareaInicial;
	private String tareaFinal;
	
	private String selectedTareaInicio;
	private String selectedTareaDestino;
	private String selectedTareaRechazo;
	private String selectedEsCondicional;
	private String selectedTipoDestino;
	
	public ConstruccionFrontBean(){
		configJProcessService = JProcessFactoryService.getInstance()
				.getService("CONF_JPROCESS_SERVICE",
						IConfigJProcessService.class);
		
		listaAplicacion = configJProcessService.selectAllAplicacion();
		tareasInicio   = new ArrayList<SelectItem>();
		tareasDestino  = new ArrayList<SelectItem>();
		tareasRechazo  = new ArrayList<SelectItem>();
		
		listaConstruccion = new ArrayList<CreacionDTO>();
		for (TareaDTO tarea : configJProcessService.selectAllTarea()){
			tareasInicio.add(new SelectItem( tarea.getId(), tarea.getNombre()));
			tareasDestino.add(new SelectItem( tarea.getId(), tarea.getNombre()));
			tareasRechazo.add(new SelectItem( tarea.getId(), tarea.getNombre()));
		}
	}
	
	public void agregarEventoListener(ActionEvent e){
		CreacionDTO creacion = new CreacionDTO();
		creacion.setIdAplicacion(idAplicacion);
		creacion.setIdTareaInicio(Integer.parseInt(selectedTareaInicio));
		creacion.setIdTareaExito(Integer.parseInt(selectedTareaDestino));
		creacion.setEsCondicional(selectedEsCondicional);
		creacion.setIdTareaRechazo(Integer.parseInt(selectedTareaRechazo));
		creacion.setTipoDestino(selectedTipoDestino);
		
		configJProcessService.insertCreacion(creacion);
		configJProcessService.updateInicioFinAplicacion(idAplicacion);
		listaConstruccion = configJProcessService.selectCreacionByIdAplicacion(idAplicacion);
		resetEvento();
	}
	
	public void updateEventoListener(ActionEvent e){
		CreacionDTO creacion = new CreacionDTO();
		creacion.setId(idCreacion);
		creacion.setIdAplicacion(idAplicacion);
		creacion.setIdTareaInicio(Integer.parseInt(selectedTareaInicio));
		creacion.setIdTareaExito(Integer.parseInt(selectedTareaDestino));
		creacion.setEsCondicional(selectedEsCondicional);
		creacion.setIdTareaRechazo(Integer.parseInt(selectedTareaRechazo));
		creacion.setTipoDestino(selectedTipoDestino);
		
		configJProcessService.updateCreacion(creacion);
		configJProcessService.updateInicioFinAplicacion(idAplicacion);
		aplicacion = configJProcessService.selectAplicacionById(idAplicacion);
		tareaInicial = String.valueOf(aplicacion.getIdTareaInicial());
		tareaFinal   = String.valueOf(aplicacion.getIdTareaFinal());
		listaConstruccion = configJProcessService.selectCreacionByIdAplicacion(idAplicacion);
		
		resetEvento();
	}
	
	public void eventoSelectListener(SelectEvent event){
		creacion = (CreacionDTO) event.getObject();	
		
		selectedTareaInicio   = String.valueOf(creacion.getIdTareaInicio());
		selectedTareaDestino  = String.valueOf(creacion.getIdTareaExito());
		selectedTareaRechazo  = String.valueOf(creacion.getIdTareaRechazo());
		selectedEsCondicional = creacion.getEsCondicional();
		selectedTipoDestino   = String.valueOf(creacion.getTipoDestino());
		idCreacion            = creacion.getId();
		
		selectedCreacion = true;		
	}
	
	public void aplicacionSelectListener(SelectEvent event) {
		aplicacion = (AplicacionDTO) event.getObject();			
		idAplicacion     = aplicacion.getId();
		tareaInicial     = String.valueOf(aplicacion.getIdTareaInicial());
		tareaFinal       = String.valueOf(aplicacion.getIdTareaFinal());
		
		listaConstruccion = configJProcessService.selectCreacionByIdAplicacion(idAplicacion);
		
		selected         = true;
	}
	
	public void resetEventoListener(ActionEvent e){
		resetEvento();
	}
	
	public void resetEvento(){
		selectedTareaInicio   = "-1";
		selectedTareaDestino  = "-1";
		selectedTareaRechazo  = "-1";
		selectedEsCondicional = "-1";
		selectedTipoDestino   = "-1";
		idCreacion            = 0;
		
		selectedCreacion = false;
	}
	
	public void cerrarVentanaListener(ActionEvent e){
		selected  = false;
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
	 * @return the construccionBean
	 */
	public static String getConstruccionBean() {
		return CONSTRUCCION_BEAN;
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
	 * @return the idAplicacion
	 */
	public int getIdAplicacion() {
		return idAplicacion;
	}

	/**
	 * @param idAplicacion the idAplicacion to set
	 */
	public void setIdAplicacion(int idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	/**
	 * @return the tareasInicio
	 */
	public List<SelectItem> getTareasInicio() {
		return tareasInicio;
	}

	/**
	 * @param tareasInicio the tareasInicio to set
	 */
	public void setTareasInicio(List<SelectItem> tareasInicio) {
		this.tareasInicio = tareasInicio;
	}

	/**
	 * @return the tareasDestino
	 */
	public List<SelectItem> getTareasDestino() {
		return tareasDestino;
	}

	/**
	 * @param tareasDestino the tareasDestino to set
	 */
	public void setTareasDestino(List<SelectItem> tareasDestino) {
		this.tareasDestino = tareasDestino;
	}

	/**
	 * @return the selectedTareaInicio
	 */
	public String getSelectedTareaInicio() {
		return selectedTareaInicio;
	}

	/**
	 * @param selectedTareaInicio the selectedTareaInicio to set
	 */
	public void setSelectedTareaInicio(String selectedTareaInicio) {
		this.selectedTareaInicio = selectedTareaInicio;
	}

	/**
	 * @return the selectedTareaDestino
	 */
	public String getSelectedTareaDestino() {
		return selectedTareaDestino;
	}

	/**
	 * @param selectedTareaDestino the selectedTareaDestino to set
	 */
	public void setSelectedTareaDestino(String selectedTareaDestino) {
		this.selectedTareaDestino = selectedTareaDestino;
	}

	/**
	 * @return the listaConstruccion
	 */
	public List<CreacionDTO> getListaConstruccion() {
		return listaConstruccion;
	}

	/**
	 * @param listaConstruccion the listaConstruccion to set
	 */
	public void setListaConstruccion(List<CreacionDTO> listaConstruccion) {
		this.listaConstruccion = listaConstruccion;
	}

	/**
	 * @return the tareasRechazo
	 */
	public List<SelectItem> getTareasRechazo() {
		return tareasRechazo;
	}

	/**
	 * @param tareasRechazo the tareasRechazo to set
	 */
	public void setTareasRechazo(List<SelectItem> tareasRechazo) {
		this.tareasRechazo = tareasRechazo;
	}

	/**
	 * @return the selectedTareaRechazo
	 */
	public String getSelectedTareaRechazo() {
		return selectedTareaRechazo;
	}

	/**
	 * @param selectedTareaRechazo the selectedTareaRechazo to set
	 */
	public void setSelectedTareaRechazo(String selectedTareaRechazo) {
		this.selectedTareaRechazo = selectedTareaRechazo;
	}

	/**
	 * @return the selectedEsCondicional
	 */
	public String getSelectedEsCondicional() {
		return selectedEsCondicional;
	}

	/**
	 * @param selectedEsCondicional the selectedEsCondicional to set
	 */
	public void setSelectedEsCondicional(String selectedEsCondicional) {
		this.selectedEsCondicional = selectedEsCondicional;
	}

	/**
	 * @return the creacion
	 */
	public CreacionDTO getCreacion() {
		return creacion;
	}

	/**
	 * @param creacion the creacion to set
	 */
	public void setCreacion(CreacionDTO creacion) {
		this.creacion = creacion;
	}

	/**
	 * @return the selectedCreacion
	 */
	public boolean isSelectedCreacion() {
		return selectedCreacion;
	}

	/**
	 * @param selectedCreacion the selectedCreacion to set
	 */
	public void setSelectedCreacion(boolean selectedCreacion) {
		this.selectedCreacion = selectedCreacion;
	}

	/**
	 * @return the idCreacion
	 */
	public int getIdCreacion() {
		return idCreacion;
	}

	/**
	 * @param idCreacion the idCreacion to set
	 */
	public void setIdCreacion(int idCreacion) {
		this.idCreacion = idCreacion;
	}

	/**
	 * @return the tareaInicial
	 */
	public String getTareaInicial() {
		return tareaInicial;
	}

	/**
	 * @param tareaInicial the tareaInicial to set
	 */
	public void setTareaInicial(String tareaInicial) {
		this.tareaInicial = tareaInicial;
	}

	/**
	 * @return the tareaFinal
	 */
	public String getTareaFinal() {
		return tareaFinal;
	}

	/**
	 * @param tareaFinal the tareaFinal to set
	 */
	public void setTareaFinal(String tareaFinal) {
		this.tareaFinal = tareaFinal;
	}

	/**
	 * @return the selectedTipoDestino
	 */
	public String getSelectedTipoDestino() {
		return selectedTipoDestino;
	}

	/**
	 * @param selectedTipoDestino the selectedTipoDestino to set
	 */
	public void setSelectedTipoDestino(String selectedTipoDestino) {
		this.selectedTipoDestino = selectedTipoDestino;
	}
	
	
	
}
