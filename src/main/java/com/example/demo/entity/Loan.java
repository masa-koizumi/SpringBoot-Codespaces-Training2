package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate; // ★必要

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate loan_date; // ★追加
    private Integer period_days;  // ★追加
    
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // getter/setter
    public LocalDate getLoanDate() { return loan_date; }
    public void setLoanDate(LocalDate loan_date) { this.loan_date = loan_date; }

    public Integer getPeriodDays() { return period_days; }
    public void setPeriodDays(Integer period_days) { this.period_days = period_days; }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Asset getAsset() { return asset; }
    public void setAsset(Asset asset) { this.asset = asset; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
