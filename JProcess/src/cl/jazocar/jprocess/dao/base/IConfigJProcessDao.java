package cl.jazocar.jprocess.dao.base;

import java.util.List;

import cl.jazocar.jprocess.dto.AplicacionDTO;
import cl.jazocar.jprocess.dto.AreaDTO;
import cl.jazocar.jprocess.dto.CasoComentarioDTO;
import cl.jazocar.jprocess.dto.CreacionDTO;
import cl.jazocar.jprocess.dto.OrganizacionDTO;
import cl.jazocar.jprocess.dto.PendienteDTO;
import cl.jazocar.jprocess.dto.SolicitudDTO;
import cl.jazocar.jprocess.dto.SubareaDTO;
import cl.jazocar.jprocess.dto.TareaConfiguracionDTO;
import cl.jazocar.jprocess.dto.TareaDTO;
import cl.jazocar.jprocess.dto.UsuarioDTO;
import cl.jazocar.jprocess.exception.JProcessPersistenceException;
import cl.jazocar.jprocess.model.ConfigModel;
import cl.jazocar.jprocess.sys.dto.MenuDTO;
import cl.jazocar.jprocess.sys.dto.PanelDTO;
import cl.jazocar.jprocess.sys.dto.RolDTO;
import cl.jazocar.jprocess.sys.dto.SubmenuDTO;

public interface IConfigJProcessDao {

	public ConfigModel getConfigModel(String id) throws JProcessPersistenceException;

	public List<ConfigModel> getConfigModelList()
			throws JProcessPersistenceException;

	public Integer insertUserProfileModel(String rut, int idProfile)
			throws JProcessPersistenceException;

	public Integer updateUserProfileModel(String rut, int newProfile,
			int oldProfile) throws JProcessPersistenceException;

	public Integer deleteUserProfileModel(String rut, int profile)
			throws JProcessPersistenceException;

	/* Usuarios */
	public Integer insertUsuario(UsuarioDTO usuario);

	public Integer updateUsuario(UsuarioDTO usuario);

	public Integer deleteUsuario(UsuarioDTO usuario);

	public List<UsuarioDTO> getAllUsuarios();

	public List<UsuarioDTO> getAllUsuariosSoporte();

	public List<UsuarioDTO> getAllUsuariosSoporteAsignacion();

	public List<UsuarioDTO> getAllUsuariosActivos();

	public Integer darBajaAltaUsuario(int id, String accion);

	public UsuarioDTO getUsuario(int idUsuario);

	public UsuarioDTO loginUsuario(String usuario, String password);

	public Integer updatePasswordUsuario(UsuarioDTO usuario);

	/* Systema */
	/* Menu */
	public Integer insertMenu(String idMenu, String label);

	public Integer updateMenu(int id, String idMenu, String label, int order);

	public Integer deleteMenu(int id);

	public List<MenuDTO> selectMenu();

	public Integer clearMenuRol(int idMenu);

	public Integer addMenuRol(int idMenu, int idRol);

	public List<MenuDTO> obtenerMenuPerfiladoByRol(int rol);

	public List<MenuDTO> obtenerMenuPerfiladoByRolOposite(int rol);

	/* Submenu */
	public Integer insertSubmenu(String submenuId, String etiqueta,
			String stackName, int idMenu);

	public Integer updateSubmenu(int id, String submenuId, String etiqueta,
			String stackName, int idMenu, int order);

	public Integer deleteSubmenu(int id);

	public List<SubmenuDTO> selectAllSubmenu();

	public List<SubmenuDTO> selectSubmenuByMenu(int idMenu);

	/* Paneles */
	public Integer insertPanel(PanelDTO panel);

	public Integer updatePanel(PanelDTO panel);

	public Integer deletePanel(int id);

	public List<PanelDTO> selectAllPanel();

	/* Roles */
	public Integer insertRol(RolDTO rol);

	public Integer updateRol(RolDTO rol);

	public Integer deleteRol(int id);

	public List<RolDTO> selectAllRol();

	public RolDTO selectRolById(int id);

	public List<RolDTO> selectRolByMenu(int idMenu);

	/* Tarea */

	public Integer insertTarea(TareaDTO tarea);

	public Integer updateTarea(TareaDTO tarea);

	public Integer deleteTarea(TareaDTO tarea);

	public List<TareaDTO> selectAllTarea();

