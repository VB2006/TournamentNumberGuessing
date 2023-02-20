import java.io.*;
import java.util.*;
public class TournamentSetup {
    private Participant[] partList;
    private List<Participant> waitingList;

    public TournamentSetup(int length) {
        partList = new Participant[length];
    }

    public Participant requestSlot(int partNum) {
        for (int i = 0; i < partList.length; i++) {
            if (partList[i] == null) {
                Participant p = new Participant(partNum);
                partList[i] = p;
                return p;
            }
        }
        waitingList.add(new Participant(partNum));
        return null;
    }

    public int size() {
        return partList.length;
    }
}