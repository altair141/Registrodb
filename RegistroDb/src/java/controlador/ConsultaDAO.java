/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;


import modelo.Consulta;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 *
 * @author Altair0141
 */
public class ConsultaDAO {
    public boolean ingresarEmpresa(Consulta c) {  //Este mÃƒÂ©todo recibe el pojo preparado
        SessionFactory sf = null;
        Session sesion = null;
        Transaction tx = null;
        boolean flag = true;
        try {
            sf = NewHibernateUtil.getSessionFactory(); //obtener acceso  a la factorÃƒÂ­a de  sesiones
            sesion = sf.openSession(); // iniciar la sesiÃƒÂ³n
            tx = sesion.beginTransaction(); //iniciar una transacciÃƒÂ³n
            sesion.save(c); //para guardar la transacciÃƒÂ³n
            tx.commit();//confinar los cambios que se hacen en la base de datos
            sesion.close(); //para cerrar la sesiÃƒÂ³n
        } catch (Exception ex) {
            tx.rollback(); //volver un paso atrÃƒÂ¡s
            flag = false;
            throw new RuntimeException("No se pudo guardar el producto");
        }
        return flag;
    }

    public Consulta consultarEmpresa(String rol) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();//obtener acceso  a la factorÃƒÂ­a de  sesiones 
        Session sesion = sf.openSession();// iniciar la sesiÃƒÂ³n
        Consulta c = (Consulta) sesion.get(Consulta.class, rol);/*instanciamos p, el metodo sesion.get           
         obtiene un poducto de la tabla Producto y el campo que corresponde a su clave primaria que es codigo
         ,  debemos fozar a que el objeto obtenido sea de tipo Producto.*/

        sesion.close();
        if (c != null) {
            return c;
        } else {
            return null;
        }
    }

    public List<Consulta> verEmpresas() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();//obtener acceso  a la factorÃƒÂ­a de  sesiones
        Session sesion = sf.openSession();// iniciar la sesiÃƒÂ³n
        Query query = sesion.createQuery("from Empresa");//definimos una query y una consulta, esta consulta es en base al pojo por eso con mayÃƒÂºscula, luego hibÃƒÂ©rnate mapea a la tabla
        List<Consulta> lista = query.list();//en  una lista se rescata los objetos por medio de objeto c/u de las tuplas. Cada una de los tuplas se guarda como objeto en una lista
        sesion.close();
        return lista;
    } 
}


