package com.example.allergy_db;

import android.widget.TextView;

import java.io.Serializable;

public class Menu  implements Serializable {
    private String profile;
    private String menuName;
    private String menuInfo;
    private Long price=null;
    private String cal = "N/A";
    private String carbonhydrate = "N/A";
    private String protein = "N/A";
    private String fat = "N/A";
    private String na = "N/A";
    private String sugar = "N/A";


    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(String menuInfo) {
        this.menuInfo = menuInfo;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCal() { return cal; }

    public void setCal(String cal) { this.cal = cal; }

    public String getCarbonhydrate() { return carbonhydrate; }

    public void setCarbonhydrate(String carbonhydrate) { this.carbonhydrate = carbonhydrate; }

    public String getProtein() { return protein; }

    public void setProtein(String protein) { this.protein = protein; }

    public String getFat() { return fat; }

    public void setFat(String fat) { this.fat = fat; }

    public String getNa() { return na; }

    public void setNa(String na) { this.na = na; }

    public String getSugar() { return sugar; }

    public void setSugar(String sugar) { this.sugar = sugar; }

    public Menu(){

    }
}
