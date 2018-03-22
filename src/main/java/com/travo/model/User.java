package com.travo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table
@Entity(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name", columnDefinition = "nvarchar(255)")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "enable")
    private Boolean enable;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, targetEntity = Spot.class)
    private Set<Spot> spots;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, targetEntity = Diary.class)
    private Set<Diary> diaries;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, targetEntity = Image.class)
    private Set<Image> images;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, targetEntity = Comment.class)
    private Set<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "wish_list", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "spot_id", referencedColumnName = "id"))
    private Set<Spot> spotsWishList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "favorite", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "spot_id", referencedColumnName = "id"))
    private Set<Spot> spotsFavorite;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Set<Spot> getSpots() {
        return spots;
    }

    public void setSpots(Set<Spot> spots) {
        this.spots = spots;
    }

    public Set<Diary> getDiaries() {
        return diaries;
    }

    public void setDiaries(Set<Diary> diaries) {
        this.diaries = diaries;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Spot> getSpotsWishList() {
        return spotsWishList;
    }

    public void setSpotsWishList(Set<Spot> spotsWishList) {
        this.spotsWishList = spotsWishList;
    }

    public Set<Spot> getSpotsFavorite() {
        return spotsFavorite;
    }

    public void setSpotsFavorite(Set<Spot> spotsFavorite) {
        this.spotsFavorite = spotsFavorite;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
