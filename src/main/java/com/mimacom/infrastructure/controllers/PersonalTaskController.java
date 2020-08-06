package com.mimacom.infrastructure.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mimacom.infrastructure.api.PersonalTask;
import com.mimacom.infrastructure.configuration.ThingsToDoConfig;
import com.mimacom.infrastructure.dto.ResponseTask;
import com.mimacom.infrastructure.dto.TaskDTO;
import com.mimacom.infrastructure.exception.MicroserviceException;
import com.mimacom.infrastructure.exception.ThingsToDoErrorCode;
import com.mimacom.infrastructure.services.PersonalTaskService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PersonalTaskController {

	@Autowired
	private PersonalTaskService personalTaskService;
	
	@Autowired
	private ThingsToDoConfig thingsToDoConfig;
	
    @ApiOperation(value = "Endpoint que obtiene un listado completo de todas las tareas")
	@GetMapping(value = "/getAllTask", produces = "application/json")
    public ResponseEntity<List<PersonalTask>> getAllPublicTask() {
    	log.info("Ini endpoint getAllPublicTask");
    	
    	List<PersonalTask> listResult = personalTaskService.getAllPublicPersonalTask();
    	
    	return new ResponseEntity<>(listResult, HttpStatus.OK);
    }
    
    @ApiOperation("Endpoint que permite editar una tarea")
	@PostMapping(value = "/update/{taskId}")
	public ResponseEntity<ResponseTask> updateTask(@PathVariable(required = true) Integer taskId, 
			@RequestParam(defaultValue="", required=false) String typology,
			@RequestParam(defaultValue="", required=false) String description, 
			@RequestParam(defaultValue="", required=false) String status) throws Exception{

    	ResponseTask taskDTO =  new ResponseTask();
		
    	if(description.length() > thingsToDoConfig.getMaxSizeDescription()) {
    		throw new MicroserviceException("Error actualizando la tarea, el tamaño máximo de la description es "+thingsToDoConfig.getMaxSizeDescription(), ThingsToDoErrorCode.ERROR_DESCRIPTION_TOO_LONG);			
    	}
    	
    	taskDTO = personalTaskService.modifyPersonalTask(taskId, typology, description, status);
		
    	return new ResponseEntity<>(taskDTO, HttpStatus.OK);
	}
    
    @ApiOperation("Endpoint que permite crear una tarea y asigna un identificador de forma automática a al tarea")
    @PostMapping(value = "/create")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 500, message = "Error")
	})
	public ResponseEntity<List<PersonalTask>> createPersonalTask (@RequestBody TaskDTO personalTask) {
    	List<PersonalTask> listPersonalTask = new ArrayList<>();
    	
    	if(personalTask.getDescription().length() > thingsToDoConfig.getMaxSizeDescription()) {
    		throw new MicroserviceException("Error creando la tarea,  el tamaño máximo de la description es "+thingsToDoConfig.getMaxSizeDescription(), ThingsToDoErrorCode.ERROR_DESCRIPTION_TOO_LONG);			
    	}
    	
		listPersonalTask = personalTaskService.createPersonalTask(personalTask);
		
		return new ResponseEntity<List<PersonalTask>>(listPersonalTask, HttpStatus.OK);
	}
    
    @ApiOperation("Endpoint que permite cambiar el estado de una tarea a finalizada a partir del id de la tarea")
	@PostMapping(value = "/finish")
	public ResponseEntity<ResponseTask> finishTask(@RequestParam("taskId") Integer taskId) {
    	ResponseTask taskDTO =  new ResponseTask();
    	
    	taskDTO = personalTaskService.finishPersonalTask(taskId);
    	
		return new ResponseEntity<>(taskDTO, HttpStatus.OK);
	}
    
    @ApiOperation(value = "Endpoint que permite borrar una tarea de forma definitva a partir del id de la tarea")
	@DeleteMapping(value = "/task/{taskId}", produces = "application/json")
	public ResponseEntity<List<PersonalTask>> deleteTask(@PathVariable(required = true) Integer taskId) {
    	log.info("Ini endpoint deleteTask");
    	
    	List<PersonalTask> listResult = personalTaskService.deletePersonalTask(taskId);

		return new ResponseEntity<>(listResult, HttpStatus.OK);

	}
    
     
}
