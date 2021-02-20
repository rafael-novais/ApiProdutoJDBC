package ultraje.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ultraje.domain.dto.error.FormValidationError;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormValidationError> formErrorHandle(MethodArgumentNotValidException exception) {
		
		List<FormValidationError> errorsDTO = new ArrayList<FormValidationError>();
		
		exception.getBindingResult().getFieldErrors()
			.forEach(fieldError -> {
				String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
				FormValidationError errorDTO = 
						new FormValidationError(fieldError.getField(), message);
				errorsDTO.add(errorDTO);
			});
		
		return errorsDTO;
	}
	
}
