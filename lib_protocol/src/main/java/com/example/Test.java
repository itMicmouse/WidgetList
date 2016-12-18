package com.example;

import com.example.content.LoginContent;
import com.example.errorcontent.LoginErrorContent;
import com.example.param.LoginParam;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by yakun on 2016/10/25.
 */

public class Test {
    public static void main(String[] args) {
        getParam();
        getContent();
        getContentErrorabc();
    }
    public static void getParam(){
        BaseProtocol<LoginContent> baseProtocol = new BaseProtocol<>();
        baseProtocol.getHeader().setClinicId("110288f4a5160a0520151656ed0testE");
        baseProtocol.getHeader().setDoctorMainId("30288f4a5160a0520151656ed0testE2");
        baseProtocol.getHeader().setV("v_1.0411196");
        LoginParam loginParam = new LoginParam();
        loginParam.setLoginName("test0032");
        loginParam.setMac("787ec78f7abe5b94");
        loginParam.setPassWord("e10adc3949ba59abbe56e057f20f883e");
        Gson gson = new Gson();
        baseProtocol.getBody().setParam(loginParam);
        System.out.println(gson.toJson(baseProtocol));
    }
    @SuppressWarnings("unchecked")
    public static void getContent(){
       String content = "{\n" +
               "    \"body\": {\n" +
               "        \"content\": {\n" +
               "            \"discoverIPPort\": \"http://192.168.15.202:8087/dos-0.1/\",\n" +
               "            \"dicVersion\": \"1472806508239\",\n" +
               "            \"promotionAttend\": \"1\",\n" +
               "            \"prescriptionVersion\": \"1477272845430\",\n" +
               "            \"sysLableMainVersion\": \"1476772311018\",\n" +
               "            \"mosIPPort\": \"http://192.168.10.207:8087/clinicMos/\",\n" +
               "            \"padreportIPPort\": \"http://192.168.10.218:8087/padreport/\",\n" +
               "            \"mosInquiryRead\": \"1\",\n" +
               "            \"commondityVersion\": \"1477272845437\",\n" +
               "            \"clinicName\": \"yangyakun\",\n" +
               "            \"sysLableDetailVersion\": \"1476944023308\",\n" +
               "            \"pharmacyChargeVersion\": \"1477272845433\",\n" +
               "            \"city\": \"131000\",\n" +
               "            \"promotionId\": \"11111111222222223333\",\n" +
               "            \"county\": \"131081\",\n" +
               "            \"doseRemindIPPort\": \"http://192.168.10.250:8083/clinicpe/\", \n" +
               "            \"userId\": \"30288f4a5160a0520151656ed0testE2\",\n" +
               "            \"province\": \"130000\",\n" +
               "            \"promotionPageTwo\": \"http://192.168.15.202:8087/dos-0.1/promotion/attendPromotion\",\n" +
               "            \"userName\": \"testE2\",\n" +
               "            \"patientVersion\": \"1477272845225\",\n" +
               "            \"promotionPageOne\": \"http://192.168.15.202:8087/dos-0.1/promotion/promotionStart.gsp\",\n" +
               "            \"clinicId\": \"110288f4a5160a0520151656ed0testE\",\n" +
               "            \"loginName\": \"test0032\"\n" +
               "        },\n" +
               "        \"param\": {\n" +
               "            \"mac\": \"787ec78f7abe5b94\",\n" +
               "            \"passWord\": \"e10adc3949ba59abbe56e057f20f883e\",\n" +
               "            \"loginName\": \"test0032\"\n" +
               "        },\n" +
               "        \"code\": \"0000\"\n" +
               "    },\n" +
               "    \"header\": {\n" +
               "        \"v\": \"v_1.0411196\",\n" +
               "        \"clinicId\": \"\",\n" +
               "        \"doctorMainId\": \"\"\n" +
               "    }\n" +
               "}";

        BaseProtocol<LoginContent> loginContentBaseProtocol= BaseProtocol.fromJson(content, LoginContent.class,LoginErrorContent.class);
        System.out.println(loginContentBaseProtocol.getBody().getContent().getClinicId());
    }
    public static void getContentErrorabc(){
        String content = "{\n" +
                "    \"body\": {\n" +
                "        \"content\": \"error\",\n" +
                "        \"param\": {\n" +
                "            \"mac\": \"787ec78f7abe5b94\",\n" +
                "            \"passWord\": \"d8578edf8458ce06fbc5bb76a58c5ca4\",\n" +
                "            \"loginName\": \"test003o\"\n" +
                "        },\n" +
                "        \"code\": \"0101\"\n" +
                "    },\n" +
                "    \"header\": {\n" +
                "        \"v\": \"v_1.0411196\",\n" +
                "        \"clinicId\": \"\",\n" +
                "        \"doctorMainId\": \"\"\n" +
                "    }\n" +
                "}";
        BaseProtocol<LoginContent> loginContentBaseProtocol= BaseProtocol.fromJson(content, LoginContent.class, LoginErrorContent.class);
        if(loginContentBaseProtocol.getBody().getCode().equals("0000"))
            System.out.println(loginContentBaseProtocol.getBody().getContent().getClinicId());
        else
            System.out.println(loginContentBaseProtocol.getBody().getContent());
    }



}
