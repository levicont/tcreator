package com.lvg.tcreator.persistence.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Victor Levchenko LVG Corp. on 30.04.2020.
 */
@Entity
@Table(name = "STATISTIC")
public class StatisticDB implements Serializable {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "exam_order_id", unique = true)
    private OrderDB order;

    @ElementCollection
    @CollectionTable(name = "exam_ticket", joinColumns = @JoinColumn(name = "statistic_id"))
    @OrderBy("number")
    private Collection<ExamTicketDB> examTickets = new ArrayList<>();

    protected StatisticDB(){}

    public StatisticDB(OrderDB orderDB){
        this.order = orderDB;
    }

    public Long getId() {
        return id;
    }

    public OrderDB getOrder() {
        return order;
    }

    public Collection<ExamTicketDB> getExamTickets() {
        return examTickets;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(OrderDB order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticDB that = (StatisticDB) o;
        return Objects.equals(getId(), that.getId()) &&
                getOrder().equals(that.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrder());
    }
}
