package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create a timebased key-value store class TimeMap, that supports two operations.
 *
 * 1. set(string key, string value, int timestamp)
 *
 * Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 *
 * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 *
 *
 * Example 1:
 *
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 *
 * Example 2:
 *
 * Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * Output: [null,null,null,"","high","high","low","low"]
 *
 *
 * Note:
 *
 * All key/value strings are lowercase.
 * All key/value strings have length in the range [1, 100]
 * The timestamps for all TimeMap.set operations are strictly increasing.
 * 1 <= timestamp <= 10^7
 * TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 * @author haitian.gan
 */
public class TimeBasedKeyValueStore {

    // 我操嘞，完美的解决
    public class TimeMap {

        private class Value{
            private int timestamp;
            private String value;

            Value(String value, int timestamp){
                this.value = value;
                this.timestamp = timestamp;
            }
        }

        private Map<String, List<Value>> map = new HashMap<>();

        /** Initialize your data structure here. */
        public TimeMap() {

        }

        public void set(String key, String value, int timestamp) {
            map.compute(key, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }

                Value newVal = new Value(value, timestamp);
                v.add(newVal);
                return v;
            });
        }

        public String get(String key, int timestamp) {
            List<Value> valueList = map.get(key);
            if (valueList == null || valueList.size() == 0) {
                return "";
            }

            if (timestamp < valueList.get(0).timestamp) {
                return "";
            }

            if(timestamp >= valueList.get(valueList.size() - 1).timestamp){
                return valueList.get(valueList.size() - 1).value;
            }

            // 二分查找
            int left = 0,right = valueList.size() - 1,mid;
            while (true) {
                mid = (left + right) / 2;
                int midTs = valueList.get(mid).timestamp;
                if (timestamp >= midTs) {
                    if (timestamp < valueList.get(mid + 1).timestamp) {
                        return valueList.get(mid).value;
                    } else {
                        left = mid;
                    }
                } else {
                    if (timestamp >= valueList.get(mid - 1).timestamp) {
                        return valueList.get(mid - 1).value;
                    } else {
                        right = mid;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();
        TimeMap                map   = store.new TimeMap();

        String[]   op     = {"set", "set", "get", "get", "get", "get", "get"};
        Object[][] params = {{"love", "high", 10}, {"love", "low", 20}, {"love", 5}, {"love", 10}, {"love", 15}, {"love", 20}, {"love", 25}};

        List<String> result = new ArrayList<>();
        for (int i = 0; i < op.length; i++) {
            if (op[i].equals("set")) {
                map.set(params[i][0].toString(), params[i][1].toString(), Integer.parseInt(params[i][2].toString()));
                result.add(null);
            }else{
                String v = map.get(params[i][0].toString(), Integer.parseInt(params[i][1].toString()));
                result.add(v);
            }
        }

        System.out.print(result.toString());
    }

}
