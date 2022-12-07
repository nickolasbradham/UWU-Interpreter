package nbradham.uwu;

/**
 * Used for basic two variable operations.
 * 
 * @author Nickolas Bradham
 *
 */
@FunctionalInterface
interface OpInterface {

	/**
	 * Called when a simple two variable operation is needed.
	 * 
	 * @param a The first variable.
	 * @param b The second variable.
	 * @return The result of the operation.
	 */
	float doOp(float a, float b);
}