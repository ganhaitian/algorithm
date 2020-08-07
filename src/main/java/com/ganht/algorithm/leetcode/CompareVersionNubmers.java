package com.ganht.algorithm.leetcode;

/**
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * <p>
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 * <p>
 * Here is an example of version numbers ordering:
 * <p>
 * 0.1 < 1.1 < 1.2 < 13.37
 * Created by ganhaitian on 2017/2/23.
 */
public class CompareVersionNubmers {

    public int compareVersion(String version1, String version2) {
        String[] version1Parts = version1.split("\\.");
        String[] version2Parts = version2.split("\\.");

        int v1 = 0;
        int v2 = 0;
        int commonIndex = Math.max(version1Parts.length,version2Parts.length);
        for(int  i = 0;i <commonIndex;i ++){
            if(i >= version1Parts.length){
                v2 = Integer.parseInt(version2Parts[i]);
                if(v2 != 0)
                    return -1;
            }else if(i >= version2Parts.length){
                v1 = Integer.parseInt(version1Parts[i]);
                if(v1 != 0)
                    return 1;
            }else{
                v1 = Integer.parseInt(version1Parts[i]);
                v2 = Integer.parseInt(version2Parts[i]);

                if(v1 > v2){
                    return 1;
                }else if(v1 < v2){
                    return -1;
                }
            }
        }

        return 0;
    }

    public static void main(String[] args){
        System.out.println(new CompareVersionNubmers().compareVersion("1.01","1"));
    }
}
