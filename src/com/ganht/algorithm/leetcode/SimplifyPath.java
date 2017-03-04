package com.ganht.algorithm.leetcode;

/**
 *  Given an absolute path for a file (Unix-style), simplify it.

     For example,
     path = "/home/", => "/home"
     path = "/a/./b/../../c/", => "/c"

     Corner Cases:
     Did you consider the case where path = "/../"?
     In this case, you should return "/".
     Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
     In this case, you should ignore redundant slashes and return "/home/foo".

 * Created by ganhaitian on 16/8/18.
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        if(path == null || path.trim().equals(""))
            return "/";

        String[] parts = path.split("//");



        return null;
    }

    public static void main(String[] args){
        System.out.println(TestA.class);
    }

}
