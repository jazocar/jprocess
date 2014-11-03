package cl.jazocar.jprocess.front.form.impl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.icefaces.ace.event.SelectEvent;

import cl.jazocar.jprocess.constants.IConstants;
import cl.jazocar.jprocess.front.AplicationAbstract;

@ManagedBean(name = IConstants.INFORME_LEGAL_FRONT_CLASS)
@SessionScoped
public class InformeLegalFrontClass extends AplicationAbstract {

	private boolean selected;
	private boolean isJuridica;

	private String selectedTipoPersona;

	private boolean isHipoteca;
	private boolean isAdjuntos;

	public InformeLegalFrontClass() {
		init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		isHipoteca = true;
	}

	@Override
	public void insertObjectListener(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateObjectListener(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteObjectListener(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetObjectListener(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void searchObjectListener(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetObject() {
		// TODO Auto-generated method stub

	}

	@Override
	public void objectSelectListener(SelectEvent event) {
		// TODO Auto-generated method stub

	}

	// Custom Method.
	public void changeHipotecaAdjuntosListener(ValueChangeEvent evnt) {
		String estado = evnt.getNewValue().toString();
		if (estado.equals("true")) {
			isAdjuntos = true;
		} else {
			isAdjuntos = false;
		}
	}

	public void onChangeTipoCliente(ValueChangeEvent evnt) {
		if (evnt.getNewValue().equals("PN"))
			isJuridica = false;
		else if (evnt.getNewValue().equals("PJ"))
			isJuridica = true;
	}

	public void setTipoOperacionToFalse() {
		isHipoteca = false;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isJuridica() {
		return isJuridica;
	}

	public void setJuridica(boolean isJuridica) {
		this.isJuridica = isJuridica;
	}

	public boolean isHipoteca() {
		return isHipoteca;
	}

	public void setHipoteca(boolean isHipoteca) {
		this.isHipoteca = isHipoteca;
	}

	public boolean isAdjuntos() {
		return isAdjuntos;
	}

	public void setAdjuntos(boolean isAdjuntos) {
		this.isAdjuntos = isAdjuntos;
	}

	public String getSelectedTipoPersona() {
		return selectedTipoPersona;
	}

	public void setSelectedTipoPersona(String selectedTipoPersona) {
		this.selectedTipoPersona = selectedTipoPersona;
	}

}
