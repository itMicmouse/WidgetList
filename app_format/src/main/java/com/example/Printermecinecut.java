package com.hospital.device.printer.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.hospital.ConfigureParams;
import com.hospital.ConstantValue;
import com.hospital.R;
import com.hospital.application.BaseApplication;
import com.hospital.device.printer.bluetable.CellLine;
import com.hospital.device.printer.bluetable.Line;
import com.hospital.main.domain.PrescribeItemDefault;
import com.hospital.utils.StringUtilsmine;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2017/2/24.
 */

public class Printermecinecut {

    private static ArrayList<CutLine> getAllLineWester(List<PrescribeItemDefault> medicineandinstrumentlist, int otherline){
        DecimalFormat df = new DecimalFormat("#");
        df.format(1.20);
        StringBuilder sbrow;
        ArrayList<CutLine> printerList = new ArrayList<>();
        int number = 0;
        int pager = 1;
        int j = 0;
        List<PrescribeItemDefault> medicineandChineseinstrumentlist = new ArrayList<>();
        for (int i = 0; i < medicineandinstrumentlist.size(); i++) {
            PrescribeItemDefault hashMap = medicineandinstrumentlist.get(i);
            CutLine cutLine = new CutLine();
            if (pager == 1) {
                if (otherline + j >= 22) {
                    cutLine.setCutpage(true);
                    pager++;
                    j = 0;
                }
            } else {
                if (j >= 37) {
                    cutLine.setCutpage(true);
                    pager++;
                    j = 0;
                }
            }
            sbrow = new StringBuilder();
            if (hashMap.getIsBlank().equals("1")) {
                number = 0;
                sbrow.append("\n");
                cutLine.setPrinterContent("   " + hashMap.getDocterOrder() + "\n");
                j++;
                cutLine.setPager(pager);
                cutLine.setPosition(j);
                printerList.add(cutLine);
                continue;
            }
            sbrow.append(formatNum(++number));//编号 占用4

            String showCommodityName = (null == hashMap.getShowCommodityName() ? "" : hashMap.getShowCommodityName());
            String totalNumber = (null == hashMap.getTotalNumber() ? "0" : hashMap.getTotalNumber());
            String priceUnit = (null == hashMap.getPriceUnit() ? "" : hashMap.getPriceUnit());
            String specifcations = (null == hashMap.getSpecifcations() ? "" : hashMap.getSpecifcations());

            String usage = (null == hashMap.getUsage() ? "" : hashMap.getUsage());
            String frequency = (null == hashMap.getFrequency() ? "" : hashMap.getFrequency());
            String useLevel = (null == hashMap.getUseLevel() ? "" : hashMap.getUseLevel());
            String useLevelUnit = (null == hashMap.getUseLevelUnit() ? "" : hashMap.getUseLevelUnit());
            String numberStr = totalNumber + priceUnit;//数量
            //名称
            Line nameline = new Line(22, showCommodityName);
            ArrayList<CellLine> nameLine = nameline.getAllLine();
            //规格
            Line specifcationsline = new Line(11, specifcations);
            ArrayList<CellLine> specifcationsLine = specifcationsline.getAllLine();
            //数量
            Line numberline = new Line(7, numberStr);
            ArrayList<CellLine> numberLine = numberline.getAllLine();

            int size = Math.max(nameLine.size(), Math.max(specifcationsLine.size(), numberLine.size()));

            if (hashMap.getCommodityCategory().equals("1") || hashMap.getCommodityCategory().equals("0")) {// 药品
                //第一队列

                for (int lineNum = 0; lineNum < size; lineNum++) {
                    if (lineNum > 0) {
                        sbrow.append("    ");
                    }
                    if (nameLine.size() > lineNum)
                        sbrow.append(nameLine.get(lineNum));
                    else
                        sbrow.append("                    ");

                    sbrow.append(" ");

                    if (specifcationsLine.size() > lineNum)
                        sbrow.append(specifcationsLine.get(lineNum));
                    else
                        sbrow.append("            ");

                    sbrow.append(" ");

                    if (numberLine.size() > lineNum)
                        sbrow.append(numberLine.get(lineNum));
                    else
                        sbrow.append("       ");

                    sbrow.append("\n");
                    j++;
                }
                boolean isInjection = hashMap.getDosageFormId().equals(ConstantValue.INJECTION_DRUG_USE) || hashMap.getDosageFormId().equals(ConstantValue.INJECTION_DRUG_USE_LIQUID);
                if (hashMap.getCommodityCategory().equals("2") || hashMap.getCommodityCategory().equals("3")) {
                    sbrow.append("");
                    sbrow.append("\n");
                    j++;
                } else {
                    if (isInjection) {
                        sbrow.append("");
                    } else {
                        if (StringUtilsmine.isBlank(usage) || StringUtilsmine.isBlank(frequency) || StringUtilsmine.isBlank(useLevelUnit) || StringUtilsmine.isBlank(useLevel)) {
                            sbrow.append("");
                        } else {
                            sbrow.append("            ");
                            if (useLevelUnit.equals(BaseApplication.getApplication().getString(R.string.each_amount))) {
                                sbrow.append(usage + "/每次适量/" + frequency);
                            } else {
                                sbrow.append(usage + "/每次" + useLevel + useLevelUnit + "/" + frequency);
                            }
                            sbrow.append("\n");
                            j++;
                        }
                    }
                }


            } else if (hashMap.getCommodityCategory().equals("2")) {

                for (int lineNum = 0; lineNum < size; lineNum++) {
                    if (lineNum > 0) {
                        sbrow.append("    ");
                    }
                    if (nameLine.size() > lineNum)
                        sbrow.append(nameLine.get(lineNum));
                    else
                        sbrow.append("                    ");

                    sbrow.append(" ");

                    if (specifcationsLine.size() > lineNum)
                        sbrow.append(specifcationsLine.get(lineNum));
                    else
                        sbrow.append("            ");

                    sbrow.append(" ");

                    if (numberLine.size() > lineNum)
                        sbrow.append(numberLine.get(lineNum));
                    else
                        sbrow.append("       ");

                    sbrow.append("\n");
                    j++;
                }
            } else if (hashMap.getCommodityCategory().equals("3")) {// 服务
                for (int lineNum = 0; lineNum < size; lineNum++) {
                    if (lineNum > 0) {
                        sbrow.append("    ");
                    }
                    if (nameLine.size() > lineNum)
                        sbrow.append(nameLine.get(lineNum));
                    else
                        sbrow.append("                    ");

                    sbrow.append(" ");

                    sbrow.append("            ");

                    sbrow.append(" ");

                    if (numberLine.size() > lineNum)
                        sbrow.append(numberLine.get(lineNum));
                    else
                        sbrow.append("       ");

                    sbrow.append("\n");
                    j++;
                }
            }
            cutLine.setPrinterContent(sbrow.toString());
            cutLine.setPager(pager);
            cutLine.setPosition(j);
            printerList.add(cutLine);
        }

        return printerList;
    }


