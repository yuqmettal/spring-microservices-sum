package com.yuqmettal.sum.sumservice.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sums")
public class Sum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int firstNumber;
    private int secondNumber;
    @Transient
    private int total;


    public Sum() {
    }

    public Sum(Long id, int firstNumber, int secondNumber) {
        this.id = id;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    @PostLoad
    private void postLoad() {
        this.total = this.firstNumber + this.secondNumber;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFirstNumber() {
        return this.firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return this.secondNumber;
    }
    
    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }
    
    public int getTotal() {
        return this.total;
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Sum)) {
            return false;
        }
        Sum sum = (Sum) o;
        return Objects.equals(id, sum.id) && firstNumber == sum.firstNumber && secondNumber == sum.secondNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstNumber, secondNumber);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstNumber='" + getFirstNumber() + "'" +
            ", secondNumber='" + getSecondNumber() + "'" +
            "}";
    }

}