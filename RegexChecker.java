/*
 * Copyright 2016 Göksenin GÖZDE
 */
package musicbot;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Göksenin GÖZDE
 */

public class RegexChecker {
    
    private String regex;
    private String str2Check;
    public RegexChecker(String regex, String str2Check){
        this.regex = regex;
        this.str2Check = str2Check;

    }    
    
    public String check(){
    
        Pattern checkRegex = Pattern.compile(this.regex);
        Matcher regexMatcher = checkRegex.matcher(this.str2Check);
        
        while (regexMatcher.find()){
            if(regexMatcher.group().length() != 0) 
                return regexMatcher.group().trim();
           
        
        }
        
        return " ";
    
    }
    }

