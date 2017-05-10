package com.hospital;

import android.os.Bundle;


public interface ConfigureParams {
	
	
	// region 接口
	// **********************************************
	// *********************接口开始***********************
	// **********************************************
	// 登录接口
	String LOGIN_URL = "login/padLogin";
	// 远程会诊菜单，当医生点击了远程会诊菜单时，就调用该接口
	String LOGIN_MOSREAD_URL = "login/mosRead";

	// 添加用户
	String ADDSATIENT = "clinicPatient/savePatientFromPad";
	// 编辑药品详细信息接口
	String MODIFYCLINICCOMMODITY_URL = "clinicCommodity/modifyClinicCommodity";
	// 编辑药品(中药)详细信息接口
	String MODIFYCLINICCOMMODITYCHINESE_URL = "clinicCommodityChinese/modifyClinicCommodityChinese";
	// 转换药品类型接口
	String MODIFYCLINICCOMMODITYTOOTHER_URL = "clinicCommodity/modifyClinicCommodityToOther";
	// 编辑药品详细信息保存接口（用法、频度、用量、用量单位）
	String MODIFYCLINICCOMMODITYEX_URL = "clinicCommodity/modifyClinicCommodityEx";
	// 药品禁用接口
	String MODIFYCOMMODITYSTATUS_URL = "clinicCommodity/modifyCommodityStatus";
	// 药品(中药)禁用接口
	String DELETECLINICCOMMODITYCHINESE_URL = "clinicCommodityChinese/deleteClinicCommodityChinese";

	// 新增药品-新检索诊所药品信息-搜索下拉列表(有大量的附加信息)
	String INQUIREDRUGSWAREHOUSE_URL = "medicinal/inquireDrugsWarehouse";
	// 新增药品-根据商品条码得到药品信息
	String INQUIREDRUGSCOMMODITYCODE_URL = "medicinal/inquireDrugsCommodityCode";

	// 新增药品
	String CREATECLINICCOMMODITYLIST_URL = "clinicCommodity/createClinicCommodityList";
	// 新增药品(中药)
	String ADDCLINICCOMMODITYCHINESE_URL = "clinicCommodityChinese/addClinicCommodityChinese";
	// 新增药品(中药)-新检索诊所药品信息-搜索下拉列表(有大量的附加信息)
	String SHOWCHINESEMEDICINALLIST_URL = "ClinicCommodityChinese/showChineseMedicinalList";
	// 修改密码接口
	String REVICERPASSWORD_URL = "login/modifyPassWord";
	// 付款
	String UPDATEPAYMENTORDER4PURCHASEORDER_URL = "paymentOrder/updatePaymentOrder4PurchaseOrder";
	// 处方保存
	String CREATEPRESCRIPTION_URL = "prescription/createPrescription";
	// 中药处方保存
	String CREATEPRESCRIPTIONCHINESE_URL = "prescriptionChinese/createPrescription";
	
	// 处方临时保存
	String CREATETEMPPRESCRIPTION_URL = "prescription/createTempPrescription";
	// 中药处方临时保存
	String CREATETEMPPRESCRIPTIONCHINESE_URL = "prescriptionChinese/createTempPrescription";
	
	// 处方收款
	String CREATEPRESCRIPTION = "prescription/pay4Prescription";
	// 中药处方收款
	String CREATEPRESCRIPTIONCHINESE = "prescriptionChinese/pay4Prescription";
	// 修改处方状态
	String MODIFYPRESCRIPTIONSTATUS = "prescription/modifyPrescriptionStatus";

	// 反馈意见接口
	String FEED_URL = "feedback/createFeedback";
	// 错误日志上传
	String CREATECLIENTLOG = "clientLog/createClientLog";

	//版本更新接口
	String FINDNEWVERSION = "updateVersion/findNewVersion";

	//服务器坐标上传 
	String SYSMONITOR_URL = "SysMonitor/uploadPosition";
	
	//新建常见病模板接口
	String CREATELABELS_URL = "clinicLabel/createLabel";
	
	//修改常见病模板接口
	String MODIFYLABEL = "clinicLabel/modifyLabel";
	
	//删除常见病模板接口
	String DELETELABELS_URL = "clinicLabel/deleteLabel";

