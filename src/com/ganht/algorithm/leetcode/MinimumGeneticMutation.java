package com.ganht.algorithm.leetcode;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


/**
 * A gene string can be represented by an 8-character long string, with choices from "A","C","G","T".
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation
 * is defined as ONE single character changed in the gene string.
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 * Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the
 * bank to make it a valid gene string.
 * <p>
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations
 * needed to mutate from "start" to "end". If there is no such a mutation, return -1.
 * <p>
 * NOTE: 1. Starting point is assumed to be valid, so it might not be included in the bank. 2. If multiple
 * mutations are needed, all mutations during in the sequence must be valid. For example,
 * <p>
 * bank: "AACCGGTA"
 * start: "AACCGGTT"
 * end: "AACCGGTA"
 * return: 1
 * <p>
 * bank: "AACCGGTA", "AACCGCTA", "AAACGGTA"
 * start: "AACCGGTT"
 * end: "AAACGGTA"
 * return: 2
 * <p>
 * bank: "AAAACCCC", "AAACCCCC", "AACCCCCC"
 * start: "AAAAACCC"
 * end: "AACCCCCC"
 * return: 3
 * <p>
 * Subscribe to see which companies asked this question
 * Created by haitian.gan on 2016/10/24.
 */
public class MinimumGeneticMutation {

    public class Gene {

        private final String     str;
        private final List<Gene> mutations = new ArrayList<>();
        private       boolean    touched;
        private       Gene       last;

        private Gene(String str) {
            this.str = str;
        }

        private void addMutation(Gene gene) {
            this.mutations.add(gene);
        }

        @Override
        public boolean equals(Object target) {
            if (!(target instanceof Gene))
                return false;

            Gene _t = (Gene) target;
            return _t.str.equals(this.str);
        }

        @Override
        public int hashCode() {
            return str == null ? super.hashCode() : str.hashCode();
        }

        public String toString() {
            return str;
        }
    }

    // 建立一个图的结构，每个结点中存储着基因突变的相关结点。
    // 图建立好后，从end一个结点倒溯到start结点的最少步数就是结果
    // 这他妈做的是bfs呀，咋和注解说的不一样 2020-06-09
    public int minMutation(String start, String end, String[] bank) {
        var genes     = Arrays.stream(bank).map(Gene::new).collect(toList());
        var startGene = new Gene(start);
        var endGene   = new Gene(end);

        if (!genes.contains(startGene))
            genes.add(startGene);
        else {
            // 将原来的替换掉
            genes.set(genes.indexOf(startGene), startGene);
        }

        if (!genes.contains(endGene)) {
            return -1;
        }

        for (int i = 0; i < genes.size(); i++) {
            var baseGene = genes.get(i);
            for (int j = i + 1; j < genes.size(); j++) {
                var gene = genes.get(j);
                if (isMutated(baseGene.str, gene.str)) {
                    baseGene.addMutation(gene);
                    gene.addMutation(baseGene);
                }
            }
        }

        var tmpQueue = new LinkedList<Gene>();
        tmpQueue.offer(startGene);

        boolean found = false;
        while (tmpQueue.size() > 0) {
            var tmpGene = tmpQueue.poll();
            tmpGene.touched = true;
            if (tmpGene.equals(endGene)) {
                endGene.last = tmpGene.last;
                found        = true;
                break;
            } else {
                tmpGene.mutations.forEach(gene -> {
                    if (gene.touched)
                        return;

                    gene.last = tmpGene;
                    tmpQueue.offer(gene);
                });
            }
        }

        if (!found)
            return -1;

        int result = 0;
        var tmp    = endGene.last;
        while (tmp != null) {
            tmp = tmp.last;
            result++;
        }

        return result;
    }

    public static void main(String[] args) {
        MinimumGeneticMutation mgm = new MinimumGeneticMutation();
        //mgm.minMutation("AACCGGTT","AAACGGTA",new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"});
        mgm.minMutation("AAAAAAAA", "CCCCCCCC", new String[]{"AAAAAAAA", "AAAAAAAC", "AAAAAACC", "AAAAACCC", "AAAACCCC", "AACACCCC", "ACCACCCC", "ACCCCCCC", "CCCCCCCA", "CCCCCCCC"});
    }

    private boolean isMutated(String a, String b) {
        int mutatedNums = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                mutatedNums++;
            }

            if (mutatedNums > 1)
                return false;
        }

        return mutatedNums == 1;
    }

    // 二刷，我的思路是构造一张图出来，然后从和start差1mutation的结点开始做dfs

}
