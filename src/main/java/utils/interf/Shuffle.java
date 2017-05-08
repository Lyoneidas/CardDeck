package utils.interf;


import utils.exception.ShuffleException;

public interface Shuffle {
    Integer[] shuffle(Integer[] origin) throws ShuffleException;
    void setParameters(Integer a, Integer[] sequence) throws ShuffleException;
    void setAutoGenerateSequence();
    void setA(Integer a);
    Integer getA();
    Integer[] getSequence();
}
