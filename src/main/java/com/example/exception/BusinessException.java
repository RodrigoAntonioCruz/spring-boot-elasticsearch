package com.example.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -5241554286656150984L;

	@JsonIgnore
	private HttpStatus httpStatusCode;
	private String timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	public BusinessExceptionBody getOnlyBody() {
		return BusinessExceptionBody.builder()
				.timestamp(this.timestamp)
				.status(this.status)
				.error(this.error)
				.message(this.message)
				.path(this.path)
				.build();
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class BusinessExceptionBody {

		private String timestamp;

		private Integer status;

		private String error;

		private String message;

		private String path;

	}
}
