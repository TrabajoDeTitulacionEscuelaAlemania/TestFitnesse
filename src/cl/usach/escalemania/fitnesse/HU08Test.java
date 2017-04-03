/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usach.escalemania.fitnesse;

import cl.usach.escalemania.sessionbeans.SimulacionFacadeLocal;
import fit.ColumnFixture;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class HU08Test extends ColumnFixture{
    
    String nombrePrograma;
    
     public InitialContext ctx() throws IOException, NamingException{
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Rodrigo.DESKTOP-8KS3UKH\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
     
    public double simulacionNota() throws IOException, NamingException{
        SimulacionFacadeLocal simulacionFacade=(SimulacionFacadeLocal)ctx().lookup
        ("cl.usach.escalemania.sessionbeans.SimulacionFacadeLocal");
        double resultado=simulacionFacade.notaSimulacion(nombrePrograma);
        return resultado;
    }
    
    public double simulacionPorcentaje() throws IOException, NamingException{
        SimulacionFacadeLocal simulacionFacade=(SimulacionFacadeLocal)ctx().lookup
        ("cl.usach.escalemania.sessionbeans.SimulacionFacadeLocal");
        double resultado=simulacionFacade.porcentajeSimulacion(nombrePrograma);
        return resultado;
    }
}
