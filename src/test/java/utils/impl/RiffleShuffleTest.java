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
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class RiffleShuffleTest {

    private Shuffle shuffle;
    private Integer[] input;
    private Integer[] expectedOutput;

    public RiffleShuffleTest(Integer[] input, Integer[] expectedOutput){
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Before
    public void initialization() throws ShuffleException {
        shuffle = AShuffle.getInstance();
        shuffle.setParameters(2, new Integer[]{1,1,1,0,0,0});
    }

    @Parameterized.Parameters
    public static Collection testCriteria(){
        return Arrays.asList(new Object[][]{
                {new Integer[]{}, new Integer[]{}},
                {new Integer[]{1,2,3,4,5,6},new Integer[]{1,2,3,4,5,6}},
        });
    }

    @Test
    public void testShuffle() throws ShuffleException {
        assertThat(shuffle.shuffle(input),is(expectedOutput));
    }

}
