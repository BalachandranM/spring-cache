package com.sample.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.bean.Student;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "/v1")
public interface CacheApi {

	@ApiOperation(value = "This API endpoint will retrieve all students", notes = "", response = ResponseEntity.class, tags = {
			"Students API", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Request Processed Successfully", response = ResponseEntity.class),
			@ApiResponse(code = 400, message = "Bad Request. Please see response body for more details", response = Void.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error. Please see response body for more details", response = Void.class) })
	@GetMapping(value = "/get-all-students", produces = { "application/json" })
	public ResponseEntity<List<Student>> getAllStudents();

	@ApiOperation(value = "This API endpoint will retrieve specific student according to the input", notes = "", response = ResponseEntity.class, tags = {
			"Students API", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Request Processed Successfully", response = ResponseEntity.class),
			@ApiResponse(code = 400, message = "Bad Request. Please see response body for more details", response = Void.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error. Please see response body for more details", response = Void.class) })
	@GetMapping(value = "/get-student/{id}", produces = { "application/json" })
	public ResponseEntity<Student> getStudent(@PathVariable("id") Long id);
	
	@ApiOperation(value = "This API endpoint will save specific student according to the input", notes = "", response = ResponseEntity.class, tags = {
			"Students API", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Request Processed Successfully", response = ResponseEntity.class),
			@ApiResponse(code = 400, message = "Bad Request. Please see response body for more details", response = Void.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error. Please see response body for more details", response = Void.class) })
	@PostMapping(value = "/save-student", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Student> saveStudent(@RequestBody Student student);

}
