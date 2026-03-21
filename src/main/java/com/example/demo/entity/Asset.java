package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String status; // AVAILABLE / LOANED

    // ★ 追加：Loanエンティティとの紐付け（1対多）
    // FetchType.EAGER にすることで、Asset取得時に貸出情報も一緒に読み込みます
    @OneToMany(mappedBy = "asset", fetch = FetchType.EAGER)
    private List<Loan> loans;

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Loan> getLoans() { return loans; }
    public void setLoans(List<Loan> loans) { this.loans = loans; }

    /**
     * ★ 追加：現在の貸出情報を取得するロジック
     */
    public Loan getCurrentLoan() {
        if ("LOANED".equals(this.status) && loans != null && !loans.isEmpty()) {
            // 履歴の最後に登録されたものが現在の貸出情報
            return loans.get(loans.size() - 1);
        }
        return null;
    }

    /**
     * ★ 追加：借用者名を取得（以前の loanUserName の代わり）
     */
    public String getLoanUserName() {
        Loan current = getCurrentLoan();
        return (current != null) ? current.getUser().getName() : "-";
    }

    /**
     * ★ 追加：返却期限を計算して取得
     */
    public LocalDate getReturnDeadline() {
        Loan current = getCurrentLoan();
        if (current != null && current.getLoanDate() != null && current.getPeriodDays() != null) {
            // 貸出日 + 期間 = 返却期限
            return current.getLoanDate().plusDays(current.getPeriodDays());
        }
        return null;
    }
}
