/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usach.escalemania.fitnesse;


import cl.usach.escalemania.entities.Programa;
import cl.usach.escalemania.entities.Simulacion;
import cl.usach.escalemania.sessionbeans.ProgramaFacadeLocal;
import cl.usach.escalemania.sessionbeans.SimulacionFacadeLocal;
import fit.ColumnFixture;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Rodrigo Rivas
 */
public class HU07Test extends ColumnFixture{
    
    String nombrePrograma;
    
    public InitialContext ctx() throws IOException, NamingException {
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Rodrigo Rivas\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
    
    public String ultimaSimulacion() throws IOException, NamingException{
        SimulacionFacadeLocal simulacionFacade = (SimulacionFacadeLocal) ctx().lookup
        ("cl.usach.escalemania.sessionbeans.SimulacionFacadeLocal");
        ProgramaFacadeLocal programaFacade = (ProgramaFacadeLocal) ctx().
                lookup("cl.usach.escalemania.sessionbeans.ProgramaFacadeLocal");
        List<Programa> programas = programaFacade.findAll();
        Simulacion simulacion=simulacionFacade.ultimaSimulacion(nombrePrograma, programas);
        if(simulacion==null)
            return "No existen simulaciones";
        return simulacion.getDocCompletos()+" Completos-"+
                simulacion.getDocIncompletos()+" Incompletos-"+
                simulacion.getDocDesactualizados()+" Desactualizados-"+
                simulacion.getDocSinInformacion()+" Sin informacion-"+
                simulacion.getPorcentajeAprobacion()+"%-"+
                simulacion.getNota();
    }
}
