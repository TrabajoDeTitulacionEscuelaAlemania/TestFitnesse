/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usach.escalemania.fitnesse;

import cl.usach.escalemania.entities.Documento;
import cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal;
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
        props.load(new FileInputStream("C:\\Users\\Rodrigo.DESKTOP-8KS3UKH\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
    
    public int filtrarDocumentoPorEstado() throws IOException, NamingException{
        DocumentoFacadeLocal documentoFacade=(DocumentoFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal");
        List<Documento> documentos=documentoFacade.findAll();
        List<Documento> documentosFiltrados= documentoFacade.filtrarPorEstado(documentos, estadoDocumento);
        if(documentosFiltrados==null)
            return 0;
        return documentosFiltrados.size();
    }
}
