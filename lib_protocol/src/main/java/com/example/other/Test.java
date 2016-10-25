package com.example.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yakun on 2016/10/25.
 */

public class Test {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        CommonJson<AverageStarLevelAndCount> cj = new CommonJson<>();
        AverageStarLevelAndCount data = new AverageStarLevelAndCount();
        data.setAverageStarLevel(4.7f);
        data.setRemarkCount(10);

        cj.setSuccess(Boolean.TRUE);
        cj.setData(data);

        String res1 = cj.toJson(AverageStarLevelAndCount.class);

        System.out.println("-----:"+res1+"----------");

        CommonJson<AverageStarLevelAndCount> cj2 = CommonJson.fromJson(res1,AverageStarLevelAndCount.class);

        System.out.println(cj2.getSuccess());
        System.out.println(cj2.getData().getAverageStarLevel());





        EvalutionProfile p = new EvalutionProfile();
        p.setPage(10);
        p.setPageCount(29);

        StarLevelStatistics s = new StarLevelStatistics();
        s.setStar1(0);
        s.setStar2(10);
        s.setStar3(30);
        s.setStar4(40);
        s.setStar5(20);

        p.setStatistics(s);

        Evalution e1 = new Evalution();

        e1.setStarLevel(4);
        e1.setRemarkTime("2013-02-27 07:21:48");
        e1.setRemarkCotnent("评价方未及时做出评价，系统默认满意！");
        e1.setTpLogoURL("http://i04.c.aliimg.com/cms/upload/2012/186/684/486681_1232736939.png");
        e1.setExplainContent("");
        e1.setPostMemberId("y**f");

        Evalution e2 = new Evalution();

        e2.setStarLevel(4);
        e2.setRemarkTime("2013-02-27 07:21:48");
        e2.setRemarkCotnent("评价方未及时做出评价，系统默认满意！");
        e2.setTpLogoURL("http://i04.c.aliimg.com/cms/upload/2012/186/684/486681_1232736939.png");
        e2.setExplainContent("");
        e2.setPostMemberId("y**f");

        List<Evalution> le = new ArrayList<>();
        le.add(e1);
        le.add(e2);

        p.setList(le);

        CommonJson<EvalutionProfile> ce = new CommonJson<>();

        ce.setSuccess(Boolean.TRUE);
        ce.setData(p);

        String res2 = ce.toJson(EvalutionProfile.class);

        System.out.println(res2);

        String ps = "{\"data\":{\"pageCount\":29,\"page\":\"1\",\"list\":[{\"starLevel\":4,\"remarkTime\":\"2013-04-10 05:17:42\",\"explainTime\":\"\",\"remarkContent\":\"评价方未及时做出评价，系统默认满意！\",\"tpLogoURL\":\"\",\"explainContent\":\"\",\"postMemberId\":\"b**6\"},{\"starLevel\":4,\"remarkTime\":\"2013-04-05 04:42:40\",\"explainTime\":\"\",\"remarkContent\":\"评价方未及时做出评价，系统默认满意！\",\"tpLogoURL\":\"\",\"explainContent\":\"\",\"postMemberId\":\"b**8\"},{\"starLevel\":4,\"remarkTime\":\"2013-03-20 00:52:18\",\"explainTime\":\"\",\"remarkContent\":\"评价方未及时做出评价，系统默认满意！\",\"tpLogoURL\":\"\",\"explainContent\":\"\",\"postMemberId\":\"b**5\"},{\"starLevel\":4,\"remarkTime\":\"2013-03-07 01:51:01\",\"explainTime\":\"\",\"remarkContent\":\"评价方未及时做出评价，系统默认满意！\",\"tpLogoURL\":\"\",\"explainContent\":\"\",\"postMemberId\":\"b**7\"},{\"starLevel\":4,\"remarkTime\":\"2013-03-06 03:38:57\",\"explainTime\":\"\",\"remarkContent\":\"评价方未及时做出评价，系统默认满意！\",\"tpLogoURL\":\"\",\"explainContent\":\"\",\"postMemberId\":\"z**2\"},{\"starLevel\":4,\"remarkTime\":\"2013-03-06 03:17:33\",\"explainTime\":\"\",\"remarkContent\":\"评价方未及时做出评价，系统默认满意！\",\"tpLogoURL\":\"\",\"explainContent\":\"\",\"postMemberId\":\"b**2\"},{\"starLevel\":4,\"remarkTime\":\"2013-03-03 01:40:17\",\"explainTime\":\"\",\"remarkContent\":\"评价方未及时做出评价，系统默认满意！\",\"tpLogoURL\":\"\",\"explainContent\":\"\",\"postMemberId\":\"a**6\"},{\"starLevel\":4,\"remarkTime\":\"2013-03-01 02:41:31\",\"explainTime\":\"\",\"remarkContent\":\"评价方未及时做出评价，系统默认满意！\",\"tpLogoURL\":\"\",\"explainContent\":\"\",\"postMemberId\":\"b**4\"},{\"starLevel\":4,\"remarkTime\":\"2013-02-28 03:20:45\",\"explainTime\":\"\",\"remarkContent\":\"评价方未及时做出评价，系统默认满意！\",\"tpLogoURL\":\"\",\"explainContent\":\"\",\"postMemberId\":\"t**y\"},{\"starLevel\":4,\"remarkTime\":\"2013-02-27 07:21:48\",\"explainTime\":\"\",\"remarkContent\":\"评价方未及时做出评价，系统默认满意！\","
                + "\"tpLogoURL\":\""
                + "http://i04.c.aliimg.com/cms/upload/2012/186/684/486681_1232736939.png\",\"explainContent\":\"\",\"postMemberId\":\"y**f\"}],"
                + "\"statistics\":{\"star5\":59,\"star4\":38,\"star3\":2,\"star2\":1,\"star1\":0}},\"success\":true}";

        CommonJson<EvalutionProfile> re = CommonJson.fromJson(ps,EvalutionProfile.class);

        System.out.println(re.getData().getStatistics().getStar5());

        System.out.println(re.getData().getPageCount());

        System.out.println(re.getData().getList().get(0).getPostMemberId());




        CommonJson4List<Evalution> cjl = new CommonJson4List<Evalution>();

        cjl.setSuccess(Boolean.TRUE);
        cjl.setData(le);

        System.out.println("###" + cjl.toJson(Evalution.class));

        String sList = "{\"data\":[{\"starLevel\":4,\"remarkCotnent\":\"评价方未及时做出评价，系统默认满意！\",\"remarkTime\":\"2013-02-27 07:21:48\",\"explainContent\":\"\",\"postMemberId\":\"y**f\",\"tpLogoURL\":\"http://i04.c.aliimg.com/cms/upload/2012/186/684/486681_1232736939.png\"},{\"starLevel\":4,\"remarkCotnent\":\"评价方未及时做出评价，系统默认满意！\",\"remarkTime\":\"2013-02-27 07:21:48\",\"explainContent\":\"\",\"postMemberId\":\"y**f\",\"tpLogoURL\":\"http://i04.c.aliimg.com/cms/upload/2012/186/684/486681_1232736939.png\"}],\"success\":true}";

        CommonJson4List<Evalution> cjle = CommonJson4List.fromJson(sList,
                Evalution.class);

        System.out.println("***"+ cjle.getData().get(0).getPostMemberId());

    }
}