	public TareaDTO selectTareaById(int id);

	/* Aplicacion */
	public Integer insertAplicacion(AplicacionDTO aplicacion);

	public Integer updateAplicacion(AplicacionDTO aplicacion);

	public Integer deleteAplicacion(AplicacionDTO aplicacion);

	public List<AplicacionDTO> selectAllAplicacion();

	public AplicacionDTO selectAplicacionById(int id);

	public Integer updateInicioFinAplicacion(int id);

	/* Organizacion */
	public OrganizacionDTO getOrganizacionById(int id);

	public Integer insertOrganizacion(OrganizacionDTO organizacion);

	public Integer updateOrganizacion(OrganizacionDTO organizacion);

	public Integer deleteOrganizacion(OrganizacionDTO organizacion);

	public List<OrganizacionDTO> selectAllOrganizacion();

	/* Creación */
	public Integer insertCreacion(CreacionDTO creacion);

	public Integer updateCreacion(CreacionDTO creacion);

	public Integer deleteCreacion(CreacionDTO creacion);

	public List<CreacionDTO> selectAllCreacion();

	public CreacionDTO selectCreacionById(int id);

	public List<CreacionDTO> selectCreacionByIdAplicacion(int idAplicacion);

	public CreacionDTO selectCreacionByTareaInicial(int idTarea,
			int idAplicacion);

	public Integer cerrarCreacion(int idPendiente);

	public Integer despacharCreacion(int idTarea, int idSolicitud, int rol);

	/* Solicitudes */
	public Integer insertSolicitud(SolicitudDTO solicitud);

	public Integer desistirSolicitud(SolicitudDTO solicitud);

	public Integer eliminarSolicitud(SolicitudDTO solicitud);

	public List<SolicitudDTO> selectAllSolicitudes();

	public List<SolicitudDTO> selectAllSolicitudesByRol(int idRol);

	public List<SolicitudDTO> selectAllSolicitudesPendientesByRol(int idRol);

	public SolicitudDTO selectSolicitudById(int idSolicitud);

	public int selectNewSolicitudId();

	public TareaDTO selectTareaActualBySolicitudId(int idSolicitud);

	public Integer cerrarSolicitud(int idSolicitud);

	public Integer asignarResponsable(int idUsuario, int idSolicitud);

	public Integer liberarResponsable(int idUsuario, int idSolicitud);

	/* Solicitud Comentarios */
	public Integer insertSolicitudComentario(int solicitudId, String usuario,
			String comentario);

	public List<CasoComentarioDTO> selectComentariosBySolicitud(int idSolicitud);

	/* Pendientes */
	public Integer insertPendiente(PendienteDTO pendiente);

	public List<PendienteDTO> selectAllPendientes();

	public List<PendienteDTO> selectAllPendientesByRol(int idRol);

	public List<PendienteDTO> selectAllPendientesBySolicitud(int idSolicitud);

	public PendienteDTO selectPendientesById(int id);

	public PendienteDTO selectPendientesBySolicitud(int id);

	/* Tarea Configuracion */
	public Integer insertTareaConfiguracion(TareaConfiguracionDTO configuracion);

	public Integer updateTareaConfiguracion(TareaConfiguracionDTO configuracion);

	public Integer deleteTareaConfiguracion(TareaConfiguracionDTO configuracion);

	public List<TareaConfiguracionDTO> selectTareaConfiguracion();

	public List<TareaConfiguracionDTO> selectTareaConfiguracionByTarea(
			int idTarea);

	public TareaConfiguracionDTO selectTareaConfiguracionById(int id);

	/* Áreas */
	public Integer insertArea(AreaDTO area);

	public Integer updateArea(AreaDTO area);

	public Integer deleteArea(AreaDTO area);

	public List<AreaDTO> selectAreas();

	public List<AreaDTO> selectAreasByOrganizacion(int idOrganizacion);

	public AreaDTO selectAreaById(int idArea);

	/* Sub Áreas */
	public Integer insertSubarea(SubareaDTO subarea);

	public Integer updateSubarea(SubareaDTO subarea);

	public Integer deleteSubarea(SubareaDTO subarea);

	public List<SubareaDTO> selectSubareas();

	public List<SubareaDTO> selectSubareasByArea(int idArea);

	public SubareaDTO selectSubareaById(int idSubarea);

}
