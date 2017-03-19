package com.example.android7;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 
 * @ClassName: BaseApplication
 * @Description: TODO
 * @author yangyakun 
 * @date 2015年11月7日 下午3:17:05
 * 
 */
public class BaseApplication extends Application {
	// 获取到主线程的上下文
	private static BaseApplication mContext;
	// 获取到主线程的handler
	private static Handler mMainThreadHanler;
	// 获取到主线程
	private static Thread mMainThread;
	// 获取到主线程的id
	private static int mMainThreadId;
	
	private static HashMap<String, String> ERRORCODE;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mContext = this;
		mMainThreadHanler = new Handler();
		mMainThread = Thread.currentThread();
		// 获取到调用线程的id
		mMainThreadId = android.os.Process.myTid();

	}

	private void initErrorCode() {
		HashMap<String, String> errorcode = new HashMap<String, String>();
		errorcode.put("0301", "保存患者信息失败");
		errorcode.put("0302", "新增患者参数验证失败");
		errorcode.put("0303", "商品库存不够销售扣减");
		errorcode.put("0350", "id长度非法");
		errorcode.put("0351", "userName长度非法");
		errorcode.put("0352", "userShortName长度非法");
		errorcode.put("0353", "birthday格式非法");
		errorcode.put("0354", "sex格式非法");
		errorcode.put("0355", "phone长度非法");
		errorcode.put("0356", "homeAddress长度非法患者住址");
		errorcode.put("0357", "此患者已经存在");
		errorcode.put("0304", "id长度非法");
		errorcode.put("0305", "patientId长度非法");
		errorcode.put("0306", "错误的患者ID");
		errorcode.put("0307", "complaint长度非法");
		errorcode.put("0308", "illHistory长度非法");
		errorcode.put("0309", "allergicHistory长度非法");
		errorcode.put("0310", "height长度非法");
		errorcode.put("0311", "temperature长度非法");
		errorcode.put("0312", "weight长度非法");
		errorcode.put("0313", "systolicPressure长度非法");
		errorcode.put("0314", "diastolicPressure长度非法");
		errorcode.put("0315", "diagnose长度非法");
		errorcode.put("0316", "taboo长度非法");
		errorcode.put("0317", "diagnoseDate必须录入");
		errorcode.put("0318", "diagnoseDate格式非法");
		errorcode.put("0319", "status状态错误");
		errorcode.put("0320", "isPrint打印状态错误");
		errorcode.put("0321", "medicineCost格式非法");
		errorcode.put("0322", "otherCost格式非法");
		errorcode.put("0323", "medDisId无商品信息");
		errorcode.put("0324", "saleUnit字典项验证失败");
		errorcode.put("0325", "useLevelUnit长度非法");
		errorcode.put("0326", "frequencyId长度非法");
		errorcode.put("0327", "usageId长度非法");
		errorcode.put("0328", "commodityCode商品信息错误");
		errorcode.put("0329", "diagnosisId长度非法");
		errorcode.put("0330", "处方ID未对应处方或状态不可结算");
		errorcode.put("0331", "chargeDate格式非法");
		errorcode.put("0332", "该商品无有效库存记录");
		errorcode.put("0333", "saleUnitPrice不是有效值");
		
		errorcode.put("0401", "保存供应商信息失败");
		errorcode.put("0402", "新增诊所服务参数验证失败");
		errorcode.put("0403", "没有找到对应ID的供应商");
		errorcode.put("0404", "id长度非法");
		errorcode.put("0405", "clientModel长度非法");
		errorcode.put("0406", "phone长度非法");
		errorcode.put("0407", "此名称供应商已经添加过");
		errorcode.put("0408", "pageStart参数不是数字");
		errorcode.put("0409", "pageNum参数不是数字");
		errorcode.put("0410", "删除供应商信息失败");
		
		if(ERRORCODE==null){
			ERRORCODE=errorcode;
		}
		
	}
	
	public static HashMap<String, String> getERRORCODE() {
		return ERRORCODE;
	}

	public static BaseApplication getApplication() {
		return mContext;
	}

	public static Handler getMainThreadHandler() {
		return mMainThreadHanler;
	}

	public static Thread getMainThread() {
		return mMainThread;
	}

	public static int getMainThreadId() {
		return mMainThreadId;
	}


	private LinkedList<Activity> mList = new LinkedList<>();

	// add Activity
	public void addActivity(Activity activity) {
		mList.add(activity);
	}
	public void deleteActivity(Activity activity){
		mList.remove(activity);
	}

	public void exit() {
		try {
			for (Activity activity : mList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	public void onLowMemory() {
		super.onLowMemory();
		System.gc();
	}

}
