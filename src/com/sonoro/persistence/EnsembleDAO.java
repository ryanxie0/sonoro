/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonoro.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sonoro.model.Section;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author ryanl
 */
public class EnsembleDAO {
    
    public void saveSection(int ensembleId, Section section) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(getSectionFile(ensembleId, section.getId()), section);
    }
    
    public Section readSection(int ensembleId, int sectionId) throws IOException
    {
        File sectionFile = getSectionFile(ensembleId, sectionId);
        if (sectionFile.exists())
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(sectionFile, Section.class);
        }
        return null;
    }
    
    private File getSectionFile(int ensembleId, int sectionId) 
    {
        File ensembleDir = new File("ensemble" + ensembleId);
        ensembleDir.mkdirs();
        
        File sectionFile = new File(ensembleDir.getAbsolutePath() + "/section" + sectionId + ".txt");
        return sectionFile;
    }
}
