package com.thoughtworks.springExercise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@RequestMapping("/api")
@RestController
public class SortedPeople {

    @RequestMapping("sortedPeople")
    public ResponseEntity getSortedPeople(@RequestParam List<String> names, @RequestParam List<Integer> ages) {

        if (!isValidData(names, ages)) {
            return new ResponseEntity( HttpStatus.BAD_REQUEST);

        }
        Map<String, Person> people =  IntStream.range(0, names.size()).mapToObj(index -> new Person(ages.get(index), names.get(index))).
                sorted().collect(LinkedHashMap::new,((linkedHashMap, person) -> linkedHashMap.put(person.getName(), person)),
                LinkedHashMap::putAll);
        return new ResponseEntity(people, HttpStatus.OK);
    }

    private boolean isValidData(List<String> names, List<Integer> ages) {
        String namePattern = "^\\[([a-z ]+(, )?)+\\]$";
        for (Integer age : ages) {
            if (age < 0) {
                return false;
            }
        }
        return Pattern.matches(namePattern, names.toString());
    }


}
