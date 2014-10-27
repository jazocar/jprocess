/**
 * 
 */
package cl.jazocar.jprocess.front.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
import org.icefaces.ace.component.textentry.TextEntry;
import org.icefaces.ace.event.SelectEvent;

import cl.jazocar.jprocess.common.connection.JProcessFactoryService;
import cl.jazocar.jprocess.dao.service.IConfigJProcessService;
import cl.jazocar.jprocess.dto.AplicacionDTO;
import cl.jazocar.jprocess.dto.CasoComentarioDTO;
import cl.jazocar.jprocess.dto.CasoDTO;
import cl.jazocar.jprocess.dto.CreacionDTO;
import cl.jazocar.jprocess.dto.PendienteDTO;
import cl.jazocar.jprocess.dto.SolicitudDTO;
import cl.jazocar.jprocess.dto.TareaConfiguracionDTO;
import cl.jazocar.jprocess.dto.TareaDTO;

/**
 * @author Diablo
 * 
 */
@ManagedBean(name = ToDoFrontBean.TODOFRONTBEAN)
@SessionScoped
public class ToDoFrontBean {

	public static final String TODOFRONTBEAN = "toDoFrontBean";

	private TareaDTO tareaDto = null;
	private CasoDTO casoDto = null;
	private AplicacionDTO appDto = null;
	private SolicitudDTO solicitudDto = null;
	private CreacionDTO creacionDto = null;
	private PendienteDTO pendienteDto = null;

	public List<TareaDTO> listaTareaDto = null;
	public List<CasoComentarioDTO> listaComentario = null;
	public List<AplicacionDTO> listaAplicacion = null;
	public List<CasoDTO> listaCaso = null;
	public static List<SolicitudDTO> listaSolicitud;
	private static boolean selected;
	private boolean selectAll;

	private String outputNombreTarea;
	private String outputDescripcionTarea;
	private String outputCaso;
	private String outputAplicacion;
	private String outputVencimiento;
	private String outputPrioridad;
	private String outputUsuario;

	private String inputComentario;

	// Caso.
	private String outputCasoIdCaso;
	private String outputCasoAplicacion;
	private String outputCasoVersion;
	private String outputCasoIniciadoEn;
	private String outputCasoIniciadoPor;

	// Aplicacion
	private String outputAppId;
	private String outputAppAplicacion;
	private String outputAppDescripcion;
	private String outputAppVersion;
	private String outputAppInicio;
	private String outputAppFin;

	private boolean tareaPanel;
	private boolean casoPanel;
	private boolean apliacionPanel;
	private boolean esRechazo;
	private boolean esAutomatica;
	private boolean mensajes;
	
	private boolean tieneAsignacion;

	private List<CasoComentarioDTO> listaComentariosCaso;
	
	private List<TareaConfiguracionDTO> listaConfiguracion;

	private IConfigJProcessService configJProcessService;
	
	 private List<String> fileData;

	 private static HashMap<String, Integer> severityMap = new HashMap<String, Integer>() {{
         put("Error", 2);
         
     }
     private static final long serialVersionUID = 6584997908723158778L;
 };
 private static String[] severityNames = {"Info", "Warn", "Error", "Fatal"};
	public ToDoFrontBean() {
		configJProcessService = JProcessFactoryService.getInstance()
				.getService("CONF_JPROCESS_SERVICE",
						IConfigJProcessService.class);

		init();
	}

	public void init() {
		if (listaTareaDto == null) {

			listaSolicitud     = new ArrayList<SolicitudDTO>();
			listaTareaDto      = new ArrayList<TareaDTO>();
			listaComentario    = new ArrayList<CasoComentarioDTO>();			
			listaConfiguracion = new ArrayList<TareaConfiguracionDTO>();

			listaAplicacion = configJProcessService.selectAllAplicacion();

			listaCaso = new ArrayList<CasoDTO>();
			listaCaso.add(new CasoDTO(1, "Alzamiento", "1.0", "Walter Bates",
					new Date(), null, null));
			listaCaso.add(new CasoDTO(2, "Hipoteca", "1.0", "Juan Azócar M.",
					new Date(), null, null));
			listaCaso.add(new CasoDTO(3, "Prenda", "1.0", "María Rodriguez",
					new Date(), null, null));
			listaCaso.add(new CasoDTO(4, "Prenda", "1.0", "Isidora Azócar",
					new Date(), null, null));
			listaCaso.add(new CasoDTO(5, "Alzamiento", "1.0", "Juan Azócar M.",
					new Date(), null, null));

		} else {
			System.out.println("No pasa nada");
		}
	}

