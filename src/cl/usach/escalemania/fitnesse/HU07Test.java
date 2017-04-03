/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usach.escalemania.fitnesse;


import cl.usach.escalemania.entities.Programa;
import cl.usach.escalemania.entities.Simulacion;
import cl.usach.escalemania.sessionbeans.PlanificacionLocal;
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
    int realizarSimulacion;
    
    public InitialContext ctx() throws IOException, NamingException {
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Rodrigo.DESKTOP-8KS3UKH\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
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
        int completoVital, completoImportante, completoNormal, 
                incompletoVital, incompletoImportante, incompletoNormal, 
                desactualizadoVital, desactualizadoImportante, desactualizadoNormal,
                sinInformacionVital, sinInformacionImportante, sinInformacionNormal;
        completoVital=simulacion.getDocCompletosVital();
        completoImportante=simulacion.getDocCompletosImportante();
        completoNormal=simulacion.getDocCompletosNormal();
        incompletoVital=simulacion.getDocIncompletosVital();
        incompletoImportante=simulacion.getDocIncompletosImportante();
        incompletoNormal=simulacion.getDocIncompletosNormal();
        desactualizadoVital=simulacion.getDocDesactualizadosVital();
        desactualizadoImportante=simulacion.getDocDesactualizadosImportante();
        desactualizadoNormal=simulacion.getDocDesactualizadosNormal();
        sinInformacionVital=simulacion.getDocSinInformacionVital();
        sinInformacionImportante=simulacion.getDocSinInformacionImportante();
        sinInformacionNormal=simulacion.getDocSinInformacionNormal();
        return completoVital+" Completos seccion vital-"+completoImportante+" Completos seccion importante-"+completoNormal+" Completos seccion normal-"
                + incompletoVital+" Incompletos seccion vital-"+incompletoImportante+" Incompletos seccion importante-"+incompletoNormal+" Incompletos seccion normal-"
                + desactualizadoVital+" Desactualizados seccion vital-"+desactualizadoImportante+" Desactualizados seccion importante-"+desactualizadoNormal+" Desactualizados seccion normal-"
                + sinInformacionVital+" Sin informacion seccion vital-"+sinInformacionImportante+" Sin informacion seccion importante-"+sinInformacionNormal+" Sin informacion seccion normal-"
                + simulacion.getPorcentajeAprobacion()+"%-"+simulacion.getNota();
    }
    public String simulacionMensual() throws IOException, NamingException{
        if(realizarSimulacion==1){
            PlanificacionLocal planificacion = (PlanificacionLocal) ctx().lookup
                    ("cl.usach.escalemania.sessionbeans.PlanificacionLocal");
            planificacion.realizarSimulacion();
            return "Simulación realizada";
        }
        return "No se realizó la simulación";
    }
}
