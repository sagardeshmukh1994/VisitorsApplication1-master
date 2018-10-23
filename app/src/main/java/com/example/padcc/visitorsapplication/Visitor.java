package com.example.padcc.visitorsapplication;

import java.io.Serializable;

/**
 * Created by padcc on 29/09/2018.
 */

public class Visitor implements Serializable {
    int visitorId;
    String VfirstnName,VLastName,VPhone,VEmail,VTechnique,Vgender;


    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public String getVfirstnName() {
        return VfirstnName;
    }

    public void setVfirstnName(String vfirstnName) {
        VfirstnName = vfirstnName;
    }

    public String getVLastName() {
        return VLastName;
    }

    public void setVLastName(String VLastName) {
        this.VLastName = VLastName;
    }

    public String getVPhone() {
        return VPhone;
    }

    public void setVPhone(String VPhone) {
        this.VPhone = VPhone;
    }

    public String getVEmail() {
        return VEmail;
    }

    public void setVEmail(String VEmail) {
        this.VEmail = VEmail;
    }

    public String getVTechnique() {
        return VTechnique;
    }

    public void setVTechnique(String VTechnique) {
        this.VTechnique = VTechnique;
    }


    public String getVgender() {
        return Vgender;
    }

    public void setVgender(String vgender) {
        Vgender = vgender;
    }
}
