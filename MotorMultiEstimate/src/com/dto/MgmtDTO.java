package com.dto;

public class MgmtDTO 
{
	private String MANAGER_CODE;
	private String MANAGER_PAGE;
	
	public MgmtDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MgmtDTO(String mANAGER_CODE, String mANAGER_PAGE) {
		super();
		MANAGER_CODE = mANAGER_CODE;
		MANAGER_PAGE = mANAGER_PAGE;
	}

	public String getMANAGER_CODE() {
		return MANAGER_CODE;
	}

	public void setMANAGER_CODE(String mANAGER_CODE) {
		MANAGER_CODE = mANAGER_CODE;
	}

	public String getMANAGER_PAGE() {
		return MANAGER_PAGE;
	}

	public void setMANAGER_PAGE(String mANAGER_PAGE) {
		MANAGER_PAGE = mANAGER_PAGE;
	}

	@Override
	public String toString() {
		return "MgmtDTO [MANAGER_CODE=" + MANAGER_CODE + ", MANAGER_PAGE=" + MANAGER_PAGE + "]";
	}
	
	
	
	

}
