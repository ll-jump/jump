package com.ll.jump.service.impl.test;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.ll.jump.model.Car;
import com.ll.jump.model.TestXmlModel;
import com.ll.jump.model.TestXmlModel2;
import com.ll.jump.model.User;
import com.ll.jump.service.impl.TestPutTask;
import com.ll.jump.service.impl.TestTakeTask;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 〈测试〉
 *
 * @author LiLin
 * @create 2019/8/16 0016
 */
public class LLTest {
    private HashMap<Character, Character> bracketMap;

    public LLTest() {
        bracketMap = new HashMap<>();
        bracketMap.put('(', ')');
        bracketMap.put('[', ']');
        bracketMap.put('{', '}');
    }
    int i;

    @Test
    public void testThreadLocal(){
        User user = new User();
        WeakHashMap<User, String> weakHashMap = new WeakHashMap<>();

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("abc");
        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
        threadLocal2.set("def");
        System.out.println(threadLocal.get());
        System.out.println(threadLocal2.get());
    }

    @Test
    public void testMap() throws UnsupportedEncodingException {
      Set<String> filter = new HashSet<>();
        for (int i = 0; i < 5000000; i++){
            filter.add(UUID.randomUUID().toString());
        }
        int errorNum = 0;
        long start = System.currentTimeMillis();
        for (int j = 0; j < 5000000; j++){
            String test = UUID.randomUUID().toString() + "AAAAAAAAAAAAAAAAAAAAAAaaaaa";
            if (filter.contains(test)){
                errorNum ++;
            }
        }
        System.out.println("errorNum:" + errorNum);
        System.out.println("time:" + (System.currentTimeMillis() - start));

    }
    @Test
    public void testBloom() throws UnsupportedEncodingException {
        BloomFilter<String> filter =
            BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), 5000000, 0.001);
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 5000000; i++){
            String temp = UUID.randomUUID().toString();
            filter.put(temp);
            set.add(temp);
        }
        int errorNum = 0;
        long start = System.currentTimeMillis();
        System.out.println(set.size());