	public void checkboxValueChangeListener(ValueChangeEvent evt) {
		boolean estado = Boolean.parseBoolean(evt.getNewValue().toString());
		System.out.println(estado);

		if (estado) {
			for (int i = 0; i < listaSolicitud.size(); i++)
				listaSolicitud.get(i).setSelected(true);
		} else
			for (int i = 0; i < listaSolicitud.size(); i++)
				listaSolicitud.get(i).setSelected(false);
		System.out.println("");

	}

	public void insertComentarioListener(ActionEvent e) {
		/*
		 * listaComentario.add(new CasoComentarioDTO("Invitado", new Date(),
		 * inputComentario));
		 */
		configJProcessService.insertSolicitudComentario(
				Integer.parseInt(outputCaso),
				UsuarioFrontBean.usuarioBean.getUsuario(), inputComentario);
		inputComentario = "";
		listaComentario = configJProcessService
				.selectComentariosBySolicitud(Integer.parseInt(outputCaso));
	}

	public void hacerTareaListener(AjaxBehaviorEvent e) {
		 mensajes = false;
		creacionDto = configJProcessService.selectCreacionByTareaInicial(
				tareaDto.getId(), Integer.parseInt(outputAppId));
		appDto = configJProcessService.selectAplicacionById(Integer
				.parseInt(outputAppId));
		pendienteDto = configJProcessService
				.selectPendientesBySolicitud(solicitudDto.getId());

		if (creacionDto.getEsCondicional().equals("S"))
			esRechazo = true;
		else
			esRechazo = false;

		if (tareaDto.getTipoTarea().equals("Manual"))
			esAutomatica = false;
		else
			esAutomatica = true;

		NavigationBean.selectedPanel = "tareaDetallePanel";
	}

	public void despacharCreacionListener(ActionEvent e) {
		try{
		 mensajes = false;
		 
		for(TareaConfiguracionDTO conf : listaConfiguracion){
			if(conf.isRequerido() == true && conf.isSeleccionado() == false){
				mensajesListener(conf.getCheckOption(), "Error");				
			}
		}
		
		configJProcessService.cerrarCreacion(pendienteDto.getId());

		if (tareaDto.getId() == appDto.getIdTareaFinal())
			configJProcessService
					.cerrarSolicitud(Integer.parseInt(outputAppId));
		else {
			TareaDTO tarea = configJProcessService.selectTareaById(creacionDto
					.getIdTareaExito());
			configJProcessService.despacharCreacion(
					creacionDto.getIdTareaExito(), solicitudDto.getId(),
					tarea.getIdRolAsignacion());
		}
		listaSolicitud = configJProcessService
				.selectAllSolicitudesPendientesByRol(UsuarioFrontBean.usuarioBean
						.getRol());
		selected = false;
		NavigationBean.selectedPanel = "inicioPorHacerPanel";
		
		}catch(Exception ex){
			mensajes = true;
		}
	}

	public void rechazarTareaListener(ActionEvent e) {
		configJProcessService.cerrarCreacion(pendienteDto.getId());

		if (tareaDto.getId() == appDto.getIdTareaFinal())
			configJProcessService
					.cerrarSolicitud(Integer.parseInt(outputAppId));
		else {
			TareaDTO tarea = configJProcessService.selectTareaById(creacionDto
					.getIdTareaExito());
			configJProcessService.despacharCreacion(
					creacionDto.getIdTareaRechazo(), solicitudDto.getId(),
					tarea.getIdRolAsignacion());
		}
		listaSolicitud = configJProcessService
				.selectAllSolicitudesPendientesByRol(UsuarioFrontBean.usuarioBean
						.getRol());
		selected = false;
		NavigationBean.selectedPanel = "inicioPorHacerPanel";
	}

