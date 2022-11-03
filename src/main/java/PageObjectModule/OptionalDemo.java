package PageObjectModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        List<String> dataList = new ArrayList<String>();
        dataList.add("23");
        Optional<Object> values = Optional.of(dataList.get(0));
        if(values.isPresent()){
            System.out.println(values.get().equals("23"));
        }else{
            System.out.println("break");
        }
        dataList.stream().peek(System.out::println).count();

    }
}
