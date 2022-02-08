package com.mcode.moneymieassessment.controllers;

import com.mcode.moneymieassessment.model.Car;
import com.mcode.moneymieassessment.services.CarService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/all")
    public List<Car> getAllCars() throws IOException {
        return carService.getAllCars();
    }

    @PostMapping(value = "/save", consumes = {"application/json"})
    public Car createCar(@RequestBody Car car) throws IOException {
        return carService.createCar(car);
    }

    @GetMapping("/vin/{vin}")
    public Car getCarByVin(@PathVariable String vin) throws IOException {
        return  carService.getCarByVin(vin);
    }

    @GetMapping("/filter")
    public List<Car> filterCars(@RequestParam(required = false) String brand,
                                @RequestParam(required = false) String year,
                                @RequestParam(required = false) String color) throws IOException {
        return carService.filterCars(brand,year,color);
    }

    @GetMapping("/filter/sort")
    public List<Car> sortedFilterCars(@RequestParam(required = false) String brand,
                                      @RequestParam(required = false) String year,
                                      @RequestParam(required = false) String color,
                                      @RequestParam String order) throws IOException {
        return carService.filterCarsAndOrder(brand, year, color, order);
    }


}
