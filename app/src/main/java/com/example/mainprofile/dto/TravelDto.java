package com.example.mainprofile.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TravelDto implements Serializable {

    private String uri;
    private String desc;
    private String address;
    private String contact;
    private String fees;
    private String name;
    private String operation;
    private String type;
    private String key;
    private String state;

    public TravelDto(String uri, String desc, String address, String contact, String fees, String name, String operation, String type, String key, String state) {
        this.uri = uri;
        this.desc = desc;
        this.address = address;
        this.contact = contact;
        this.fees = fees;
        this.name = name;
        this.operation = operation;
        this.type = type;
        this.key = key;
        this.state = state;
    }

    public TravelDto() {
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uri", uri);
        result.put("desc", desc);
        result.put("address", address);
        result.put("contact", contact);
        result.put("fees", fees);
        result.put("name", name);
        result.put("operation", operation);
        result.put("type", type);
        return result;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "TravelDto{" +
                "uri='" + uri + '\'' +
                ", desc='" + desc + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", fees='" + fees + '\'' +
                ", name='" + name + '\'' +
                ", operation='" + operation + '\'' +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
