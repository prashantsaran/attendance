package com.attendance.entity;

public class MyResponse<T> {
	
	 private String message;
	 private T data;
	 private int status;
	 
	public MyResponse(String message, T data, int status) {
		super();
		this.message = message;
		this.data = data;
		this.status = status;
	}
	
	public MyResponse() {
		super();
	}

	@Override
	public String toString() {
		return "MyResponse [message=" + message + ", data=" + data + ", status=" + status + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	 
	
}
