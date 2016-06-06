/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usach.escalemania.fitnesse;

import cl.usach.escalemania.entities.Documento;
import cl.usach.escalemania.entities.EstadoDocumento;
import cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal;
import cl.usach.escalemania.sessionbeans.EstadoDocumentoFacadeLocal;
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
public class HU06Test extends ColumnFixture{
    
    String fecha;
    
    public InitialContext ctx() throws IOException, NamingException{
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Desarrollo\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
    
    public String alerta() throws IOException, NamingException{
        EstadoDocumentoFacadeLocal estadoDocumentoFacade = (EstadoDocumentoFacadeLocal) ctx().
                lookup("cl.usach.escalemania.sessionbeans.EstadoDocumentoFacadeLocal");
        List<EstadoDocumento> estadoDocumentos = estadoDocumentoFacade.findAll();
        DocumentoFacadeLocal documentoFacade=(DocumentoFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal");
        List<Documento> documentos=documentoFacade.obtenerDocumentoPorEstado(estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(
                estadoDocumentos, "Incompleto"));
        documentos.addAll(documentoFacade.obtenerDocumentoPorEstado(estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(estadoDocumentos, "Desactualizado")));
        documentos.addAll(documentoFacade.obtenerDocumentoPorEstado(estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(estadoDocumentos, "Sin informacion")));
        for(Documento doc:documentos)
            if(doc.getEstadoDocumento().getEstado().compareTo("Completo")==0)
                return "Existen documentos completos en la lista";
        return documentos.size()+" documentos en alerta";
    }
    
    public String setearEstado(){
        return null;
    }
}