    /**
     *
     * @param medicineandinstrumentlist 处方附表信息
     * @param otherline 诊断和主诉的行数
     * @param prescriptionFlag
     * @return
     */
    public static ArrayList<CutLine> getAllLine(List<PrescribeItemDefault> medicineandinstrumentlist, int otherline, String prescriptionFlag) {
        Gson gson = new Gson();
        String s = gson.toJson(medicineandinstrumentlist);
        if(prescriptionFlag.equals("2")){
            return getAllLineChinese(medicineandinstrumentlist,otherline);
        }else {
            return getAllLineWester(medicineandinstrumentlist,otherline);
        }
    }
    private static int j = 0;
    private static ArrayList<CutLine> getAllLineChinese(List<PrescribeItemDefault> medicineandinstrumentlist, int otherline) {
        StringBuilder sbrow;
        ArrayList<CutLine> printerList = new ArrayList<>();
        int number = 0;
        int pager = 1;

        int position = 0; //最后一个中药
        List<PrescribeItemDefault> medicineandChineseinstrumentlist = new ArrayList<>();
        for (int i = 0; i < medicineandinstrumentlist.size(); i++) {
            PrescribeItemDefault hashMap = medicineandinstrumentlist.get(i);
            if(!hashMap.getCommodityCategory().equals(ConfigureParams.MEDICINE_CHINESE)){
                position = i;
                if(medicineandChineseinstrumentlist.size()==1){
                    ArrayList<CutLine> cutLines = setChineseOneMedicineLine(medicineandChineseinstrumentlist.get(0), pager, otherline);
                    if(cutLines!=null){
                        printerList.addAll(cutLines);
                    }
                }
                break;
            }
            medicineandChineseinstrumentlist.add(medicineandinstrumentlist.get(i));
            if(medicineandChineseinstrumentlist.size()==2){
                ArrayList<CutLine> cutLines = setChineseMedicineLine(medicineandChineseinstrumentlist, pager, otherline);
                if(cutLines!=null){
                    printerList.addAll(cutLines);
                }
                medicineandChineseinstrumentlist.clear();
            }
        }

        for (int i = position; i < medicineandinstrumentlist.size(); i++) {
            PrescribeItemDefault hashMap = medicineandinstrumentlist.get(i);
            CutLine cutLine = new CutLine();
            if (pager == 1) {
                if (otherline + j >= 22) {
                    cutLine.setCutpage(true);
                    pager++;
                    j = 0;
                }
            } else {
                if (j >= 37) {
                    cutLine.setCutpage(true);
                    pager++;
                    j = 0;
                }
            }
            sbrow = new StringBuilder();
            if (hashMap.getIsBlank().equals("1")) {
                number = 0;
                sbrow.append("\n");
                cutLine.setPrinterContent("   " + hashMap.getDocterOrder() + "\n");
                j++;
                cutLine.setPager(pager);
                cutLine.setPosition(j);
                printerList.add(cutLine);
                continue;
            }
            sbrow.append(formatNum(++number));//编号 占用4

            String showCommodityName = (null == hashMap.getShowCommodityName() ? "" : hashMap.getShowCommodityName());
            String totalNumber = (null == hashMap.getTotalNumber() ? "0" : hashMap.getTotalNumber());
            String priceUnit = (null == hashMap.getPriceUnit() ? "" : hashMap.getPriceUnit());
            String specifcations = (null == hashMap.getSpecifcations() ? "" : hashMap.getSpecifcations());

            String usage = (null == hashMap.getUsage() ? "" : hashMap.getUsage());
            String frequency = (null == hashMap.getFrequency() ? "" : hashMap.getFrequency());
            String useLevel = (null == hashMap.getUseLevel() ? "" : hashMap.getUseLevel());
            String useLevelUnit = (null == hashMap.getUseLevelUnit() ? "" : hashMap.getUseLevelUnit());
            String numberStr = totalNumber + priceUnit;//数量
            //名称
            Line nameline = new Line(22, showCommodityName);
            ArrayList<CellLine> nameLine = nameline.getAllLine();
            //规格
            Line specifcationsline = new Line(11, specifcations);
            ArrayList<CellLine> specifcationsLine = specifcationsline.getAllLine();
            //数量
            Line numberline = new Line(7, numberStr);
            ArrayList<CellLine> numberLine = numberline.getAllLine();

            int size = Math.max(nameLine.size(), Math.max(specifcationsLine.size(), numberLine.size()));

            if (hashMap.getCommodityCategory().equals("1") || hashMap.getCommodityCategory().equals("0")) {// 药品
                //第一队列

                for (int lineNum = 0; lineNum < size; lineNum++) {
                    if (lineNum > 0) {
                        sbrow.append("    ");
                    }
                    if (nameLine.size() > lineNum)
                        sbrow.append(nameLine.get(lineNum));
                    else
                        sbrow.append("                    ");

                    sbrow.append(" ");

                    if (specifcationsLine.size() > lineNum)
                        sbrow.append(specifcationsLine.get(lineNum));
                    else
                        sbrow.append("            ");

                    sbrow.append(" ");

                    if (numberLine.size() > lineNum)
                        sbrow.append(numberLine.get(lineNum));
                    else
                        sbrow.append("       ");

                    sbrow.append("\n");
                    j++;
                }
                boolean isInjection = hashMap.getDosageFormId().equals(ConstantValue.INJECTION_DRUG_USE) || hashMap.getDosageFormId().equals(ConstantValue.INJECTION_DRUG_USE_LIQUID);
                if (hashMap.getCommodityCategory().equals("2") || hashMap.getCommodityCategory().equals("3")) {
                    sbrow.append("");
                    sbrow.append("\n");
                    j++;
                } else {
                    if (isInjection) {
                        sbrow.append("");
                    } else {
                        if (StringUtilsmine.isBlank(usage) || StringUtilsmine.isBlank(frequency) || StringUtilsmine.isBlank(useLevelUnit) || StringUtilsmine.isBlank(useLevel)) {
                            sbrow.append("");
                        } else {
                            sbrow.append("            ");
                            if (useLevelUnit.equals(BaseApplication.getApplication().getString(R.string.each_amount))) {
                                sbrow.append(usage + "/每次适量/" + frequency);
                            } else {
                                sbrow.append(usage + "/每次" + useLevel + useLevelUnit + "/" + frequency);
                            }
                            sbrow.append("\n");
                            j++;
                        }
                    }
                }


            } else if (hashMap.getCommodityCategory().equals("2")) {

                for (int lineNum = 0; lineNum < size; lineNum++) {
                    if (lineNum > 0) {
                        sbrow.append("    ");
                    }
                    if (nameLine.size() > lineNum)
                        sbrow.append(nameLine.get(lineNum));
                    else
                        sbrow.append("                    ");

                    sbrow.append(" ");

                    if (specifcationsLine.size() > lineNum)
                        sbrow.append(specifcationsLine.get(lineNum));
                    else
                        sbrow.append("            ");

                    sbrow.append(" ");

                    if (numberLine.size() > lineNum)
                        sbrow.append(numberLine.get(lineNum));
                    else
                        sbrow.append("       ");

                    sbrow.append("\n");
                    j++;
                }
            } else if (hashMap.getCommodityCategory().equals("3")) {// 服务
                for (int lineNum = 0; lineNum < size; lineNum++) {
                    if (lineNum > 0) {
                        sbrow.append("    ");
                    }
                    if (nameLine.size() > lineNum)
                        sbrow.append(nameLine.get(lineNum));
                    else
                        sbrow.append("                    ");

                    sbrow.append(" ");

                    sbrow.append("            ");

                    sbrow.append(" ");

                    if (numberLine.size() > lineNum)
                        sbrow.append(numberLine.get(lineNum));
                    else
                        sbrow.append("       ");

                    sbrow.append("\n");
                    j++;
                }
            }
            cutLine.setPrinterContent(sbrow.toString());
            cutLine.setPager(pager);
            cutLine.setPosition(j);
            printerList.add(cutLine);
        }

        return printerList;
    }

