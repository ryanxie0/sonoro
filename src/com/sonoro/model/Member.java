package com.sonoro.model;

public class Member
{
    private String name;
    private String email;
    private boolean selected;

    public Member()
    {

    }

    public Member(String name, String email)
    {
        this.name = name;
        this.email = email;
    }
    
    public Member clone()
    {
        Member clone = new Member(name, email);
        clone.setSelected(selected);
        return clone;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    } 

    public String getName() { 
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
