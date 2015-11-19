package huffman;

import util.HuffmanComparator;
import util.HuffmanData;
import util.MapUtil;
import util.Node;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Rael on 18.11.2015.
 */
public class Huffman {

   /* 1.  Zaimplementowaæ algorytm Huffmana
    a) przeczytaæ o algorytmie
    b) zaimplementowaæ
    c) w przypadku niepowodzenia znaleŸæ gotow¹ implementacjê
    d) przetestowaæ   */

   /* aa) jaka struktura danych do reprezentacji drzewa?
    ab) jak reprezentowaæ bity w poszczególnych wez³ach drzewa?
    ac) jak odczytaæ iloœæ niezbêdnych znaków do zakodowania? hashmapa?*/

    String file;
    int blockSize;
    int beforeSize;
    int afterSize;
    float ratio;
    String outputPath = "D:\\compresed.txt";
    public Huffman()
    {

    }
    public float runHuffman(String path, int blockSize) throws IOException {
        try {
            beforeSize = (int) new File(path).length();


            file = readFile(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.blockSize=blockSize;
        Map<String,Integer> hmap = createStringHashMap(file, blockSize);
        PriorityQueue<Node> queue = createPriorityQueue(hmap);
        Node root = createTree(queue);
        Map<String, String> codes = generateCodes(hmap.keySet(), root);
        String encodedMessage = encodeMessage(codes, file, blockSize);
        //serializeTree(root);
        try {
            serializeMessage(encodedMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        afterSize = (int) new File("D:\\compresed.txt").length();

        ratio = (float)(this.beforeSize-this.afterSize)/(float)this.beforeSize;
        //ratio = ratio/1.0f;
        /*
        System.out.print("Done!");
        System.out.println("After compression ->  " + afterSize);
        System.out.println("Before compression ->  " + beforeSize);
        System.out.println("Compression ratio : " + ratio + "%");
        */
        return ratio;
    }
    private  Map<String, String> generateCodes(Set<String> chars, Node node) {
        final Map<String, String> map = new HashMap<String, String>();// block of chars to bytes
        doGenerateCode(node, map, "");
        return map;
    }

    private void doGenerateCode(Node node, Map<String, String> map, String s) {
        if (node.getLeft() == null && node.getRight() == null) { // leaf
            map.put(node.getData().getCh(), s);
            return;
        }
        doGenerateCode(node.getLeft(), map, s + '0');
        doGenerateCode(node.getRight(), map, s + '1' );
    }

    private  String encodeMessage(Map<String, String> charCode, String file, int blockSize) {
        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < file.length()/blockSize; i++) {
            stringBuilder.append(charCode.get(file.substring(i, Math.min(i + blockSize, file.length()))));
        }
        return stringBuilder.toString();
    }

    private  void serializeMessage(String message) throws IOException {
        final BitSet bitSet = getBitSet(message);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputPath))){

            oos.writeObject(bitSet);
        }


    }

    private static BitSet getBitSet(String message) {
        final BitSet bitSet = new BitSet();
        int i = 0;
        for (i = 0; i < message.length(); i++) {
            if (message.charAt(i) == '0') {
                bitSet.set(i, false);
            } else {
                bitSet.set(i, true);
            }
        }
        bitSet.set(i, true); // dummy bit set to know the length
        return bitSet;
    }


    public Node createTree(PriorityQueue<Node> queue)
    {
        PriorityQueue<Node> q = queue;

        while (q.size() > 1)
        {
            Node first = q.remove();
            Node second = q.remove();
            q.add(new Node(new HuffmanData("\0", first.getData().getFreq() + second.getData().getFreq()), first, second ));
        }
        return q.remove();

    }

    public PriorityQueue<Node> createPriorityQueue(Map<String,Integer> hmap) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(11, new HuffmanComparator());
        Set set = hmap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            pq.add(new Node(new HuffmanData((String) mentry.getKey(), (Integer) mentry.getValue()), null, null));
        }
        return pq;
    }
    public static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }



    /*public static Map<String, Integer>  createHashMap(String file)
    {
        Map<String, Integer> hmap = new HashMap<String, Integer>();
        for (char ch: file.toCharArray()) {
            if(hmap.get(ch) == null)
            {
                hmap.put(ch, 1);
            }
            else
            {
                hmap.put(ch, hmap.get(ch) + 1);
            }

        }
        hmap = MapUtil.sortByValue(hmap);
        return hmap;

    }*/

    public static Map<String, Integer> createStringHashMap(String file, int block)
    {
        Map<String, Integer> hmap = new HashMap<String, Integer>();
        for(int i = 0; i < file.length(); i=i+block)
        {
            String ch = file.substring(i,  Math.min(i + block, file.length()));

            if(hmap.get(ch) == null)
            {
                hmap.put( ch, 1);
            }
            else
            {
                hmap.put(ch, hmap.get(ch) + 1);
            }

        }
        hmap = MapUtil.sortByValue(hmap);
        return hmap;

    }

}
