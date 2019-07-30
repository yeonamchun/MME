package com.dto;

import com.itf.CarELE;

public class CarDTO
{
	private String product_id;					// 제품 아이디	// 자동 생성
	private String brand_id;					// 제조 회사 아이디
	private String car_new;						// 신차 중고차
	private String car_model_year;				// 연식
	private String car_name;					// 자동차 이름
	
	private String type_id;						// 자동차 종류
	private String size_id;						// 자동차 크기 아이디
	
	private String engine_id;					// 엔진 아이디
	private int car_cc;							// 자동차 CC
	private String cylinder_id;					// 실린터 아이디
	private String inhalation_type_id;			// 흡기 방식 아이디
	private String gear_type_id;				// 기어 방식 아이디
	private String gear_count_id;				// 기어 단수
	private int car_hp;							// 자동차 마력
	private float torque;						// 자동차 토크
	private String driven_system_id;			// 굴림 방식 아이디
	private String door_id;						// 자동차 문 갯수
	private String made_in_id;					// 국산 수입 구분

	private int car_passengers;					// 승차 인원
	private String car_color;					// 자동차 컬러
	private float car_fuel;						// 연비
	private int car_fuel_tank;					// 주유 탱크 용량
	private String car_safety;					// 안전 사항
	private String car_convenience;				// 편의 사항
	private int car_amount;						// 자동차 가격
	
	private String car_main_image_1;			// 메인 이미지 1
	private String car_main_image_2;			// 메인 이미지 2
	private String car_main_image_3;			// 메인 이미지 3
	
	private String car_thumb_image_1;			// 메인 썸 이미지 1
	private String car_thumb_image_2;			// 메인 썸 이미지 2
	private String car_thumb_image_3;			// 메인 썸 이미지 3
	
	
	public CarDTO()
	{
		super();
		// TODO Auto-generated constructor stub
	}


	public CarDTO(String product_id, String brand_id, String car_new, String car_model_year, String car_name,
			String type_id, String size_id, String engine_id, int car_cc, String cylinder_id, String inhalation_type_id,
			String gear_type_id, String gear_count_id, int car_hp, float torque, String driven_system_id,
			int car_passengers, String car_color, float car_fuel, int car_fuel_tank, String car_safety,
			String car_convenience, int car_amount, String car_main_image_1, String car_main_image_2,
			String car_main_image_3, String car_thumb_image_1, String car_thumb_image_2, String car_thumb_image_3)
	{
		super();
		this.product_id = product_id;
		this.brand_id = brand_id;
		this.car_new = car_new;
		this.car_model_year = car_model_year;
		this.car_name = car_name;
		this.type_id = type_id;
		this.size_id = size_id;
		this.engine_id = engine_id;
		this.car_cc = car_cc;
		this.cylinder_id = cylinder_id;
		this.inhalation_type_id = inhalation_type_id;
		this.gear_type_id = gear_type_id;
		this.gear_count_id = gear_count_id;
		this.car_hp = car_hp;
		this.torque = torque;
		this.driven_system_id = driven_system_id;
		this.car_passengers = car_passengers;
		this.car_color = car_color;
		this.car_fuel = car_fuel;
		this.car_fuel_tank = car_fuel_tank;
		this.car_safety = car_safety;
		this.car_convenience = car_convenience;
		this.car_amount = car_amount;
		this.car_main_image_1 = car_main_image_1;
		this.car_main_image_2 = car_main_image_2;
		this.car_main_image_3 = car_main_image_3;
		this.car_thumb_image_1 = car_thumb_image_1;
		this.car_thumb_image_2 = car_thumb_image_2;
		this.car_thumb_image_3 = car_thumb_image_3;
	}


	public String getProduct_id()
	{
		return product_id;
	}


	public void setProduct_id(String product_id)
	{
		this.product_id = product_id;
	}


	public String getBrand_id()
	{
		return brand_id;
	}
	
	public String getBrand_id_desc()
	{
		return CarELE.BRAND_ELE.get(brand_id);
	}

	public void setBrand_id(String brand_id)
	{
		this.brand_id = brand_id;
	}


	public String getCar_new()
	{
		return car_new;
	}
	
	public String getCar_new_desc()
	{
		return CarELE.NEW_CAR_ELE.get(car_new);
	}


	public void setCar_new(String car_new)
	{
		this.car_new = car_new;
	}


	public String getCar_model_year()
	{
		return car_model_year;
	}


	public void setCar_model_year(String car_model_year)
	{
		this.car_model_year = car_model_year;
	}


	public String getCar_name()
	{
		return car_name;
	}


	public void setCar_name(String car_name)
	{
		this.car_name = car_name;
	}


	public String getType_id()
	{
		return type_id;
	}
	
	public String getType_id_desc()
	{
		return CarELE.TYPE_ELE.get(type_id);
	}


	public void setType_id(String type_id)
	{
		this.type_id = type_id;
	}


	public String getSize_id()
	{
		return size_id;
	}
	
	public String getSize_id_desc()
	{
		return CarELE.SIZE_ELE.get(size_id);
	}


	public void setSize_id(String size_id)
	{
		this.size_id = size_id;
	}


	public String getEngine_id()
	{
		return engine_id;
	}
	
	public String getEngine_id_desc()
	{
		return CarELE.ENGINE_ELE.get(engine_id);
	}

