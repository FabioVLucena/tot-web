package com.aeon.tot.auth.api.dto;

public class TesteDTO {

	private Long userId;

	public TesteDTO(Long userId) {
		super();
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
