package org.sunrain.openapi.model;

import java.io.Serializable;
import java.util.Date;

public class Weibo implements Serializable {
	
	private static final long serialVersionUID = 6262014855514251752L;
	
	private User user = null;                            //������Ϣ
	private Date createdAt;                              //status����ʱ��
	private String id;                                   //status id
	private String mid;                                  //΢��MID
	private long idstr;                                  //�����ֶΣ�����ʹ��                     
	private String text;                                 //΢������
	private WeiboSource source;                          //΢����Դ
	private boolean favorited;                           //�Ƿ����ղ�
	private long inReplyToStatusId;                      //�ظ�ID
	private long inReplyToUserId;                        //�ظ���ID
	private String inReplyToScreenName;                  //�ظ����ǳ�
	private String thumbnailPic;                         //΢�������е�ͼƬ�����Ե�ַ
	private String bmiddlePic;                           //����ͼƬ
	private String originalPic;                          //ԭʼͼƬ
	private Weibo retweetedStatus = null;                //ת���Ĳ��ģ�����Ϊstatus���������ת������û�д��ֶ�
	private String geo;                                  //������Ϣ�����澭γ�ȣ�û��ʱ�����ش��ֶ�
	private double latitude = -1;                        //γ��
	private double longitude = -1;                       //����
	private int repostsCount;                            //ת����
	private int commentsCount;                           //������
	private String annotations;                          //Ԫ���ݣ�û��ʱ�����ش��ֶ�
	private int mlevel;
	
	public Weibo(){

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public long getIdstr() {
		return idstr;
	}

	public void setIdstr(long idstr) {
		this.idstr = idstr;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public WeiboSource getSource() {
		return source;
	}

	public void setSource(WeiboSource source) {
		this.source = source;
	}

	public boolean isFavorited() {
		return favorited;
	}

	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	public long getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	public void setInReplyToStatusId(long inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}

	public long getInReplyToUserId() {
		return inReplyToUserId;
	}

	public void setInReplyToUserId(long inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}

	public String getInReplyToScreenName() {
		return inReplyToScreenName;
	}

	public void setInReplyToScreenName(String inReplyToScreenName) {
		this.inReplyToScreenName = inReplyToScreenName;
	}

	public String getThumbnailPic() {
		return thumbnailPic;
	}

	public void setThumbnailPic(String thumbnailPic) {
		this.thumbnailPic = thumbnailPic;
	}

	public String getBmiddlePic() {
		return bmiddlePic;
	}

	public void setBmiddlePic(String bmiddlePic) {
		this.bmiddlePic = bmiddlePic;
	}

	public String getOriginalPic() {
		return originalPic;
	}

	public void setOriginalPic(String originalPic) {
		this.originalPic = originalPic;
	}

	public Weibo getRetweetedStatus() {
		return retweetedStatus;
	}

	public void setRetweetedStatus(Weibo retweetedStatus) {
		this.retweetedStatus = retweetedStatus;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getRepostsCount() {
		return repostsCount;
	}

	public void setRepostsCount(int repostsCount) {
		this.repostsCount = repostsCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public String getAnnotations() {
		return annotations;
	}

	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}

	public int getMlevel() {
		return mlevel;
	}

	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}
}
