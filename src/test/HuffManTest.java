package test;

/**
 * Created by Rael on 19.11.2015.
 */

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;


import static huffman.Huffman.createStringHashMap;
import static huffman.Huffman.readFile;

import huffman.Huffman;
import util.MapUtil;

public class HuffManTest {

    public static void runHuffmanTest() {
        String path = "C:\\Users\\Rael\\IdeaProjects\\ZAISD_LAB5_HUFFMAN\\seneca.txt"; // TODO: zapamiêtaæ -> \ to znak ucieczki, wiêc w stringach musi byæ podwojony
        Map<Integer,Float> performance = new HashMap<Integer,Float>();

        try {


            String file = readFile(path, StandardCharsets.UTF_8);
            for(int i = 1; i< 100/*file.length()*/; i++)
            {
               Huffman h = new Huffman();
               float r = h.runHuffman(path, i);
                performance.put(i,r);
            }
            performance = MapUtil.sortByValue(performance);

            util.MapUtil.printMap(performance);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