	public void setEngine_id(String engine_id)
	{
		this.engine_id = engine_id;
	}


	public int getCar_cc()
	{
		return car_cc;
	}


	public void setCar_cc(int car_cc)
	{
		this.car_cc = car_cc;
	}


	public String getCylinder_id()
	{
		return cylinder_id;
	}
	
	public String getCylinder_id_desc()
	{
		return CarELE.CYLINDER_ELE.get(cylinder_id);
	}


	public void setCylinder_id(String cylinder_id)
	{
		this.cylinder_id = cylinder_id;
	}


	public String getInhalation_type_id()
	{
		return inhalation_type_id;
	}
	
	public String getInhalation_type_id_desc()
	{
		return CarELE.INHALATION_TYPE_ELE.get(inhalation_type_id);
	}


	public void setInhalation_type_id(String inhalation_type_id)
	{
		this.inhalation_type_id = inhalation_type_id;
	}


	public String getGear_type_id()
	{
		return gear_type_id;
	}
	
	public String getGear_type_id_desc()
	{
		return CarELE.GEAR_TYPE_ELE.get(gear_type_id);
	}


	public void setGear_type_id(String gear_type_id)
	{
		this.gear_type_id = gear_type_id;
	}


	public String getGear_count_id()
	{
		return gear_count_id;
	}
	
	public String getGear_count_id_desc()
	{
		return CarELE.GEAR_COUNT_ELE.get(gear_count_id);
	}


	public void setGear_count_id(String gear_count_id)
	{
		this.gear_count_id = gear_count_id;
	}


	public int getCar_hp()
	{
		return car_hp;
	}


	public void setCar_hp(int car_hp)
	{
		this.car_hp = car_hp;
	}


	public float getTorque()
	{
		return torque;
	}


	public void setTorque(float torque)
	{
		this.torque = torque;
	}


	public String getDriven_system_id()
	{
		return driven_system_id;
	}
	
	public String getDriven_system_id_desc()
	{
		return CarELE.DRIVEN_SYSTEM_ELE.get(driven_system_id);
	}


	public void setDriven_system_id(String driven_system_id)
	{
		this.driven_system_id = driven_system_id;
	}


	public int getCar_passengers()
	{
		return car_passengers;
	}
	
	public void setCar_passengers(int car_passengers)
	{
		this.car_passengers = car_passengers;
	}


	public String getCar_color()
	{
		return car_color;
	}


	public void setCar_color(String car_color)
	{
		this.car_color = car_color;
	}


	public float getCar_fuel()
	{
		return car_fuel;
	}


	public void setCar_fuel(float car_fuel)
	{
		this.car_fuel = car_fuel;
	}


	public int getCar_fuel_tank()
	{
		return car_fuel_tank;
	}


	public void setCar_fuel_tank(int car_fuel_tank)
	{
		this.car_fuel_tank = car_fuel_tank;
	}


	public String getCar_safety()
	{
		return car_safety;
	}


	public void setCar_safety(String car_safety)
	{
		this.car_safety = car_safety;
	}


	public String getCar_convenience()
	{
		return car_convenience;
	}


	public void setCar_convenience(String car_convenience)
	{
		this.car_convenience = car_convenience;
	}


	public int getCar_amount()
	{
		return car_amount;
	}


	public void setCar_amount(int car_amount)
	{
		this.car_amount = car_amount;
	}


	public String getCar_main_image_1()
	{
		return car_main_image_1;
	}


	public void setCar_main_image_1(String car_main_image_1)
	{
		this.car_main_image_1 = car_main_image_1;
	}


	public String getCar_main_image_2()
	{
		return car_main_image_2;
	}


	public void setCar_main_image_2(String car_main_image_2)
	{
		this.car_main_image_2 = car_main_image_2;
	}


	public String getCar_main_image_3()
	{
		return car_main_image_3;
	}


	public void setCar_main_image_3(String car_main_image_3)
	{
		this.car_main_image_3 = car_main_image_3;
	}


	public String getCar_thumb_image_1()
	{
		return car_thumb_image_1;
	}


	public void setCar_thumb_image_1(String car_thumb_image_1)
	{
		this.car_thumb_image_1 = car_thumb_image_1;
	}


	public String getCar_thumb_image_2()
	{
		return car_thumb_image_2;
	}


	public void setCar_thumb_image_2(String car_thumb_image_2)
	{
		this.car_thumb_image_2 = car_thumb_image_2;
	}


	public String getCar_thumb_image_3()
	{
		return car_thumb_image_3;
	}


	public void setCar_thumb_image_3(String car_thumb_image_3)
	{
		this.car_thumb_image_3 = car_thumb_image_3;
	}
	
	public String getDoor_id()
	{
		return door_id;
	}
	
	public String getDoor_id_desc()
	{
		return CarELE.DOOR_ELE.get(door_id);
	}


	public void setDoor_id(String door_id)
	{
		this.door_id = door_id;
	}
	
	public String getMade_in_id()
	{
		return made_in_id;
	}
	
	public String getMade_in_id_desc()
	{
		return CarELE.MADE_IN_TYPE_ELE.get(made_in_id);
	}


	public void setMade_in_id(String made_in_id)
	{
		this.made_in_id = made_in_id;
	}
}
