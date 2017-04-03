/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usach.escalemania.fitnesse;

import cl.usach.escalemania.entities.Documento;
import cl.usach.escalemania.entities.EstadoDocumento;
import cl.usach.escalemania.entities.Seccion;
import cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal;
import cl.usach.escalemania.sessionbeans.EstadoDocumentoFacadeLocal;
import cl.usach.escalemania.sessionbeans.SeccionFacadeLocal;
import fit.ColumnFixture;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Desarrollo
 */
public class HU01Test extends ColumnFixture{
    
    String ubicacion, observacion,estadoDocumento, seccion, nombre, documentoSeleccionado, nombreUsuario;
    
    
    public InitialContext ctx() throws IOException, NamingException{
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Rodrigo.DESKTOP-8KS3UKH\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
    
    public String modificarDocumento() throws IOException, NamingException {
        DocumentoFacadeLocal documentoFacade = (DocumentoFacadeLocal) ctx().lookup("cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal");
        Documento documento = documentoFacade.buscarDocumento(documentoSeleccionado, documentoFacade.findAll()).get(0);
        SeccionFacadeLocal seccionFacade = (SeccionFacadeLocal) ctx().lookup("cl.usach.escalemania.sessionbeans.SeccionFacadeLocal");
        List<Seccion> secciones = seccionFacade.findAll();
        EstadoDocumentoFacadeLocal estadoDocumentoFacade = (EstadoDocumentoFacadeLocal) ctx().
                lookup("cl.usach.escalemania.sessionbeans.EstadoDocumentoFacadeLocal");
        List<EstadoDocumento> estadoDocumentos = estadoDocumentoFacade.findAll();
        if(nombre.compareTo("-")==0)
            nombre=documento.getNombre();
        if(ubicacion.compareTo("-")==0)
            ubicacion=documento.getUbicacion();
        if(observacion.compareTo("-")==0)
            observacion=documento.getObservacion();
        if(estadoDocumento.compareTo("-")==0)
            estadoDocumento=documento.getEstadoDocumento().getEstado();
        if(seccion.compareTo("-")==0)
            seccion=documento.getSeccion().getSeccion();
        String resultado=documentoFacade.editarDocumento(estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(estadoDocumentos, estadoDocumento),
                ubicacion, seccionFacade.obtenerPorNombre(seccion, secciones), observacion, nombre, documento, nombreUsuario);
        return resultado;
    }
}
