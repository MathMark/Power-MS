package com.pet.mailSender.service.parsers.csvParser;

import com.pet.mailSender.model.annotations.CsvField;
import org.springframework.stereotype.Component;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CsvParser<T> implements Parser<T> {

    private static final String COMMA = ",";

    @Override
    public List<T> getJavaObjects(InputStream inputStream, Class clazz) {
//        List<List<String>> records = getRecords(inputStream);
//        List<String> headers = records.get(0);
//        Map<Integer, Field> d = findCoincidence(getAnnotatedFields(clazz), headers);
//
//        List<T> objects = new ArrayList<>();
//
//        try {
//            for(int i = 1; i < records.size(); i++){
//                Object instance = Class.forName(clazz.getName()).newInstance();
//                for(int j = 0; j < records.get(i).size(); j++){
//                    Field field = d.get(j);
//                    if(field != null){
//                        new PropertyDescriptor(field.getName(), clazz)
//                                .getWriteMethod()
//                                .invoke(instance, records.get(i).get(j));
//                    }
//                }
//                objects.add((T) instance);
//            }
//        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IntrospectionException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        return objects;
        return null;
    }

    private List<List<String>> getRecords(InputStream inputStream){
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA);
                records.add(Arrays.asList(values));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return records;
    }

    private Map<Integer, Field> findCoincidence(List<Field> javaObjectFields, List<String> headers){
        Map<Integer, Field> result = new HashMap<>();

        int index;
        for(Field field : javaObjectFields){
            if((index = headers.indexOf(field.getAnnotation(CsvField.class).columnName())) != -1){
                result.put(index, field);
            }
        }
        return result;
    }

    private List<Field> getAnnotatedFields(Class clazz){
        List<Field> annotatedFields = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields){
            if(field.isAnnotationPresent(CsvField.class)){
                annotatedFields.add(field);
            }
        }

        return annotatedFields;
    }
}
