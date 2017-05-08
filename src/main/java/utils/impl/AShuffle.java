package utils.impl;

import utils.Utilities;
import utils.exception.ShuffleException;
import utils.interf.Shuffle;

import java.util.*;

import static utils.Constants.DEFAULT_A_VALUE;
import static utils.Constants.DEFAULT_SHUFFLE_ROUNDS;
import static utils.Utilities.getCutPositions;
import static utils.Utilities.getUniqueCount;

public class AShuffle implements Shuffle {

    private Integer a = DEFAULT_A_VALUE;
    private static boolean manualInput = false;
    private Integer[] sequence = new Integer[]{};

    private Integer shuffleRounds = DEFAULT_SHUFFLE_ROUNDS;

    public void setShuffleRounds(Integer rounds) {
        this.shuffleRounds = rounds;
    }

    private AShuffle() {
    }

    private static final Object mutex = new Object();
    private static Shuffle instance = null;


    public static Shuffle getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new AShuffle();
                }
            }
        }
        return instance;
    }

    public Integer[] shuffle(Integer[] origin) throws ShuffleException {
        for (int roundCount = 0; roundCount < shuffleRounds; roundCount++) {
            origin = singleShuffle(origin);
        }
        return origin;
    }

    private Integer[] singleShuffle(Integer[] origin) throws ShuffleException {
        Integer[] result = new Integer[origin.length];
        if (origin.length < 1) {
            return result;
        }
        if (!manualInput) {
            this.sequence = Utilities.generateRandomizedSequence(a, origin.length);
        }
        if (inputValidated()) {
            Integer[] cutPositions = getCutPositions(this.sequence);
            HashMap<Integer, Integer[]> packets = new HashMap<>();
            getPackets(origin, cutPositions, packets);
            for (Map.Entry e : packets.entrySet()) {
                Integer index = (Integer) e.getKey();
                Integer[] values = (Integer[]) e.getValue();
                int valPos = 0;
                for (int shiftPos = 0; shiftPos < this.sequence.length; shiftPos++) {
                    if (this.sequence[shiftPos].equals(index)) {
                        result[shiftPos] = values[valPos];
                        valPos++;
                    }
                }
            }
        }
        return result;
    }

    private void getPackets(Integer[] origin, Integer[] cutPositions, HashMap packets) {
        Integer start = 0;
        Integer end = 0;
        for (int count = 0; count < cutPositions.length; count++) {
            if (count == 0) {
                start = 0;
                end = cutPositions[count];
            } else {
                start = start + cutPositions[count - 1];
                end = end + cutPositions[count];
            }
            Integer[] packet = Arrays.copyOfRange(origin, start, end);
            packets.put(count, packet);
        }
    }

    public void setParameters(Integer a, Integer[] sequence) throws ShuffleException {
        this.a = a;
        this.sequence = sequence;
        if (inputValidated()) {
            manualInput = true;
        }
    }

    public void setAutoGenerateSequence() {
        this.manualInput = false;
        this.a = DEFAULT_A_VALUE;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getA() {
        return a;
    }

    public Integer[] getSequence() {
        return this.sequence;
    }

    private boolean inputValidated() throws ShuffleException {
        if (a < 2) {
            throw new ShuffleException("A is smaller than 2");
        }
        if (getUniqueCount(sequence) != a) {
            throw new ShuffleException("The size of sequence is not equal to parameter a.");
        }
        if (getUniqueCount(sequence) > 52) {
            throw new ShuffleException("The size of sequence exceeds 52.");
        }
        return true;
    }
}