    /**
     *  @param medicineandChineseinstrumentlist 数据量为2
     * @param pager 页数
     * @param otherline
     */
    private static ArrayList<CutLine> setChineseMedicineLine(List<PrescribeItemDefault> medicineandChineseinstrumentlist, int pager, int otherline) {
        if(medicineandChineseinstrumentlist.size()!=2){
            return null;
        }
        ArrayList<CutLine> printerList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        PrescribeItemDefault firstItem = medicineandChineseinstrumentlist.get(0);
        PrescribeItemDefault secondItem = medicineandChineseinstrumentlist.get(1);
        ArrayList<CellLine> firstcellLines = getallline4Item(firstItem);
        ArrayList<CellLine> secondcellLines = getallline4Item(secondItem);
        int max = Math.max(firstcellLines.size(), secondcellLines.size());
        CutLine cutLine ;
        for (int i = 0; i < max; i++) {
            cutLine = new CutLine();
            stringBuilder.delete(0,stringBuilder.length());
            if(firstcellLines.size()<i){
                stringBuilder.append("                    ");
            }else {
                stringBuilder.append(firstcellLines.get(i));
            }
            if(secondcellLines.size()<i){
                stringBuilder.append("                    ");
            }else {
                stringBuilder.append(secondcellLines.get(i));
            }
            cutLine.setPrinterContent(stringBuilder.toString()+"\n");
            printerList.add(cutLine);
            j++;
            if (pager == 1) {
                if (otherline + j >= 22) {
                    cutLine.setCutpage(true);
                    pager++;
                    j = 0;
                }
            } else {
                if (j >= 37) {
                    cutLine.setCutpage(true);
                    pager++;
                    j = 0;
                }
            }
        }
        return printerList;
    }
    /**
     *  @param mPrescribeItemDefault 数据量为2
     * @param pager 页数
     * @param otherline
     */
    private static ArrayList<CutLine> setChineseOneMedicineLine(@NonNull PrescribeItemDefault mPrescribeItemDefault, int pager,  int otherline) {
        if(mPrescribeItemDefault==null){
            return null;
        }
        ArrayList<CutLine> printerList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        PrescribeItemDefault firstItem = mPrescribeItemDefault;
        ArrayList<CellLine> firstcellLines = getallline4Item(firstItem);
        CutLine cutLine ;
        for (int i = 0; i < firstcellLines.size(); i++) {
            cutLine = new CutLine();
            stringBuilder.append(firstcellLines.get(i));
            stringBuilder.append("                    \n");
            cutLine.setPrinterContent(stringBuilder.toString());
            printerList.add(cutLine);
            j++;
            if (pager == 1) {
                if (otherline + j >= 22) {
                    cutLine.setCutpage(true);
                    pager++;
                    j = 0;
                }
            } else {
                if (j >= 37) {
                    cutLine.setCutpage(true);
                    pager++;
                    j = 0;
                }
            }
        }
        return printerList;
    }

