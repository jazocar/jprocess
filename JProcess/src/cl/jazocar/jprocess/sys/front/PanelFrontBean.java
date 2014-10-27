/**
 * 
 */
package cl.jazocar.jprocess.sys.front;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.icefaces.ace.event.SelectEvent;

import cl.jazocar.jprocess.common.connection.JProcessFactoryService;
import cl.jazocar.jprocess.constants.IConstants;
import cl.jazocar.jprocess.dao.service.IConfigJProcessService;
import cl.jazocar.jprocess.sys.dto.PanelDTO;

/**
 * @author Diablo
 * 
 */
@ManagedBean(name = "panelFrontBean")
@ApplicationScoped
public class PanelFrontBean {

	private List<PanelDTO> listaPanel;
	
	private String panelId;
	private String panelSubviewId;
	private String include;	
	
	private static PanelDTO panel;
	private boolean selected;
	
	private IConfigJProcessService configBackupService;
	
	
	public PanelFrontBean(){
		configBackupService = JProcessFactoryService.getInstance().getService("CONF_JPROCESS_SERVICE", IConfigJProcessService.class );		
		listaPanel = new ArrayList<PanelDTO>();
		listaPanel = configBackupService.selectAllPanel();
		selected = false;
	}
	
	
	public void panelInsertListener(ActionEvent e) {
		try{
		PanelDTO panel = null;
		panel = new PanelDTO(panelId, panelId+"Sub", include);
		
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String realPath=(String) servletContext.getRealPath("/");
		String[] tempFile = include.split("/");
		realPath +="WEB-INF/includes/panels/"+tempFile[0];
		
		File ruta = new File(realPath);
		File archivo = new File(realPath+"/"+tempFile[1]);
		
		if(!ruta.exists()){
			ruta.mkdirs();
			if(!archivo.exists()){
				archivo.createNewFile();				
				PrintWriter pw = null;
				FileWriter fichero = null;				
				fichero = new FileWriter(archivo);
				pw = new PrintWriter(fichero);
				pw.println(IConstants.CONTENIDO_ARCHIVO_BASE);				
				pw.close();
			}			
		}else{
			if(!archivo.exists()){
				archivo.createNewFile();				
				PrintWriter pw = null;
				FileWriter fichero = null;				
				fichero = new FileWriter(archivo);
				pw = new PrintWriter(fichero);
				pw.println(IConstants.CONTENIDO_ARCHIVO_BASE);				
				pw.close();
			}
		}
		
		
		
		//Creación en el proyecto fuente.
		File rutaFuente    = new File("C:/trabajo/jazocar/workspace/jprocess/JProcess/WebContent/WEB-INF/includes/panels/"+tempFile[0]);
		File archivoFuente = new File(rutaFuente+"/"+tempFile[1]);
		if(!rutaFuente.exists()){
			rutaFuente.mkdirs();
			if(!archivoFuente.exists()){
				archivoFuente.createNewFile();				
				PrintWriter pw = null;
				FileWriter fichero = null;				
				fichero = new FileWriter(archivoFuente);
				pw = new PrintWriter(fichero);
				pw.println(IConstants.CONTENIDO_ARCHIVO_BASE);				
				pw.close();
			}			
		}else{
			if(!archivoFuente.exists()){
				archivoFuente.createNewFile();				
				PrintWriter pw = null;
				FileWriter fichero = null;				
				fichero = new FileWriter(archivoFuente);
				pw = new PrintWriter(fichero);
				pw.println(IConstants.CONTENIDO_ARCHIVO_BASE);				
				pw.close();
			}
		}		
		
		// Insertamos el registro.
		configBackupService.insertPanel(panel);
		
		MenuFrontBean.actualizarListaPaneles(configBackupService.selectAllPanel());

		// Limpiamos los campos
		panelReset();

		// Refrescamos los valores de la Lista de Productos para mostrar el
		// nuevo producto agregado.
		listaPanel = configBackupService.selectAllPanel();		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
	
	public void panelUpdateListener(ActionEvent e) {
		System.out.println("Update");
		if (panel != null) {
			
			panel = new PanelDTO(panel.getId(), panelId, panelSubviewId, include);
			
			// Actualizamos el Registro
			configBackupService.updatePanel(panel);
			// Limpiamos los campos
			panelReset();
			// Refrescamos los valores de la Lista de Proveedores para mostrar el
			// nuevo proveedor agregado.
			listaPanel = configBackupService.selectAllPanel();
		} else {
			System.out.println("Objeto nulo");
		}
	}

	public void panelDeleteListener(ActionEvent e) {
		System.out.println("Eliminando");
		if (panel != null) {
			configBackupService.deletePanel(panel.getId());
			// Limpiamos los campos
			panelReset();
			// Refrescamos los valores de la Lista de Proveedores para mostrar el
			// nuevo proveedor  agregado.
			listaPanel = configBackupService.selectAllPanel();
		}else {
			System.out.println("Objeto nulo");
		}
	}

	public void panelResetListener(ActionEvent e) {
		panelReset();
	}

	public void panelReset(){
		panelId            = "";
		panelSubviewId     = "";
		include            = "";	
		
		selected = false;
		panel = null;
	}
	
	public void panelSelectListener(SelectEvent event) {
		panel = (PanelDTO) event.getObject();

		panelId            = panel.getIdPanel();
		panelSubviewId     = panel.getIdSubview();
		include            = panel.getInclude();	
		
		selected = true;
		
	}
	
	public void refreshPanelListener(ActionEvent e){
		listaPanel = configBackupService.selectAllPanel();	
	}
	
	/**
	 * @return the listaPanel
	 */
	public List<PanelDTO> getListaPanel() {
		return listaPanel;
	}
	/**
	 * @param listaPanel the listaPanel to set
	 */
	public void setListaPanel(List<PanelDTO> listaPanel) {
		this.listaPanel = listaPanel;
	}
	/**
	 * @return the panelId
	 */
	public String getPanelId() {
		return panelId;
	}
	/**
	 * @param panelId the panelId to set
	 */
	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}
	/**
	 * @return the panelSubviewId
	 */
	public String getPanelSubviewId() {
		return panelSubviewId;
	}
	/**
	 * @param panelSubviewId the panelSubviewId to set
	 */
	public void setPanelSubviewId(String panelSubviewId) {
		this.panelSubviewId = panelSubviewId;
	}
	/**
	 * @return the include
	 */
	public String getInclude() {
		return include;
	}
	/**
	 * @param include the include to set
	 */
	public void setInclude(String include) {
		this.include = include;
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
	
	
}