	//字典数据同步接口
	String UPDATEDICTIONARY_URL = "sysDictionary/synchronizeSysDictionary";
	//患者数据同步接口
	String UPDATEPATIENT_URL = "clinicPatient/syClinicPatient";
	//商品数据同步接口
	String UPDATECOMMODITYMAIN_URL = "clinicCommodity/sysClinicCommodity";
	//处方数据同步接口
	String UPDATEPRESCRIPTIONMAIN_URL = "prescription/synchronizePrescription";
	//处方数据同步接口2
	String UPDATEPRESCRIPTIONMAIN2_URL = "prescription/synchronizePrescription2";
	//处方数据同步接口bydate
	String UPDATEPRESCRIPTIONMAINBYDATE_URL = "prescription/synchronizePrescriptionByDay";
	//药品结算数据同步接口
	String UPDATEDRUGCLEARING_URL = "saleOrder/synchronizeSaleOrder";
	//查询商品最近一次使用情况，包括用法用量频度等信息
	String FINDLATESTCLINICOTCMEDICINALINFO = "prescription/findLatestClinicOtcMedicinalInfo";
	//商品(中药)数据同步接口
	String SYSCLINICCOMMODITYCHINESE_URL = "clinicCommodityChinese/sysClinicCommodityChinese";
	
	//诊所标签接口
	String CREATELABEL_URL = "clinicLabel/createLabel"; //初始化
	String DELETELABEL_URL = "clinicLabel/deleteLabel"; //删除
	String SYNCLINICLABEL_URL = "clinicLabel/synClinicLabel"; //同步
	//诊所标签分组对照接口

	String CREATELABELGROUPBATCHP_URL = "clinicLabelGroup/createLabelGroupBatch"; //初始化

	String DELETELABELGROUPBATCH_URL = "clinicLabelGroup/deleteLabelGroupBatch"; //批量删除
	String SYNCLINICLABELGROUP_URL = "clinicLabelGroup/synClinicLabelGroup"; //同步
	
	// 患者管理全部列表
	String FINDPATIENTLIST = "clinicPatient/findPatientList";
	// 患者标签欠款列表
	String FINDPATIENTLISTARREARS = "clinicPatient/findArrearsPatientList";
	// 患者标签分组列表
	String FINDPATIENTLISTLABLE = "clinicPatient/findPatientListBylabelId";
	// 患者信息编辑
	String MODIFYPATIENTFROMPAD = "clinicPatient/modifyPatientFromPad";
	// 拼音首字母患者列表
	String FINDPATIENTLISTBYSHORTHAND = "clinicPatient/findPatientListByShorthand";
	// 患者处方列表
	String FINDPRESCRIPTIONBYPATIENT = "prescription/findPrescriptionByPatient";
	// 健康档案
	String FINDCLINICPECASEBYPATIENT = "clinicPatient/findClinicPeCaseByPatient";
	// 删除患者
	String FINDCLINICDELETEPATIENT = "clinicPatient/modifyPatientFromPad";
	
	// 患者处方列表
	String TOPAYTHEARREARS = "clinicPatient/toPayTheArrears";
	
	// 采购入库
	String CREATECLINICSTOCKSIMPLIFY = "clinicStock/createClinicStockSimplify";
	// 核对库存
	String CREATESTOCKCOLLATESIMPLIFY = "stockCollate/createStockCollateSimplify";
	// 报废库存
	String CREATESTOCKSCRAPSIMPLIFY = "stockScrap/createStockScrapSimplify";
	// 库存管理(中药)
	String modifyStock_URL = "clinicCommodityChinese/modifyStock";

	//我的-宣传积分
	String MINEPROMOTIONINTEGRAL = "promotion/getSurplus";

	// 发现_知识库_收藏列表
	String MKBFAVORITELIST = "MKBfavorite/favoriteList";

	// 发现_知识库_取消收藏
	String MKBFAVORITELISTCANCEL = "MKBfavorite/favoriteCancel";

	// 去结算(西药)
	String PAY4PRESCRIPTIONNEW = "prescription/pay4PrescriptionNew";
	// 去结算(中药)
	String PRESCRIPTIONCHINESE_PAY4PRESCRIPTIONNEW = "prescriptionChinese/pay4PrescriptionNew";

	// 去结算+收费一条龙服务(西药处方)
	String DIRECTTOCHARGE_URL = "prescription/directToCharge";

	// 去结算+收费一条龙服务(中药处方)
	String DIRECTTOCHARGE2_URL = "prescriptionChinese/directToCharge";

