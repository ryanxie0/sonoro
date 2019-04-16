/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonoro.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ryanl
 */
public class Section {

    private List<List<Member>> members = new ArrayList<>();
    private int id;
    private String name;
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int countSelectedMembers()
    {
        return retrieveSelectedMembers().size();
    }
    
    public List<Member> retrieveSelectedMembers()
    {
        List<Member> selectedMembers = new ArrayList<>();
        for (List<Member> row : members)
        {
            for (Member member : row)
            {
                if (member.isSelected())
                {
                    selectedMembers.add(member);
                }
            }
        }
        return selectedMembers;
    }
    
    public List<List<Member>> getMembers()
    {
        return members;
    }
    
    public void setMembers(List<List<Member>> members)
    {
        this.members = members;
    }
    
    public List<List<Member>> cloneMembers()
    {
        List<List<Member>> membersCopy = new ArrayList<>();
        for (List<Member> row : members)
        {
            List<Member> rowCopy = new ArrayList<>();
            for (Member member : row)
            {
                rowCopy.add(member.clone());
            }
            membersCopy.add(rowCopy);
        }
        return membersCopy;
    }
    
    public void initializeMembers(int rows, int cols)
    {
        for (int i = 0; i < rows; i++)
        {
            List<Member> col = new ArrayList<>();
            for (int j = 0; j < cols; j++)
            {
                col.add(new Member());
            }
            members.add(col);
        }
    }
}
