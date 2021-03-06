package org.dummycreator.dummyfactories;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.dummycreator.ClassBindings;
import org.dummycreator.ClassUsageInfo;
import org.dummycreator.RandomCreator;

/**
 * @author Benny Bottema <b.bottema@projectnibble.org> (further developed project)
 */
public class RandomArrayFactory<T> extends DummyFactory<T> {

	private final Class<T> clazz;

	public RandomArrayFactory(final Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * @return A new array with a random length of 2 or 3, populated with objects of the requested type.
	 * @param knownInstances Not used, but passed on to {@link ClassBasedFactory#createDummy(Map, ClassBindings, List)}.
	 * @param classBindings Not used, but passed on to {@link ClassBasedFactory#createDummy(Map, ClassBindings, List)}.
	 * @param exceptions Not used, but passed on to {@link ClassBasedFactory#createDummy(Map, ClassBindings, List)}.
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T createDummy(final Type[] genericMetaData, final Map<String, ClassUsageInfo<?>> knownInstances,
			final ClassBindings classBindings, final List<Exception> exceptions) {
		final int length = RandomCreator.getInstance().getRandomInt(2) + 2;
		final Object dummyArray = Array.newInstance(clazz.getComponentType(), length);
		for (int i = 0; i < length; i++) {
			Array.set(dummyArray, i,
					new ClassBasedFactory(clazz.getComponentType()).createDummy(genericMetaData, knownInstances, classBindings, exceptions));
		}
		return (T) dummyArray;
	}
}