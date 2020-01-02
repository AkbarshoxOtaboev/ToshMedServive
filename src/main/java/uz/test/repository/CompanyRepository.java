package uz.test.repository;


import org.hibernate.Session;
import uz.test.db.HibernateUtils;
import uz.test.model.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private Session session = HibernateUtils.openSession();

    public CompanyRepository() throws Exception {
    }

    public List<Company> getAllCompany(){

        List<Company> companies = new ArrayList<Company>();
        try{

            companies = session.createQuery("select c from Company c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  companies;
    }

    public void createCompany(Company company){
        Session session =null;
        try{
            session = HibernateUtils.openSession();
            session.getTransaction().begin();
            session.save(company);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session !=null && session.isOpen()){
                session.close();
            }
        }

    }

    public Company getCompanyById(Long id){

        Company companyById = null;
        try{

            try{
                companyById = session.find(Company.class, id);
            } catch (NullPointerException e) {
                System.out.println("No company with id" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return companyById;
    }

    public void updateCompany(Company company){

        try{

            session.getTransaction().begin();
            session.merge(company);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCompany(Long id){

        try{

            Company companyToDelete = session.find(Company.class , id);
            session.getTransaction().begin();
            session.remove(companyToDelete);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
