/*
 * QrCode-factory, short link generator ditributed by QRcode
 * Copyright (C) 2017 Nicolas Mauger <https://maugern.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.maugern.helper;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * @author Michael Remijan mjremijan@yahoo.com
 * Copied from https://dzone.com/articles/unit-testing-jpastop-integration-testing
 */
public class AssertAnnotations {

	private static void assertAnnotations(List<Class> annotationClasses, List<Annotation> annotations) {

		if (annotationClasses.size() != annotations.size()) {

			throw new AssertionError(
					String.format("Expected %d annotations, but found %d",
						annotationClasses.size(), annotations.size()
						));
		}

		annotationClasses.forEach(ac -> {
			long cnt = annotations.stream().filter(
					a -> a.annotationType().isAssignableFrom(ac))
				.count();

			if (cnt == 0) {
				throw new AssertionError(
					String.format("No annotation of type %s found", ac.getName())	);
			}
		});
	}

	public static void assertType(Class c, Class... annotationClasses) {
		assertAnnotations(Arrays.asList(annotationClasses),
                Arrays.asList(c.getAnnotations()));
	}

	public static void assertField(Class c, String fieldName, Class... annotationClasses) {
		try {
			assertAnnotations(Arrays.asList(annotationClasses),
                    Arrays.asList(c.getDeclaredField(fieldName).getAnnotations()));
		} catch (NoSuchFieldException nsfe) {
			throw new AssertionError(nsfe);
		}
    }

	public static void assertMethod(Class c, String getterName, Class... annotationClasses) {
		try {
			assertAnnotations(Arrays.asList(annotationClasses),
                    Arrays.asList(c.getDeclaredMethod(getterName).getAnnotations())
            );
		} catch (NoSuchMethodException nsfe) {
			throw new AssertionError(nsfe);
		}
	}

}
