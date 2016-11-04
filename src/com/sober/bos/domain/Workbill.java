package com.sober.bos.domain;

import java.sql.Timestamp;

/**
 * Created by Renhai on 2016/11/4.
 */
public class Workbill {
    private String id;
    private Staff staff;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    private String type;
    private String pickstate;
    private Timestamp buildtime;
    private Integer attachbilltimes;
    private String remark;
    private Noticebill noticebill;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPickstate() {
        return pickstate;
    }

    public void setPickstate(String pickstate) {
        this.pickstate = pickstate;
    }

    public Timestamp getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(Timestamp buildtime) {
        this.buildtime = buildtime;
    }

    public Integer getAttachbilltimes() {
        return attachbilltimes;
    }

    public void setAttachbilltimes(Integer attachbilltimes) {
        this.attachbilltimes = attachbilltimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workbill workbill = (Workbill) o;

        if (id != null ? !id.equals(workbill.id) : workbill.id != null) return false;
        if (type != null ? !type.equals(workbill.type) : workbill.type != null) return false;
        if (pickstate != null ? !pickstate.equals(workbill.pickstate) : workbill.pickstate != null) return false;
        if (buildtime != null ? !buildtime.equals(workbill.buildtime) : workbill.buildtime != null) return false;
        if (attachbilltimes != null ? !attachbilltimes.equals(workbill.attachbilltimes) : workbill.attachbilltimes != null)
            return false;
        if (remark != null ? !remark.equals(workbill.remark) : workbill.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (pickstate != null ? pickstate.hashCode() : 0);
        result = 31 * result + (buildtime != null ? buildtime.hashCode() : 0);
        result = 31 * result + (attachbilltimes != null ? attachbilltimes.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    public Noticebill getNoticebill() {
        return noticebill;
    }

    public void setNoticebill(Noticebill noticebill) {
        this.noticebill = noticebill;
    }
}
