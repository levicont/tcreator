package com.lvg.tcreator.persistence.models;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.Constants;

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

    @Enumerated(EnumType.STRING)
    private NdtMethod ndtMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderDB order;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExamTicketDB> tickets = new HashSet<>();

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

    public NdtMethod getNdtMethod() {
        return ndtMethod;
    }

    public void setNdtMethod(NdtMethod ndtMethod) {
        this.ndtMethod = ndtMethod;
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

        return id.equals(examDB.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
