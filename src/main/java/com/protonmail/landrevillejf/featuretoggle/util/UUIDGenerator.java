package com.protonmail.landrevillejf.featuretoggle.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class UUIDGenerator {
    private static final Logger logger = LoggerFactory.getLogger(UUIDGenerator.class);

    /**
     * Type 1 UUID Generation
     */
    public static UUID generateType1UUID() {
        long most64SigBits = get64MostSignificantBitsForVersion1();
        long least64SigBits = get64LeastSignificantBitsForVersion1();
        return new UUID(most64SigBits, least64SigBits);
    }

    private static long get64LeastSignificantBitsForVersion1() {
        Random random = new Random();
        long random63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
        long variant3BitFlag = 0x8000000000000000L;
        return random63BitLong + variant3BitFlag;
    }

    private static long get64MostSignificantBitsForVersion1() {
        LocalDateTime start = LocalDateTime.of(1582, 10, 15, 0, 0, 0);
        Duration duration = Duration.between(start, LocalDateTime.now());
        long seconds = duration.getSeconds();
        long nanos = duration.getNano();
        long timeForUuidIn100Nanos = seconds * 10000000 + nanos * 100;
        long least12SignificatBitOfTime = (timeForUuidIn100Nanos & 0x000000000000FFFFL) >> 4;
        long version = 1 << 12;
        return (timeForUuidIn100Nanos & 0xFFFFFFFFFFFF0000L) + version + least12SignificatBitOfTime;
    }
}
