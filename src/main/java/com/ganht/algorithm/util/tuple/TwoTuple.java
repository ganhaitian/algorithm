package com.ganht.algorithm.util.tuple;

/**
 * 二元组
 * 
 * @author <a href="mailto:daniel.zeng@happyelements.com">daniel.zeng</a>
 *
 * @param <A>
 * @param <B>
 */
public class TwoTuple<A, B> implements ITuple {

	public final A first;
	public final B second;

	public TwoTuple(A a, B b) {
		first = a;
		second = b;
	}

	public String toString() {
		return "(" + first + ", " + second + ")";
	}

	@Override
	public int hashCode() {// 重新定義 hashCode()
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	/**
	 * 取得first
	 * 
	 * @return the first
	 */
	public A getFirst() {
		return first;
	}

	/**
	 * 取得second
	 * 
	 * @return the second
	 */
	public B getSecond() {
		return second;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		@SuppressWarnings("rawtypes")
		TwoTuple other = (TwoTuple) obj;
		
		// 檢查第一個值
		if (first == null) {
			if (other.first != null) {
				return false;
			}
		} else if (!first.equals(other.first)) {
			return false;
		}
		
		// 檢查第二個值
		if (second == null) {
			if (other.second != null) {
				return false;
			}
		} else if (!second.equals(other.second)) {
			return false;
		}
		
		return true;
	}

	@Override
	public int size() {
		return 2;
	}

	@Override
	public Object[] toArray() {
		return new Object[] { first, second };
	}

}
