package com.github.segu23.ejercicioeldarfiserv.entity;

import com.github.segu23.ejercicioeldarfiserv.constant.CardBrand;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.Objects;

//@Entity
public class CreditCard {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private long cardNumber;

    @ManyToOne
    private User cardHolder;

    private Date expiration;

    @Enumerated(EnumType.STRING)
    private CardBrand cardBrand;

    public CreditCard(long id, long cardNumber, User cardHolder, Date expiration, CardBrand cardBrand) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiration = expiration;
        this.cardBrand = cardBrand;
    }

    public CreditCard() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public User getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(User cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public CardBrand getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(CardBrand cardBrand) {
        this.cardBrand = cardBrand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard that)) return false;
        return getId() == that.getId() && getCardNumber() == that.getCardNumber() && Objects.equals(getCardHolder(), that.getCardHolder()) && Objects.equals(getExpiration(), that.getExpiration()) && getCardBrand() == that.getCardBrand();
    }

    @Override
    public String toString() {
        return "CreditCard{" + "id=" + id + ", cardNumber=" + cardNumber + ", cardHolder=" + cardHolder + ", expiration=" + expiration + ", cardBrand=" + cardBrand + '}';
    }
}
