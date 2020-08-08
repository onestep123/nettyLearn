package com.netty.learn.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("aasdas","bsres","hello");

        List<String> stringList  = list.stream().map( s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(stringList);

        //分步写(流只能消费一次)(flatMap)
        String[] strings = {"Hello", "World"};
//        Stream stream1 = Arrays.asList(strings).stream().
//                map(str -> str.split(""));
//                Stream stringStream = stream1.flatMap(strings1 -> Arrays.stream(strings));
//
//                 List stringList1 = (List) stringStream.collect(Collectors.toList());
//                 System.out.println(stringList1);

        List stringLista = Arrays.asList(strings).stream().
                         map(str -> str.split("")).
                         flatMap(str -> Arrays.stream(str))
                         .collect(Collectors.toList());
        System.out.println(stringLista);

        List<Integer> integerList = Stream.of(Arrays.asList(1,2),Arrays.asList(3,4)).flatMap(e ->e.stream()).collect(Collectors.toList());
        System.out.println(integerList);

        List<User> users = Arrays.asList(new User("a",23),new User("b",234));
        User user = users.stream().map(e ->{
            e.setName(e.getName().toUpperCase());
            return e;
        }).min(Comparator.comparing(User::getAge)).get();
        User user2 = users.stream().map(e ->{
            e.setName(e.getName().toUpperCase());
            return e;
        }).peek(e -> {System.out.println(e.getName()+">>>>>>");}).sorted(Comparator.comparing(User::getAge)).findFirst().get();
        System.out.println(user.getName()+user.getAge());

        List<User> bigUser = Arrays.asList(new User("a",23,true),new User("b",234,false));

        Map<Boolean,List<User>> booleanListMap = bigUser.stream().collect(Collectors.partitioningBy(User::getBig));
        System.out.println(booleanListMap);

        Map<Boolean,Long> booleanList = bigUser.stream().collect(Collectors.groupingBy(User::getBig,Collectors.counting()));
        System.out.println(booleanList);

        Map<Boolean, List<String>> userNameList = bigUser.stream().collect(Collectors.groupingBy(User::getBig,Collectors.mapping(User::getName,Collectors.toList())));
        System.out.println(userNameList);

        String  userName = bigUser.stream().map(e -> e.getName()).collect(Collectors.joining("-"));
        System.out.println(userName);



        System.out.println(Arrays.asList(1,2,4).stream().reduce((integer, integer2) -> {
            return   integer*integer2;
        }).get());

    }
}
