package com.jeffveit.app;

import java.util.Arrays;
import java.util.List;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixRuntimeException;


public class movieRec  extends HystrixCommand<List<String>> implements movieRecService 
{
    private userService userService;
    List<String> young = Arrays.asList("Shrek", "Coco", "The Incredibles");
    List<String> middle = Arrays.asList("The Avengers", "The Dark Knight", "Inception");
    List<String> old = Arrays.asList("The Godfather", "Deadpool", "Saving Private Ryan");
    

    public movieRec (userService userService)
    {
        super(HystrixCommandGroupKey.Factory.asKey("Example Group"));
        this.userService = userService;
    }

    public List<String> getRecommendedMovies() {
       
        int age = userService.getAge();

        if (age < 13)
        {
            return young;
        }

        else if (age >= 13 && age < 17)
        {
            return middle;
        }

        else
        {
            return old;
        }
        

    }
   

    @Override
    protected List<String> run() throws Exception 
    {
        int age = userService.getAge();

        if (age < 13)
        {
            return young;
        }

        else if (age >= 13 && age < 17)
        {
            return middle;
        }

        else
        {
            return old;
        }
    }

    @Override
    protected List<String> getFallback() 
    {
        return young;
    }
   
}