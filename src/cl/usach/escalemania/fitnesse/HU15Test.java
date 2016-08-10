/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usach.escalemania.fitnesse;

import cl.usach.escalemania.sessionbeans.AlertaFacadeLocal;
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
public class HU15Test extends ColumnFixture{
    
    String mensajeAlerta, destinosAlerta;
    
    public InitialContext ctx() throws IOException, NamingException{
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Rodrigo Rivas\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
    
    public String enviarAlerta()throws IOException, NamingException{
        AlertaFacadeLocal alertaFacade=(AlertaFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.AlertaFacadeLocal");
        List<String> destinos=new ArrayList<>();
        if(!destinosAlerta.isEmpty())
            destinos=Arrays.asList(destinosAlerta.split(","));
        return alertaFacade.enviarAlerta(mensajeAlerta, destinos);
    }
}
