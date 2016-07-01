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
    
    String ubicacion, observacion,estadoDocumento, seccion;
    
    
    public InitialContext ctx() throws IOException, NamingException{
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Rodrigo Rivas\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
    
    public int cantDocs() throws IOException, NamingException{
        
        DocumentoFacadeLocal documentoFacade=(DocumentoFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal");
        return documentoFacade.count();
    }
    
    public String modificarDocumento() throws IOException, NamingException {
        DocumentoFacadeLocal documentoFacade = (DocumentoFacadeLocal) ctx().lookup("cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal");
        Documento documento = documentoFacade.findAll().get(0);
        SeccionFacadeLocal seccionFacade = (SeccionFacadeLocal) ctx().lookup("cl.usach.escalemania.sessionbeans.SeccionFacadeLocal");
        List<Seccion> secciones = seccionFacade.findAll();
        EstadoDocumentoFacadeLocal estadoDocumentoFacade = (EstadoDocumentoFacadeLocal) ctx().
                lookup("cl.usach.escalemania.sessionbeans.EstadoDocumentoFacadeLocal");
        List<EstadoDocumento> estadoDocumentos = estadoDocumentoFacade.findAll();

        if (ubicacion.compareTo("-") == 0 && observacion.compareTo("-") == 0 && seccion.compareTo("-") == 0) {
            ubicacion = documento.getUbicacion();
            observacion = documento.getObservacion();
            seccion = documento.getSeccion().getSeccion();
            documentoFacade.editarDocumento(estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(estadoDocumentos, estadoDocumento),
                    ubicacion, seccionFacade.obtenerPorNombre(seccion, secciones), observacion, documento);
            documento = documentoFacade.findAll().get(0);
            return documento.getEstadoDocumento().getEstado();
        } else {
            if (ubicacion.compareTo("vacio") == 0 && observacion.compareTo("-") == 0 && seccion.compareTo("-") == 0) {
                ubicacion = "";
                observacion = documento.getObservacion();
                seccion = documento.getSeccion().getSeccion();
                if (documentoFacade.editarDocumento(estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(estadoDocumentos, estadoDocumento),
                        ubicacion, seccionFacade.obtenerPorNombre(seccion, secciones), observacion, documento).compareTo("Campo vacio") == 0) {
                    return "Campo vacio";
                }
                documento =documentoFacade.findAll().get(0);
                return documento.getEstadoDocumento().getEstado();
            } else {
                if (ubicacion.compareTo("Estante 5, archivador 8") == 0 && observacion.compareTo("-") == 0
                        && seccion.compareTo("-") == 0 && estadoDocumento.compareTo("-") == 0) {
                    observacion = documento.getObservacion();
                    seccion = documento.getSeccion().getSeccion();
                    estadoDocumento = documento.getEstadoDocumento().getEstado();
                    documentoFacade.editarDocumento(estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(estadoDocumentos, estadoDocumento),
                            ubicacion, seccionFacade.obtenerPorNombre(seccion, secciones), observacion, documento);
                    documento = documentoFacade.findAll().get(0);
                    return documento.getUbicacion();
                } else {
                    if (ubicacion.compareTo("-") == 0 && observacion.compareTo("Se realizo la solicitud") == 0
                            && seccion.compareTo("-") == 0 && estadoDocumento.compareTo("-") == 0) {
                        ubicacion = documento.getUbicacion();
                        seccion = documento.getSeccion().getSeccion();
                        estadoDocumento = documento.getEstadoDocumento().getEstado();
                        documentoFacade.editarDocumento(estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(estadoDocumentos, estadoDocumento),
                                ubicacion, seccionFacade.obtenerPorNombre(seccion, secciones), observacion, documento);
                        documento = documentoFacade.findAll().get(0);
                        return documento.getObservacion();
                    } else {
                        if (ubicacion.compareTo("-") == 0 && observacion.compareTo("-") == 0
                                && estadoDocumento.compareTo("-") == 0) {
                            ubicacion = documento.getUbicacion();
                            observacion = documento.getObservacion();
                            estadoDocumento = documento.getEstadoDocumento().getEstado();
                            documentoFacade.editarDocumento(estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(estadoDocumentos, estadoDocumento),
                                    ubicacion, seccionFacade.obtenerPorNombre(seccion, secciones), observacion, documento);
                            documento = documentoFacade.findAll().get(0);
                            return documento.getSeccion().getSeccion();
                        } else {
                            if (ubicacion.compareTo("vacio") == 0 && observacion.compareTo("Parte del Sep") == 0 && seccion.compareTo("Vital") == 0
                                    && estadoDocumento.compareTo("Completo") == 0) {
                                ubicacion = "";
                                if (documentoFacade.editarDocumento(estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(estadoDocumentos, estadoDocumento),
                                        ubicacion, seccionFacade.obtenerPorNombre(seccion, secciones), observacion, documento).compareTo("Campo vacio") == 0) {
                                    return "Campo vacio";
                                }
                                documento = documentoFacade.findAll().get(0);
                                return documento.getEstadoDocumento().getEstado();
                            }
                        }
                    }
                }
            }
        }
        documentoFacade.editarDocumento(estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(estadoDocumentos, estadoDocumento),
                ubicacion, seccionFacade.obtenerPorNombre(seccion, secciones), observacion, documento);
        documento = documentoFacade.findAll().get(0);
        return documento.getSeccion().getSeccion() + "-" + documento.getEstadoDocumento().getEstado()
                + "-" + documento.getUbicacion() + "-" + documento.getObservacion();
    }
}
