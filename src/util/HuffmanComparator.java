package util;

import java.util.Comparator;

/**
 * Created by Rael on 19.11.2015.
 */
public class HuffmanComparator  implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        return node1.getData().getFreq() - node2.getData().getFreq();
    }

}
