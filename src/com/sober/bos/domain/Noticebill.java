package com.sober.bos.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Renhai on 2016/11/4.
 */
public class Noticebill {
    private String id;
    private String customerId;
    private String customerName;
    private String delegater;
    private String telephone;
    private String pickaddress;
    private String arrivecity;
    private String product;
    private Date pickdate;
    private Integer num;
    private Double weight;
    private String volume;
    private String remark;
    private String ordertype;
    private Set<Workbill> workbills=new HashSet<>();
    private User user;
    private Staff staff;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDelegater() {
        return delegater;
    }

    public void setDelegater(String delegater) {
        this.delegater = delegater;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPickaddress() {
        return pickaddress;
    }

    public void setPickaddress(String pickaddress) {
        this.pickaddress = pickaddress;
    }

    public String getArrivecity() {
        return arrivecity;
    }

    public void setArrivecity(String arrivecity) {
        this.arrivecity = arrivecity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Date getPickdate() {
        return pickdate;
    }

    public void setPickdate(Date pickdate) {
        this.pickdate = pickdate;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Noticebill that = (Noticebill) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (delegater != null ? !delegater.equals(that.delegater) : that.delegater != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (pickaddress != null ? !pickaddress.equals(that.pickaddress) : that.pickaddress != null) return false;
        if (arrivecity != null ? !arrivecity.equals(that.arrivecity) : that.arrivecity != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (pickdate != null ? !pickdate.equals(that.pickdate) : that.pickdate != null) return false;
        if (num != null ? !num.equals(that.num) : that.num != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (volume != null ? !volume.equals(that.volume) : that.volume != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (ordertype != null ? !ordertype.equals(that.ordertype) : that.ordertype != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (delegater != null ? delegater.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (pickaddress != null ? pickaddress.hashCode() : 0);
        result = 31 * result + (arrivecity != null ? arrivecity.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (pickdate != null ? pickdate.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (ordertype != null ? ordertype.hashCode() : 0);
        return result;
    }

    public Set<Workbill> getWorkbills() {
        return workbills;
    }

    public void setWorkbills(Set<Workbill> workbills) {
        this.workbills = workbills;
    }
}
