package uz.test.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer balans;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company_id")
    private List<Drug> getAllDrugsByIdCompany;

    @OneToMany(mappedBy = "companyId")
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<Payment> getAllPaymentsByIdCompany;

    public Company() {
    }

    public Company(String name, Integer balans) {
        this.name = name;
        this.balans = balans;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalans() {
        return balans;
    }

    public void setBalans(Integer balans) {
        this.balans = balans;
    }

    public List<Drug> getGetAllDrugsByIdCompany() {
        return getAllDrugsByIdCompany;
    }

    public void setGetAllDrugsByIdCompany(List<Drug> getAllDrugsByIdCompany) {
        this.getAllDrugsByIdCompany = getAllDrugsByIdCompany;
    }

    public List<Payment> getGetAllPaymentsByIdCompany() {
        return getAllPaymentsByIdCompany;
    }

    public void setGetAllPaymentsByIdCompany(List<Payment> getAllPaymentsByIdCompany) {
        this.getAllPaymentsByIdCompany = getAllPaymentsByIdCompany;
    }
}
