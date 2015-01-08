package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.*;

/**
 * Created by gan on 2015/1/6.
 */
public class CardGame extends CodeJamCase {

    public static int getSmallestLeftCardNum2(List<Integer> cards,int K){
        while(true){
            int i = 0;
            int j = cards.size() - 1;

            if( i >= j){
                i = 0;
                j = cards.size() - 1;
            }



            i++;
            j--;
        }
    }

    /**
     * 这个也是有问题的，这个方法只在K值为零时正确
     * @param cards
     * @param K
     * @return
     */
    public static int getSmallestLeftCardNum1(List<Integer> cards, int K) {

        int index = 1;
        int tmpRemoveNum = 0;
        boolean allFindOut = false;
        while (true) {

            if ((index + 3) > cards.size()) {
                index = 1;
                continue;
            }

            if ((index + 3) == cards.size()) {
                if (tmpRemoveNum <= 0) {
                    allFindOut = true;
                } else {
                    tmpRemoveNum = 0;
                    index = 1;
                    continue;
                }
            }

            int a = cards.get(index);
            int b = cards.get(index + 1);
            int c = cards.get(index + 2);

            if (c - b == b - a && c - b == K) {
                cards.remove(index + 2);
                cards.remove(index + 1);
                cards.remove(index);
                tmpRemoveNum++;
                index = Math.max(index - 1, 1);
            } else {
                index++;
            }

            if (allFindOut) {
                break;
            }
        }

        if (cards.size() >= 3) {
            int a = cards.get(0);
            int b = cards.get(1);
            int c = cards.get(2);

            if (c - b == b - a && c - b == K) {
                cards.remove(2);
                cards.remove(1);
                cards.remove(0);
                tmpRemoveNum++;
            }
        }

        return cards.size();
    }

    /**
     * 这个是错的，因为没有审清楚题目，是挑三张位置连续的卡牌移除
     *
     * @param cards
     * @param K
     * @return
     */
    public static int getSmallestLeftCardNum(List<Integer> cards, int K) {
        Collections.sort(cards);

        Map<Integer, Integer> cardNumMap = new HashMap<Integer, Integer>();
        for (Integer card : cards) {
            Integer cardNum = cardNumMap.get(card);
            if (cardNum == null) {
                cardNumMap.put(card, 1);
            } else {
                cardNumMap.put(card, cardNum + 1);
            }
        }

        while (true) {
            int index = cards.size() - 1;
            if (index < 2) {
                break;
            }
            Integer c = cards.get(index);
            Integer b = c - K;
            Integer a = b - K;
            if (cardNumMap.get(b) != null
                && cardNumMap.get(a) != null) {

                cards.remove(c);
                cards.remove(b);
                cards.remove(a);

                removeCard(cardNumMap, c);
                removeCard(cardNumMap, b);
                removeCard(cardNumMap, a);
            }
        }

        int x = 0;
        for (Map.Entry<Integer, Integer> entry : cardNumMap.entrySet()) {
            x += entry.getValue() % 3;
        }

        if (x != cards.size()) {
            System.out.println();
        }

        return cards.size();
    }

    public static void removeCard(Map<Integer, Integer> cardNumMap, Integer card) {
        Integer cardNum = cardNumMap.get(card);
        if (cardNum != null) {
            if (cardNum == 1) {
                cardNumMap.remove(card);
            } else {
                cardNumMap.put(card, cardNum - 1);
            }
        }
    }

    public static void main(String[] args) {
        new CardGame().parseInput(
            new File("C:\\Users\\gan\\Downloads\\C-large-practice.in"),
            new InputCaseBlockParser() {
                @Override
                public void parseLine(int caseIndex, String[] line) {
                    String questionStr = line[0];
                    String cardsStr = line[1];
                    int K = Integer.parseInt(questionStr.split(" ")[1]);
                    List<Integer> cards = new ArrayList<Integer>();
                    for (String cardStr : cardsStr.split(" ")) {
                        cards.add(Integer.parseInt(cardStr));
                    }
                    int smallestLeftCardNum = getSmallestLeftCardNum1(cards, K);
                    System.out.println(String.format("Case #%d: %d",
                        caseIndex, smallestLeftCardNum));
                }

                @Override
                public int getCaseLineNumber() {
                    return 2;
                }
            });
    }

    @Override
    protected void runCase() {

    }
}
