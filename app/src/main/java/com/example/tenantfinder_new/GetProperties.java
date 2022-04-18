package com.example.tenantfinder_new;

public class GetProperties {
    public String propertyname,address,city,locality,furnished,type;
    public String rent,bhk,sqft;
    GetProperties(String propertyname2, String address2, String rent2, String bhk2, String sqft2, String city2, String locality2, String furnished2, String type)
    {
        this.propertyname = propertyname2;
        this.address = address2;
        this.rent = rent2;
        this.bhk = bhk2;
        this.sqft=sqft2;

        this.city=city2;
        this.locality=locality2;

        this.furnished=furnished2;
this.type=type;
    }
}