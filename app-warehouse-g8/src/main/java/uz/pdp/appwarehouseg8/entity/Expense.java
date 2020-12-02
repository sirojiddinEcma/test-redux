package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

/**
 * BY SIROJIDDIN on 28.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expense extends AbsEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ExpenseType expenseType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CashBox cashBox;

    private Timestamp date = new Timestamp(System.currentTimeMillis());

    @Column(nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserWarehouse userWarehouse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Company company;

    @Column(columnDefinition = "text")
    private String description;
}
