package com.mimacom.infrastructure.dto;

import com.mimacom.infrastructure.api.PersonalTask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ResponseTask {

	String msg;
	PersonalTask task;
}

