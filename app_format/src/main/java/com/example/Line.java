package com.hospital.device.printer.bluetable;

import com.hospital.utils.PinyinUtils;

import java.util.ArrayList;

public class Line {
    int max = 0;
    int min = 0;
    String needSplit;

    public Line(int max, String needSplit) {
        this.max = max;
        this.min = max - 1;
        this.needSplit = needSplit;
    }

    public ArrayList<CellLine> getAllLine() {
        for (int i = 0; i < needSplit.length(); i++) {
            insert(needSplit.charAt(i));
        }
        if (abc.size < max && abc.size > 0) {
            allLine.add(abc);
        }
        for (CellLine line : allLine) {
            if (line.size < max) {

                for (int i = 0; i < max - line.size; i++) {
                    line.name.append(" ");
                }
            }
        }
        return allLine;
    }

    private ArrayList<CellLine> allLine = new ArrayList<CellLine>();
    CellLine abc = new CellLine();

    public void insert(char a) {
        if (PinyinUtils.isChinese(a)) {
            if (abc.size % max == min) {
                allLine.add(abc);
                abc = new CellLine();
            }
            abc.size += 2;
            abc.name.append(a);
        } else {
            abc.size += 1;
            abc.name.append(a);
        }
        if (abc.size % max == 0 && abc.size != 0) {
            allLine.add(abc);
            abc = new CellLine();
        }
    }
}
