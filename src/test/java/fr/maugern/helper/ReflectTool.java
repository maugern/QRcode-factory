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
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Michael Remijan mjremijan@yahoo.com
 * Copied from https://dzone.com/articles/unit-testing-jpastop-integration-testing
 */
public class ReflectTool {

	public static <T extends Annotation> T getMethodAnnotation(Class<?> c, String methodName, Class<T> annotation) {
		try {
			Method m = c.getDeclaredMethod(methodName);
			return (T)m.getAnnotation(annotation);
		} catch (NoSuchMethodException nsme) {
			throw new RuntimeException(nsme);
		}
	}

	public static <T extends Annotation> T getFieldAnnotation(Class<?> c, String fieldName, Class<T> annotation) {
		try {
			Field f = c.getDeclaredField(fieldName);
			return (T)f.getAnnotation(annotation);
		} catch (NoSuchFieldException nsme) {
			throw new RuntimeException(nsme);
		}
	}

	public static <T extends Annotation> T getClassAnnotation(Class<?> c, Class<T> annotation) {
	    return (T) c.getAnnotation(annotation);
	}

}
