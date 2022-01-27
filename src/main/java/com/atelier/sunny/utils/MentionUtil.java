package com.atelier.sunny.utils;

public class MentionUtil {
    public static boolean isChannelMention(String mention){
        return mention.startsWith("<#") && mention.endsWith(">");
    }
    public static boolean isMemberMention(String mention){
        return mention.startsWith("<@") && mention.endsWith(">");
    }

    public static String getId(String mention){
        return mention.substring(2, mention.length()-2);
    }
}
