package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlgorithmBits {

    BITS_1024(1024),
    BITS_2048(2048),
    BITS_3072(3072),
    BITS_4096(4096),
    BITS_8192(8192);

    private final int bitLength;
}
