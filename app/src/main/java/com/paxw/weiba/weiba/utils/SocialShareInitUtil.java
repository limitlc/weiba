package com.paxw.weiba.weiba.utils;

import android.app.Activity;
import android.content.Context;

/**
 * 
 * @author lichuang
 * 
 */
public class SocialShareInitUtil {
	private Activity mContext;
	private String tagetUrl;
	private String title;
	private String content;
	private String[] imageUrls;

//	private final UMSocialService mController = UM;
//	private String imageUrl;
	public SocialShareInitUtil(Context context, String tagetUrl, String title,String content,String[] imageUrls) {
		this.mContext = (Activity) context;

//		this.tagetUrl = Constant.SHARE_URL + tagetUrl;
		//这个目标url 是固定的 http://weibo.com/u/2672690885   http://weibo.com/u/2672690885
		this.tagetUrl = "http://weibo.com/u/2672690885";
		this.title = title;
		this.content = content;
		this.imageUrls = imageUrls;

//		configPlatforms();
//		setShareContent();
	}
//
//	private void configPlatforms() {
//
//		mController.getConfig().setSsoHandler(new SinaSsoHandler());
//
//
//		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(mContext, Constant.QQ_APP_ID, Constant.QQ_APP_SECREATE);
//		qqSsoHandler.addToSocialSDK();
//
//
//		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(mContext, Constant.QQ_APP_ID, Constant.QQ_APP_SECREATE);
//		qZoneSsoHandler.addToSocialSDK();
//
//
//		UMWXHandler wxHandler = new UMWXHandler(mContext, Constant.WECHAT_ID, Constant.WECHAT_SECREATE);
//		wxHandler.addToSocialSDK();
//
//
//		UMWXHandler wxCircleHandler = new UMWXHandler(mContext, Constant.WECHAT_ID, Constant.WECHAT_SECREATE);
//		wxCircleHandler.setToCircle(true);
//		wxCircleHandler.addToSocialSDK();
//	}
//
//	private void setShareContent() {
//		UMImage image = new UMImage(mContext, imageUrl);
//
//		ShareContent content =new ShareContent();
//		content.mText = "";
//		content.mTitle ="";
//		content.mMedia = image;
//
//		SinaShareContent sinaContent = new SinaShareContent(new ShareContent());
//		sinaContent.setTitle("啦啦啦" + title);
//
//
//
//		mController.;
//
//
//		QQShareContent qqShareContent = new QQShareContent();
//		qqShareContent.setTitle("�Ѵ�����?" + title);
//		qqShareContent.setShareContent("�Ѵ�����?" + title);
//		qqShareContent.setTargetUrl(tagetUrl);
//		qqShareContent.setShareImage(image);
//		mController.setShareMedia(qqShareContent);
//
//		QZoneShareContent qzone = new QZoneShareContent();
//		qzone.setTitle("�Ѵ�����?" + title);
//		qzone.setShareContent("�Ѵ�����?" + title);
//		qzone.setTargetUrl(tagetUrl);
//		qzone.setShareImage(image);
//		mController.setShareMedia(qzone);
//
//		// ����΢�ŷ��������
//		WeiXinShareContent weixinContent = new WeiXinShareContent();
//		weixinContent.setShareContent("�Ѵ�����?" + title);
//		weixinContent.setTitle("�Ѵ�����?" + title);
//		weixinContent.setTargetUrl(tagetUrl);
//		weixinContent.setShareImage(image);
//		mController.setShareMedia(weixinContent);
//
//		CircleShareContent circleMedia = new CircleShareContent();
//		circleMedia.setShareContent("�Ѵ�����?" + title);
//		circleMedia.setTitle("�Ѵ�����?" + title);
//		circleMedia.setTargetUrl(tagetUrl);
//		circleMedia.setShareImage(image);
//		mController.setShareMedia(circleMedia);
//
//	}
}
