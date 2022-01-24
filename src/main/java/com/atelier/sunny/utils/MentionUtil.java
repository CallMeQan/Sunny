package com.atelier.sunny.utils;

public class MentionUtil {
    public static boolean isChannelMention(String mention){
        return mention.startsWith("<#") && mention.endsWith(">");
    }
    public static boolean isMemberMention(String mention){
        return mention.startsWith("<@") && mention.endsWith(">");
    }
}
