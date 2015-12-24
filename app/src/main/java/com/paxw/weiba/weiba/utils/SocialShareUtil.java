package com.paxw.weiba.weiba.utils;

public class SocialShareUtil  {
//	public final UMSocialService mController = UMServiceFactory.getUMSocialService(Constant.DESCRIPTOR);
//	private Context mContext;
//	private ActionSheet menuView;
//	private SocialShareUtil shareUtil;
//
//	private  Object data;
//
//	public SocialShareUtil(Context context, Object data) {
//		this(context,data,true);
//
//	}
//	public SocialShareUtil(Context context, Object data,boolean isNeedShowSheet){
//		this.mContext = context;
//		this.data = data;
//		if (isNeedShowSheet) {
//			showActionSheet();
//		}
//	}
//
//	@Override
//	public void onItemClick(int itemPosition) {
//		switch (itemPosition) {
//		case 0:
//			performShare(SHARE_MEDIA.SINA);
//
//			break;
//		case 1:
//			performShare(SHARE_MEDIA.WEIXIN);
//
//			break;
//		case 2:
//			performShare(SHARE_MEDIA.WEIXIN_CIRCLE);
//
//			break;
//		case 3:
//			performShare(SHARE_MEDIA.QQ);
//			break;
//		case 4:
//			performShare(SHARE_MEDIA.QZONE);
//
//			break;
//		case 5:
//			copyContent();
//
//			break;
//		case 6:
//			menuView.dismiss();
//
//			break;
//		default:
//			break;
//		}
//
//	}
//
//	private void performShare(SHARE_MEDIA platform) {
//
//		mController.postShare((FragmentActivity) mContext, platform, new SnsPostListener() {
//
//			@Override
//			public void onStart() {
//
//			}
//
//			@Override
//			public void onComplete(SHARE_MEDIA platform, int eCode, SocializeEntity entity) {
//				Logs.e(platform.toString() + eCode + entity.mSessionID);
//				String showText = platform.toString();
//				if (eCode == StatusCode.ST_CODE_SUCCESSED) {
//					showText +="StatusCode.ST_CODE_SUCCESSED";
//				} else {
//					showText += "FAIL";
//				}
//				Toast.makeText(mContext, showText, Toast.LENGTH_SHORT).show();
//				menuView.dismiss();
//			}
//		});
//	}
//
//	@SuppressLint("NewApi")
//	private void copyContent() {
////		String content = Constant.SHARE_URL + data.getShareUrl();
//		String content = Constant.SHARE_URL ;
//		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
//			android.text.ClipboardManager clipboard = (android.text.ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
//			clipboard.setText(content);
//		} else {
//			android.content.ClipboardManager clipboard = (android.content.ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
//			android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", content);
//			clipboard.setPrimaryClip(clip);
//		}
//		CommonUtil.showToast(R.string.share_copy_url);
//	}
//
//	public void showActionSheet() {
//		menuView = new ActionSheet(mContext);
//		menuView.setCancelButtonTitle(mContext.getResources().getString(R.string.share_cancel));// before  add  items
//		menuView.addItems(new String[] { mContext.getResources().getString(R.string.share_sina),mContext.getResources().getString(R.string.share_wechat), mContext.getResources().getString(R.string.share_freiends), mContext.getResources().getString(R.string.share_qq), mContext.getResources().getString(R.string.share_qqzone), mContext.getResources().getString(R.string.share_copy) }, new int[] { R.drawable.img_share_sina, R.drawable.img_share_wechat, R.drawable.img_share_freiends,
//				                     R.drawable.img_share_qq, R.drawable.img_share_qqzone, R.drawable.img_share_copy });
//		menuView.setItemClickListener(this);
//		menuView.setCancelableOnTouchMenuOutside(true);
//		menuView.showMenu();
//	}
//
//	public void openMenuView(){
//		if(menuView != null){
//			menuView.showMenu();
//		}
//	}
}
