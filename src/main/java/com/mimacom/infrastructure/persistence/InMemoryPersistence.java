package com.mimacom.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mimacom.infrastructure.api.PersonalTask;
import com.mimacom.infrastructure.utils.constants.EnumTipology;

import lombok.Data;

@Data
@Repository
public class InMemoryPersistence {

    private List<PersonalTask> myTasks;
    
    public InMemoryPersistence() {
    	myTasks = new ArrayList<PersonalTask>();
    	
    	myTasks.add(new PersonalTask(1, EnumTipology.PERSONAL.getValue(), "Tarea uno", "PENDING"));
    	myTasks.add(new PersonalTask(2, EnumTipology.PROFESSIONAL.getValue(), "Tarea dos", "PENDING"));
    	myTasks.add(new PersonalTask(3, EnumTipology.PROFESSIONAL.getValue(), "Tarea tres", "PENDING"));
    	myTasks.add(new PersonalTask(4, EnumTipology.PERSONAL.getValue(), "Tarea cuatro", "PENDING"));
    	myTasks.add(new PersonalTask(5, EnumTipology.PROFESSIONAL.getValue(), "Tarea cinco", "PENDING"));
    }

}
