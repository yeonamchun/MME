package com.dto;

public class SellerDTO
{
	private String seller_num;
	private String seller_name;
	private String seller_post;
	private String seller_address1;
	private String seller_address2;
	private String seller_product_type;
	private int seller_product_quantity;
	
	public SellerDTO()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public SellerDTO(String seller_num, String seller_name, String seller_post, String seller_address1,
			String seller_address2, String seller_product_type, int seller_product_quantity)
	{
		super();
		this.seller_num = seller_num;
		this.seller_name = seller_name;
		this.seller_post = seller_post;
		this.seller_address1 = seller_address1;
		this.seller_address2 = seller_address2;
		this.seller_product_type = seller_product_type;
		this.seller_product_quantity = seller_product_quantity;
	}

	public SellerDTO(String seller_num, String seller_name, String seller_post, String seller_address1,
			String seller_address2, String seller_product_type)
	{
		super();
		this.seller_num = seller_num;
		this.seller_name = seller_name;
		this.seller_post = seller_post;
		this.seller_address1 = seller_address1;
		this.seller_address2 = seller_address2;
		this.seller_product_type = seller_product_type;
		this.seller_product_quantity = 0;
	}

	public String getSeller_num()
	{
		return seller_num;
	}

	public void setSeller_num(String seller_num)
	{
		this.seller_num = seller_num;
	}

	public String getSeller_name()
	{
		return seller_name;
	}

	public void setSeller_name(String seller_name)
	{
		this.seller_name = seller_name;
	}

	public String getSeller_post()
	{
		return seller_post;
	}

	public void setSeller_post(String seller_post)
	{
		this.seller_post = seller_post;
	}

	public String getSeller_address1()
	{
		return seller_address1;
	}

	public void setSeller_address1(String seller_address1)
	{
		this.seller_address1 = seller_address1;
	}

	public String getSeller_address2()
	{
		return seller_address2;
	}

	public void setSeller_address2(String seller_address2)
	{
		this.seller_address2 = seller_address2;
	}

	public String getSeller_product_type()
	{
		return seller_product_type;
	}

	public void setSeller_product_type(String seller_product_type)
	{
		this.seller_product_type = seller_product_type;
	}

	public int getSeller_product_quantity()
	{
		return seller_product_quantity;
	}

	public void setSeller_product_quantity(int seller_product_quantity)
	{
		this.seller_product_quantity = seller_product_quantity;
	}

	@Override
	public String toString()
	{
		return "SellerDTO [seller_num=" + seller_num + ", seller_name=" + seller_name + ", seller_post=" + seller_post
				+ ", seller_address1=" + seller_address1 + ", seller_address2=" + seller_address2
				+ ", seller_product_type=" + seller_product_type + ", seller_product_quantity="
				+ seller_product_quantity + "]";
	}
	
	
}
