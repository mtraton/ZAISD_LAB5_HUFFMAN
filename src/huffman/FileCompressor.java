package huffman;

import java.util.BitSet;

/**
 * Created by Rael on 18.11.2015.
 */
public class FileCompressor {

   /* 2. W oparciu o powy¿szy algorytm napisaæ programik do kompresji i dekompresji tekstu. Parametrami programu maj¹ byæ:

    d³ugoœæ kodowanego ci¹gu (np. 1 - pojedynczy znak, 2 - ci¹gi dwuznakowe itd.)
    nazwa pliku wejœciowego
    nazwa pliku wyjœciowego
    W przypadku kompresji na wyjœciu program powinien zwracaæ, oprócz wynikowego pliku, tak¿e stopieñ kompresji, obliczany jako: K=Linput?LcompressedLinput.
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
