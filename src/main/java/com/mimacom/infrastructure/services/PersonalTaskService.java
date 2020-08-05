package com.mimacom.infrastructure.services;

import java.util.List;

import com.mimacom.infrastructure.api.PersonalTask;
import com.mimacom.infrastructure.dto.ResponseTask;
import com.mimacom.infrastructure.dto.TaskDTO;

public interface PersonalTaskService {

	public List<PersonalTask> getAllPublicPersonalTask();
	
	public List<PersonalTask> deletePersonalTask(Integer taskId);
	
	public ResponseTask modifyPersonalTask(Integer taskId, String typology, String description, String status);
	
	public ResponseTask finishPersonalTask(Integer taskId);
	
	public List<PersonalTask> createPersonalTask(TaskDTO personalTask);
}
