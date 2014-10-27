package cl.jazocar.jprocess.dto;

import java.util.Date;
import java.util.List;

public class SolicitudDTO extends ResponseDTO{

	private static final long serialVersionUID = 1L;
	private int                id;
	private int                idAplicacion;
	private Date               fechaInicio;
	private Date               fechaFin;
	private String             estado;	
	private List<PendienteDTO> listaPendientes;
	private PendienteDTO       ultimoPendiente;
	private boolean 		   selected;
	private List<CasoComentarioDTO> listaComentarios;
	
	
	/**
	 * 
	 */
	public SolicitudDTO() {
	}
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the listaPendientes
	 */
	public List<PendienteDTO> getListaPendientes() {
		return listaPendientes;
	}
	/**
	 * @param listaPendientes the listaPendientes to set
	 */
	public void setListaPendientes(List<PendienteDTO> listaPendientes) {
		this.listaPendientes = listaPendientes;
	}
	/**
	 * @return the ultimoPendiente
	 */
	public PendienteDTO getUltimoPendiente() {
		return ultimoPendiente;
	}
	/**
	 * @param ultimoPendiente the ultimoPendiente to set
	 */
	public void setUltimoPendiente(PendienteDTO ultimoPendiente) {
		this.ultimoPendiente = ultimoPendiente;
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
	 * @return the listaComentarios
	 */
	public List<CasoComentarioDTO> getListaComentarios() {
		return listaComentarios;
	}


	/**
	 * @param listaComentarios the listaComentarios to set
	 */
	public void setListaComentarios(List<CasoComentarioDTO> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}
	
	
	
	
}
