package cl.jazocar.jprocess.dao.base.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;

import cl.jazocar.jprocess.common.context.JProcessContextImpl;
import cl.jazocar.jprocess.constants.IConstants;
import cl.jazocar.jprocess.dao.base.IConfigJProcessDao;
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
import cl.jazocar.jprocess.ibatis.dao.MyBatisCommonDao;
import cl.jazocar.jprocess.model.ConfigModel;
import cl.jazocar.jprocess.sys.dto.MenuDTO;
import cl.jazocar.jprocess.sys.dto.PanelDTO;
import cl.jazocar.jprocess.sys.dto.RolDTO;
import cl.jazocar.jprocess.sys.dto.SubmenuDTO;

public class ConfigJProcessDaoImpl extends MyBatisCommonDao implements IConfigJProcessDao {

	@Override
	protected SqlSessionFactory getSqlSessionFactory()
	{
	   return JProcessContextImpl.getInstance().lookup(IConstants.JPROCESS_PERSITENCE) ;
	}

	@Override
	public ConfigModel getConfigModel(String id) throws JProcessPersistenceException 
	{
		return genericSelectOne("mybatis.mtogen.SelectOneConfigModel", id);
	}

	@Override
	public List<ConfigModel> getConfigModelList() throws JProcessPersistenceException 
	{
		return genericSelect("mybatis.mtogen.SelectListConfigModel");
	}

	
	@Override
	public Integer insertUserProfileModel(String rut, int idProfile)
			throws JProcessPersistenceException {
		Map<String,String> map = new HashMap<String, String>();
		map.put("rut", rut );
		map.put("profile", Integer.toString(idProfile));
		return genericInsert("mybatis.mtogen.InsertUserProfile", map);
	}

	@Override
	public Integer updateUserProfileModel(String rut, int newProfile, int oldProfile)
			throws JProcessPersistenceException 
	{
		Map<String,String> map = new HashMap<String, String>();
		map.put("rut", rut );
		map.put("newProfile", Integer.toString(newProfile));
		map.put("oldProfile", Integer.toString(oldProfile));
		return genericUpdate( "mybatis.mtogen.UpdateUserProfile", map );
	}

	@Override
	public Integer deleteUserProfileModel(String rut, int profile)
			throws JProcessPersistenceException {
		Map<String,String> map = new HashMap<String, String>();
		map.put("rut", rut );
		map.put("profile", Integer.toString(profile));
		return genericDelete("mybatis.mtogen.DeleteUserProfile", map);
	}

	

	@Override
	public Integer insertMenu(String idMenu, String label) {
		// TODO Auto-generated method stub
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("id_menu", idMenu);
		hash.put("label", label);
		return genericInsert("mybatis.jprocess.InsertJProcessInsertarMenu", hash);
	}

