package com.mcode.moneymieassessment.services;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcode.moneymieassessment.exceptions.MMAExceptions;
import com.mcode.moneymieassessment.model.Car;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.io.*;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CarService {
    private ObjectMapper mapper = new ObjectMapper();
    private final String filePath = "C://Users/Mayor/Documents/Capricorn/moneymieassessment/src/main/resources/car-file/cars-large.json";

    private List<Car> cars;
    public List<Car> getCarsFromFile() throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        TypeReference<List<Car>> typeReference = new TypeReference<List<Car>>() {};
        List<Car> carsFromFile = mapper.readValue(inputStream,typeReference);
        return carsFromFile;
    }

    public CarService() throws IOException {
    }


    public List<Car> getAllCars() throws IOException {
        List<Car> allCars = getCarsFromFile();
        Stream<Car> streamedCar = allCars.stream();
        List<Car> sorted = streamedCar.sorted(Comparator.comparing(Car::getBrand)).collect(Collectors.toList());
        return sorted;
    }

    public String createCar(Car car) throws IOException {
        List<Car> cars = getCarsFromFile();
        Car returnedCar = new Car();
        Car existingCar = cars.stream()
                .filter((c)->car.getVin().equals(c.getVin()))
                .findAny()
                .orElse(null);
        if(existingCar != null)
            throw new MMAExceptions("vin already exist");
            Car newCar = new Car();
            newCar.setVin(car.getVin());
          newCar.setBrand(car.getBrand());
            newCar.setYear(car.getYear());
            newCar.setColor(car.getColor());
            cars.add(newCar);
            returnedCar = newCar;
        mapper.writeValue(new File(filePath), cars);
        return "created successfully";
    }


    public Car getCarByVin(String vin) throws IOException {
        List<Car> allcars = getCarsFromFile();
        Car car = allcars.stream()
                .filter((c)->vin.equals(c.getVin()))
                .findAny()
                .orElse(null);
        return car;
    }

    public List<Car> filterCars(String brand, String year, String color) throws IOException {
        List<Car> allCars = getCarsFromFile();
        List<Car> filteredCars = null;
        if(brand != null){
            List<Car> car = allCars.stream()
                    .filter((c)->brand.equalsIgnoreCase(c.getBrand()))
                    .collect(Collectors.toList());
            filteredCars = car;
        }
        if(year != null){
            int yearInNumber = Integer.parseInt(year);
            List<Car> car1 = allCars.stream()
                    .filter((c)->yearInNumber == c.getYear())
                    .collect(Collectors.toList());
            filteredCars = car1;
        }
        if(color != null){
            List<Car> car2 = allCars.stream()
                    .filter((c)->color.equalsIgnoreCase(c.getColor()))
                    .collect(Collectors.toList());
            filteredCars = car2;
        }
        if(brand != null && year !=null && color != null){
            List<Car> cars = allCars.stream().filter(car -> {
                if(brand.equalsIgnoreCase(car.getBrand()) || Integer.parseInt(year) ==car.getYear()|| color.equalsIgnoreCase(car.getColor())){
                    return true;
                }else{
                    return false;
                }
            }).collect(Collectors.toList());
            filteredCars = cars;
        }

        return filteredCars;
    }

    public List<Car> filterCarsAndOrder(String brand, String year, String color, String order) throws IOException {
        List<Car> allCars = getCarsFromFile();
        List<Car> filteredCars = null;
        if(brand != null){
            List<Car> car = allCars.stream()
                    .filter((c)->brand.equalsIgnoreCase(c.getBrand()))
                    .collect(Collectors.toList());
            //filteredCars = car;
            if(order.equalsIgnoreCase("asc")){
                List<Car> sortedFilterCars = car.stream().sorted(Comparator.comparing(Car::getBrand)).collect(Collectors.toList());
                filteredCars = sortedFilterCars;
            }else if(order.equalsIgnoreCase("desc")){
                List<Car> sortedFilterCars = car.stream().sorted(Comparator.comparing(Car::getBrand).reversed()).collect(Collectors.toList());
                filteredCars = sortedFilterCars;
            }
        }
        if(year != null){
            int yearInNumber = Integer.parseInt(year);
            List<Car> car1 = allCars.stream()
                    .filter((c)->yearInNumber == c.getYear())
                    .collect(Collectors.toList());
            filteredCars = car1;
            if(order.equalsIgnoreCase("asc")){
                List<Car> sortedFilterCars = car1.stream().sorted(Comparator.comparing(Car::getYear)).collect(Collectors.toList());
                filteredCars = sortedFilterCars;
            }else if(order.equalsIgnoreCase("desc")){
                List<Car> sortedFilterCars = car1.stream().sorted(Comparator.comparing(Car::getYear).reversed()).collect(Collectors.toList());
                filteredCars = sortedFilterCars;
            }
        }
        if(color != null){
            List<Car> car2 = allCars.stream()
                    .filter((c)->color.equals(c.getColor()))
                    .collect(Collectors.toList());
            //filteredCars = car2;
            if(order.equalsIgnoreCase("asc")){
                List<Car> sortedFilterCars = car2.stream().sorted(Comparator.comparing(Car::getColor)).collect(Collectors.toList());
                filteredCars = sortedFilterCars;
            }else if(order.equalsIgnoreCase("desc")){
                List<Car> sortedFilterCars = car2.stream().sorted(Comparator.comparing(Car::getColor).reversed()).collect(Collectors.toList());
                filteredCars = sortedFilterCars;
            }
        }


        return filteredCars;
    }


}