//        for (String s : set) {
//            if (!filter.mightContain(s)){
//                errorNum ++;
//            }
//        }
        for (int j = 0; j < 500000; j++){
            String test = UUID.randomUUID().toString() + "AAAAAAAAAAAAAAAAAAAAAAaaaaa";
            if (filter.mightContain(test)){
                errorNum ++;
            }
        }
        System.out.println("errorNum:" + errorNum);
        System.out.println("time:" + (System.currentTimeMillis() - start));

    }
    @Test
    public void testTask(){
        for (int i = 0; i < 10; i++){
            Thread threadPut = new Thread(new TestPutTask(String.valueOf(i) + "type"));
            threadPut.start();
        }

        Thread threadTake = new Thread(new TestTakeTask());
        threadTake.start();
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testXml() throws JAXBException, IOException {
        TestXmlModel testXmlModel = new TestXmlModel();
        testXmlModel.setName("张三");
        testXmlModel.setAge("12");
        testXmlModel.setType("aaa.bbb");

        TestXmlModel2 testXmlModel2 = new TestXmlModel2();
        testXmlModel2.setAge2("13");
        testXmlModel2.setName2("张思");
        testXmlModel2.setType2("ccc.eee");
        testXmlModel.setModel2(testXmlModel2);
        String result = null;

        JAXBContext context = JAXBContext.newInstance(TestXmlModel.class);
        Marshaller marshaller = context.createMarshaller();
        // 指定是否使用换行和缩排对已编组 XML 数据进行格式化的属性名称。
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        StringWriter writer = new StringWriter();
        marshaller.marshal(testXmlModel, writer);
        result = writer.toString();
        writer.close();
        System.out.println(result);


        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><TestXml type=\"aaa.bbb\"><name>张三</name><ageaaa>12</ageaaa><model2><type2>ccc.eee</type2><name2>张思</name2><age2>13</age2></model2></TestXml>";
        JAXBContext jaxbContext = JAXBContext.newInstance(TestXmlModel.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader sr = new StringReader(s);
        TestXmlModel t = (TestXmlModel) unmarshaller.unmarshal(sr);
        System.out.println(t.toString());
    }

    @Test
    public void testDate(){
        String clientVer = "1.3.1";
        Integer clientVerInt = Integer.valueOf(clientVer.replace(".", ""));
        System.out.println(clientVerInt);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E-D-F-w-W-a-k-K");
//        Date date = new Date();
//        System.out.println(sdf.format(date));
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        System.out.println(calendar.get(Calendar.MONTH + 1));
    }

    @Test
    public void testStream() {
        AopTest aopTest = new AopTest();
        aopTest.doSomeThing();
        System.out.println(i);
//        Integer i1 = null;
//        Integer i2 = new Integer(10);
//        Optional<Integer> a = Optional.ofNullable(i1);
//        Optional<Integer> b = Optional.of(i2);
//        System.out.println();

//        Random random = new Random();
//        random.ints().limit(10).sorted().forEach(System.out::println);
//        System.out.println("11111111");

//        Map<String, Car> map = new HashMap<>();
//        map.forEach((s, car) -> {
//
//        });
//        List<String> stringsA = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl", "abc");
//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//        numbers.stream().mapToInt(i->i).summaryStatistics();
//        Map<String, Map<String, String>> r = stringsA.stream().collect(Collectors.groupingBy(s -> s, Collectors.toMap()));
//        System.out.println(r);

//        List<String> strings = new ArrayList<>(Arrays.asList("11111", "2", "3", "4", "5"));
//        for (int i = 0; i < 1000; i++) {
//            String s = String.valueOf(i);
//            strings.add(s);
//        }
//        Long start = System.currentTimeMillis();
////        strings.forEach(System.out::println);
//        strings.stream().forEach(System.out::println);
////        strings.parallelStream().forEach(System.out::println);
////        strings.sort(String::compareTo);
//////        List<String> strings1 = strings.stream().sorted(String::compareTo).collect(Collectors.toList());
//        System.out.println("cost:" + (System.currentTimeMillis() - start));
//        System.out.println(strings.get(0));

//        List<String> strings1 = strings.stream().filter(s -> StringUtils.isNotEmpty(s)).collect(Collectors.toList());
//        strings1.stream().limit(2).forEach(System.out::println);
//        System.out.println("11111111");
//        strings1.forEach(System.out::println);

//        List<Integer> array1 = Arrays.asList(5, 2, 4, 1, 11,0, 2);
//        Collections.sort(array1, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.compareTo(o2);
//            }
//        });
//        System.out.println("1 start");
//        array1.forEach(integer -> {System.out.println(integer);});
//        System.out.println("1 end");
//
//        System.out.println("2 start");
//        List<Integer> array2 = Arrays.asList(5, 2, 4, 1, 11,0, 2);
//        Collections.sort(array2, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return 0;
//            }
//        });
//
//
//        Collections.sort(array2, (o1, o2) -> {return o2.compareTo(o1);});
//        array2.forEach(integer -> {System.out.println(integer);});
//        System.out.println("2 end");
//        List<String> filterList = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
//        List<String> filterList1 = strings.stream().filter(string -> "bc".equals(string)).collect(Collectors.toList());
//
//        filterList1.forEach(System.out::println);
//        strings.forEach(System.out::println);
//        strings.forEach(x->System.out.println(x));
//        filterList.stream().forEach(System.out::println);
    }

    @Test
    public void testAop() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"ctx/ctx_main.xml"});
        AopTest aopTest = (AopTest) context.getBean("aopTest");
        aopTest.doSomeThing();
    }

    @Test
    public void testStr() {
        String a = "hello";
        String b = a;
        String c = "hello";
        String d = "he" + "llo";
        String e = new String("hello");
        a = a + "bcd";
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(a == b);
        System.out.println(b == c);
        System.out.println(a == d);
        System.out.println(a == e);
    }

    @Test
    public void test() {
//        int r = turnOver(10089000);
//        System.out.printf(String.valueOf(r));
        int[] arr = new int[]{4, 2, 5, 7, 1, 3, 1};
//        int[] result = quickSort(arr, 0, arr.length - 1);
        int[] result = upSort(arr);
//        Integer[] arrI = new Integer[]{4,2,5,7,1,3,1};
//        List<Integer> list = arrs.asList(result);
//        arrs.sort(arrI);
//        for (int i = 0; i < arrI.length; i++) {
//            System.out.println(arrI[i]);
//        }
//        list.forEach(x->System.out.println(String.valueOf(x)));

        String r = "";
        for (int i = 0; i < result.length; i++) {
            r = r + result[i] + ',';
        }
        System.out.println(r);
    }

    /**
     * 有效的括号
     */
    @Test
    public void testLeetCode() {
        String str = "";
        boolean result = testValidBracket(str);
        System.out.println(result);
    }

    private void testFor() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            s = s + "1";
            list.add(s);
        }
        for (String s : list) {
            s = s + "1";
//            list.add(s);
        }
    }

    @Test
    public void testListA() {
        long start = System.currentTimeMillis();
        List<Integer> al = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            if (i > 4) {
                al.add(i - 4, i);
            } else {
                al.add(i);
            }
        }
        System.out.println("use time:" + (System.currentTimeMillis() - start));
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            al.remove(i + 5);
        }
        System.out.println("use time2:" + (System.currentTimeMillis() - start1));
    }

    @Test
    public void testListL() {
//        String a = "";
//        a.equals();
//        Object o = new Object();
//        o.equals();
//        Car car = new Car();
//        car.equals();
//        User user = new User();
//        user.equals();
////        Thread thread1 = new Thread(){
////            @Override
////            public void run(){
////                System.out.println("aaaaa");
////            }
////        };
////        thread1.start();
//        String s = "abc";
//        String s1 = "abc";
//        String s2 = "ab"+ "c";
//        System.out.println(s1 == s2);

//        String s3 = new String("abc");
//        String s4 = "abc";
//        System.out.println(s3 == s4);

//        String a = "a";
//        String b = "a";
//        System.out.println(a == b);
//        System.out.println(a.equals(b));
//        System.out.println(a.intern() == b.intern());
//        String s3 = new String("a");
//        String s4 = new String("a");
//        System.out.println(a.intern());
//        System.out.println(b.intern());
//        System.out.println(s3.intern());
//        System.out.println(s4.intern());
////
////        System.out.println(s3.hashCode());
////        System.out.println(s3.hashCode());
////
//        System.out.println(s3 == s4);
//        System.out.println(s3.equals(s4));
//        System.out.println(s3.intern() == s4.intern());
//        System.out.println("");
//        System.out.println(a == s3);
//        System.out.println(a.equals(s3));
//        System.out.println(a.intern() == s3.intern());
//        Set<User> userSet = new HashSet<>();
//        User user1 = new User();
//        user1.setName("a");
//        user1.setId("1");
//        User user2 = new User();
//        user2.setName("a");
//        user2.setId("1");
//        userSet.add(user1);
//        userSet.add(user2);
//        System.out.println(user1.toString());
//        System.out.println(user1.hashCode());
//        System.out.println(Integer.toHexString(user1.hashCode()));
//        System.out.println(userSet.size());

//        long start = System.currentTimeMillis();
//        List<Integer> al = new LinkedList<>();
//        for (int i = 0; i < 10000000; i++) {
//            if (i > 4){
//                al.add(i - 4, i);
//            }else {
//                al.add(i);
//            }
//
//        }
//        System.out.println("use time:" + (System.currentTimeMillis() - start));
//
//        long start1 = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            al.remove(i + 5);
//        }
//        System.out.println("use time2:" + (System.currentTimeMillis() - start1));
    }

    /**
     * 删除排序数组中的重复项
     */
    private int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        } else if (nums.length <= 1) {
            return nums.length;
        }

        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[result] != nums[i]) {
                result++;
                if (result != i) {
                    nums[result] = nums[i];
                }
            }
        }
        return result + 1;
    }

    /**
     * 合并两个有序列表
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        ListNode resultTemp = result;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                resultTemp.next = l1;
                l1 = l1.next;
            } else {
                resultTemp.next = l2;
                l2 = l2.next;
            }
            resultTemp = resultTemp.next;
        }

        if (l1 == null) {
            resultTemp.next = l2;
        }
        if (l2 == null) {
            resultTemp.next = l1;
        }

        return result.next;
    }

    /**
     * 有效的括号
     */
    private boolean testValidBracket(String str) {
        Stack<Character> left = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (bracketMap.containsKey(c)) {
                left.push(c);
            } else {
                if (left.empty()) {
                    return false;
                }
                Character leftOne = left.pop();
                Character rightOne = bracketMap.get(leftOne);
                if (!rightOne.equals(c)) {
                    return false;
                }
            }
        }
        if (left.empty()) {
            return true;
        } else {
            return false;
        }
    }

    private int[] upSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; --j) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    private int[] quickSort(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while ((i < j) && (arr[j] > pivot)) {
                j--;
            }
            while ((i < j) && (arr[i] < pivot)) {
                i++;
            }
            if ((arr[i] == arr[j]) && (i < j)) {
                i++;
            } else {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        if ((i - 1) > start) {
            arr = quickSort(arr, start, i - 1);
        }
        if ((j + 1) < end) {
            arr = quickSort(arr, j + 1, end);
        }

        return arr;
    }

    public static int[] qsort(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while ((i < j) && (arr[j] > pivot)) {
                j--;
            }
            while ((i < j) && (arr[i] < pivot)) {
                i++;
            }
            if ((arr[i] == arr[j]) && (i < j)) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if ((i - 1) > start) {
            arr = qsort(arr, start, i - 1);
        }
        if ((j + 1) < end) {
            arr = qsort(arr, j + 1, end);
        }
        return (arr);
    }


    /**
     * 整数反转
     */
    private int turnOver(int x) {
        int ten = 1;
        int temp = 0;
        int result = 0;
        while (x / ten > 0) {
            temp = (x % (ten * 10) - temp) / ten;
            result = result * 10 + temp;
            ten = ten * 10;
        }
        return result;
    }

    /**
     * Z字形变换
     */
    private String test2(String s, int rowNum) {
        if (rowNum == 1 || rowNum > s.length()) {
            System.out.println(s);
            return s;
        }
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < rowNum; ++i) {
            list.add(new StringBuilder());
        }

        boolean iGoingDown = false;
        int curRow = 0;
        for (char temp : s.toCharArray()) {
            list.get(curRow).append(temp);
            if (curRow == 0 || curRow == rowNum - 1) {
                iGoingDown = !iGoingDown;
            }

            curRow += iGoingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder temp : list) {
            result.append(temp);
        }
        System.out.println(result);
        return result.toString();
    }

    @Test
    public void test1() {
        String result = longestPalindrome("aaababadeee");
        System.out.println(result);

        //        int num = 2147483647 ;
//        num += 0 ;
//        System.out.println(num) ;

//        long num = 100 ;
//        int x = num + 2 ;
//        System.out.println(x) ;
//
//        int i = 1 ;
//        int j = i++ ;
//        if((i==(++j))&&((i++)==j))     {
//            i += j ;
//        }
//        System.out.println("i = "+i);

//        int num = 2147483647 ;
//        long temp = num + 2L ;
//        System.out.println(num) ;

//        int num = 68 ;
//        char c = (char) num ;
//        System.out.println(c) ;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}