	public void iniciarAplicacionListener(AjaxBehaviorEvent e) {
		@SuppressWarnings("rawtypes")
		Map map = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		SolicitudDTO solicitud = new SolicitudDTO();
		int idSolicitud = configJProcessService.selectNewSolicitudId();
		solicitud.setId(idSolicitud);

		solicitud.setIdAplicacion(Integer.parseInt((String) map
				.get("aplicacion")));
		configJProcessService.insertSolicitud(solicitud);		

		PendienteDTO pendiente = new PendienteDTO();
		pendiente.setIdSolicitud(idSolicitud);
		pendiente.setIdTarea(Integer.parseInt(outputAppInicio));
		pendiente.setIdRol(configJProcessService.selectTareaById(
				pendiente.getIdTarea()).getIdRolAsignacion());

		configJProcessService.insertPendiente(pendiente);
		
		solicitud = configJProcessService.selectSolicitudById(idSolicitud);

		listaComentario = configJProcessService
				.selectComentariosBySolicitud(idSolicitud);

		listaSolicitud = configJProcessService
				.selectAllSolicitudesPendientesByRol(UsuarioFrontBean.usuarioBean
						.getRol());

		creacionDto = configJProcessService.selectCreacionByTareaInicial(
				Integer.parseInt(outputAppInicio),
				Integer.parseInt((String) map.get("aplicacion")));
		
		tareaDto = configJProcessService
				.selectTareaActualBySolicitudId(solicitud.getId());
		
		listaConfiguracion = configJProcessService.selectTareaConfiguracionByTarea(tareaDto.getId());
		
		outputNombreTarea = tareaDto.getNombre();
		outputDescripcionTarea = tareaDto.getDescripcion();
		outputCaso = String.valueOf(solicitud.getId());
		outputAplicacion = configJProcessService.selectAplicacionById(
				solicitud.getIdAplicacion()).getAplicacion();
		outputVencimiento = format.format(solicitud.getFechaFin());
		outputPrioridad = tareaDto.getPrioridad();
		outputAppId = String.valueOf(solicitud.getIdAplicacion());
		outputAppDescripcion = configJProcessService.selectAplicacionById(
				solicitud.getIdAplicacion()).getDescripcion();
		outputUsuario = String.valueOf(pendiente.getIdUsuario());
		
		if(pendiente.getIdUsuario()==0)
			tieneAsignacion = false;
		else
			tieneAsignacion = true;

		if (creacionDto.getEsCondicional().equals("S"))
			esRechazo = true;
		else
			esRechazo = false;
		
		if (tareaDto.getTipoTarea().equals("Manual"))
			esAutomatica = false;
		else
			esAutomatica = true;


		NavigationBean.selectedPanel = "tareaDetallePanel";
	}

	public void tomarTareaListener(ActionEvent e) {
		for (SolicitudDTO solicitud : listaSolicitud) {
			if(solicitud.isSelected() == true)
				configJProcessService.asignarResponsable(UsuarioFrontBean.usuarioBean.getId(), solicitud.getId());			
		}
		
		selected = false;
	}
	
	public void liberarTareaListener(ActionEvent e){
		for (SolicitudDTO solicitud : listaSolicitud) {
			if(solicitud.isSelected() == true)
				configJProcessService.liberarResponsable(UsuarioFrontBean.usuarioBean.getId(), solicitud.getId());			
		}
		
		selected = false;
	}
	
	public void ocultarTareaListener(ActionEvent e){
		Iterator<SolicitudDTO> it = listaSolicitud.iterator();
		while (it.hasNext()) {
			SolicitudDTO solicitud = it.next(); 
		    if (solicitud.isSelected() == true) {
		         it.remove();
		         listaSolicitud.remove(solicitud);
		     }
		}
	}
	
	public void refreshListaTareasListener(ActionEvent e){
		listaSolicitud = configJProcessService
				.selectAllSolicitudesPendientesByRol(UsuarioFrontBean.usuarioBean
						.getRol());
	}