	//查询公告
	String FINDCLINICNOTICE = "clinic/findClinicNotice";
	
	//保存公告
	String SAVECLINICNOTICE = "clinic/saveClinicNotice";
	/**
	 * 积分策略
	 */
	String GETCLINICINTEGRALDETAILBYPAGE = "promotion/getClinicIntegralDetailByPage";


	// **********************************************
	// *********************接口结束********************
	// **********************************************
	
	
	// **********************************************
	// *********************报表地址配置开始**************
	// **********************************************
	// 门诊病人
	String OutpatientStatisticsURL = ConfigureValue.getInstance().getLoginDTO().getPadreportIPPort() + "npadReport/menzhenliang";
	// 销售详情
	String SalesDetailURL =  ConfigureValue.getInstance().getLoginDTO().getPadreportIPPort() + "npadReport/salelist";
	// 库存详情
	String StockDetailURL = ConfigureValue.getInstance().getLoginDTO().getPadreportIPPort() + "npadReport/stocklist";
	// **********************************************
	// *********************报表地址配置结束**************
	// **********************************************
	// endregion


	// region 废弃接口
	/**
	 * 数据同步(小红点临时方案)
	 */
	String FINDOTHERUSERDATA_URL = "sysMonitor/findOtherUserData";
	//字典数据同步接口(暂时屏蔽)
	//String FINDBASEVERSION_URL = "sysMonitor/findBaseVersion";
	//疾病字典数据同步接口
	String UPDATESICKNESSDIC_URL = "clinicSickness/synchronizeClinicSickness";
	String INITLABEL_URL = "clinicLabel/initLabel"; //诊所标签新增
	String MODIFYLABEL_URL = "clinicLabel/modifyLabel"; //诊所标签修改
	String CREATELABELGROUP_URL = "clinicLabelGroup/createLabelGroup"; //诊所标签初始化
	String UserAction = "userAction/saveAction";//行为监控
	String MODIFYLABELGROUP_URL = "clinicLabelGroup/modifyLabelGroup"; //诊所标签修改
	String DELETELABELGROUP_URL = "clinicLabelGroup/deleteLabelGroup"; //诊所标签删除
	String CLINICPADGETQRCODEURL= "promotion/getQRCodeUrl";
	// 检查是否有版本更新
	String UPDATEADDREDD = "/update.json";
	// 检索药品信息-搜索详细信息接口
	String SHOWMEDICINAL_URL = "medicinal/showMedicinal";
	// 入库
	String CREATECLINICSTOCK_URL = "clinicStock/createClinicStock";
	// 诊所服务列表
	String CLINICSERVICE = "clinicCommodity/findClinicServiceListInPage";
	// 添加诊所服务
	String ADDCLINICSERVICE = "clinicCommodity/addClinicService";
	// 删除诊所服务
	String DELETECLINICSERVICE = "clinicCommodity/deleteClinicService";
	// 供应商列表
	String SHOWSUPPLIERLIST = "supplier/showSupplierList";
	// 厂家列表
	String FINDFACTORYBYNAMESPELL = "medicinalFactory/findFactoryByNameSpell";
	// 添加供应商
	String CREATESUPPLIER = "supplier/createSupplier";
	// 删除供应商
	String DELETESUPPLIER = "supplier/deleteSupplier";
	// 获取药品数量接口
	String GETMEDICINALTOTAL_URL = "clinicCommodity/showCount";
	// 获取药品列表接口
	String GETMEDICINALLIST_URL = "clinicCommodity/findClinicMedicinalListInPage";
	// 查询商品批次信息接口
	String SHOWBATCHLIST_URL = "clinicCommodity/showBatchList";
	// 查看诊所药品批次列表中的药品信息
	String SHOWCOMMODITYINFO4BATCH_URL = "clinicCommodity/showCommodityInfo4Batch";
	// 查询药品详细信息接口
	String SHOWCOMMODITYDETAIL_URL = "clinicCommodity/showCommodityDetail";

