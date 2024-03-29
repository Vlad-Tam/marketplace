<<<<<<<< HEAD:src/main/java/com/vladtam/jspapplication/models/User.java
package com.vladtam.jspapplication.models;

import java.util.*;

public class User implements BaseModelInterface {
    private Integer id;
    private BasicUserInfo basicInfo = new BasicUserInfo();
    private Date registrationDate;
    private Address address;
    private Set<Advertisement> salesList;
    private Set<Advertisement> wishList;
    private Set<Review> commentsList;

    public User(){}

    public User(Integer id, BasicUserInfo basicInfo, Date registrationDate, Address address, Set<Advertisement> salesList, Set<Advertisement> wishList, Set<Review> commentsList) {
        this.id = id;
        this.basicInfo = basicInfo;
        this.registrationDate = registrationDate;
        this.address = address;
        this.salesList = salesList;
        this.wishList = wishList;
        this.commentsList = commentsList;
    }

    public User(Integer id, String name, String surname) {
        this.id = id;
        this.basicInfo.setName(name);
        this.basicInfo.setSurname(surname);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BasicUserInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicUserInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Advertisement> getSalesList() {
        return salesList;
    }

    public void setSalesList(Set<Advertisement> salesList) {
        this.salesList = salesList;
    }

    public Set<Advertisement> getWishList() {
        return wishList;
    }

    public void setWishList(Set<Advertisement> wishList) {
        this.wishList = wishList;
    }

    public Set<Review> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(Set<Review> commentsList) {
        this.commentsList = commentsList;
    }

    @Override
    public String toString() {
        return "id=" + id + " " + basicInfo.getName() + " " + basicInfo.getSurname();
    }

    @Override
    public String outputFullInfo() {
        StringBuilder salesListInfo = new StringBuilder();
        StringBuilder wishListInfo = new StringBuilder();
        StringBuilder commentsListInfo = new StringBuilder();
        if(salesList != null) {
            for (Advertisement s : salesList) {
                salesListInfo.append(s);
                salesListInfo.append("\n");
            }
        }
        if(wishList != null) {
            for (Advertisement w : wishList) {
                wishListInfo.append(w);
                wishListInfo.append("\n");
            }
        }
        if(commentsList != null) {
            for (Review r : commentsList) {
                commentsListInfo.append(r);
                commentsListInfo.append(" \"").append(r.getComment()).append("\"");
                commentsListInfo.append("\n");
            }
        }
        return "USER INFO:\n" +
                "id = " + id + "\n" +
                "name = " + basicInfo.getName() + "\n" +
                "surname = " + basicInfo.getSurname() + "\n" +
                "phoneNumber = " + basicInfo.getPhoneNumber() + "\n" +
                "email = " + basicInfo.getEmail() + "\n" +
                "password = " + basicInfo.getPassword() + "\n" +
                "registrationDate = " + registrationDate + "\n" +
                "address = " + address + "\n\n" +
                "SALES LIST:\n" + salesListInfo + "\n" +
                "WISH LIST:\n" + wishListInfo + "\n" +
                "COMMENTS LIST:\n" + commentsListInfo + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(basicInfo, user.basicInfo) && Objects.equals(registrationDate, user.registrationDate) && Objects.equals(address, user.address) && Objects.equals(salesList, user.salesList) && Objects.equals(wishList, user.wishList) && Objects.equals(commentsList, user.commentsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, basicInfo, registrationDate, address, salesList, wishList, commentsList);
    }
}
========
package com.vladtam.marketplace.models;

import java.util.*;

public class User implements BaseModelInterface {
    private Integer id;
    private BasicUserInfo basicInfo = new BasicUserInfo();
    private Date registrationDate;
    private Address address;
    private Set<Advertisement> salesList;
    private Set<Advertisement> wishList;
    private Set<Review> commentsList;

    public User(){}

    public User(Integer id, BasicUserInfo basicInfo, Date registrationDate, Address address, Set<Advertisement> salesList, Set<Advertisement> wishList, Set<Review> commentsList) {
        this.id = id;
        this.basicInfo = basicInfo;
        this.registrationDate = registrationDate;
        this.address = address;
        this.salesList = salesList;
        this.wishList = wishList;
        this.commentsList = commentsList;
    }

    public User(Integer id, String name, String surname) {
        this.id = id;
        this.basicInfo.setName(name);
        this.basicInfo.setSurname(surname);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BasicUserInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicUserInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Advertisement> getSalesList() {
        return salesList;
    }

    public void setSalesList(Set<Advertisement> salesList) {
        this.salesList = salesList;
    }

    public Set<Advertisement> getWishList() {
        return wishList;
    }

    public void setWishList(Set<Advertisement> wishList) {
        this.wishList = wishList;
    }

    public Set<Review> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(Set<Review> commentsList) {
        this.commentsList = commentsList;
    }

    @Override
    public String toString() {
        return "id=" + id + " " + basicInfo.getName() + " " + basicInfo.getSurname();
    }

    @Override
    public String outputFullInfo() {
        StringBuilder salesListInfo = new StringBuilder();
        StringBuilder wishListInfo = new StringBuilder();
        StringBuilder commentsListInfo = new StringBuilder();
        if(salesList != null) {
            for (Advertisement s : salesList) {
                salesListInfo.append(s);
                salesListInfo.append("\n");
            }
        }
        if(wishList != null) {
            for (Advertisement w : wishList) {
                wishListInfo.append(w);
                wishListInfo.append("\n");
            }
        }
        if(commentsList != null) {
            for (Review r : commentsList) {
                commentsListInfo.append(r);
                commentsListInfo.append(" \"").append(r.getComment()).append("\"");
                commentsListInfo.append("\n");
            }
        }
        return "USER INFO:\n" +
                "id = " + id + "\n" +
                "name = " + basicInfo.getName() + "\n" +
                "surname = " + basicInfo.getSurname() + "\n" +
                "phoneNumber = " + basicInfo.getPhoneNumber() + "\n" +
                "email = " + basicInfo.getEmail() + "\n" +
                "password = " + basicInfo.getPassword() + "\n" +
                "registrationDate = " + registrationDate + "\n" +
                "address = " + address + "\n\n" +
                "SALES LIST:\n" + salesListInfo + "\n" +
                "WISH LIST:\n" + wishListInfo + "\n" +
                "COMMENTS LIST:\n" + commentsListInfo + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(basicInfo, user.basicInfo) && Objects.equals(registrationDate, user.registrationDate) && Objects.equals(address, user.address) && Objects.equals(salesList, user.salesList) && Objects.equals(wishList, user.wishList) && Objects.equals(commentsList, user.commentsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, basicInfo, registrationDate, address, salesList, wishList, commentsList);
    }
}
>>>>>>>> main:task2/src/main/java/com/vladtam/marketplace/models/User.java
