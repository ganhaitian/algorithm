package com.ganht.algorithm.leetcode;

/**
 * Created by haitian.gan on 2017/6/15.
 */
public class LicenceKeyFormating {

    public String licenseKeyFormatting(String S, int K) {
        String noSlashStr = S.replace("-","");
        int noSlashLength = noSlashStr.length();

        int firstPartLength = noSlashLength % K;

        String firstPart = noSlashStr.substring(0,firstPartLength).toUpperCase();
        String result = firstPart;

        for(int i = firstPartLength;i < S.length();i=i + K){
            if(i + K > noSlashLength)
                break;

            if(result.trim().length() > 0)
                result += "-";

            result += noSlashStr.substring(i,i + K).toUpperCase();
        }

        return result;
    }

    public static void main(String[] args){
        new LicenceKeyFormating().licenseKeyFormatting("2-4A0r7-4k",3);
    }

}
