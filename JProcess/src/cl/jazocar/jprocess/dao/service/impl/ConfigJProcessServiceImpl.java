package cl.jazocar.jprocess.dao.service.impl;

import java.util.List;

import javax.swing.table.TableModel;

import org.apache.ibatis.session.SqlSessionFactory;

import cl.jazocar.jprocess.common.context.JProcessContextImpl;
import cl.jazocar.jprocess.constants.IConstants;
import cl.jazocar.jprocess.dao.base.IConfigJProcessDao;
import cl.jazocar.jprocess.dao.service.IConfigJProcessService;
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
import cl.jazocar.jprocess.exception.JProcessBusinessException;
import cl.jazocar.jprocess.ibatis.dao.MyBatisCommonDao;
import cl.jazocar.jprocess.model.ConfigModel;
import cl.jazocar.jprocess.sys.dto.MenuDTO;
import cl.jazocar.jprocess.sys.dto.PanelDTO;
import cl.jazocar.jprocess.sys.dto.RolDTO;
import cl.jazocar.jprocess.sys.dto.SubmenuDTO;

public class ConfigJProcessServiceImpl extends MyBatisCommonDao implements IConfigJProcessService 
{
	private IConfigJProcessDao configJProcessDao;
		
	/**
	 * @return the configBackupDao
	 */
	public IConfigJProcessDao getConfigJProcessDao() {
		return configJProcessDao;
	}

	/**
	 * @param iConfigBackupDao the configBackupDao to set
	 */
	public void setConfigJProcessDao(IConfigJProcessDao iConfigJProcessDao) {
		this.configJProcessDao = iConfigJProcessDao;
	}

	@Override
	public TableModel getTableModel(String id) throws JProcessBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableModel> getTableModelList() throws JProcessBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConfigModel> getConfigModelList() throws JProcessBusinessException {
		// TODO Auto-generated method stub
		return genericSelect("mybatis.mtogen.SelectListConfigModel");
	}

	@Override
	public ConfigModel getConfigModel(String configCode)
			throws JProcessBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNameTableModel(String string) throws JProcessBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUserProfile(String user, int profile)
			throws JProcessBusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserProfile(String user, int profile)
			throws JProcessBusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUserProfile(String user, int profile)
			throws JProcessBusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected SqlSessionFactory getSqlSessionFactory() {
		// TODO Auto-generated method stub
		return JProcessContextImpl.getInstance().lookup(IConstants.JPROCESS_PERSITENCE) ;
	}
	

