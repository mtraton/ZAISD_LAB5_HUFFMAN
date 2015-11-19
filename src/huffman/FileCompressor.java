package huffman;

import java.util.BitSet;

/**
 * Created by Rael on 18.11.2015.
 */
public class FileCompressor {

   /* 2. W oparciu o powy�szy algorytm napisa� programik do kompresji i dekompresji tekstu. Parametrami programu maj� by�:

    d�ugo�� kodowanego ci�gu (np. 1 - pojedynczy znak, 2 - ci�gi dwuznakowe itd.)
    nazwa pliku wej�ciowego
    nazwa pliku wyj�ciowego
    W przypadku kompresji na wyj�ciu program powinien zwraca�, opr�cz wynikowego pliku, tak�e stopie� kompresji, obliczany jako: K=Linput?LcompressedLinput.
*/
    // Kod z forum - zapi
    private static byte[] toByteArray(BitSet bits) {
        byte[] bytes = new byte[(bits.length() + 7) / 8];
        for (int i=0; i<bits.length(); i++) {
            if (bits.get(i)) {
                bytes[bytes.length-i/8-1] |= 1<<(i%8);
            }
        }
        return bytes;
    }

}
