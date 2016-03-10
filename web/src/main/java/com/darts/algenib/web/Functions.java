package com.darts.algenib.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: jpc
 * Date: 5/6/13
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public final class Functions {
    public static final Logger LOGGER = LoggerFactory.getLogger(Functions.class);
    private final static Pattern EMAIL_PATTERN = Pattern.compile(".+@.+\\.[a-z]+");
    public static final String DIGITS = "0123456789";
    public static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

    private Functions() {
    }

    public static <T> List<List<T>> getBatches(List<T> collection, int batchSize){
        int i = 0;
        List<List<T>> batches = new ArrayList<List<T>>();
        while(i < collection.size()){
            int nextInc = Math.min(collection.size() - i, batchSize);
            List<T> batch = collection.subList(i, i + nextInc);
            batches.add(batch);
        }
        return batches;
    }

    public static String digest(final String pw, final String algorithm) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hash = md.digest(pw.getBytes(Charset.forName("UTF-8")));
        char[] chars = new char[hash.length * 2];
        for (int i = 0; i < hash.length; i++){
            int v = hash[i] & 0xFF;
            chars[i * 2] = HEX_DIGITS[v >>> 4];
            chars[i * 2 + 1] = HEX_DIGITS[v & 0x0F];
        }
        return new String(chars);
    }

    public static @NotNull
    String mask(String number, int digitCount){
        if (number == null) return null;
        if (digitCount <= 0) return number;
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            final int limit = number.length() - digitCount;
            sb.append(i < limit ? number.charAt(i) : 'x');
        }
        return sb.toString();
    }

    public static @NotNull
    String filename(String s){
        return s == null ? "_" : s.replaceAll("\\W+", "_");
    }

    public static @NotNull
    List list(Object e){
        return Collections.singletonList(e);
    }

    public static @NotNull
    Object mapKey(Map<?, ?> map, Object value){
        if (value != null) for (final Map.Entry<?, ?> entry: map.entrySet()) if (value.equals(entry.getValue())) return entry.getKey();
        return null;
    }

    public static Collection mapKeys(Map<?, ?> map){
        return map == null ? Collections.emptySet() : map.keySet();
    }

    public static String truncate(final String s, int maxLength, String def){
        if (s == null) return def;
        final String trimmed = s.trim();
        if (trimmed.length() == 0) return def;
        if (trimmed.length() > maxLength) return trimmed.substring(0, maxLength);
        return trimmed;
    }

    public static String truncate(final String s, int maxLength){
        return truncate(s, maxLength, null);
    }

    public static boolean isStringEqual(String s1, String s2){
        if ((s1 == null || s1.length() == 0) && (s2 == null || s2.length() == 0)) return true;
        return !(s1 == null || s2 == null) && s1.equals(s2);
    }

    public static boolean isStringBlank(String s){
        if (s == null || s.length() == 0) return true;
        for (char c: s.trim().toCharArray()) if (!Character.isSpaceChar(c)) return false;
        return true;
    }

    public static String cleanBreaks(String s){
        return s == null ? null : s.replace("\n", "").replace("\r", "");
    }

}
