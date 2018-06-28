package es.redmic.brokerlib.constraintvalidation.base;

import java.lang.reflect.InvocationTargetException;

public abstract class BaseValidator<T extends Object> {

	private static final Class<?>[] EMPTY_CLASS_ARRAY = new Class<?>[0];
	private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

	@SuppressWarnings("unchecked")
	protected static <T> T invokeDateGetter(Object obj, String methodName) {
		try {
			return (T) obj.getClass().getMethod(methodName, EMPTY_CLASS_ARRAY).invoke(obj, EMPTY_OBJECT_ARRAY);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | ClassCastException e) {
			throw new IllegalArgumentException("Unable to access " + obj.getClass().getName() + "#" + methodName, e);
		}
	}

	protected static String getAccessorMethodName(String property) {
		StringBuilder builder = new StringBuilder("get");
		builder.append(Character.toUpperCase(property.charAt(0)));
		builder.append(property.substring(1));
		return builder.toString();
	}
}
