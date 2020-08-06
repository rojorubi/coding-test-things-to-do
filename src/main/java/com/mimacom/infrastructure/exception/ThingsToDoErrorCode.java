package com.mimacom.infrastructure.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;


@Getter
@AllArgsConstructor
@ToString
public enum ThingsToDoErrorCode{		
	
	DEFAULT("001-001", "This is a default error to make test", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_UNAUTHORIZED("001-400", "The user does not have permissions to perform the operation.", HttpStatus.UNAUTHORIZED),
	ERROR_UPDATING_TASK("001-002", "Error updating task", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_UPDATING_TASK_ID_NOT_EXIST("001-003", "Error updating task, the identifier is not valid", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_FINISHING_TASK("001-004", "Error during the process of finishing a task", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_CREATING_TASK_ID_NOT_EXIST("001-005", "Error creating task, the identifier is not valid", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_DELETING_TASK_ID_NOT_EXIST("001-006", "Error deleting task, the identifier is not valid", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_CREATING_TASK_TYPOLOGY_NOT_EXIST("001-007", "Error creating the task, the value of typology is not valid", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_DESCRIPTION_TOO_LONG("001-008", "Error, description too long", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_FINISHING_TASK_ID_NOT_EXIST("001-009", "Error finishing task, the identifier is not valid", HttpStatus.INTERNAL_SERVER_ERROR);
	
	private final @NonNull String code;
	private final @NonNull String message;
	private final @NonNull HttpStatus httpStatus;

}
