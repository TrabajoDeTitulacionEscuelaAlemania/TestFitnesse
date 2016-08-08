/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usach.escalemania.fitnesse;

import cl.usach.escalemania.sessionbeans.ProgramaFacadeLocal;
import fit.ColumnFixture;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Rodrigo Rivas
 */
public class HU19Test extends ColumnFixture{
    
    String nombrePrograma, nuevoNombrePrograma;
    boolean moverDocumentos;
    
    public InitialContext ctx() throws IOException, NamingException{
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Rodrigo Rivas\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
    
    public String crearPrograma() throws IOException, NamingException{
        ProgramaFacadeLocal programaFacade=(ProgramaFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.ProgramaFacadeLocal");
        return programaFacade.crearPrograma(nombrePrograma);
    }
    
    public String editarPrograma() throws IOException, NamingException{
        ProgramaFacadeLocal programaFacade=(ProgramaFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.ProgramaFacadeLocal");
        return programaFacade.editarPrograma(nombrePrograma, nuevoNombrePrograma);
    }
    
    public String eliminarPrograma() throws IOException, NamingException{
        ProgramaFacadeLocal programaFacade=(ProgramaFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.ProgramaFacadeLocal");
        return programaFacade.eliminarPrograma(nombrePrograma, moverDocumentos, nuevoNombrePrograma);
    }
}
