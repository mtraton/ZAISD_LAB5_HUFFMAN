package util;

import huffman.Huffman;

/**
 * Created by Rael on 19.11.2015.
 */
public class HuffmanData {
    private Integer freq;
    private String ch;

    public HuffmanData(String ch, Integer freq)
    {
        this.ch = ch;
        this.freq = freq;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }
}
