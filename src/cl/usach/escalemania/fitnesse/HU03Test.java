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
public class HU03Test extends ColumnFixture{
    
    String estadoDocumento;
    
    public InitialContext ctx() throws IOException, NamingException{
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Desarrollo\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
    
    public String filtrarDocumentoPorEstado() throws IOException, NamingException{
        DocumentoFacadeLocal documentoFacade=(DocumentoFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal");
        EstadoDocumentoFacadeLocal estadoDocumentoFacade=(EstadoDocumentoFacadeLocal)ctx().
                lookup("cl.usach.escalemania.sessionbeans.EstadoDocumentoFacadeLocal");
        List<EstadoDocumento> estadoDocumentos=estadoDocumentoFacade.findAll();
        List<Documento> documentos=documentoFacade.obtenerDocumentoPorEstado
                (estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(estadoDocumentos, estadoDocumento));
        if(documentos==null)
            return "No hay documentos";
        for(Documento doc:documentos)
            if(doc.getEstadoDocumento().getEstado().compareTo(estadoDocumento)!=0)
                return "Hay docuemntos con diferente estado";
        return "OK";
    }
}