	// 药品单一批次禁用接口
	String MODIFYCOMMODITYBATCHSTATUS_URL = "clinicCommodity/modifyCommodityBatchStatus";
	// 药品单一批次报废接口
	String CREATESTOCKSCRAP_URL = "stockScrap/createStockScrap";
	// 药品单一批次报废接口
	String CREATESTOCKCOLLATE_URL = "stockCollate/createStockCollate";
	// 药品调价接口
	String MODIFYCOMMODITYPRICE_URL = "commodityPrice/modifyCommodityPrice";
	// 检索诊所药品信息-搜索下拉列表接口
	String SHOWCLINICCOMMODITYLIST_URL = "clinicCommodity/showClinicCommodityList";
	// 新增药品-检索药品信息-搜索下拉列表接口
	String SHOWMEDICINALLIST_URL = "medicinal/showMedicinalList";
	// 新增药品-检索诊所药品信息-搜索下拉列表(药品大类无厂家信息)
	String SHOWCHINAMEDICINALCLASSLIST_URL = "medicinal/showChinaMedicinalClassList";

	// endregion

	//发布类型 
	String PUBLISHTYPE_RELEASE = "release";
	String PUBLISHTYPE_BETA = "beta";
	//GPS定时上传的间隔时间
	int GPS_INTERVAL = 10 * 60 * 1000;

//	int SEXOTHER = 0;// 其他
//	int SEXMALE = 1;// 男
//	int SEXFEMALE = 2;// 女
//	String Sex[] = { "其他", "男", "女" };

	/**
	 * 更新诊所其他服务 notify_Clinc_Other_Service_Changed
	 */
	int NOTIFY_CLINC_OTHER_SERVICE_CHANGED = 0;
	/**
	 * 更新药品及器械服务 notify_Prescription_Detail_tChanged
	 */
	int NOTIFY_PRESCRIPTION_DETAIL_TCHANGED = 1;

	/**
	 * 0:'其他' 1:'西药',2:'器械',3:'服务',4:'中药' pb_commodity_main 表中的commodityCategory 枚举值
	 */
	
	String OTHER = "0";
	String MEDICINE = "1";
	String INSTRUMENT = "2";
	String SERVES = "3";
	String MEDICINE_CHINESE = "4";
	/**
	 * 0:'商品' 是药品的一部分
	 */
	int isNew = 0;
	/**
	 * 已上传
	 */
	String isUpdate = "1";
	/**
	 * 为上传
	 */
	String isNotUpdate = "0";

	/**
	 * 0:'已保存'
	 */
	String PRESCRIPTION_STATUS_ACCOUNT = "0";
	/**
	 * 1:'已结算'
	 */
	String PRESCRIPTION_STATUS_NOACCOUNTED = "1";
	/**
	 * 2:’待结算’
	 */
	String PRESCRIPTION_STATUS_CHARGED = "2";
	/**
	 * 3:'已作废'
	 */
	String PRESCRIPTION_STATUS_CANCELLED = "3";


	String ERRORNUM = "不能为负值";
	
	/**
	 * 添加新患者，错误码
	 */
	String ErrorCode = "0357";
	
	
	String SUCCESSCODE = "0000";
	/**
	 * 服务器正在使用中
	 */
	String HADUSED = "0410";
	
	String MEDICINETYPECOMMON = "common";
	String MEDICINETYPEMIN = "min";
	
	/**
	 * 其他
	 */
	String MEDICINE_TYPE_ELSE = "0";
	
	/**
	 * 基础版本号
	 */
	String BASE_VERSION = "1111111111111";
	
	/**
	 * 标签id
	 */
	String LABEL_MEDICINALCATAGORY = "40288c8451339424015133aa8fc80018";//中药药品id
	String LABEL_COMMODITY = "40288c8451339424015133aa8fc80008"; //药品标签id
	String LABEL_DISEASES = "f794117a7f8d11e5b31128936a597r00"; //疾病标签id
	String LABEL_COMPLAINT = "40288c8451339424015133aa8fc80010"; //常用主诉
	String LABEL_UNIT = "40288c8451339424015133b0b13a0009"; //单位标签id
	String LABEL_USAGE = "40288c8451339424015133a8d8870000"; //用法标签id
	String LABEL_FREQUENCY = "40288c8451339424015133aa8fc80004"; //频度标签id
	String LABEL_ORDER_CHINESE = "40288c8451339424015133aa8fc80022"; //中药煎药顺序标签id
	String LABEL_PATIENTGROUP = "40288c8451339424015133aa8fc80007"; //患者分组标签id
	String PRESCRIPTION_FORMWORK = "40288c8451339424015133aa8fc80004"; //处方模板标签id
	String LABEL_MEDICAL_HISTORY = "40288c8451339424015133aa8fc80011"; //病史模板
	String LABEL_ALLERGY_HISTORY = "40288c8451339424015133aa8fc80012"; //过敏史模板

