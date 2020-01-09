package uz.test.repository;

import org.hibernate.Session;
import uz.test.db.HibernateUtils;
import uz.test.model.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdmiRepository {
    private  Session session = HibernateUtils.openSession();

    public AdmiRepository() throws Exception {
    }

    public List<Admin> getAllAdmin(){

        List<Admin> admins = new ArrayList<>();
        try{
            admins = session.createQuery("SELECT s FROM Admin s").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;
    }
    public void createAdmin(Admin admin){
        try{
            session.getTransaction().begin();
            session.save(admin);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Admin getAdminByID(Long id){
        Admin  adminID = null;
        try{
            adminID =session.find(Admin.class , id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminID;
    }
    public void updateAdmin(Admin admin){
        try{
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
