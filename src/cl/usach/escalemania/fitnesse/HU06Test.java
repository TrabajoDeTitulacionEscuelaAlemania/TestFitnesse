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
import java.util.ArrayList;
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
        props.load(new FileInputStream("C:\\Users\\Rodrigo Rivas\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
    
    public String alerta() throws IOException, NamingException{
        DocumentoFacadeLocal documentoFacade=(DocumentoFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal");
        List<Documento> documentos=documentoFacade.findAll();
        List<Documento> documentosAlerta=new ArrayList<>();
        documentosAlerta.addAll(documentoFacade.filtrarPorEstado(documentos, "Sin informacion"));
        documentosAlerta.addAll(documentoFacade.filtrarPorEstado(documentos, "Desactualizado"));
        documentosAlerta.addAll(documentoFacade.filtrarPorEstado(documentos, "Incompleto"));
        for(Documento doc:documentosAlerta)
            if(doc.getEstadoDocumento().getEstado().compareTo("Completo")==0)
                return "Existen documentos completos en la lista";
        return documentosAlerta.size()+" documentos en alerta";
    }
    
    public String setearEstado(){
        return null;
    }
}
