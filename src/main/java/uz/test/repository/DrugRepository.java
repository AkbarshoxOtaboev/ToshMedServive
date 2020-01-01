package uz.test.repository;

import javafx.collections.ObservableList;
import org.hibernate.Session;
import uz.test.db.HibernateUtils;
import uz.test.model.Company;
import uz.test.model.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugRepository {

    public List<Drug> getAllDrugs(){
        Session session =null;
        List<Drug> drugs = new ArrayList<>();
        try{
            session = HibernateUtils.openSession();
            drugs =session.createQuery("SELECT c FROM Drug c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return  drugs;
    }

    public void saveDrug(Drug drug){
        Session session = null;
        try {
            session = HibernateUtils.openSession();
            session.getTransaction().begin();
            session.save(drug);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){

                session.close();
            }
        }
    }

    public Drug getDrugByID(Long id){
        Session session =null;
        Drug drug = null;
        try{
            session= HibernateUtils.openSession();
            try{
                drug=session.find(Drug.class, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return drug;
    }

    public void deleteDrug(Long id){
        Session session = null;
        try{
            session = HibernateUtils.openSession();
            Drug deleteDrug = session.find(Drug.class,id);
            session.getTransaction().begin();
            session.remove(deleteDrug);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public List<Drug> getDrugsByIdCompany(Long company_id){
        Session session = null;
        Company company = null;
        List<Drug> drugs = null;
        try{
            session = HibernateUtils.openSession();
            try {
                company = session.find(Company.class,company_id);
                drugs = company.getGetAllDrugsByIdCompany();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return drugs;
    }

}
