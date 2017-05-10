package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by android on 2017/4/6.
 */

public class FileToMap1 {
    private String line = System.getProperty("line.separator");
    private String separ = "=";
    static String basePath = System.getProperty("user.dir");
    static String filePath = "./Alms.cfg";

    public static void main(String[] args) {
        FileToMap1 fileToMap1 = new FileToMap1();
        Map<String, String> oldMap = fileToMap1.fTM();
        Map<String, String> newMap = new HashMap<>();
        Map<String, String> map = fileToMap1.newMapToOldMap(newMap, oldMap, false);
        try {
            fileToMap1.mapToFileDefault(map, new File(filePath), fileToMap1.separ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 有参构造函数
     *
     * @param separ
     * @param filePath
     */
    public FileToMap1(String separ, String filePath) {
        this.filePath = filePath;
        this.separ = separ;
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("file not found");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("path:" + filePath + "create false");
                e.printStackTrace();
            }
        }
    }

    /**
     * 无参构造函数，用类默认的配置。
     */
    public FileToMap1() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("file not found");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("path:" + filePath + "create default");
                e.printStackTrace();
            }
        }
    }

    /**
     * 将map写入到file文件中。默认map（String A,String A')file中以A=A'来表示，map中每个键值对显示一行
     * @throws IOException
     */
    private File mapToFileDefault(Map<String, String> map,File file,String separ) throws IOException{
        StringBuffer buffer = new StringBuffer();
        FileWriter writer = new FileWriter(file, false);
        for(Map.Entry entry:map.entrySet()){
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            buffer.append(key + "=" + value).append(line);
        }
        writer.write(buffer.toString());
        writer.close();
        return file;

    }
    /**
     * 在newMap替换oldMap时，是否覆盖（isOverwrite)如果是，就直接替换，如果否，则将oldMap中的key前加“#”，默认为否
     * @param newMap
     * @param oldMap
     * @return
     */
    private Map<String, String> newMapToOldMapDefault(Map<String, String> newMap,Map<String, String> oldMap){
        return newMapToOldMap(newMap,oldMap,false);
    }

    /**
     * 在newMap替换oldMap时，是否覆盖（isOverwrite)如果是，就直接替换，如果否，则将oldMap中的key前加“#”，默认为否
     */
    private Map<String, String> newMapToOldMap(Map<String, String> newMap,
                                               Map<String, String> oldMap, boolean isOverwrite) {
        // 由于oldMap中包含了file中更多内容，所以newMap中内容在oldMap中调整后，最后返回oldMap修改之后的map.
        // 如果选择true覆盖相同的key
        if (isOverwrite) {
            // 循环遍历newMap
            for (Map.Entry entry : newMap.entrySet()) {
                String newKey = (String) entry.getKey();
                String newValue = (String) entry.getValue();
                oldMap.put(newKey, newValue);
            }
        } else {
            // 不覆盖oldMap,需要在key相同的oldMap的key前加#；
            // 循环遍历newMap
            for (Map.Entry entry : newMap.entrySet()) {
                String newKey = (String) entry.getKey();
                String newValue = (String) entry.getValue();
                String oldValue = oldMap.get(newKey);
                oldMap.put("#" + newKey, oldValue);
                oldMap.put(newKey, newValue);
            }
        }
        return oldMap;
    }

    /**
     * 将文件转换成map存储
     *
     * @return
     */
    private Map<String, String> fTM() {
        Map<String, String> map = new HashMap<String, String>();
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            System.out.println("line to line:");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                if (!tempString.startsWith("#")) {
                    String[] strArray = tempString.split("=");
                    map.put(strArray[0], strArray[1]);
                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        return map;
    }
}