	@Override
	public Integer insertMenu(String idMenu, String label) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertMenu(idMenu, label);
	}

	@Override
	public Integer updateMenu(int id, String idMenu, String label, int order) {
		// TODO Auto-generated method stub
		return configJProcessDao.updateMenu(id, idMenu, label, order);
	}

	@Override
	public Integer deleteMenu(int id) {
		// TODO Auto-generated method stub
		return configJProcessDao.deleteMenu(id);
	}

	@Override
	public List<MenuDTO> selectMenu() {
		// TODO Auto-generated method stub
		
		List<MenuDTO> listaMenu = configJProcessDao.selectMenu();		
		
		for(MenuDTO menu : listaMenu){			
			menu.setListaSubmenu(configJProcessDao.selectSubmenuByMenu(menu.getId()));
		}		
		return listaMenu;
	}

	@Override
	public Integer insertSubmenu(String submenuId, String etiqueta,
			String stackName, int idMenu) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertSubmenu(submenuId, etiqueta, stackName, idMenu);
	}

	@Override
	public Integer updateSubmenu(int id, String submenuId, String etiqueta,
			String stackName, int idMenu, int order) {
		// TODO Auto-generated method stub
		return configJProcessDao.updateSubmenu(id, submenuId, etiqueta, stackName, idMenu, order);
	}

	@Override
	public Integer deleteSubmenu(int id) {
		// TODO Auto-generated method stub
		return configJProcessDao.deleteSubmenu(id);
	}

	@Override
	public List<SubmenuDTO> selectAllSubmenu() {
		// TODO Auto-generated method stub
		return configJProcessDao.selectAllSubmenu();
	}

	@Override
	public List<SubmenuDTO> selectSubmenuByMenu(int idMenu) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectSubmenuByMenu(idMenu);
	}

	@Override
	public Integer insertPanel(PanelDTO panel) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertPanel(panel);
	}

	@Override
	public Integer updatePanel(PanelDTO panel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deletePanel(int id) {
		// TODO Auto-generated method stub
		return configJProcessDao.deletePanel(id);
	}

	@Override
	public List<PanelDTO> selectAllPanel() {
		// TODO Auto-generated method stub
		return configJProcessDao.selectAllPanel();
	}

	@Override
	public Integer insertRol(RolDTO rol) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertRol(rol);
	}

	@Override
	public Integer updateRol(RolDTO rol) {
		// TODO Auto-generated method stub
		return configJProcessDao.updateRol(rol);
	}

	@Override
	public Integer deleteRol(int id) {
		// TODO Auto-generated method stub
		return configJProcessDao.deleteRol(id);
	}

	@Override
	public List<RolDTO> selectAllRol() {
		// TODO Auto-generated method stub
		return configJProcessDao.selectAllRol();
	}

	@Override
	public List<MenuDTO> obtenerMenuPerfiladoByRol(int rol) {
		// TODO Auto-generated method stub
		List<MenuDTO> listaMenu = configJProcessDao.obtenerMenuPerfiladoByRol(rol);		
		
		for(MenuDTO menu : listaMenu){			
			menu.setListaSubmenu(configJProcessDao.selectSubmenuByMenu(menu.getId()));
		}	
		return listaMenu;
	}

	@Override
	public List<RolDTO> selectRolByMenu(int idMenu) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectRolByMenu(idMenu);
	}

	@Override
	public Integer clearMenuRol(int idMenu) {
		// TODO Auto-generated method stub
		return configJProcessDao.clearMenuRol(idMenu);
	}

	@Override
	public Integer addMenuRol(int idMenu, int idRol) {
		// TODO Auto-generated method stub
		return configJProcessDao.addMenuRol(idMenu, idRol);
	}
	
	@Override
	public Integer insertUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertUsuario(usuario);
	}

	@Override
	public Integer updateUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		return configJProcessDao.updateUsuario(usuario);
	}

	@Override
	public Integer deleteUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		return configJProcessDao.deleteUsuario(usuario);
	}

	@Override
	public List<UsuarioDTO> getAllUsuarios() {
		// TODO Auto-generated method stub
		return configJProcessDao.getAllUsuarios();
	}

	@Override
	public List<UsuarioDTO> getAllUsuariosSoporte() {
		// TODO Auto-generated method stub
		return configJProcessDao.getAllUsuariosSoporte();
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
		return configJProcessDao.getUsuario(idUsuario);
	}

	@Override
	public UsuarioDTO loginUsuario(String usuario, String password) {
		// TODO Auto-generated method stub
		return configJProcessDao.loginUsuario(usuario, password);
	}

	@Override
	public Integer updatePasswordUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertTarea(TareaDTO tarea) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertTarea(tarea);
	}

	@Override
	public Integer updateTarea(TareaDTO tarea) {
		// TODO Auto-generated method stub
		return configJProcessDao.updateTarea(tarea);
	}

	@Override
	public Integer deleteTarea(TareaDTO tarea) {
		// TODO Auto-generated method stub
		return configJProcessDao.deleteTarea(tarea);
	}

	@Override
	public List<TareaDTO> selectAllTarea() {
		// TODO Auto-generated method stub
		return configJProcessDao.selectAllTarea();
	}

	@Override
	public TareaDTO selectTareaById(int id) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectTareaById(id);
	}

	@Override
	public OrganizacionDTO getOrganizacionById(int id) {
		// TODO Auto-generated method stub
		return configJProcessDao.getOrganizacionById(id);
	}

	@Override
	public Integer insertOrganizacion(OrganizacionDTO organizacion) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertOrganizacion(organizacion);
	}

	@Override
	public Integer updateOrganizacion(OrganizacionDTO organizacion) {
		// TODO Auto-generated method stub
		return configJProcessDao.updateOrganizacion(organizacion);
	}

	@Override
	public Integer deleteOrganizacion(OrganizacionDTO organizacion) {
		// TODO Auto-generated method stub
		return configJProcessDao.deleteOrganizacion(organizacion);
	}

	@Override
	public List<OrganizacionDTO> selectAllOrganizacion() {
		// TODO Auto-generated method stub
		return configJProcessDao.selectAllOrganizacion();
	}

	@Override
	public RolDTO selectRolById(int id) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectRolById(id);
	}

	@Override
	public Integer insertAplicacion(AplicacionDTO aplicacion) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertAplicacion(aplicacion);
	}

	@Override
	public Integer updateAplicacion(AplicacionDTO aplicacion) {
		// TODO Auto-generated method stub
		return configJProcessDao.updateAplicacion(aplicacion);
	}

	@Override
	public Integer deleteAplicacion(AplicacionDTO aplicacion) {
		// TODO Auto-generated method stub
		return configJProcessDao.deleteAplicacion(aplicacion);
	}

	@Override
	public List<AplicacionDTO> selectAllAplicacion() {
		// TODO Auto-generated method stub
		return configJProcessDao.selectAllAplicacion();
	}

	@Override
	public AplicacionDTO selectAplicacionById(int id) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectAplicacionById(id);
	}

	@Override
	public Integer insertCreacion(CreacionDTO creacion) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertCreacion(creacion);
	}

	@Override
	public Integer updateCreacion(CreacionDTO creacion) {
		// TODO Auto-generated method stub
		return configJProcessDao.updateCreacion(creacion);
	}

	@Override
	public Integer deleteCreacion(CreacionDTO creacion) {
		// TODO Auto-generated method stub
		return configJProcessDao.deleteCreacion(creacion);
	}

	@Override
	public List<CreacionDTO> selectAllCreacion() {
		// TODO Auto-generated method stub
		return configJProcessDao.selectAllCreacion();
	}

	@Override
	public List<CreacionDTO> selectCreacionByIdAplicacion(int idAplicacion) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectCreacionByIdAplicacion(idAplicacion);
	}

	@Override
	public CreacionDTO selectCreacionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateInicioFinAplicacion(int id) {
		// TODO Auto-generated method stub
		return configJProcessDao.updateInicioFinAplicacion(id);
	}

	@Override
	public Integer insertSolicitud(SolicitudDTO solicitud) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertSolicitud(solicitud);
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
		return configJProcessDao.selectAllSolicitudesPendientesByRol(idRol);
	}

	@Override
	public SolicitudDTO selectSolicitudById(int idSolicitud) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectSolicitudById(idSolicitud);
	}

	@Override
	public Integer insertPendiente(PendienteDTO pendiente) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertPendiente(pendiente);
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
		return null;
	}

	@Override
	public int selectNewSolicitudId() {
		// TODO Auto-generated method stub
		return configJProcessDao.selectNewSolicitudId();
	}

	@Override
	public TareaDTO selectTareaActualBySolicitudId(int idSolicitud) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectTareaActualBySolicitudId(idSolicitud);
	}

	@Override
	public Integer insertSolicitudComentario(int solicitudId, String usuario,
			String comentario) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertSolicitudComentario(solicitudId, usuario, comentario);
	}

	@Override
	public List<CasoComentarioDTO> selectComentariosBySolicitud(int idSolicitud) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectComentariosBySolicitud(idSolicitud);
	}

	@Override
	public CreacionDTO selectCreacionByTareaInicial(int idTarea,
			int idAplicacion) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectCreacionByTareaInicial(idTarea, idAplicacion);
	}

	@Override
	public Integer cerrarCreacion(int idPendiente) {
		// TODO Auto-generated method stub
		return configJProcessDao.cerrarCreacion(idPendiente);
	}

	@Override
	public Integer despacharCreacion(int idTarea, int idAplicacion, int rol) {
		// TODO Auto-generated method stub
		return configJProcessDao.despacharCreacion(idTarea, idAplicacion, rol);
	}

	@Override
	public Integer cerrarSolicitud(int idSolicitud) {
		// TODO Auto-generated method stub
		return configJProcessDao.cerrarSolicitud(idSolicitud);
	}

	@Override
	public PendienteDTO selectPendientesBySolicitud(int id) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectPendientesBySolicitud(id);
	}

	@Override
	public Integer asignarResponsable(int idUsuario, int idSolicitud) {
		// TODO Auto-generated method stub
		return configJProcessDao.asignarResponsable(idUsuario, idSolicitud);
	}

	@Override
	public Integer liberarResponsable(int idUsuario, int idSolicitud) {
		// TODO Auto-generated method stub
		return configJProcessDao.liberarResponsable(idUsuario, idSolicitud);
	}

	@Override
	public Integer insertTareaConfiguracion(TareaConfiguracionDTO configuracion) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertTareaConfiguracion(configuracion);
	}

	@Override
	public Integer updateTareaConfiguracion(TareaConfiguracionDTO configuracion) {
		// TODO Auto-generated method stub
		return configJProcessDao.updateTareaConfiguracion(configuracion);
	}

	@Override
	public Integer deleteTareaConfiguracion(TareaConfiguracionDTO configuracion) {
		// TODO Auto-generated method stub
		return configJProcessDao.deleteTareaConfiguracion(configuracion);
	}

	@Override
	public List<TareaConfiguracionDTO> selectTareaConfiguracion() {
		// TODO Auto-generated method stub
		return configJProcessDao.selectTareaConfiguracion();
	}

	@Override
	public List<TareaConfiguracionDTO> selectTareaConfiguracionByTarea(
			int idTarea) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectTareaConfiguracionByTarea(idTarea);
	}

	@Override
	public TareaConfiguracionDTO selectTareaConfiguracionById(int id) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectTareaConfiguracionById(id);
	}

	@Override
	public Integer insertArea(AreaDTO area) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertArea(area);
	}

	@Override
	public Integer updateArea(AreaDTO area) {
		// TODO Auto-generated method stub
		return configJProcessDao.updateArea(area);
	}

	@Override
	public Integer deleteArea(AreaDTO area) {
		// TODO Auto-generated method stub
		return configJProcessDao.deleteArea(area);
	}

	@Override
	public List<AreaDTO> selectAreas() {
		// TODO Auto-generated method stub
		return configJProcessDao.selectAreas();
	}

	@Override
	public List<AreaDTO> selectAreasByOrganizacion(int idOrganizacion) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectAreasByOrganizacion(idOrganizacion);
	}

	@Override
	public AreaDTO selectAreaById(int idArea) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectAreaById(idArea);
	}

	@Override
	public Integer insertSubarea(SubareaDTO subarea) {
		// TODO Auto-generated method stub
		return configJProcessDao.insertSubarea(subarea);
	}

	@Override
	public Integer updateSubarea(SubareaDTO subarea) {
		// TODO Auto-generated method stub
		return configJProcessDao.updateSubarea(subarea);
	}

	@Override
	public Integer deleteSubarea(SubareaDTO subarea) {
		// TODO Auto-generated method stub
		return configJProcessDao.deleteSubarea(subarea);
	}

	@Override
	public List<SubareaDTO> selectSubareas() {
		// TODO Auto-generated method stub
		return configJProcessDao.selectSubareas();
	}

	@Override
	public List<SubareaDTO> selectSubareasByArea(int idArea) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectSubareasByArea(idArea);
	}

	@Override
	public SubareaDTO selectSubareaById(int idSubarea) {
		// TODO Auto-generated method stub
		return configJProcessDao.selectSubareaById(idSubarea);
	}
}
