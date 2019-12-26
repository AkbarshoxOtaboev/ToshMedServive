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
    private Integer price;
    private Integer generalPrice;
    private String date;

    public Drug() {
    }

    public Drug(String drugName, Integer count, Integer price, Integer generalPrice, String date) {
        this.drugName = drugName;
        this.count = count;
        this.price = price;
        this.generalPrice = generalPrice;
        this.date = date;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getGeneralPrice() {
        return generalPrice;
    }

    public void setGeneralPrice(Integer generalPrice) {
        this.generalPrice = generalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
