package uz.test.model;

import javax.persistence.*;

@Entity
@Table(name = "drugs")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String drugName;
    private Integer count;
    private Double price;
    private Double generalPrice;
    private String date;

    private Long company_id;

    public Drug(String drugName, Integer count, Double price, Double generalPrice, String date) {
        this.drugName = drugName;
        this.count = count;
        this.price = price;
        this.generalPrice = generalPrice;
        this.date = date;
    }
    public Drug() {

    }

    public Drug(String drugName, Integer count, Double price, Double generalPrice, String date, Long company_id) {
        this.drugName = drugName;
        this.count = count;
        this.price = price;
        this.generalPrice = generalPrice;
        this.date = date;
        this.company_id = company_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getGeneralPrice() {
        return generalPrice;
    }

    public void setGeneralPrice(Double generalPrice) {
        this.generalPrice = generalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }
}