	public void checkboxValueTareaChangeListener(ValueChangeEvent evt) {
		boolean estado = Boolean.parseBoolean(evt.getNewValue().toString());
		System.out.println(estado);
		FacesContext context = FacesContext.getCurrentInstance();
		@SuppressWarnings("rawtypes")
		Map map = context.getExternalContext().getRequestParameterMap();
		int id = Integer.parseInt(String.valueOf(map.get("id")).trim());
		System.out.println("Id : " + selected);

		if (estado) {
			for (int i = 0; i < listaTareaDto.size(); i++) {
				if (listaTareaDto.get(i).getId() == id) {
					// listaTareaDto.get(i).setSelected(false);
				}
			}

		} else
			for (int i = 0; i < listaTareaDto.size(); i++) {
				if (listaTareaDto.get(i).getId() == id) {
					// listaTareaDto.get(i).setSelected(true);
				}
			}

	}

	public void ajax(AjaxBehaviorEvent e) {
		FacesContext context = FacesContext.getCurrentInstance();
		@SuppressWarnings("rawtypes")
		Map map = context.getExternalContext().getRequestParameterMap();
		String selected = (String) map.get("valor");
		System.out.println("Valor : " + selected);

		System.out.println("ajax(AjaxBehaviorEvent)");
	}

	public void toDoSelectListener(SelectEvent event) {
		solicitudDto = (SolicitudDTO) event.getObject();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		tareaDto = configJProcessService
				.selectTareaActualBySolicitudId(solicitudDto.getId());
		
		pendienteDto = configJProcessService.selectPendientesBySolicitud(solicitudDto.getId());
		
		listaConfiguracion = configJProcessService.selectTareaConfiguracionByTarea(tareaDto.getId());
		

		outputNombreTarea = tareaDto.getNombre();
		outputDescripcionTarea = tareaDto.getDescripcion();
		outputCaso = String.valueOf(solicitudDto.getId());
		outputAplicacion = configJProcessService.selectAplicacionById(
				solicitudDto.getIdAplicacion()).getAplicacion();
		outputVencimiento =    format.format(solicitudDto.getFechaFin());
		outputPrioridad = tareaDto.getPrioridad();
		outputAppId = String.valueOf(solicitudDto.getIdAplicacion());
		outputAppDescripcion = configJProcessService.selectAplicacionById(
				solicitudDto.getIdAplicacion()).getDescripcion();
		outputUsuario = String.valueOf(pendienteDto.getIdUsuario());
		
		if(pendienteDto.getIdUsuario()==0)
			tieneAsignacion = false;
		else
			tieneAsignacion = true;
		

		listaComentario = configJProcessService
				.selectComentariosBySolicitud(Integer.parseInt(outputCaso));

		/*
		 * outputNombreTarea = tareaDto.getNombreTarea(); outputDescripcionTarea
		 * = tareaDto.getDescripcion(); outputCaso =
		 * String.valueOf(tareaDto.getCaso()); outputAplicacion =
		 * tareaDto.getAplicacion(); outputVencimiento =
		 * format.format(tareaDto.getFechaVencimiento()); outputPrioridad =
		 * tareaDto.getPrioridad();
		 */
		selected = true;

		tareaPanel = true;
		casoPanel = false;
		apliacionPanel = false;
	}

	public void casoSelectListener(SelectEvent event) {
		casoDto = (CasoDTO) event.getObject();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		outputCasoIdCaso = String.valueOf(casoDto.getId());
		outputCasoAplicacion = casoDto.getAplicacion();
		outputCasoVersion = casoDto.getAplicacionVersion();
		outputCasoIniciadoEn = format.format(casoDto.getIniciadoEn());
		outputCasoIniciadoPor = casoDto.getIniciadoPor();
		selected = true;

		casoPanel = true;
		tareaPanel = false;
		apliacionPanel = false;
	}

