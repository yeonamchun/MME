package com.dto;

public class UserDTO
{
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_alias;
	private String user_mobile1;
	private String user_mobile2;
	private String user_mobile3;
	private String user_address;
	private String user_brand;
	
	private String seller_num;
	private String manager_code;
	
	private String cre_dt;

	public UserDTO()
	{
		super();
	}
	
	public UserDTO(String user_id, String user_pw, String user_name, String user_alias, String user_mobile1,
			String user_mobile2, String user_mobile3, String user_address, String user_brand, String seller_num,
			String manager_code)
	{
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_alias = user_alias;
		this.user_mobile1 = user_mobile1;
		this.user_mobile2 = user_mobile2;
		this.user_mobile3 = user_mobile3;
		this.user_address = user_address;
		this.user_brand = user_brand;
		this.seller_num = seller_num;
		this.manager_code = manager_code;
		this.cre_dt = null;
	}
	
	
	public UserDTO(String user_id, String user_pw, String user_name, String user_alias, String user_mobile1,
			String user_mobile2, String user_mobile3, String user_address, String user_brand)
	{
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_alias = user_alias;
		this.user_mobile1 = user_mobile1;
		this.user_mobile2 = user_mobile2;
		this.user_mobile3 = user_mobile3;
		this.user_address = user_address;
		this.user_brand = user_brand;
		this.seller_num = "";
		this.manager_code = "";
		this.cre_dt = null;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getUser_pw()
	{
		return user_pw;
	}

	public void setUser_pw(String user_pw)
	{
		this.user_pw = user_pw;
	}

	public String getUser_name()
	{
		return user_name;
	}

	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}

	public String getUser_alias()
	{
		return user_alias;
	}

	public void setUser_alias(String user_alias)
	{
		this.user_alias = user_alias;
	}

	public String getUser_mobile1()
	{
		return user_mobile1;
	}

	public void setUser_mobile1(String user_mobile1)
	{
		this.user_mobile1 = user_mobile1;
	}

	public String getUser_mobile2()
	{
		return user_mobile2;
	}

	public void setUser_mobile2(String user_mobile2)
	{
		this.user_mobile2 = user_mobile2;
	}

	public String getUser_mobile3()
	{
		return user_mobile3;
	}

	public void setUser_mobile3(String user_mobile3)
	{
		this.user_mobile3 = user_mobile3;
	}

	public String getUser_address()
	{
		return user_address;
	}

	public void setUser_address(String user_address)
	{
		this.user_address = user_address;
	}

	public String getUser_brand()
	{
		return user_brand;
	}

	public void setUser_brand(String user_brand)
	{
		this.user_brand = user_brand;
	}

	public String getSeller_num()
	{
		return seller_num;
	}

	public void setSeller_num(String seller_num)
	{
		this.seller_num = seller_num;
	}

	public String getManager_code()
	{
		return manager_code;
	}

	public void setManager_code(String manager_code)
	{
		this.manager_code = manager_code;
	}

	public String getCre_dt()
	{
		return cre_dt;
	}

	public void setCre_dt(String cre_dt)
	{
		this.cre_dt = cre_dt;
	}

	@Override
	public String toString()
	{
		return "UserDTO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name + ", user_alias="
				+ user_alias + ", user_mobile1=" + user_mobile1 + ", user_mobile2=" + user_mobile2 + ", user_mobile3="
				+ user_mobile3 + ", user_address=" + user_address + ", user_brand=" + user_brand + ", seller_num="
				+ seller_num + ", manager_code=" + manager_code + ", cre_dt=" + cre_dt + "]";
	}
	
}
