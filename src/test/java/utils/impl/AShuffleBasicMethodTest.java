package utils.impl;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.exception.ShuffleException;
import utils.interf.Shuffle;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AShuffleBasicMethodTest {

    private Shuffle shuffle;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void initialization(){
        shuffle = AShuffle.getInstance();
    }

    @Test
    public void testGetAAfterSetting() throws ShuffleException {
        shuffle.setParameters(3,new Integer[]{1,2,3,3});
        assertThat(shuffle.getA(),is(Integer.valueOf(3)));
    }

    @Test
    public void testSetAExceptionSmallerThanTwo() throws ShuffleException {
        exception.expect(ShuffleException.class);
        exception.expectMessage(containsString("A is smaller than 2"));
        shuffle.setParameters(-1,new Integer[]{1});
    }

    @Test
    public void testGetSequenceAfterSetting() throws ShuffleException {
        Integer[] mandatorySequence = new Integer[]{1,2,3,4,5};
        shuffle.setParameters(5,mandatorySequence);
        assertThat(shuffle.getSequence(),is(mandatorySequence));
    }

    @Test
    public void testSetSequenceExceptionNotEqualToA() throws ShuffleException {
        exception.expect(ShuffleException.class);
        exception.expectMessage(containsString("The size of sequence is not equal to parameter a."));
        shuffle.setParameters(2,new Integer[]{1});
    }

    @Test
    public void testSetSequenceExceptionGreaterThanFiftyTwo() throws ShuffleException {
        exception.expect(ShuffleException.class);
        exception.expectMessage(containsString("The size of sequence exceeds 52."));
        Integer i = 0;
        List<Integer> list = new LinkedList<>();

        while(i<=55){
           list.add(i);
           i++;
        }
        shuffle.setParameters(list.size(),list.toArray(new Integer[list.size()]));
    }

}