package org.sunrain.openapi.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.sunrain.openapi.util.DefaultDateAdapter;

public class User {
	
	private String id;                    //用户UID
	private String weiboUid;              //微博Id
	private String doubanUid;             //微博Id
	private String screenName;            //昵称
	private String name;                  //友好显示名称，如Bill Gates,名称中间的空格正常显示(此特性暂不支持)
	private int province;                 //省份编码（参考省份编码表）
	private int city;                     //城市编码（参考城市编码表）
	private String location;              //地址
	private String description;           //个人描述
	private String url;                   //用户博客地址
	private String profileImageUrl;       //自定义图像
	private String gender;                //性别,m--男，f--女,n--未知
	@XmlJavaTypeAdapter(DefaultDateAdapter.class)
	@XmlElement(name = "created_at")
	private Date createdAt;               //创建时间
	private String lang;                  //用户语言版本

	public User() {
	}

	public User(String weiboUid, String doubanUid,
			String screenName, String name, int province, int city,
			String location, String description, String url,
			String profileImageUrl, String gender, Date createdAt, String lang) {
		this.weiboUid = weiboUid;
		this.doubanUid = doubanUid;
		this.screenName = screenName;
		this.name = name;
		this.province = province;
		this.city = city;
		this.location = location;
		this.description = description;
		this.url = url;
		this.profileImageUrl = profileImageUrl;
		this.gender = gender;
		this.createdAt = createdAt;
		this.lang = lang;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getWeiboUid() {
		return weiboUid;
	}

	public void setWeiboUid(String weiboUid) {
		this.weiboUid = weiboUid;
	}

	public String getDoubanUid() {
		return doubanUid;
	}

	public void setDoubanUid(String doubanUid) {
		this.doubanUid = doubanUid;
	}
}
