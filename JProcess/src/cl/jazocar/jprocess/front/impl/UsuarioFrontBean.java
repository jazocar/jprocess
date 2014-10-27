/**
 * 
 */
package cl.jazocar.jprocess.front.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.icefaces.ace.event.SelectEvent;
import org.icefaces.application.SessionExpiredException;

import cl.jazocar.jprocess.common.connection.JProcessFactoryService;
import cl.jazocar.jprocess.dao.service.IConfigJProcessService;
import cl.jazocar.jprocess.dto.AreaDTO;
import cl.jazocar.jprocess.dto.OrganizacionDTO;
import cl.jazocar.jprocess.dto.SubareaDTO;
import cl.jazocar.jprocess.dto.UsuarioDTO;
import cl.jazocar.jprocess.sys.dto.RolDTO;
import cl.jazocar.jprocess.sys.front.MenuFrontBean;
import cl.jazocar.jprocess.util.EncrypterDecrypter;

import com.icesoft.faces.context.effects.BlindDown;
import com.icesoft.faces.context.effects.Effect;

/**
 * @author Jazocar Soluciones Informaticas
 * @fecha 01-07-2013
 * @version 1.0.0
 * @description
 */
@ManagedBean(name = UsuarioFrontBean.USUARIO_BEAN)
@SessionScoped
public class UsuarioFrontBean {

	public static final String USUARIO_BEAN = "usuarioFrontBean";
	public static UsuarioDTO usuarioBean = null;
	private UsuarioDTO usuarioDto;

	private IConfigJProcessService configJProcessService;

	private List<UsuarioDTO> listaUsuarios;

	private List<SelectItem> listaOrg;
	private List<SelectItem> listaArea;
	private List<SelectItem> listaSubarea;
	private List<SelectItem> listaRol;

	private String inputUsuario;
	private String inputPassword;
	private String inputRepassword;
	private String inputNombre;
	private String inputRol;
	private String inputOrganizacion;
	private String inputArea;
	private String inputSubarea;

	private boolean selected;

	private boolean lock;
	private boolean lockOut;

	private boolean mostrarBanco;
	private boolean esAdministrador;

	private boolean abrirPopup;

	// Login.
	private String usuario;
	private String clave;
	private String empresa;

	private String nombreUsuario;

	private BlindDown effectCustom;

	private static UsuarioDTO selectedUsuario;
	
	private int idUsuario;

	public UsuarioFrontBean() {

		configJProcessService = JProcessFactoryService.getInstance()
				.getService("CONF_JPROCESS_SERVICE",
						IConfigJProcessService.class);

		listaOrg = new ArrayList<SelectItem>();
		listaArea = new ArrayList<SelectItem>();
		listaSubarea = new ArrayList<SelectItem>();
		listaRol = new ArrayList<SelectItem>();

		for (OrganizacionDTO org : configJProcessService
				.selectAllOrganizacion())
			listaOrg.add(new SelectItem(org.getId(), org.getNombre()));

		for (AreaDTO area : configJProcessService.selectAreas())
			listaArea.add(new SelectItem(area.getId(), area.getNombre()));
		
		for(SubareaDTO subarea : configJProcessService.selectSubareas())
			listaSubarea.add(new SelectItem(subarea.getId(), subarea.getNombre()));

		for (RolDTO rol : configJProcessService.selectAllRol())
			listaRol.add(new SelectItem(rol.getId(), rol.getNombreRol()));

		listaUsuarios = configJProcessService.getAllUsuarios();
	}