	public void aplicacionSelectListener(SelectEvent event) {

		appDto = (AplicacionDTO) event.getObject();

		outputAppAplicacion = appDto.getAplicacion();
		outputAppDescripcion = appDto.getDescripcion();
		outputAppVersion = appDto.getVersion();
		outputAppInicio = String.valueOf(appDto.getIdTareaInicial());
		outputAppFin = String.valueOf(appDto.getIdTareaFinal());
		outputAppId = String.valueOf(appDto.getId());

		selected = true;
		apliacionPanel = true;
		tareaPanel = false;
		casoPanel = false;
	}
	
	 public void sampleListener(FileEntryEvent e)
	    {
	        FileEntry fe = (FileEntry)e.getComponent();
	        FileEntryResults results = fe.getResults();
	        File parent = null;

	        fileData = new ArrayList<String>();

	    //get data About File
	    
	        for (FileEntryResults.FileInfo i : results.getFiles()) 
	        {
	            fileData.add("Nombre del Archivo: " + i.getFileName());

	            if (i.isSaved()) {
	                fileData.add("Peso del Archivo: " + i.getSize() + " bytes");

	                File file = i.getFile();
	                if (file != null) {
	                    parent = file.getParentFile();
	                }
	            } else {
	                fileData.add("File was not saved because: " +
	                    i.getStatus().getFacesMessage(
	                        FacesContext.getCurrentInstance(),
	                        fe, i).getSummary());
	            }
	        }

	        if (parent != null) {
	            long dirSize = 0;
	            int fileCount = 0;
	            for (File file : parent.listFiles()) {
	                fileCount++;
	                dirSize += file.length();
	            }
	            fileData.add("Total de Archivos Subidos: " + fileCount);
	            fileData.add("Total de tamano de archivos en el direcotorio: " + dirSize + " bytes");
	        }
	    }
	 
	 public void mensajesListener(String checkOption, String level) throws Exception{
            int index = severityMap.get(level);
            String message = severityNames[index] + ": La opción : \"" + checkOption + "\", es requerida para poder continuar";
            FacesMessage facesMessage = new FacesMessage((FacesMessage.Severity) FacesMessage.VALUES.get(index), message, message);
            FacesContext.getCurrentInstance().addMessage("No puede ser nulo", facesMessage);
	        throw new Exception();
	    }

	/**
	 * @return the tareaDto
	 */
	public TareaDTO getTareaDto() {
		return tareaDto;
	}

	/**
	 * @param tareaDto
	 *            the tareaDto to set
	 */
	public void setTareaDto(TareaDTO tareaDto) {
		this.tareaDto = tareaDto;
	}

	/**
	 * @return the listaTareaDto
	 */
	public List<TareaDTO> getListaTareaDto() {
		return listaTareaDto;
	}

