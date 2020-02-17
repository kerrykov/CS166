package simpleSubstitution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleSubstitution {
	
	static String ciphertext;
	static char[] plaintextArray;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
	static char currentChar, oldChar;
	static Map<Character, Integer> cipherFrequencyMap = new HashMap<>(26);
	static ArrayList<Character> realFrequency = new ArrayList<>(Arrays.asList('e', 't', 'a', 'o', 'i', 'n', 's', 'r', 'h', 'd', 'l', 'u', 'c', 'm', 'f', 'y', 'w', 'g', 'p', 'b', 'v', 'k', 'x', 'q', 'j', 'z'));
	static LinkedHashMap<Character, Integer> sortedFrequencyMap = new LinkedHashMap<>();
	static Character[] keyOrder = new Character[26];
	static Map<Character, Character> pairedFrequencyMap = new HashMap<>(26);
	
	public static void main(String args[]) throws IOException {
		//Read the ciphertext
		System.out.println("Submit the ciphertext: ");
		ciphertext = br.readLine();
		ciphertext = ciphertext.toLowerCase();
		
		//Count the character frequency of the ciphertext
		for (int index = 0; index < ciphertext.length(); index++) {
			currentChar = ciphertext.charAt(index);
			switch(currentChar) {
			case 'a': a++; break;	case 'b': b++; break;	case 'c': c++; break;	case 'd': d++; break;	case 'e': e++; break;	
			case 'f': f++; break;	case 'g': g++; break;	case 'h': h++; break;	case 'i': i++; break;	case 'j': j++; break;
			case 'k': k++; break;	case 'l': l++; break;	case 'm': m++; break;	case 'n': n++; break;	case 'o': o++; break; 	
			case 'p': p++; break;	case 'q': q++; break;	case 'r': r++; break;	case 's': s++; break;	case 't': t++; break;
			case 'u': u++; break;	case 'v': v++; break;	case 'w': w++; break;	case 'x': x++; break;	case 'y': y++; break;	
			case 'z': z++; break;
			}
		}
		
		//Put the keys and values in the map
		cipherFrequencyMap.put('a', a);		cipherFrequencyMap.put('b', b);		cipherFrequencyMap.put('c', c);
		cipherFrequencyMap.put('d', d);		cipherFrequencyMap.put('e', e);		cipherFrequencyMap.put('f', f);
		cipherFrequencyMap.put('g', g);		cipherFrequencyMap.put('h', h);		cipherFrequencyMap.put('i', i);
		cipherFrequencyMap.put('j', j);		cipherFrequencyMap.put('k', k);		cipherFrequencyMap.put('l', l);
		cipherFrequencyMap.put('m', m);		cipherFrequencyMap.put('n', n); 	cipherFrequencyMap.put('o', o);
		cipherFrequencyMap.put('p', p);		cipherFrequencyMap.put('q', q);		cipherFrequencyMap.put('r', r);
		cipherFrequencyMap.put('s', s);		cipherFrequencyMap.put('t', t);		cipherFrequencyMap.put('u', u);
		cipherFrequencyMap.put('v', v);		cipherFrequencyMap.put('w', w);		cipherFrequencyMap.put('x', x);
		cipherFrequencyMap.put('y', y);		cipherFrequencyMap.put('z', z);
		
		//Sort the character frequency counts
		cipherFrequencyMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(x -> sortedFrequencyMap.put(x.getKey(), x.getValue()));
		sortedFrequencyMap.keySet().toArray(keyOrder);
		
		//Frequency order(common -> rare): e, t, a, o, i, n, s, r, h, d, l, u, c, m, f, y, w, g, p, b, v, k, x, q, j, z
		//Order from cornell university cite
		//Pair the characters
		for (int index = 0; index < 26; index++) {
			pairedFrequencyMap.put(keyOrder[keyOrder.length - 1 - index], realFrequency.get(index));
		}
		
		//Substitute the new characters for the old ones in the ciphertext
		plaintextArray = new char[ciphertext.length()];
		for (int index = 0; index < ciphertext.length(); index++) {
			oldChar = ciphertext.charAt(index);
			if (!realFrequency.contains(oldChar)) {
				plaintextArray[index] = oldChar;
			} else {
				plaintextArray[index] = pairedFrequencyMap.get(oldChar);
			}
		}
		
		//Print the plaintext
		String plaintext = new String(plaintextArray);
		System.out.println("Resulting plaintext: " + plaintext);
	}
}
