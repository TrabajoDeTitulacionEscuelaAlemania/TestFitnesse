/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usach.escalemania.fitnesse;

import cl.usach.escalemania.entities.EstadoDocumento;
import cl.usach.escalemania.entities.Programa;
import cl.usach.escalemania.entities.Seccion;
import cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal;
import cl.usach.escalemania.sessionbeans.EstadoDocumentoFacadeLocal;
import cl.usach.escalemania.sessionbeans.ProgramaFacadeLocal;
import cl.usach.escalemania.sessionbeans.SeccionFacadeLocal;
import fit.ColumnFixture;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Rodrigo Rivas
 */
public class HU09Test extends ColumnFixture{
    
    String ubicacion, observacion,estadoDocumento, seccion, nombre,programa;
    List<String> programas;
    
    public InitialContext ctx() throws IOException, NamingException{
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Rodrigo Rivas\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
    
    public String crearDocumento() throws IOException, NamingException{
        DocumentoFacadeLocal documentoFacade = (DocumentoFacadeLocal) ctx().lookup
        ("cl.usach.escalemania.sessionbeans.DocumentoFacadeLocal");
        SeccionFacadeLocal seccionFacade = (SeccionFacadeLocal) ctx().lookup
        ("cl.usach.escalemania.sessionbeans.SeccionFacadeLocal");
        List<Seccion> secciones = seccionFacade.findAll();
        EstadoDocumentoFacadeLocal estadoDocumentoFacade = (EstadoDocumentoFacadeLocal) ctx().
                lookup("cl.usach.escalemania.sessionbeans.EstadoDocumentoFacadeLocal");
        List<EstadoDocumento> estadoDocumentos = estadoDocumentoFacade.findAll();
        ProgramaFacadeLocal programaFacade = (ProgramaFacadeLocal) ctx().
                lookup("cl.usach.escalemania.sessionbeans.ProgramaFacadeLocal");
        List<Programa> programasOriginal = programaFacade.findAll();
        if(programa.compareTo("vacio")!=0)
            programas=Arrays.asList(programa.split("-"));
        else
            programas=new ArrayList<>();
        if(nombre.compareTo("vacio")==0)
            nombre="";
        if(ubicacion.compareTo("vacio")==0)
            ubicacion="";
        if(observacion.compareTo("vacio")==0)
            observacion="";
        String result=documentoFacade.crearDocumento(nombre,
                ubicacion,
                observacion, 
                estadoDocumentoFacade.obtenerEstadDocumentoPorNombre(estadoDocumentos, estadoDocumento),
                seccionFacade.obtenerPorNombre(seccion, secciones),
                programaFacade.obtenerListaDeProgramas(programas, programasOriginal));
        return result;
        
    }
}