	String INJECTION_DRUG_USE = "40288c2a5157239d015157360cc60a01"; //溶媒
	String INJECTION_DRUG_USE_LIQUID = "40288c2a5157239d015157360cc60a02"; //药品
	
	String INJECTION_DRUG = "40288c2a5157239d015157360cc60002"; //注射剂

	String Show = "Show"; //是否显示处方的价格
	
	int ISMEDICINE = 0; // 药品
	int ISBLANK = 1; // 空白
	int ISNEWMedicine = 2; // 新增的其他
	
	
	String splitnei = "|||";
	String split = "\\|\\|\\|";
	String splitTo = "  ";
	
	//接受极光推送消息监听
	String MESSAGE_CUSTOM_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
	//接受极光推送消息后，在主页监听具体操作
	String MESSAGE_MAIN_RECEIVED_ACTION = "com.hospital.widget.jpush.JpushMessageReceiver";

	//升级app的name
	String APPNAME = "version.apk";
	String PRINTDRIVERNAME = "printer.apk";
	
	/**
	 * 信息同步到服务器了
	 */
	String ISFLAT = "1";
	/**
	 * 信息未同步到服务器了
	 */
	String ISNOTFLAT = "0";
	
	/**
	 *屏幕宽度的比例0.2
	 */
	int LAYOUT_2_10 = 2;
	/**
	 *屏幕宽度的比例0.3
	 */
	int LAYOUT_3_10 = 3;
	/**
	 *屏幕宽度的比例0.4
	 */
	int LAYOUT_4_10 = 4;
	/**
	 *屏幕宽度的比例0.45
	 */
	float LAYOUT_4_5_10 = 4.5f;
	/**
	 *屏幕宽度的比例0.5
	 */
	int LAYOUT_5_10 = 5;
	/**
	 *屏幕宽度的比例0.5
	 */
	float LAYOUT_5_3_10 = 5.3f;
	/**
	 *屏幕宽度的比例0.6
	 */
	int LAYOUT_6_10 = 6;
	/**
	 *屏幕宽度的比例0.7
	 */
	int LAYOUT_7_10 = 7;
	/**
	 *屏幕宽度的比例0.8
	 */
	int LAYOUT_8_10 = 8;
	/**
	 *屏幕宽度的比例0.9
	 */
	int LAYOUT_9_10 = 9;

	/**
	 *屏幕宽度的比例10
	 */
	int LAYOUT_10_10 = 10;
	
	
	
	int TOASTLONGSHOW = 5000;

	
	/**
	 * 头信息
	 */
	//	String HEADER = ConstantValue.HEADERS;

    /**
     * 端口
     */
    int PROXY_PORT = 0;
    /**
     * 屏幕宽度
     */
    int WIN_WIDTH = 0;
    /**
     * 屏幕高度
     */
    int WIN_HEIGHT = 0;
    /**
     *  屏幕比率
     */
    float RATIO = 0; 

    /**
     * 传输数据
     */
    Bundle bundle = new Bundle();
    /**
     * 登录状态
     */
    boolean isLogin = false;


	/**
	 * 省市编号
	 */
	String PROVINCE = "";
	/**
	 * 城市编号
	 */
	String CITY = "";
	/**
	 * 区县编号
	 */
	String COUNTY = "";
	/**
	 * 该操作已由其他用户完成
	 */
	String CODE_CLOSED = "0336";
	String HAVE_CHARGE = "0330";

	/**
	 * 删除患者失败，有处方
	 */
	String CODE_DELETEPATIENT = "0330";


	int PRINTBLE=0;  //蓝牙打印机
	int PRINTA5=1;   //A5 激光打印机
	int PRINTA6=2;   //A6 激光打印机
	int PRINTDefault=2;   //A6 激光打印机
	String PRINT="PRINT";

	int VALIDDefault=2;   //3个月
	String VALID="VALID";

	String PRESCRIPTION="PRESCRIPTION";
	int PRESCRIPTIONDefault = 1;   //1西药方 2中药方
	
	float maxfomat = 9999.999f;


	String regiFlag = "1";
	
	


}
