/**
 * 
 */
package cl.jazocar.jprocess.constants;

/**
 * @author Diablo
 *
 */
public interface IConstants {
	
	// Servlet.
	public static final String JPROCESS_PERSITENCE      = "JPROCESS_PERSITENCE";
	public static final String JPROCESS_SERVICE_FACTORY = "JPROCESS_SERVICE_FACTORY";
	public static final String JPROCESS_PROPERTIES      = "JPROCESS_SERVICE";
	
	
	public static final String INFORME_LEGAL_FRONT_CLASS = "informeLegalFrontClass";
	
			
	public static final String CONTEXT_LOOKUP = "java:/comp/env";
	public static final String LOOKUP = "jdbc/jprocess";
	
	// Archivo Base.

	public static final String CONTENIDO_ARCHIVO_BASE = "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:h=\"http://java.sun.com/jsf/html\" xmlns:f=\"http://java.sun.com/jsf/core\" xmlns:c=\"http://java.sun.com/jsp/jstl/core\" xmlns:ace=\"http://www.icefaces.org/icefaces/components\" xmlns:ice=\"http://www.icesoft.com/icefaces/component\" xmlns:icecore=\"http://www.icefaces.org/icefaces/core\" xmlns:ui=\"http://java.sun.com/jsf/facelets\"><h:form><ace:tabSet clientSide=\"true\" immediate=\"true\" selectedIndex=\"0\"><ace:tabPane label=\"Mantenedor\" cache=\"dynamic\"><table><tr><td><ice:outputText value=\"Nombre Campo\" style=\"font-size:small; color:black;\"/></td><td><ice:inputText/></td></tr></table><div align=\"left\"><table><tr><td><ace:pushButton value=\"Ingresar\" rendered=\"true\" actionListener=\"#{FrontBean.insertObjectListener}\" /></td><td><ace:pushButton value=\"Modificar\"	rendered=\"true\" actionListener=\"#{FrontBean.updateObjectListener}\" /></td><td><ace:pushButton value=\"Dar de Baja\" rendered=\"true\" actionListener=\"#{FrontBean.deleteObjectListener}\"/></td><td><ace:pushButton value=\"Limpiar\" actionListener=\"#{FrontBean.resetObjectListener}\"/></td></tr></table></div><hr /><div align=\"right\"><table><tr><td><ace:dataExporter id=\"dataExporterXls\" label=\"Excel\" type=\"xls\" target=\"idListado\" fileName=\"output\" /></td><td><ace:dataExporter id=\"dataExporterPdf\" label=\"PDF\" type=\"pdf\" target=\"idListado\" fileName=\"output\" /></td><td><ace:dataExporter id=\"dataExporterCsv\" label=\"CSV\" type=\"csv\" target=\"idListado\" fileName=\"output\" /></td><td><ace:dataExporter id=\"dataExporterXml\" label=\"XML\" type=\"xml\" target=\"idListado\" fileName=\"output\" /></td></tr></table></div><ace:dataTable id=\"idListado\" rowSelectListener=\"#{FrontBean.objectSelectListener}\"><ace:column headerText=\"Id\"><h:outputText /></ace:column></ace:dataTable></ace:tabPane></ace:tabSet></h:form></ui:composition>";

}