	public void insertUsuarioListener(ActionEvent e) {
		try {
			if (inputPassword.equals(inputRepassword)) {
				usuarioDto = new UsuarioDTO();
				usuarioDto.setUsuario(inputUsuario);
				usuarioDto.setRol(Integer.parseInt(inputRol));
				usuarioDto.setNombre(inputNombre);
				usuarioDto.setOrganizacion(Integer.parseInt(inputOrganizacion));
				usuarioDto.setArea(Integer.parseInt(inputArea));
				usuarioDto.setSubarea(Integer.parseInt(inputSubarea));
				configJProcessService.insertUsuario(usuarioDto);
				resetUsuario();
			} else {
				System.out.println("Las password deben coincidir");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateUsuarioListener(ActionEvent e) {
		try {
			if (inputPassword.equals(inputRepassword)) {
				usuarioDto = new UsuarioDTO();
				usuarioDto.setId(idUsuario);
				usuarioDto.setUsuario(inputUsuario);
				usuarioDto.setRol(Integer.parseInt(inputRol));
				usuarioDto.setNombre(inputNombre);
				usuarioDto.setOrganizacion(Integer.parseInt(inputOrganizacion));
				usuarioDto.setArea(Integer.parseInt(inputArea));
				usuarioDto.setSubarea(Integer.parseInt(inputSubarea));
				configJProcessService.updateUsuario(usuarioDto);
				resetUsuario();

			} else {
				System.out.println("Las password deben coincidir");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deleteUsuarioListener(ActionEvent e) {
		try {
			if (inputPassword.equals(inputRepassword)) {
				usuarioDto = new UsuarioDTO();
				usuarioDto.setId(idUsuario);
				
				configJProcessService.deleteUsuario(usuarioDto);
				resetUsuario();

			} else {
				System.out.println("Las password deben coincidir");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void resetUsuarioListener(ActionEvent e){
		resetUsuario();
	}
	
	public void resetUsuario(){
		inputUsuario = "";
		inputPassword = "";
		inputRepassword = "";
		inputNombre     = "";
		inputRol        = "-1";
		inputOrganizacion = "-1";
		inputArea         = "-1";
		inputSubarea      = "-1";
		
		listaUsuarios = configJProcessService.getAllUsuarios();
		selected = false;
	}
	
	public void usuarioSelectListener(SelectEvent event) {
		try{
		usuarioDto = (UsuarioDTO) event.getObject();	
		
		inputUsuario      = usuarioDto.getUsuario();
		inputPassword     = EncrypterDecrypter.decrypt(usuarioDto.getPassword());
		inputRepassword   = usuarioDto.getPassword();
		inputNombre       = usuarioDto.getNombre();
		inputRol          = String.valueOf(usuarioDto.getRol());
		inputOrganizacion = String.valueOf(usuarioDto.getOrganizacion());
		inputArea         = String.valueOf(usuarioDto.getArea());
		inputSubarea      = String.valueOf(usuarioDto.getSubarea());
		idUsuario = usuarioDto.getId();
		
		selected = true;
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void loginUsuarioListener(ActionEvent e) {
		try {
			if (this.usuario != null && !this.usuario.equals("")) {
				if (!clave.equals("")) {
					UsuarioDTO usuario = configJProcessService.loginUsuario(
							this.usuario, EncrypterDecrypter.encrypt(clave));

					if (usuario != null) {

						if (usuario.getEstado() == 3) {
							empresa = "";
							clave = "";
						} else {
							usuarioBean = new UsuarioDTO();
							usuarioBean.setId(usuario.getId());
							usuarioBean.setUsuario(this.usuario);
							usuarioBean.setPassword(clave);
							usuarioBean.setRol(usuario.getRol());
							usuarioBean.setOrganizacion(usuario
									.getOrganizacion());

							lockOut = true;
							nombreUsuario = usuario.getNombre();

							MenuFrontBean.listaMenu = configJProcessService
									.obtenerMenuPerfiladoByRol(usuario.getRol());

							if (usuarioBean.getRol() == 1
									|| usuarioBean.getRol() == 2)
								esAdministrador = true;
							else
								esAdministrador = false;

							ToDoFrontBean.listaSolicitud = configJProcessService
									.selectAllSolicitudesPendientesByRol(usuarioBean
											.getRol());

							NavigationBean.selectedPanel = "inicioPorHacerPanel";

						}

					} else {
						this.usuario = "";
						clave = "";
					}
				} else {
					this.usuario = "";
					clave = "";
				}
			} else {
				this.usuario = "";
				clave = "";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void limpiarLoginListener(ActionEvent e) {
		limpiarLogin();
	}

	public void limpiarLogin() {
		usuario = "";
		clave = "";
	}

	public void changeOrganizacion(ValueChangeEvent event) {
		String option = event.getNewValue().toString();
		if (option.equals("Banco")) {
			mostrarBanco = true;
		} else {
			mostrarBanco = false;
		}
	}

	public void logoutUsuarioListener(ActionEvent e) {
		// if(usuarioBean != null){
		// usuarioBean = null;
		// lockOut = false;
		// BridgeFacesContext fc = ((BridgeFacesContext)
		// FacesContext.getCurrentInstance());
		// InterceptingServletSession s = (InterceptingServletSession)
		// fc.getExternalContext().getSession(false);
		// s.invalidate();
		/*
		 * FacesContext facesContext = FacesContext.getCurrentInstance();
		 * HttpSession httpSession =
		 * (HttpSession)facesContext.getExternalContext().getSession(false);
		 * httpSession.invalidate();
		 */
		try {
			ExternalContext ectx = FacesContext.getCurrentInstance()
					.getExternalContext();
			HttpServletRequest req = (HttpServletRequest) ectx.getRequest();
			HttpSession session = req.getSession(false);
			if (session != null) {
				session.invalidate();
			}
		} catch (SessionExpiredException see) {

		}
		// }
	}

	public void closeLoginUsuarioListener(ActionEvent e) {
		this.lock = false;
		usuario = "";
		clave = "";
	}

	public void openLoginListener(ActionEvent e) {
		this.lock = true;
	}

	public Effect getEffectCustom() {
		return effectCustom;
	}

	public void setEffectCustom(BlindDown effectCustom) {
		this.effectCustom = effectCustom;
	}

	/**
	 * @return the usuarioBean
	 */
	public UsuarioDTO getUsuarioBean() {
		return usuarioBean;
	}

	/**
	 * @param usuarioBean
	 *            the usuarioBean to set
	 */
	public void setUsuarioBean(UsuarioDTO usuarioBean) {
		UsuarioFrontBean.usuarioBean = usuarioBean;
	}

	/**
	 * @return the listaUsuarios
	 */
	public List<UsuarioDTO> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * @param listaUsuarios
	 *            the listaUsuarios to set
	 */
	public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave
	 *            the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the lock
	 */
	public boolean isLock() {
		return lock;
	}

	/**
	 * @param lock
	 *            the lock to set
	 */
	public void setLock(boolean lock) {
		this.lock = lock;
	}

	/**
	 * @return the lockOut
	 */
	public boolean isLockOut() {
		return lockOut;
	}

	/**
	 * @param lockOut
	 *            the lockOut to set
	 */
	public void setLockOut(boolean lockOut) {
		this.lockOut = lockOut;
	}

	/**
	 * @return the inputUsuario
	 */
	public String getInputUsuario() {
		return inputUsuario;
	}

	/**
	 * @return the inputNombre
	 */
	public String getInputNombre() {
		return inputNombre;
	}

	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param nombreUsuario
	 *            the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * @return the selectedUsuario
	 */
	public static UsuarioDTO getSelectedUsuario() {
		return selectedUsuario;
	}

	/**
	 * @param selectedUsuario
	 *            the selectedUsuario to set
	 */
	public static void setSelectedUsuario(UsuarioDTO selectedUsuario) {
		UsuarioFrontBean.selectedUsuario = selectedUsuario;
	}

	/**
	 * @return the isSelected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param isSelected
	 *            the isSelected to set
	 */
	public void setSelected(boolean isSelected) {
		this.selected = isSelected;
	}

	/**
	 * @return the abrirPopup
	 */
	public boolean isAbrirPopup() {
		return abrirPopup;
	}

	/**
	 * @param abrirPopup
	 *            the abrirPopup to set
	 */
	public void setAbrirPopup(boolean abrirPopup) {
		this.abrirPopup = abrirPopup;
	}

	/**
	 * @return the mostrarBanco
	 */
	public boolean isMostrarBanco() {
		return mostrarBanco;
	}

	/**
	 * @param mostrarBanco
	 *            the mostrarBanco to set
	 */
	public void setMostrarBanco(boolean mostrarBanco) {
		this.mostrarBanco = mostrarBanco;
	}

	/**
	 * @return the esAdministrador
	 */
	public boolean isEsAdministrador() {
		return esAdministrador;
	}

	/**
	 * @param esAdministrador
	 *            the esAdministrador to set
	 */
	public void setEsAdministrador(boolean esAdministrador) {
		this.esAdministrador = esAdministrador;
	}

	/**
	 * @return the listaOrg
	 */
	public List<SelectItem> getListaOrg() {
		return listaOrg;
	}

	/**
	 * @param listaOrg
	 *            the listaOrg to set
	 */
	public void setListaOrg(List<SelectItem> listaOrg) {
		this.listaOrg = listaOrg;
	}

	/**
	 * @return the listaArea
	 */
	public List<SelectItem> getListaArea() {
		return listaArea;
	}

	/**
	 * @param listaArea
	 *            the listaArea to set
	 */
	public void setListaArea(List<SelectItem> listaArea) {
		this.listaArea = listaArea;
	}

	/**
	 * @return the listaSubarea
	 */
	public List<SelectItem> getListaSubarea() {
		return listaSubarea;
	}

	/**
	 * @param listaSubarea
	 *            the listaSubarea to set
	 */
	public void setListaSubarea(List<SelectItem> listaSubarea) {
		this.listaSubarea = listaSubarea;
	}

	/**
	 * @return the listaRol
	 */
	public List<SelectItem> getListaRol() {
		return listaRol;
	}

	/**
	 * @param listaRol
	 *            the listaRol to set
	 */
	public void setListaRol(List<SelectItem> listaRol) {
		this.listaRol = listaRol;
	}

	/**
	 * @return the usuarioDto
	 */
	public UsuarioDTO getUsuarioDto() {
		return usuarioDto;
	}

	/**
	 * @param usuarioDto the usuarioDto to set
	 */
	public void setUsuarioDto(UsuarioDTO usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

	/**
	 * @return the inputPassword
	 */
	public String getInputPassword() {
		return inputPassword;
	}

	/**
	 * @param inputPassword the inputPassword to set
	 */
	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}

	/**
	 * @return the inputRepassword
	 */
	public String getInputRepassword() {
		return inputRepassword;
	}

	/**
	 * @param inputRepassword the inputRepassword to set
	 */
	public void setInputRepassword(String inputRepassword) {
		this.inputRepassword = inputRepassword;
	}

	/**
	 * @return the inputRol
	 */
	public String getInputRol() {
		return inputRol;
	}

	/**
	 * @param inputRol the inputRol to set
	 */
	public void setInputRol(String inputRol) {
		this.inputRol = inputRol;
	}

	/**
	 * @return the inputOrganizacion
	 */
	public String getInputOrganizacion() {
		return inputOrganizacion;
	}

	/**
	 * @param inputOrganizacion the inputOrganizacion to set
	 */
	public void setInputOrganizacion(String inputOrganizacion) {
		this.inputOrganizacion = inputOrganizacion;
	}

	/**
	 * @return the inputArea
	 */
	public String getInputArea() {
		return inputArea;
	}

	/**
	 * @param inputArea the inputArea to set
	 */
	public void setInputArea(String inputArea) {
		this.inputArea = inputArea;
	}

	/**
	 * @return the inputSubarea
	 */
	public String getInputSubarea() {
		return inputSubarea;
	}

	/**
	 * @param inputSubarea the inputSubarea to set
	 */
	public void setInputSubarea(String inputSubarea) {
		this.inputSubarea = inputSubarea;
	}

	/**
	 * @param inputUsuario the inputUsuario to set
	 */
	public void setInputUsuario(String inputUsuario) {
		this.inputUsuario = inputUsuario;
	}

	/**
	 * @param inputNombre the inputNombre to set
	 */
	public void setInputNombre(String inputNombre) {
		this.inputNombre = inputNombre;
	}
	
	

}
