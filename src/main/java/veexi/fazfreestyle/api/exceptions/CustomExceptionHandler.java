package veexi.fazfreestyle.api.exceptions;

import java.util.Date;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import veexi.fazfreestyle.api.exceptions.handle.BadRequestException;
import veexi.fazfreestyle.api.exceptions.handle.ForbiddenException;
import veexi.fazfreestyle.api.exceptions.handle.UnauthorizedException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	// métodos de tratamento de exceções aqui

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequestException(Exception ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}

}