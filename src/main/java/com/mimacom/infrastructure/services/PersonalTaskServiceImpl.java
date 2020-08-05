package com.mimacom.infrastructure.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mimacom.infrastructure.api.PersonalTask;
import com.mimacom.infrastructure.configuration.ThingsToDoConfig;
import com.mimacom.infrastructure.dto.ResponseTask;
import com.mimacom.infrastructure.dto.TaskDTO;
import com.mimacom.infrastructure.exception.MicroserviceException;
import com.mimacom.infrastructure.exception.ThingsToDoErrorCode;
import com.mimacom.infrastructure.persistence.InMemoryPersistence;
import com.mimacom.infrastructure.utils.constants.EnumTipology;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonalTaskServiceImpl implements PersonalTaskService{

	@Autowired
	private InMemoryPersistence inMemoryPersistence;
	
	@Autowired
	private ThingsToDoConfig thingsToDoConfig;
	
	
	@Override
	public List<PersonalTask> getAllPublicPersonalTask() {
		
		//cargamos los datos de todos los anuncios y fotos disponibles
		List<PersonalTask> tasks = inMemoryPersistence.getMyTasks();
		tasks.forEach(task -> {
			log.info("-----------------------------------");
			log.info(task.toString());
			log.info("-----------------------------------");
		});
		return tasks;
	}

	@Override
	public List<PersonalTask> deletePersonalTask(Integer taskId) {
		
		List<PersonalTask> tasks = inMemoryPersistence.getMyTasks();
		
		Optional<PersonalTask> elementToFind = tasks.stream()
	    		.filter(element -> element.getId()==taskId)
		    	.findFirst();
		
		if(elementToFind.isPresent()) {	
		
		tasks.stream()
        	.filter(element -> element.getId().equals(taskId))
        	.findFirst()
        	.map(ele -> {
        		tasks.remove(ele);
        		return ele;
        	});
		
		}else {
			throw new MicroserviceException("Error al intentar borrar la tarea, el identificador = "+taskId+" de la tarea es incorrecto", ThingsToDoErrorCode.ERROR_DELETING_TASK_ID_NOT_EXIST);			
		}
		return tasks;
	}

	@Override
	public ResponseTask modifyPersonalTask(Integer taskId, String typology, String description, String status) throws MicroserviceException {
		
		List<PersonalTask> tasks = inMemoryPersistence.getMyTasks();
		List<String> listTipology = EnumTipology.getListEnumTipology();
		List<String> listStatus = thingsToDoConfig.getListStatusTask();
		ResponseTask taskDTO = new ResponseTask();
		
		Optional<PersonalTask> elementToFind = tasks.stream()
    		.filter(element -> element.getId()==taskId)
	    	.findFirst();
		
		if(elementToFind.isPresent()) {
			tasks.stream()
    		.filter(element -> element.getId()==taskId)
    		.findFirst()
	    	.map(ele -> {
	    		
	    		ele.setDescription(!description.isEmpty() 
	    				? description: ele.getDescription());
	    		ele.setTypology(!typology.isEmpty() 
	    				&& listTipology.contains(typology.toUpperCase()) 
	    					? typology: ele.getTypology());
	    		ele.setStatus(!status.isEmpty() 
	    				&& listStatus.contains(status.toUpperCase()) 
	    					? status: ele.getStatus());
	    		
	    		taskDTO.setMsg("Tarea actualizada correctamente");
	    		taskDTO.setTask(ele);
	    		
	    		return ele;
	    	});
			
		}else {
			throw new MicroserviceException("Error al actualizar la tarea, el identificador = "+taskId+" de la tarea es incorrecto", ThingsToDoErrorCode.ERROR_UPDATING_TASK_ID_NOT_EXIST);			
		}
		
		return taskDTO;
	}

	@Override
	public ResponseTask finishPersonalTask(Integer taskId) {
			
		List<PersonalTask> tasks = inMemoryPersistence.getMyTasks();
		ResponseTask taskDTO = new ResponseTask();
		
		tasks.stream()
	    	.filter(element -> element.getId()==taskId)
	    	.findFirst()
	    	.map(ele -> {
	    		
	    		ele.setStatus(thingsToDoConfig.getFinishString());
	    		
	    		taskDTO.setMsg("Tarea finalizada correctamente");
	    		taskDTO.setTask(ele);
	    		
	    		return ele;
	    	});
		
		if(taskDTO.getMsg().isEmpty()) {
			taskDTO.setMsg("Error al finalizar la tarea");
		}
		
		return taskDTO;
	}

	@Override
	public List<PersonalTask> createPersonalTask(TaskDTO personalTask) throws MicroserviceException {
		
		List<PersonalTask> tasks = inMemoryPersistence.getMyTasks();
		List<String> listTipology = EnumTipology.getListEnumTipology();
		
		Comparator<PersonalTask> comparator = Comparator.comparing(PersonalTask::getId);
		PersonalTask personalTaskMaxId = tasks.stream().max(comparator).get();
		
		Integer maxId = personalTaskMaxId.getId();
		
		if(maxId!=null) {
		
			if(listTipology.contains(personalTask.getTypology().toUpperCase())){
			
				PersonalTask newPersonalTask = new PersonalTask(personalTaskMaxId.getId()+1, personalTask.getTypology(), personalTask.getDescription(), thingsToDoConfig.getPendingString());
				tasks.add(newPersonalTask);
			
			}else {
				throw new MicroserviceException("Error al crear la tarea, el valor de tipologia = "+personalTask.getTypology()+" no es válido, los"
						+ " valores correctos son: "+listTipology.toString(), ThingsToDoErrorCode.ERROR_CREATING_TASK_TYPOLOGY_NOT_EXIST);	
			}
		
		}else {
			throw new MicroserviceException("Error al crear la tarea, error al crear el identificador de la tarea", ThingsToDoErrorCode.ERROR_CREATING_TASK_ID_NOT_EXIST);			
		}
		
		return tasks;
	}

}














