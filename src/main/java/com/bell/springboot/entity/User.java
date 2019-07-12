package com.bell.springboot.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 329835398738805850L;
	public int getId() {
		return 123+id;
	}
	public static void main(String[] args) {
		User user = new User();
		int id2 = user.getId();
		System.out.println(id2);
	}
//	@Getter
	private int id; //用户详情ID	
	private int userId;    //用户id
	private String account; //用户名
	private String realName; //真实姓名
	private String phone; //电话
	private String companyId;   //单位id（待定）
	private String deptId;      //部门id（待定）
	private String roleId;     //角色id（待定）
	private String areas; //用户标签名（管理范围）
	private String idNumber; //身份证号码
	private String idNumberImgUrl; //身份证号码(待定)
	private String email; //邮箱
	private Date lastLoginTime; //最后登录时间（待定）
	private String password; //密码
	private int salt;  //盐值
	private String appIsOk;//是否为app用户类型（0：是（无法登录平台） 1：否（可以登录平台））
	private int isBle;    //是否开通蓝牙开锁，1开  2关
	private int way;      //开户方式   1.email   2.短信

	private String departmentName;
	private String companyName;
	
	private String device;
	
}
