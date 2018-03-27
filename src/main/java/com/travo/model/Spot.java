package com.travo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table
@Entity(name = "spot")
public class Spot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "spot_name")
    private String spotName;

    @Column(name = "description")
    private String description;

    @Column(name = "x_location")
    private String xLocation;

    @Column(name = "y_location")
    private String yLocation;

    @Column(name = "z_location")
    private String zLocation;

    @Column(name = "address")
    private String address;

    @Column(name = "province_city")
    private String provinceCity;

    @Column(name = "district")
    private String district;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "enable")
    private Boolean enable;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, targetEntity = Diary.class)
    private Set<Diary> diaries;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, targetEntity = Image.class)
    private Set<Image> images;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, targetEntity = Comment.class)
    private Set<Comment> comments;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "wish_list", joinColumns = @JoinColumn(name = "spot_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> usersWishList;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "favorite", joinColumns = @JoinColumn(name = "spot_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> usersFavorite;

    public Spot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getxLocation() {
        return xLocation;
    }

    public void setxLocation(String xLocation) {
        this.xLocation = xLocation;
    }

    public String getyLocation() {
        return yLocation;
    }

    public void setyLocation(String yLocation) {
        this.yLocation = yLocation;
    }

    public String getzLocation() {
        return zLocation;
    }

    public void setzLocation(String zLocation) {
        this.zLocation = zLocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvinceCity() {
        return provinceCity;
    }

    public void setProvinceCity(String provinceCity) {
        this.provinceCity = provinceCity;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<User> getUsersWishList() {
        return usersWishList;
    }

    public void setUsersWishList(Set<User> usersWishList) {
        this.usersWishList = usersWishList;
    }

    public Set<User> getUsersFavorite() {
        return usersFavorite;
    }

    public void setUsersFavorite(Set<User> usersFavorite) {
        this.usersFavorite = usersFavorite;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