	/**
	 * @param listaTareaDto
	 *            the listaTareaDto to set
	 */
	public void setListaTareaDto(List<TareaDTO> listaTareaDto) {
		this.listaTareaDto = listaTareaDto;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	@SuppressWarnings("static-access")
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the selectAll
	 */
	public boolean isSelectAll() {
		return selectAll;
	}

	/**
	 * @param selectAll
	 *            the selectAll to set
	 */
	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	/**
	 * @return the listaComentario
	 */
	public List<CasoComentarioDTO> getListaComentario() {
		return listaComentario;
	}

	/**
	 * @param listaComentario
	 *            the listaComentario to set
	 */
	public void setListaComentario(List<CasoComentarioDTO> listaComentario) {
		this.listaComentario = listaComentario;
	}

	/**
	 * @return the listaAplicacion
	 */
	public List<AplicacionDTO> getListaAplicacion() {
		return listaAplicacion;
	}

	/**
	 * @param listaAplicacion
	 *            the listaAplicacion to set
	 */
	public void setListaAplicacion(List<AplicacionDTO> listaAplicacion) {
		this.listaAplicacion = listaAplicacion;
	}

	/**
	 * @return the listaCaso
	 */
	public List<CasoDTO> getListaCaso() {
		return listaCaso;
	}

	/**
	 * @param listaCaso
	 *            the listaCaso to set
	 */
	public void setListaCaso(List<CasoDTO> listaCaso) {
		this.listaCaso = listaCaso;
	}

	/**
	 * @return the outputNombreTarea
	 */
	public String getOutputNombreTarea() {
		return outputNombreTarea;
	}

	/**
	 * @param outputNombreTarea
	 *            the outputNombreTarea to set
	 */
	public void setOutputNombreTarea(String outputNombreTarea) {
		this.outputNombreTarea = outputNombreTarea;
	}

	/**
	 * @return the outputDescripcionTarea
	 */
	public String getOutputDescripcionTarea() {
		return outputDescripcionTarea;
	}

	/**
	 * @param outputDescripcionTarea
	 *            the outputDescripcionTarea to set
	 */
	public void setOutputDescripcionTarea(String outputDescripcionTarea) {
		this.outputDescripcionTarea = outputDescripcionTarea;
	}

	/**
	 * @return the outputCaso
	 */
	public String getOutputCaso() {
		return outputCaso;
	}

	/**
	 * @param outputCaso
	 *            the outputCaso to set
	 */
	public void setOutputCaso(String outputCaso) {
		this.outputCaso = outputCaso;
	}

	/**
	 * @return the outputAplicacion
	 */
	public String getOutputAplicacion() {
		return outputAplicacion;
	}

	/**
	 * @param outputAplicacion
	 *            the outputAplicacion to set
	 */
	public void setOutputAplicacion(String outputAplicacion) {
		this.outputAplicacion = outputAplicacion;
	}

	/**
	 * @return the outputVencimiento
	 */
	public String getOutputVencimiento() {
		return outputVencimiento;
	}

	/**
	 * @param outputVencimiento
	 *            the outputVencimiento to set
	 */
	public void setOutputVencimiento(String outputVencimiento) {
		this.outputVencimiento = outputVencimiento;
	}

	/**
	 * @return the outputPrioridad
	 */
	public String getOutputPrioridad() {
		return outputPrioridad;
	}

	/**
	 * @param outputPrioridad
	 *            the outputPrioridad to set
	 */
	public void setOutputPrioridad(String outputPrioridad) {
		this.outputPrioridad = outputPrioridad;
	}

	/**
	 * @return the listaComentariosCaso
	 */
	public List<CasoComentarioDTO> getListaComentariosCaso() {
		return listaComentariosCaso;
	}

	/**
	 * @param listaComentariosCaso
	 *            the listaComentariosCaso to set
	 */
	public void setListaComentariosCaso(
			List<CasoComentarioDTO> listaComentariosCaso) {
		this.listaComentariosCaso = listaComentariosCaso;
	}

	/**
	 * @return the inputComentario
	 */
	public String getInputComentario() {
		return inputComentario;
	}

	/**
	 * @param inputComentario
	 *            the inputComentario to set
	 */
	public void setInputComentario(String inputComentario) {
		this.inputComentario = inputComentario;
	}

	/**
	 * @return the tareaPanel
	 */
	public boolean isTareaPanel() {
		return tareaPanel;
	}

	/**
	 * @param tareaPanel
	 *            the tareaPanel to set
	 */
	public void setTareaPanel(boolean tareaPanel) {
		this.tareaPanel = tareaPanel;
	}

	/**
	 * @return the casoPanel
	 */
	public boolean isCasoPanel() {
		return casoPanel;
	}

	/**
	 * @param casoPanel
	 *            the casoPanel to set
	 */
	public void setCasoPanel(boolean casoPanel) {
		this.casoPanel = casoPanel;
	}

	/**
	 * @return the apliacionPanel
	 */
	public boolean isApliacionPanel() {
		return apliacionPanel;
	}

	/**
	 * @param apliacionPanel
	 *            the apliacionPanel to set
	 */
	public void setApliacionPanel(boolean apliacionPanel) {
		this.apliacionPanel = apliacionPanel;
	}

	/**
	 * @return the outputCasoIdCaso
	 */
	public String getOutputCasoIdCaso() {
		return outputCasoIdCaso;
	}

	/**
	 * @param outputCasoIdCaso
	 *            the outputCasoIdCaso to set
	 */
	public void setOutputCasoIdCaso(String outputCasoIdCaso) {
		this.outputCasoIdCaso = outputCasoIdCaso;
	}

	/**
	 * @return the outputCasoAplicacion
	 */
	public String getOutputCasoAplicacion() {
		return outputCasoAplicacion;
	}

	/**
	 * @param outputCasoAplicacion
	 *            the outputCasoAplicacion to set
	 */
	public void setOutputCasoAplicacion(String outputCasoAplicacion) {
		this.outputCasoAplicacion = outputCasoAplicacion;
	}

	/**
	 * @return the outputCasoVersion
	 */
	public String getOutputCasoVersion() {
		return outputCasoVersion;
	}

	/**
	 * @param outputCasoVersion
	 *            the outputCasoVersion to set
	 */
	public void setOutputCasoVersion(String outputCasoVersion) {
		this.outputCasoVersion = outputCasoVersion;
	}

	/**
	 * @return the outputCasoIniciadoEn
	 */
	public String getOutputCasoIniciadoEn() {
		return outputCasoIniciadoEn;
	}

	/**
	 * @param outputCasoIniciadoEn
	 *            the outputCasoIniciadoEn to set
	 */
	public void setOutputCasoIniciadoEn(String outputCasoIniciadoEn) {
		this.outputCasoIniciadoEn = outputCasoIniciadoEn;
	}

	/**
	 * @return the outputCasoIniciadoPor
	 */
	public String getOutputCasoIniciadoPor() {
		return outputCasoIniciadoPor;
	}

	/**
	 * @param outputCasoIniciadoPor
	 *            the outputCasoIniciadoPor to set
	 */
	public void setOutputCasoIniciadoPor(String outputCasoIniciadoPor) {
		this.outputCasoIniciadoPor = outputCasoIniciadoPor;
	}

	/**
	 * @return the outputAppAplicacion
	 */
	public String getOutputAppAplicacion() {
		return outputAppAplicacion;
	}

	/**
	 * @param outputAppAplicacion
	 *            the outputAppAplicacion to set
	 */
	public void setOutputAppAplicacion(String outputAppAplicacion) {
		this.outputAppAplicacion = outputAppAplicacion;
	}

	/**
	 * @return the outputAppDescripcion
	 */
	public String getOutputAppDescripcion() {
		return outputAppDescripcion;
	}

	/**
	 * @param outputAppDescripcion
	 *            the outputAppDescripcion to set
	 */
	public void setOutputAppDescripcion(String outputAppDescripcion) {
		this.outputAppDescripcion = outputAppDescripcion;
	}

	/**
	 * @return the outputAppVersion
	 */
	public String getOutputAppVersion() {
		return outputAppVersion;
	}

	/**
	 * @param outputAppVersion
	 *            the outputAppVersion to set
	 */
	public void setOutputAppVersion(String outputAppVersion) {
		this.outputAppVersion = outputAppVersion;
	}

	/**
	 * @return the casoDto
	 */
	public CasoDTO getCasoDto() {
		return casoDto;
	}

	/**
	 * @param casoDto
	 *            the casoDto to set
	 */
	public void setCasoDto(CasoDTO casoDto) {
		this.casoDto = casoDto;
	}

	/**
	 * @return the appDto
	 */
	public AplicacionDTO getAppDto() {
		return appDto;
	}

	/**
	 * @param appDto
	 *            the appDto to set
	 */
	public void setAppDto(AplicacionDTO appDto) {
		this.appDto = appDto;
	}

	/**
	 * @return the outputAppInicio
	 */
	public String getOutputAppInicio() {
		return outputAppInicio;
	}

	/**
	 * @param outputAppInicio
	 *            the outputAppInicio to set
	 */
	public void setOutputAppInicio(String outputAppInicio) {
		this.outputAppInicio = outputAppInicio;
	}

	/**
	 * @return the outputAppFin
	 */
	public String getOutputAppFin() {
		return outputAppFin;
	}

	/**
	 * @param outputAppFin
	 *            the outputAppFin to set
	 */
	public void setOutputAppFin(String outputAppFin) {
		this.outputAppFin = outputAppFin;
	}

	/**
	 * @return the configJProcessService
	 */
	public IConfigJProcessService getConfigJProcessService() {
		return configJProcessService;
	}

	/**
	 * @param configJProcessService
	 *            the configJProcessService to set
	 */
	public void setConfigJProcessService(
			IConfigJProcessService configJProcessService) {
		this.configJProcessService = configJProcessService;
	}

	/**
	 * @return the outputAppId
	 */
	public String getOutputAppId() {
		return outputAppId;
	}

	/**
	 * @param outputAppId
	 *            the outputAppId to set
	 */
	public void setOutputAppId(String outputAppId) {
		this.outputAppId = outputAppId;
	}

	/**
	 * @return the listaSolicitud
	 */
	public List<SolicitudDTO> getListaSolicitud() {
		return listaSolicitud;
	}

	/**
	 * @param listaSolicitud
	 *            the listaSolicitud to set
	 */
	public void setListaSolicitud(List<SolicitudDTO> listaSolicitud) {
		this.listaSolicitud = listaSolicitud;
	}

	/**
	 * @return the solicitudDto
	 */
	public SolicitudDTO getSolicitudDto() {
		return solicitudDto;
	}

	/**
	 * @param solicitudDto
	 *            the solicitudDto to set
	 */
	public void setSolicitudDto(SolicitudDTO solicitudDto) {
		this.solicitudDto = solicitudDto;
	}

	/**
	 * @return the creacionDto
	 */
	public CreacionDTO getCreacionDto() {
		return creacionDto;
	}

	/**
	 * @param creacionDto
	 *            the creacionDto to set
	 */
	public void setCreacionDto(CreacionDTO creacionDto) {
		this.creacionDto = creacionDto;
	}

	/**
	 * @return the esRechazo
	 */
	public boolean isEsRechazo() {
		return esRechazo;
	}

	/**
	 * @param esRechazo
	 *            the esRechazo to set
	 */
	public void setEsRechazo(boolean esRechazo) {
		this.esRechazo = esRechazo;
	}

	/**
	 * @return the esAutomatica
	 */
	public boolean isEsAutomatica() {
		return esAutomatica;
	}

	/**
	 * @param esAutomatica
	 *            the esAutomatica to set
	 */
	public void setEsAutomatica(boolean esAutomatica) {
		this.esAutomatica = esAutomatica;
	}

	/**
	 * @return the outputUsuario
	 */
	public String getOutputUsuario() {
		return outputUsuario;
	}

	/**
	 * @param outputUsuario the outputUsuario to set
	 */
	public void setOutputUsuario(String outputUsuario) {
		this.outputUsuario = outputUsuario;
	}

	/**
	 * @return the tieneAsignacion
	 */
	public boolean isTieneAsignacion() {
		return tieneAsignacion;
	}

	/**
	 * @param tieneAsignacion the tieneAsignacion to set
	 */
	public void setTieneAsignacion(boolean tieneAsignacion) {
		this.tieneAsignacion = tieneAsignacion;
	}

	/**
	 * @return the fileData
	 */
	public List<String> getFileData() {
		return fileData;
	}

	/**
	 * @param fileData the fileData to set
	 */
	public void setFileData(List<String> fileData) {
		this.fileData = fileData;
	}

	/**
	 * @return the pendienteDto
	 */
	public PendienteDTO getPendienteDto() {
		return pendienteDto;
	}

	/**
	 * @param pendienteDto the pendienteDto to set
	 */
	public void setPendienteDto(PendienteDTO pendienteDto) {
		this.pendienteDto = pendienteDto;
	}

	/**
	 * @return the listaConfiguracion
	 */
	public List<TareaConfiguracionDTO> getListaConfiguracion() {
		return listaConfiguracion;
	}

	/**
	 * @param listaConfiguracion the listaConfiguracion to set
	 */
	public void setListaConfiguracion(List<TareaConfiguracionDTO> listaConfiguracion) {
		this.listaConfiguracion = listaConfiguracion;
	}

	/**
	 * @return the mensajes
	 */
	public boolean isMensajes() {
		return mensajes;
	}

	/**
	 * @param mensajes the mensajes to set
	 */
	public void setMensajes(boolean mensajes) {
		this.mensajes = mensajes;
	}

	
	
}