	@Override
	public Integer updateMenu(int id, String idMenu, String label, int order) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", id);
		hash.put("id_menu", idMenu);
		hash.put("label", label);
		hash.put("order", order);
		return genericUpdate("mybatis.jprocess.UpdateJProcessModificarMenu", hash);
	}

	@Override
	public Integer deleteMenu(int id) {
		// TODO Auto-generated method stub
		return genericUpdate("mybatis.jprocess.UpdateSystemJProcessDaBajaMenu", id);
	}

	@Override
	public List<MenuDTO> selectMenu() {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectSystemJProcessMenu");
	}

	@Override
	public Integer insertSubmenu(String submenuId, String etiqueta,
			String stackName, int idMenu) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("name_id", submenuId);
		hash.put("etiqueta", etiqueta);
		hash.put("stack_name", stackName);
		hash.put("menu_id", idMenu);
		return genericInsert("mybatis.jprocess.InsertSystemJProcessSubmenu", hash);
	}

	@Override
	public Integer updateSubmenu(int id, String submenuId, String etiqueta,
			String stackName, int idMenu, int order) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", id);
		hash.put("submenu_id", submenuId);
		hash.put("etiqueta", etiqueta);
		hash.put("stack_name", stackName);
		hash.put("menu_id", idMenu);
		hash.put("order", order);
		return genericUpdate("mybatis.jprocess.UpdateSystemJProcessModificarSubmenu", hash);
	}

	@Override
	public Integer deleteSubmenu(int id) {
		// TODO Auto-generated method stub
		return genericUpdate("mybatis.jprocess.UpdateSystemJProcessDaBajaSubMenu", id);
	}

	@Override
	public List<SubmenuDTO> selectAllSubmenu() {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectSystemJProcessAllSubmenu");
	}

	@Override
	public List<SubmenuDTO> selectSubmenuByMenu(int idMenu) {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectSystemJProcessSubmenu", idMenu);
	}

	@Override
	public Integer insertPanel(PanelDTO panel) {
		// TODO Auto-generated method stub
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("panel_id", panel.getIdPanel());
		hash.put("subview_id", panel.getIdSubview());
		hash.put("include", panel.getInclude());
		return genericInsert("mybatis.jprocess.InsertSystemJProcessPanel", hash);
	}

	@Override
	public Integer updatePanel(PanelDTO panel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deletePanel(int id) {
		// TODO Auto-generated method stub
		return genericDelete("mybatis.jprocess.DeleteSystemJProcessPanel", id);
	}

	@Override
	public List<PanelDTO> selectAllPanel() {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectSystemJProcessPanel");
	}

	@Override
	public Integer insertRol(RolDTO rol) {
		// TODO Auto-generated method stub
		HashMap<String,String> hash = new HashMap<String, String>();
		hash.put("valor", rol.getNombreRol());
		return genericInsert("mybatis.jprocess.InsertSystemJProcessRol", hash);
	}

	@Override
	public Integer updateRol(RolDTO rol) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("valor", rol.getNombreRol());
		hash.put("id", rol.getId());
		return genericUpdate("mybatis.jprocess.UpdateSystemJProcessRol", hash);
	}

	@Override
	public Integer deleteRol(int id) {
		// TODO Auto-generated method stub
		return genericUpdate("mybatis.jprocess.DeleteSystemJProcessRol", id);
	}

	@Override
	public List<RolDTO> selectAllRol() {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectSystemJProcessRoles");
	}

	@Override
	public List<MenuDTO> obtenerMenuPerfiladoByRol(int rol) {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectSystemJProcessMenuPerfilado", rol);
	}

	@Override
	public List<MenuDTO> obtenerMenuPerfiladoByRolOposite(int rol) {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectSystemJProcessMenuPerfilado", rol);
	}

	@Override
	public List<RolDTO> selectRolByMenu(int idMenu) {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectSystemJProcessRolByMenu", idMenu);
	}

	@Override
	public Integer clearMenuRol(int idMenu) {
		// TODO Auto-generated method stub
		return genericDelete("mybatis.jprocess.DeleteSystemJProcessMenuRol", idMenu);
	}

	@Override
	public Integer addMenuRol(int idMenu, int idRol) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash.put("id_menu", idMenu);
		hash.put("id_rol", idRol);
		return genericInsert("mybatis.jprocess.InsertSystemJProcessMenuRol", hash);
	}
	

	@Override
	public Integer insertUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("usuario", usuario.getUsuario());
		hash.put("password", usuario.getPassword());
		hash.put("rol", usuario.getRol());
		hash.put("nombre", usuario.getNombre());
		hash.put("area", usuario.getArea());
		hash.put("subarea", usuario.getSubarea());		
		return genericInsert("mybatis.jprocess.InsertJProcessUsers", hash);
	}

	@Override
	public Integer updateUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", usuario.getId());
		hash.put("usuario", usuario.getUsuario());
		hash.put("nombre", usuario.getNombre());
		hash.put("rol", usuario.getRol());
		hash.put("estado", usuario.getEstado());
		hash.put("organizacion", usuario.getOrganizacion());
		hash.put("area", usuario.getArea());
		hash.put("subarea", usuario.getSubarea());		
		return genericUpdate("mybatis.jprocess.UpdateJProcessModificarUsuario", hash);
	}

	@Override
	public Integer deleteUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash.put("id", usuario.getId());
		return genericUpdate("mybatis.jprocess.UpdateDeleteUsuario", hash);
	}

	@Override
	public List<UsuarioDTO> getAllUsuarios() {
		// TODO Auto-generated method stub  
		return genericSelect("mybatis.jprocess.SelectJProcessAllUsers");
	}

	@Override
	public List<UsuarioDTO> getAllUsuariosSoporte() {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectJProcessAllUsersSoporte");
	}

	@Override
	public List<UsuarioDTO> getAllUsuariosSoporteAsignacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioDTO> getAllUsuariosActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer darBajaAltaUsuario(int id, String accion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDTO getUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectUsuario", idUsuario);
	}

	@Override
	public UsuarioDTO loginUsuario(String usuario, String password) {
		// TODO Auto-generated method stub
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("usuario", usuario);
		hash.put("password", password);
		return genericSelectOne("mybatis.jprocess.SelectJProcessLoginUsuario", hash);
	}

	@Override
	public Integer updatePasswordUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertTarea(TareaDTO tarea) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nombre", tarea.getNombre());
		hash.put("descripcion", tarea.getDescripcion());
		hash.put("prioridad", tarea.getPrioridad());
		hash.put("tipo_tarea", tarea.getTipoTarea());
		hash.put("organizacion", tarea.getIdOrganizacion());
		hash.put("tipo_duracion", tarea.getTipoDuracion());
		hash.put("duracion", tarea.getDuracion());
		hash.put("tipo_asignacion", tarea.getTipoAsignacion());
		hash.put("rol", tarea.getIdRolAsignacion());
		return genericInsert("mybatis.jprocess.InsertTarea", hash);
	}

	@Override
	public Integer updateTarea(TareaDTO tarea) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", tarea.getId());
		hash.put("nombre", tarea.getNombre());
		hash.put("descripcion", tarea.getDescripcion());
		hash.put("prioridad", tarea.getPrioridad());
		hash.put("tipo_tarea", tarea.getTipoTarea());
		hash.put("organizacion", tarea.getIdOrganizacion());
		hash.put("tipo_duracion", tarea.getTipoDuracion());
		hash.put("duracion", tarea.getDuracion());
		hash.put("tipo_asignacion", tarea.getTipoAsignacion());
		hash.put("rol", tarea.getIdRolAsignacion());
		return genericInsert("mybatis.jprocess.UpdateTarea", hash);
	}

	@Override
	public Integer deleteTarea(TareaDTO tarea) {
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash.put("id", tarea.getId());
		return genericInsert("mybatis.jprocess.DeleteTarea", hash);
	}

	@Override
	public List<TareaDTO> selectAllTarea() {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectTareas");
	}

	@Override
	public TareaDTO selectTareaById(int id) {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectTareaById", id);
	}

	@Override
	public OrganizacionDTO getOrganizacionById(int id) {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectOrganizacionById", id);
	}

	@Override
	public Integer insertOrganizacion(OrganizacionDTO organizacion) {
		// TODO Auto-generated method stub
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("valor", organizacion.getNombre());
		return genericInsert("mybatis.jprocess.InsertOrganizacion", hash);
	}

	@Override
	public Integer updateOrganizacion(OrganizacionDTO organizacion) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", organizacion.getId());
		hash.put("valor", organizacion.getNombre());
		return genericUpdate("mybatis.jprocess.UpdateOrganizacion", hash);
	}

	@Override
	public Integer deleteOrganizacion(OrganizacionDTO organizacion) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", organizacion.getId());
		return genericDelete("mybatis.jprocess.DeleteOrganizacion", hash);
	}

	@Override
	public List<OrganizacionDTO> selectAllOrganizacion() {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectOrganizacion");
	}

	@Override
	public RolDTO selectRolById(int id) {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectRolById", id);
	}

	@Override
	public Integer insertAplicacion(AplicacionDTO aplicacion) {
		// TODO Auto-generated method stub
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("aplicacion", aplicacion.getAplicacion());
		hash.put("descripcion", aplicacion.getDescripcion());
		hash.put("version", aplicacion.getVersion());
		return genericInsert("mybatis.jprocess.InsertAplicacion", hash);
	}

	@Override
	public Integer updateAplicacion(AplicacionDTO aplicacion) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("aplicacion", aplicacion.getAplicacion());
		hash.put("descripcion", aplicacion.getDescripcion());
		hash.put("version", aplicacion.getVersion());
		hash.put("id", aplicacion.getId());
		return genericUpdate("mybatis.jprocess.UpdateAplicacion", hash);
	}

	@Override
	public Integer deleteAplicacion(AplicacionDTO aplicacion) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash.put("id", aplicacion.getId());
		return genericDelete("mybatis.jprocess.DeleteAplicacion", hash);
	}

	@Override
	public List<AplicacionDTO> selectAllAplicacion() {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectAplicacion");
	}

	@Override
	public AplicacionDTO selectAplicacionById(int id) {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectAplicacionById", id);
	}

	@Override
	public Integer insertCreacion(CreacionDTO creacion) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("aplicacion", creacion.getIdAplicacion());
		hash.put("inicio", creacion.getIdTareaInicio());
		hash.put("exito", creacion.getIdTareaExito());
		hash.put("condicional", creacion.getEsCondicional());
		hash.put("rechazo", creacion.getIdTareaRechazo());
		hash.put("tipo_destino", creacion.getTipoDestino());
		return genericInsert("mybatis.jprocess.InsertCreacion", hash);
	}

	@Override
	public Integer updateCreacion(CreacionDTO creacion) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", creacion.getId());
		hash.put("aplicacion", creacion.getIdAplicacion());
		hash.put("inicio", creacion.getIdTareaInicio());
		hash.put("exito", creacion.getIdTareaExito());
		hash.put("condicional", creacion.getEsCondicional());
		hash.put("rechazo", creacion.getIdTareaRechazo());
		hash.put("tipo_destino", creacion.getTipoDestino());		
		return genericUpdate("mybatis.jprocess.UpdateCreacion", hash);
	}

	@Override
	public Integer deleteCreacion(CreacionDTO creacion) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash.put("id", creacion.getId());
		return genericDelete("mybatis.jprocess.UpdateCreacion", hash);
	}

	@Override
	public List<CreacionDTO> selectAllCreacion() {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectCreacion");
	}

	@Override
	public List<CreacionDTO> selectCreacionByIdAplicacion(int idAplicacion) {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectCreacionByAplicacion", idAplicacion);
	}

	@Override
	public CreacionDTO selectCreacionById(int id) {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectCreacionById", id);
	}

	@Override
	public Integer updateInicioFinAplicacion(int id) {
		// TODO Auto-generated method stub
		return genericUpdate("mybatis.jprocess.UpdateAplicacionInicioFin", id);
	}

	@Override
	public Integer insertSolicitud(SolicitudDTO solicitud) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", solicitud.getId());
		hash.put("aplicacion", solicitud.getIdAplicacion());
		return genericInsert("mybatis.jprocess.InsertSolicitud", hash);
	}

	@Override
	public Integer desistirSolicitud(SolicitudDTO solicitud) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer eliminarSolicitud(SolicitudDTO solicitud) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SolicitudDTO> selectAllSolicitudes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SolicitudDTO> selectAllSolicitudesByRol(int idRol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SolicitudDTO> selectAllSolicitudesPendientesByRol(int idRol) {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectSolicitudesPendientesPorRol", idRol);
	}

	@Override
	public SolicitudDTO selectSolicitudById(int idSolicitud) {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectSolicitudById", idSolicitud);
	}

	@Override
	public Integer insertPendiente(PendienteDTO pendiente) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("solicitud", pendiente.getIdSolicitud());
		hash.put("tarea", pendiente.getIdTarea());
		hash.put("rol", pendiente.getIdRol());
		return genericInsert("mybatis.jprocess.InsertPendiente", hash);
	}

	@Override
	public List<PendienteDTO> selectAllPendientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PendienteDTO> selectAllPendientesByRol(int idRol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PendienteDTO> selectAllPendientesBySolicitud(int idSolicitud) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PendienteDTO selectPendientesById(int id) {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectPendienteById");
	}

	@Override
	public int selectNewSolicitudId() {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectNewSolicitudId");
	}

	@Override
	public TareaDTO selectTareaActualBySolicitudId(int idSolicitud) {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectTareaActualBySolicitudId", idSolicitud);
	}

	@Override
	public Integer insertSolicitudComentario(int solicitudId, String usuario,
			String comentario) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("solicitud", solicitudId);
		hash.put("usuario", usuario);
		hash.put("comentario", comentario);
		return genericInsert("mybatis.jprocess.InsertSolicitudComentario", hash);
	}

	@Override
	public List<CasoComentarioDTO> selectComentariosBySolicitud(int idSolicitud) {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectComentariosBySolicitud", idSolicitud);
	}

	@Override
	public CreacionDTO selectCreacionByTareaInicial(int idTarea, int idAplicacion) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash.put("tarea", idTarea);
		hash.put("aplicacion", idAplicacion);
		return genericSelectOne("mybatis.jprocess.SelectCreacionByTarea", hash);
	}

	@Override
	public Integer cerrarCreacion(int idPendiente) {
		// TODO Auto-generated method stub
		return genericUpdate("mybatis.jprocess.UpdateCerrarCreacion", idPendiente);
	}

	@Override
	public Integer despacharCreacion(int idTarea, int idSolicitud, int idRol) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> hash = new  HashMap<String, Integer>();
		hash.put("tarea", idTarea);
		hash.put("solicitud", idSolicitud);
		hash.put("rol", idRol);
		return genericInsert("mybatis.jprocess.InsertDespacharCreacion", hash);
	}

	@Override
	public Integer cerrarSolicitud(int idSolicitud) {
		// TODO Auto-generated method stub
		return genericUpdate("mybatis.jprocess.UpdateCerrarSolicitud", idSolicitud);
	}

	@Override
	public PendienteDTO selectPendientesBySolicitud(int id) {
		// TODO Auto-generated method stub
	   return genericSelectOne("mybatis.jprocess.SelectPendienteBySolicitud", id);
	}

	@Override
	public Integer asignarResponsable(int idUsuario, int idSolicitud) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash.put("usuario", idUsuario);
		hash.put("solicitud", idSolicitud);
		return genericUpdate("mybatis.jprocess.UpdateAsignarResponsableSolicitud", hash);
	}

	@Override
	public Integer liberarResponsable(int idUsuario, int idSolicitud) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash.put("usuario", idUsuario);
		hash.put("solicitud", idSolicitud);
		return genericUpdate("mybatis.jprocess.UpdateLiberarResponsableSolicitud", hash);
	}

	@Override
	public Integer insertTareaConfiguracion(TareaConfiguracionDTO configuracion) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("tarea", configuracion.getIdTarea());
		hash.put("option", configuracion.getCheckOption());
		hash.put("requerido", configuracion.isRequerido());
		return genericInsert("mybatis.jprocess.InsertTareaConfiguracion", hash);
	}

	@Override
	public Integer updateTareaConfiguracion(TareaConfiguracionDTO configuracion) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", configuracion.getId());		
		hash.put("tarea", configuracion.getIdTarea());
		hash.put("option", configuracion.getCheckOption());
		hash.put("requerido", configuracion.isRequerido());
		return genericUpdate("mybatis.jprocess.UpdateTareaConfiguracion", hash);
	}

	@Override
	public Integer deleteTareaConfiguracion(TareaConfiguracionDTO configuracion) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", configuracion.getId());
		return genericInsert("mybatis.jprocess.DeleteTareaConfiguracion", hash);
	}

	@Override
	public List<TareaConfiguracionDTO> selectTareaConfiguracion() {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectTareaConfiguracion");
	}

	@Override
	public List<TareaConfiguracionDTO> selectTareaConfiguracionByTarea(
			int idTarea) {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectTareaConfiguracionByTarea", idTarea);
	}

	@Override
	public TareaConfiguracionDTO selectTareaConfiguracionById(int id) {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectTareaConfiguracionById", id);
	}

	@Override
	public Integer insertArea(AreaDTO area) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("organizacion", area.getIdOrganizacion());
     	hash.put("nombre", area.getNombre());
		hash.put("descripcion", area.getDescripcion());
		return genericInsert("mybatis.jprocess.InsertArea", hash);
	}

	@Override
	public Integer updateArea(AreaDTO area) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", area.getId());
		hash.put("organizacion", area.getIdOrganizacion());
     	hash.put("nombre", area.getNombre());
		hash.put("descripcion", area.getDescripcion());
		return genericUpdate("mybatis.jprocess.UpdateArea", hash);
	}

	@Override
	public Integer deleteArea(AreaDTO area) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", area.getId());
		return genericUpdate("mybatis.jprocess.DeleteArea", hash);
	}

	@Override
	public List<AreaDTO> selectAreas() {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectAreas");
	}

	@Override
	public List<AreaDTO> selectAreasByOrganizacion(int idOrganizacion) {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectAreasByOrganizacion", idOrganizacion);
	}

	@Override
	public AreaDTO selectAreaById(int idArea) {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectAreaById", idArea);
	}

	@Override
	public Integer insertSubarea(SubareaDTO subarea) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("area", subarea.getIdArea());
		hash.put("nombre", subarea.getNombre());
		hash.put("descripcion", subarea.getDescripcion());
		return genericInsert("mybatis.jprocess.InsertSubarea", hash);
	}

	@Override
	public Integer updateSubarea(SubareaDTO subarea) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", subarea.getId());
		hash.put("area", subarea.getIdArea());
		hash.put("nombre", subarea.getNombre());
		hash.put("descripcion", subarea.getDescripcion());
		return genericUpdate("mybatis.jprocess.UpdateSubarea", hash);
	}

	@Override
	public Integer deleteSubarea(SubareaDTO subarea) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", subarea.getId());
		return genericDelete("mybatis.jprocess.DeleteSubarea", hash);
	}

	@Override
	public List<SubareaDTO> selectSubareas() {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectSubareas");
	}

	@Override
	public List<SubareaDTO> selectSubareasByArea(int idArea) {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.jprocess.SelectSubareaByArea", idArea);
	}

	@Override
	public SubareaDTO selectSubareaById(int idSubarea) {
		// TODO Auto-generated method stub
		return genericSelectOne("mybatis.jprocess.SelectSubareaById", idSubarea);
	}

	
}
