package com.niit.pojo;

public enum ResponseCode {
	SuccessGetData(1,"successfully get data!"),
	SuccessGetStusData(2,"successfully get all students data!"),
	SuccessfullyUpdate(200,"successfully update!"),
	SuccessfullyAdd(201,"successfully add!"),
	SuccessfullyDelete(202,"successfully delete"),
	SuccessfullAskLeave(203,"successfully ask leave"),
	SuccessfullyToAgree(204, "successfully to agree leave" ),
	FailGetData(0,"fail to get data!"),
	FailUpdate(400,"fail to update"),
	FailAdd(401,"fail to add"),
	FailDelete(402,"fail to delete"),
	FailAskLeave(403,"fail to ask leave"),
	FailToAgree(404, "fail to agree leave" );



    private final int code;
	private final String reply;

	
	private ResponseCode(int code, String reply) {
		this.code=code;
		this.reply=reply;
	}

	public int getCode() {
		return code;
	}

	public String getReply() {
		return reply;
	}
}
