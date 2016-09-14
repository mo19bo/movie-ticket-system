package DuangClass;

import java.util.Date;

public class Adver {

	public Adver() {
		// TODO Auto-generated constructor stub
	}

	private String image_url;// ͼƬ·��
	private String text;// ������
	private Date pu_da;// ��������
	private Date vailddate_da;// ��Ч����

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPu_da() {
		return pu_da;
	}

	public void setPu_da(Date pu_da) {
		this.pu_da = pu_da;
	}

	public Date getVailddate_da() {
		return vailddate_da;
	}

	public void setVailddate_da(Date vailddate_da) {
		this.vailddate_da = vailddate_da;
	}

}
