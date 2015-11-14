package project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thomas on 14-11-2015.
 */
public class IntervalRadixSorter {

    public static void main(String[] args) {
        IntervalRadixSorter sorter = new IntervalRadixSorter();
        List<Pair<Integer, Integer>> intervals = new ArrayList<>();
        intervals.add(new Pair<>(3, 4));
        intervals.add(new Pair<>(3, 1));
        intervals.add(new Pair<>(0, 8));
        intervals.add(new Pair<>(0, 8));
        intervals.add(new Pair<>(-1, -1));

        sorter.sortList(intervals, 9).forEach(i -> System.out.println(i.getLeft() + ", " + i.getRight()));
    }

    public static List<Pair<Integer, Integer>> sortList(List<Pair<Integer, Integer>> intervalList, int numberOfLeaves){
        List<Pair<Integer, Integer>> result = new ArrayList<>();

        List<Pair<Integer, Integer>>[] halfSortedList = new ArrayList[numberOfLeaves];
        for (int i = 0; i < numberOfLeaves; i++) {
            halfSortedList[i] = new ArrayList<>();
        }

        for(Pair<Integer, Integer> interval : intervalList){
            Integer left = interval.getLeft();
            if(left == -1){
                result.add(interval);
            }
            else
                halfSortedList[left].add(interval);
        }

        int[] numberCount = new int[numberOfLeaves];
        Arrays.fill(numberCount, 0);

        for (int i = 0; i < numberOfLeaves; i++) {
            for(Pair<Integer, Integer> interval : halfSortedList[i]){
                numberCount[interval.getRight()]++;
            }
            for(int j = 0 ; j < numberOfLeaves ; j++){
                if(numberCount[j] == 1)
                    result.add(new Pair<>(i, j));
                if(numberCount[j] == 2){
                    result.add(new Pair<>(i, j));
                    result.add(new Pair<>(i, j));
                }
            }

            Arrays.fill(numberCount, 0);
        }

        return result;
    }
}
