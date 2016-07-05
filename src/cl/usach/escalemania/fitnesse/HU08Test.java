/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usach.escalemania.fitnesse;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class HU08Test {
    
    
     public InitialContext ctx() throws IOException, NamingException{
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Rodrigo Rivas\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
}
