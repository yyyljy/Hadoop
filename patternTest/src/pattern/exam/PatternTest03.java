package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest03 {
	public static void main(String[] args) {
		//String str = "-@-ja1- -111aCva--@@-@@@@- 한글 --@@@@-- progra44568EmgFmiJng";
		String str = "a3mJA4VA _java aaaxl  programming and spring , hadoop1";
		//String patternStr = ".*";
		//String patternStr = "-@+-";
		//String patternStr = "-@?-";
		//String patternStr = "[^ ]";//공백 아닌 문자
		//String patternStr = ".{5}";//다섯 글자씩
		//String patternStr = "[amv]{1,3}";//a,m,v가 1~3회
		//String patternStr = "[a-z]{3,}";//a-z가 3글자 이상
		//String patternStr = "\\W";//대문자, 소문자, 숫자 뺀 모두
		//String patternStr = "\\w";//대문자, 소문자, 숫자 모두
		//String patternStr = "\\D";//숫자를 뺀 나머지
		String patternStr = "\\d";//숫자만
		
		
		equalsPattern(str, patternStr);
	}
	public static void equalsPattern(String str, String patternStr) {
		//1. 패턴을 인식
		Pattern pattern = Pattern.compile(patternStr);
		//2. 패턴을 적용하여 문자열을 관리
		Matcher m = pattern.matcher(str);
		/*System.out.println(m.find());
		System.out.println(m.start());
		System.out.println(m.end());
		System.out.println(m.group());*/
		
		while(m.find()) {
			System.out.println(m.group());
			System.out.println(m.start()+":"+(m.end()-1));
		}
	}
}
