package uz.test.repository;

import org.hibernate.Session;
import uz.test.db.HibernateUtils;
import uz.test.model.Company;
import uz.test.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private Session session = HibernateUtils.openSession();

    public PaymentRepository() throws Exception {
    }

    public List<Payment> getAllPayments(){
        List<Payment> payments = new ArrayList<>();
        try{
            payments = session.createQuery("SELECT c FROM Payment c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }
    public void creataPayment (Payment payment){
        try {
            session.getTransaction().begin();
            session.save(payment);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deletePayment(Long id){
        try{
            Payment payment = session.find(Payment.class,id);
            session.getTransaction().begin();
            session.remove(payment);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getPaymentsByCompanyID(Long id){
        List<Payment> payments = null;
        try{
            Company company = session.find(Company.class, id);
            payments = company.getGetAllPaymentsByIdCompany();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }

    public void updatePayment(Payment payment){
        try{
            session.getTransaction().begin();
            session.merge(payment);
            session.getTransaction().commit();
        } catch (Exception e) {
           System.err.println(e);
        }
    }
}
