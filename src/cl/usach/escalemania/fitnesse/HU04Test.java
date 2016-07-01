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
public class HU04Test extends ColumnFixture{
    
    String frase;
    
    public InitialContext ctx() throws IOException, NamingException{
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Rodrigo Rivas\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
    
    public String buscarDocumento() throws IOException, NamingException{
        DocumentoFacadeLocal documentoFacade=(DocumentoFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal");
        List<Documento> documentos=documentoFacade.findAll();
        documentos=documentoFacade.buscarDocumento(frase, documentos);
        String result=documentos.get(0).getNombre()+"-"+documentos.get(1).getNombre()+"-"+documentos.get(2).getNombre();
        return result;
        //return null;
    }
}