    private static ArrayList<CellLine> getallline4Item(PrescribeItemDefault firstItem) {
        StringBuilder medicineInfo = new StringBuilder();
        String showCommodityName = firstItem.getShowCommodityName()==null?"":firstItem.getShowCommodityName();
        String useLevel = firstItem.getUseLevel()==null?"":firstItem.getUseLevel();
        String useLevelUnit = firstItem.getUseLevelUnit()==null?"":firstItem.getUseLevelUnit();
        String usage = firstItem.getUsage()==null?"":firstItem.getUsage();
        medicineInfo.append(showCommodityName);
        medicineInfo.append("  "+useLevel+useLevelUnit);
        if(!TextUtils.isEmpty(usage.trim())){
            medicineInfo.append("(");
            medicineInfo.append(usage);
            medicineInfo.append(")");
        }
        Line nameline = new Line(22, medicineInfo.toString());
        return nameline.getAllLine();
    }

    private static String formatNum(int num) {
        if (num < 10) {
            return num + ".";
        } else if (num < 100) {
            return String.valueOf(num) + ".";
        } else {
            return String.valueOf(num / 10);
        }
    }

    public static class CutLine {
        String printerContent;
        boolean cutpage = false;
        int pager;
        int position;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public CutLine() {
        }

        public CutLine(String printerContent) {
            this.printerContent = printerContent;
        }

        public String getPrinterContent() {
            return printerContent;
        }

        public void setPrinterContent(String printerContent) {
            this.printerContent = printerContent;
        }

        public boolean isCutpage() {
            return cutpage;
        }

        public void setCutpage(boolean cutpage) {
            this.cutpage = cutpage;
        }

        public int getPager() {
            return pager;
        }

        public void setPager(int pager) {
            this.pager = pager;
        }
    }
}
