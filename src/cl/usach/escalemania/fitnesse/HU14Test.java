/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usach.escalemania.fitnesse;

import cl.usach.escalemania.sessionbeans.UsuarioFacadeLocal;
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
public class HU14Test extends ColumnFixture{
    
    String nuevoUsuario, contraseñaActual, nuevaContraseña1, nuevaContraseña2, usuarioReestablecerContraseña, 
            usuarioEliminar, nuevaContraseñaVisitante1,nuevaContraseñaVisitante2, nombreUsuario,nombreUsuarioCorreo, correoUsuario;
    
    public InitialContext ctx() throws IOException, NamingException{
        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Rodrigo Rivas\\Documents\\NetBeansProjects\\TestFitnesse\\nbproject\\jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        return ctx;
    }
    
    public String crearUsuario() throws IOException, NamingException{
        UsuarioFacadeLocal usuarioFacade=(UsuarioFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.UsuarioFacadeLocal");
        return usuarioFacade.crearUsuario(nuevoUsuario);
    }
    
    public String cambiarContraseña() throws IOException, NamingException{
        UsuarioFacadeLocal usuarioFacade=(UsuarioFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.UsuarioFacadeLocal");
        return usuarioFacade.cambiarContraseña(nombreUsuario, contraseñaActual, nuevaContraseña1, nuevaContraseña2);
    }
    
    public String reestablecerContraseña() throws IOException, NamingException{
        UsuarioFacadeLocal usuarioFacade=(UsuarioFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.UsuarioFacadeLocal");
        return usuarioFacade.reestablecerContraseña(usuarioReestablecerContraseña);
    }
    
    public String eliminarUsuario() throws IOException, NamingException{
        UsuarioFacadeLocal usuarioFacade=(UsuarioFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.UsuarioFacadeLocal");
        return usuarioFacade.eliminarUsuario(usuarioEliminar);
    }
    
    public String cambiarContraseñaVisitante() throws IOException, NamingException{
        UsuarioFacadeLocal usuarioFacade=(UsuarioFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.UsuarioFacadeLocal");
        return usuarioFacade.cambiarContraseñaVisitante(nuevaContraseñaVisitante1, nuevaContraseñaVisitante2);
    }
    
    public String asociarCorreo()throws IOException, NamingException{
        UsuarioFacadeLocal usuarioFacade=(UsuarioFacadeLocal)ctx().lookup("cl.usach.escalemania.sessionbeans.UsuarioFacadeLocal");
        return usuarioFacade.asociarCorreo(nombreUsuarioCorreo, correoUsuario);
    }
}
