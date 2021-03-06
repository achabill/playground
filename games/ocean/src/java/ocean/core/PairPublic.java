package ocean.core;

/**
 * A very primitive generic pair.
 *
 * @param <S>
 * @param <T>
 */
public class PairPublic<S, T> {
	public S x;
	public T y;

	public PairPublic(S first, T second) {
		this.x = first;
		this.y = second;
	}

	/**
	 * Equals
	 * 
	 * @param other
	 *            Another Pair.
	 * @return true iff the Pairs have the same data according to equals().
	 */
	public boolean equals(PairPublic<S, T> other) {
		if (other == null)
			return false;
		if (this.x == null && other.x != null)
			return false;
		if (!this.x.equals(other.x))
			return false;

		if (this.y == null && other.y != null)
			return false;
		if (!this.y.equals(other.y))
			return false;

		return true;
	}
}