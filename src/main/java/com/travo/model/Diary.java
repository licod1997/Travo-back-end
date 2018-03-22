package com.travo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table
@Entity(name = "Diary")
public class Diary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "visited_date")
    @Temporal(TemporalType.DATE)
    private Date visitedDate;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Spot.class)
    @JoinColumn(name = "spot_id")
    private Spot spot;

    public Diary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVisitedDate() {
        return visitedDate;
    }

    public void setVisitedDate(Date visitedDate) {
        this.visitedDate = visitedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }
}
