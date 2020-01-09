package uz.test.repository;


import org.hibernate.Session;
import uz.test.db.HibernateUtils;
import uz.test.model.Company;
import uz.test.model.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugRepository {
    private Session session = HibernateUtils.openSession();

    public DrugRepository() throws Exception {
    }

    public List<Drug> getAllDrugs() {

        List<Drug> drugs = new ArrayList<>();
        try {

            drugs = session.createQuery("SELECT c FROM Drug c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drugs;
    }

    public void saveDrug(Drug drug) {

        try {

            session.getTransaction().begin();
            session.save(drug);
            session.getTransaction().commit();

        } catch (Exception e) {
            System.err.println(e + "  na gap");
        }
    }

    public Drug getDrugByID(Long id) {

        Drug drug = null;
        try {

            try {
                drug = session.find(Drug.class, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drug;
    }

    public void deleteDrug(Long id) {

        try {

            Drug deleteDrug = session.find(Drug.class, id);
            session.getTransaction().begin();
            session.remove(deleteDrug);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDrug(Drug drug){
        try{
            session.getTransaction().begin();
            session.merge(drug);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Drug> getDrugsByIdCompany(Long company_id) {

        List<Drug> drugs = null;
        try {
            Company company = session.find(Company.class, company_id);
            drugs = company.getGetAllDrugsByIdCompany();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return drugs;
    }

}
