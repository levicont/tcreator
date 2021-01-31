package com.lvg.tcreator.persistence.models;

import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.Constants;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EXAM")
public class ExamDB  implements ModelDB{


    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TestTypes testTypes;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private OrderDB order;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<ExamTicketDB> tickets = new HashSet<>();

    public void addExamTicket(ExamTicketDB ticket){
        tickets.add(ticket);
        ticket.setExam(this);
    }

    public void removeExamTicket(ExamTicketDB ticket){
        tickets.remove(ticket);
        ticket.setExam(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestTypes getTestTypes() {
        return testTypes;
    }

    public void setTestTypes(TestTypes testTypes) {
        this.testTypes = testTypes;
    }

    public OrderDB getOrder() {
        return order;
    }

    public void setOrder(OrderDB order) {
        this.order = order;
    }

    public Set<ExamTicketDB> getTickets() {
        return tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamDB examDB = (ExamDB) o;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(order, examDB.order);
        eb.append(testTypes, examDB.testTypes);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder().append(order).append(testTypes).hashCode();
    }
}
