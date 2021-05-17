package com.ganht.algorithm.tools;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransformServerStr {
    
    public static void main(String[] args) {
        int hK1 = 6;
        int hK2 = hK1 * hK1;
        
        int max = 0;
        for(int i = 0;i < 2000;i++){
            int delay = i % hK2 * hK2 + i / hK2 % hK2 % hK1 * hK1 + i / hK2 / hK1;
            System.out.println(delay);
            
            if(delay > max){
                max = delay;
            }
        }
        
        System.out.print("MAX:" + max);
    }

    public static void main1(String[] args){
        try {
            List<String> resultLines = new ArrayList<>();
            for (String idGroupStr : Files.readAllLines(Path.of("C:\\Users\\im30\\Documents\\input"))) {
                Integer startId = null;
                Integer lastId  = null;

                var newGroupStr = idGroupStr + ";9999999";
                var sb          = new StringBuilder();
                for (String idStr : newGroupStr.split(";")) {
                    int id = Integer.parseInt(idStr);
                    if (startId == null) {
                        startId = id;
                    }

                    if (lastId != null && id - lastId > 1) {
                        if (sb.length() > 0) {
                            sb.append(";");
                        }

                        if (lastId > startId) {
                            sb.append(startId).append("-").append(lastId);
                        } else {
                            sb.append(startId);
                        }

                        startId = id;
                    }

                    lastId = id;
                }

                resultLines.add(sb.toString());
            }

            Files.write(Path.of("C:\\Users\\im30\\Documents\\output"), resultLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
