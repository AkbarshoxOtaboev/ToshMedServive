package uz.test.repository;

import org.hibernate.Session;
import uz.test.db.HibernateUtils;
import uz.test.model.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdmiRepository {

    public List<Admin> getAllAdmin(){
        Session session = null;
        List<Admin> admins = new ArrayList<>();
        try{
            session = HibernateUtils.openSession();
            admins = session.createQuery("SELECT s FROM Admin s").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return admins;
    }
    public void createAdmin(Admin admin){
        Session session = null;
        try{
            session =HibernateUtils.openSession();
            session.getTransaction().begin();
            session.save(admin);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session !=null && session.isOpen()){
                session.close();
            }
        }
    }
    public Admin getAdminByID(Long id){
        Session session = null;
        Admin  adminID = null;
        try{
            session = HibernateUtils.openSession();
            adminID =session.find(Admin.class , id);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return adminID;
    }
    public void updateAdmin(Admin admin){
        Session session = null;
        try{
            session = HibernateUtils.openSession();
            session.getTransaction().begin();
            session.merge(admin);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }
}
