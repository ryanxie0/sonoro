/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonoro.model;

import com.sonoro.persistence.EnsembleDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ryanl
 */
public class Ensemble {
    
    private String ensembleName; // can eventually be used later to support more than one ensemble
    private int id;
    private Map<Integer, Section> sections = new HashMap<>();
    private EnsembleDAO ensembleDAO = new EnsembleDAO();
    
    public Ensemble(int id) throws IOException
    {
        this.id = id;
        
        // project is hard-coded to support up to 10 sections
        for (int i = 0; i < 10; i++)
        {            
            Section section = ensembleDAO.readSection(this.getId(), i);
            if (section == null)
            {
                section = createNewSection(i);
            }
            sections.put(i, section);
        }
    }
    
    private Section createNewSection(int sectionId)
    {
        Section section = new Section();
        section.setId(sectionId);
        if (sectionId == 0) // special section for conductor/soloists
        {
            section.initializeMembers(2, 2);
        }
        else if (sectionId >= 1 && sectionId <= 4) // string sections
        {
            section.initializeMembers(5, 4);
        }
        else // all other sections
        {
            section.initializeMembers(2, 6);
        }
        return section;
    }
    
    public Section getSection(int sectionId)
    {        
        return sections.get(sectionId);
    }
    
    public int getId()
    {
        return id;
    }
    
    public List<String> getSelectedMemberEmails()
    {
        List<String> emails = new ArrayList<>();
        for (Section section : sections.values())
        {
            for (Member member : section.retrieveSelectedMembers())
            {
                emails.add(member.getEmail());
            }
        }
        return emails;
    }
}
