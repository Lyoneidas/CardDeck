package utils.impl;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import utils.exception.ShuffleException;
import utils.interf.Shuffle;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class AShuffleTest {

    private Shuffle shuffle;
    private Integer[] input;
    private Integer[] expectedOutput;

    public AShuffleTest(Integer[] input, Integer[] expectedOutput){
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Before
    public void initialization(){
        shuffle = AShuffle.getInstance();
    }

    @Parameterized.Parameters
    public static Collection testCriteria(){
        return Arrays.asList(new Object[][]{
                {new Integer[]{}, new Integer[]{}},
                {new Integer[]{1,2,3,4,5,6,7,8}, new Integer[]{8,2,5,4,7,6,1,3}}
        });
    }

    @Test
    public void testShuffle() throws ShuffleException {
        shuffle.setParameters(5,new Integer[]{2,3,0,0,4,1,0,3});
        assertThat(shuffle.shuffle(input),is(expectedOutput));
    }
}
