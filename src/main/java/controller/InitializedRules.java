package controller;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class InitializedRules {
    private static final String START = "S";
    private static final String MILL = "M";
    private static final String HEN_HOUSE = "H";
    private static final String MEADOW = "C";
    private static final String[] PLAY_FIELDS = {START, MILL, HEN_HOUSE, MEADOW};
    private static final int COUNT = 4;

    private final String[] strings;

    /**
     * the first element must be StartField
     * @return true when the first is start field
     */
    public boolean rule1() {
        return START.equals(strings[0].trim()) &&
                !List.of(noStart()).stream().collect(Collectors.toSet()).contains(START);
    }

    /**
     * all fields must be included
     * @return true when they are included
     */
    public boolean rule2() {
        return List.of(PLAY_FIELDS).stream().
                allMatch((s -> List.of(strings).stream().collect(Collectors.toSet()).contains(s)));
    }

    /**
     * two same fields are not allowed neighboured
     * @return true when they are not neighboured
     */
    public boolean rule3() throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        String[] strs = noStart();
        for (int i = 0; i < strs.length; i++) {
            boolean temp = exec.submit(new RuleChecker3(i, noStart())).get();
            if (temp == false) return false;
        }
        exec.shutdown();
        return true;
    }

    /**
     * the same fields must be shrank within 4 fields
     * @return true when within four fields
     */
    public boolean rule4() throws ExecutionException, InterruptedException {
        int size = noStart().length;
        ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < size; i++) {
            boolean temp = exec.submit(new RuleChecker4(noStart(), i)).get();
            if (temp == false) return false;
        }
        exec.shutdown();
        return true;
    }


    private String[] noStart() {
        String[] strs = new String[strings.length - 1];
        for (int i = 1; i < strings.length; i++) {
            strs[i - 1] = strings[i];
        }
        return strs;
    }

    /**
     * multi-thread checker to improve efficiency in large string array size
     */
    private class RuleChecker3 implements Callable<Boolean> {

        private final int i;
        private final String[] strs;

        private RuleChecker3(int i, String[] strs) {
            this.i = i;
            this.strs = strs;
        }


        @Override
        public Boolean call() {
            int size = strs.length;
            int j = i;
            String str1 = strs[j].trim();
            j++;
            String str2 = strs[j % size].trim();
            if (str1.equals(str2)) return false;
            return true;
        }
    }

    /**
     * multi-thread checker to improve efficiency in large string array size
     */
    private class RuleChecker4 implements Callable<Boolean> {

        private final String[] strs;

        private final int i;

        public RuleChecker4(String[] strs, int i) {
            this.strs = strs;
            this.i = i;
        }

        @Override
        public Boolean call() {
            int k = i + 1;
            int count = COUNT;
            while (count > 0) {
                String s = strs[k % strs.length];
                if (s.equals(strs[i])) return true;
                k++;
                count--;
            }
            return false;
        }
    }

    public InitializedRules(String[] strings) {
        this.strings = strings;
    }
}
