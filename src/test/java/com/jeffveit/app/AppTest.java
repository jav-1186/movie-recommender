package com.jeffveit.app;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */

public class AppTest {
    private userService userService;
    private movieRec movieRec;

    List<String> young = Arrays.asList("Shrek", "Coco", "The Incredibles");
    List<String> middle = Arrays.asList("The Avengers", "The Dark Knight", "Inception");
    List<String> old = Arrays.asList("The Godfather", "Deadpool", "Saving Private Ryan");
    List<String> test = Arrays.asList("Alien", "Deadpool", "Saving Private Ryan");

    @Before
    public void setup() {
        userService = mock(userService.class);
        movieRec = new movieRec(userService);
    }

    @Test
    public void young() {
        when(userService.getAge()).thenReturn(12);
        assertThat(movieRec.getRecommendedMovies(), is(young));
        System.out.println("Test 1 - Age under 13 years is running!");
    }

    @Test
    public void middle() {
        when(userService.getAge()).thenReturn(15);
        assertThat(movieRec.getRecommendedMovies(), is(middle));
        System.out.println("Test 2 - Age between 13 and 17 is running!");
    }

    @Test
    public void old() {
        when(userService.getAge()).thenReturn(18);
        assertThat(movieRec.getRecommendedMovies(), is(old));
        System.out.println("Test 3 - Age over 17 years is running!");
    }

    @Test
    public void exceptionError() {
        when(userService.getAge()).thenThrow(new RuntimeException());
        assertThat(movieRec.execute(), is(young));
        System.out.println("Test 4 - Exception Test Running!");
    }

    @Test
    public void mockDelay() throws InterruptedException {
        when(userService.getAge()).thenAnswer(new Answer() {
            
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Thread.sleep(4000);
                return 12;
            }
         });
        assertThat(movieRec.getRecommendedMovies(), is(young));
        System.out.println("Test 5 - Delay Test Running!");
    }
}
