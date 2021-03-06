// Long package name is via the Java naming conventions
package au.edu.uq.csse2002.week2.tutorial;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class contains a series of utility methods posed as problems for the
 * second week of tutorials in CSSE2002 at UQ in 2013.
 *
 * Note: the methods in this class aren't static for the reasons touched on
 * in my tutorials. Hopefully I'll be able to convince you more why I think
 * that as you get to learn more advanced concepts.
 *
 * @author Malcolm Inglis
 */
public class Utility {

	/**
	 * Returns the total sum of the elements in the given array.
	 *
	 * @param xs  the array to return the sum of
	 * @return the sum of the elements in the given array
	 */
	public int sum(int[] xs) {
		int total = 0;
		for (int x : xs) {
			total += x;
		}
		return total;
	}

	/**
	 * Returns the number of occurrences of the given value in the given array.
	 *
	 * @param item  the value to return the number of occurrences of
	 * @param xs    the array to look in
	 * @return the number of occurrences of the given value in the given array
	 */
	public int countItem(int item, int[] xs) {
		int count = 0;
		for (int x : xs) {
			if (x == item) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Sums the values in a and b into c, up to the shortest length of the
	 * given arrays.
	 *
	 * Thus, for the i'th element in a and b, the i'th position in c will be
	 * assigned to <code>a[i] + b[i]</code>.
	 *
	 * @param a  the first array to take values from to add
	 * @param b  the second array to take values from to add
	 * @param c  the array to sum the values of a and b into
	 */
	public void sumArray(int[] a, int[] b, int[] c) {
		for (int i = 0; i < a.length && i < b.length && i < c.length; i++) {
			c[i] = a[i] + b[i];
		}
	}

	/* I actually decided to not publish this because it's not useful.
	 * Note: "publish" means having an access level of public or protected,
	 * because they allow access from outside of the package. This has
	 * the access level of package-private: the default access level.
	 */
	boolean isPrefix(String prefix, String string) {
		return string.startsWith(prefix);
	}

	/* (use "open declaration" to see how the Java authors made this fast)
	boolean isPrefixHardWay(String prefix, String string) {
		if (prefix.length() > string.length()) {
			return false;
		}

		for (int i = 0; i < prefix.length(); i++) {
			if (prefix.charAt(i) != string.charAt(i)) {
				return false;
			}
		}

		return true;
	}
	*/

	/**
	 * @param string  the string whose uniqueness is to be tested
	 * @return true if the string contains only unique characters
	 */
	public boolean isUniqueChars(String string) {
		// Using a Set to determine uniqueness should generally be much more
		// memory-efficient than sorting the string.
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < string.length(); i++) {
			set.add(string.charAt(i));
		}
		return set.size() == string.length();
	}

	/**
	 * Removes duplicate characters from the given array, retaining order,
	 * moving all unique characters to the front, and assigning zero to the
	 * freed positions at the end of the array.
	 *
	 * @param chars  the character array
	 */
	public void removeDuplicates(char[] chars) {
		Set<Character> set = new HashSet<>();
		int assignAt = 0;
		for (char c : chars) {
			if (!set.contains(c)) {
				set.add(c);
				chars[assignAt] = c;
				assignAt += 1;
			}
		}
		// Zero out the rest of the array.
		Arrays.fill(chars, assignAt, chars.length, (char) 0);
	}

	/**
	 * @param s1  the first string to be compared
	 * @param s2  the second string to be compared
	 * @return true if the two given strings are anagrams of one another
	 */
	public boolean areAnagrams(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}

		Map<Character, Integer> s1Counts = new HashMap<>();
		Map<Character, Integer> s2Counts = new HashMap<>();
		// Note: guaranteed here that s1.length == s2.length
		for (int i = 0; i < s1.length(); i++) {
			incrementCount(s1Counts, s1.charAt(i));
			incrementCount(s2Counts, s2.charAt(i));
		}
		// They're anagrams only if the counts of their chars are equal
		return s1Counts.equals(s2Counts);
	}

	/* Note: this method is private (rather than package-private) because
	 * it's an implementation detail of areAnagrams, so tests of areAnagrams
	 * test this too.
	 */
	private void incrementCount(Map<Character, Integer> map, char c) {
		map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
	}

}
