package cl.jazocar.jprocess.sql.factory;

import java.sql.Connection;

import cl.jazocar.jprocess.exception.JProcessPersistenceException;
import cl.jazocar.jprocess.model.ConfigModel;

public interface FactoryConnection 
{
	public void setConfigModel(ConfigModel configModel);
	
	public Connection getConnection() throws JProcessPersistenceException ;
}
