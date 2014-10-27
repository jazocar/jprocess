package cl.jazocar.jprocess.dao.base;

import java.util.Map;

import javax.swing.table.TableModel;

import cl.jazocar.jprocess.exception.JProcessPersistenceException;
import cl.jazocar.jprocess.sql.factory.FactoryConnection;

public interface GenericCrudJProcessDao 
{
	public boolean add( TableModel tableModel, String[] dataArray, FactoryConnection factoryConection) throws JProcessPersistenceException ;
	
	public boolean update( TableModel tableModel , Map<String, String> valuesMap, Map<String, String> primaryKey, FactoryConnection factoryConection ) throws JProcessPersistenceException;
		
	public boolean delete( TableModel tableModel, Map<String, String> primaryKey, FactoryConnection factoryConection) throws JProcessPersistenceException; 
	
	public Map<String, String> getDataRow(TableModel tableModel, Map<String, String> primaryKey, FactoryConnection factoryConnection) throws JProcessPersistenceException;
	
	public int getNumberFromSQL( FactoryConnection factoryConnection, String sqlString) throws JProcessPersistenceException;

}
