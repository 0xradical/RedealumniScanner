package com.redealumni.scanner;

import com.google.gson.Gson;

public class StudentInfo
{
	private String verificationCode;
	private String name;
	private String cpf;
	
	public StudentInfo()
	{
		this.verificationCode = "123456";
		this.name             = "Teste";
		this.cpf              = "59230385603";
	}
	
	public String toJson()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public static StudentInfo fromJson(String json)
	{
		Gson gson = new Gson();
		return gson.fromJson(json, StudentInfo.class);
	}

	public String getVerificationCode()
	{
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode)
	{
		this.verificationCode = verificationCode;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCpf()
	{
		return cpf;
	}

	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}
	
	
	
	
}
