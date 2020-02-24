package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest02 {
	public static void main(String[] args) {
		String str = "ja1111aCva--@@-@@@@- 한글 --@@@@-- progra44568EmgFmiJng";
		//String patternStr = "a|m|g";//1. |는 or을 의미(a or m or g인 문자 추출)
		//String patternStr = "[amg]";//2. 1번과 동일
		//String patternStr = "[amg][ma]";//3. 두 글자에 대해서 2번의 조건 적용
										//첫 글자가 a,m,g 이고 두 번째 글자가 m,a인 문자
		//String patternStr = "[c-j]";//4. c-j사이에 해당하는 문자(c,d,e,f,g,h,i,j)
		//String patternStr = "[C-J]";
		//String patternStr = "[C-Jc-j]";//대문자C~J 소문자 c~j 문자 추출
		//String patternStr = "[^4-8]";// ^가 []안에 있으면 부정의 의미
									// 4,5,6,7,8이 아닌 문자
		//ex1) c~j사이의 영문자가 아닌 것
		//ex2) 영문자와 숫자만 추출
		//ex3) 영문자와 숫자를 뺀 나머지 문자만 추출
		//ex4) 한글만 추출
		//String patternStr = "[^c-j]";
		//String patternStr = "[a-zA-Z0-9]";
		//String patternStr = "[^a-zA-Z0-9]";
		String patternStr = "[가-힣]";
		
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
