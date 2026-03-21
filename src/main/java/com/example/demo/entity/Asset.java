package com.example.demo.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status; // AVAILABLE / LOANED

    @Transient
    private String loanUserName; // 表示用

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLoanUserName() { return loanUserName; }
    public void setLoanUserName(String loanUserName) { this.loanUserName = loanUserName; }
}
