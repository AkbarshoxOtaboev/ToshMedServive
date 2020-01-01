package uz.test.model;

import javax.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer paymentVolume;
    private String date;

    public Payment() {
    }

    public Payment(Integer paymentVolume, String date) {
        this.paymentVolume = paymentVolume;
        this.date = date;
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
}