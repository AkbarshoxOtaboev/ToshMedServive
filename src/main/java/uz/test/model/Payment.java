package uz.test.model;

import javax.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String commentary;
    private Integer paymentVolume;
    private String date;

    private Long companyId;

    public Payment() {
    }


    public Payment(String commentary, Integer paymentVolume, String date, Long companyId) {
        this.commentary = commentary;
        this.paymentVolume = paymentVolume;
        this.date = date;
        this.companyId = companyId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPaymentVolume() {
        return paymentVolume;
    }

    public void setPaymentVolume(Integer paymentVolume) {
        this.paymentVolume = paymentVolume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long company_id) {
        this.companyId = company_id;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
