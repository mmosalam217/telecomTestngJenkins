package telecom.intern.helpers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class ReflectHelper {

	public static Boolean objectHasProperty(Object obj, String propertyName){
	    List<Field> properties = getAllFields(obj);
	    for(Field field : properties){
	        if(field.getName().equalsIgnoreCase(propertyName)){
	            return true;
	        }
	    }
	    return false;
	}
	
	public static Object getCurrentFieldValue(Object obj, String property) {
	    List<Field> properties = getAllFields(obj);
	    Object currentFieldValue = null;
	    for(Field field : properties){
	        if(field.getName().equalsIgnoreCase(property)){
	            field.setAccessible(true);
	            try {
	            	currentFieldValue = field.get(obj);
	            	break;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
	        }
	    }
	    return currentFieldValue;
	}
	
	public static void updateFieldValue(Object obj, String property, Object newValue) {
	    List<Field> properties = getAllFields(obj);
	    for(Field field : properties){
	        if(field.getName().equalsIgnoreCase(property)){
	            field.setAccessible(true);
	            try {
	            	Object currentFieldValue = field.get(obj);
	            	if (currentFieldValue != null && currentFieldValue instanceof WebDriver) {
	            		newValue = currentFieldValue;
	            	}
					field.set(obj, newValue);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
	        }
	    }
	}

	private static List<Field> getAllFields(Object obj){
	    List<Field> fields = new ArrayList<Field>();
	    getAllFieldsRecursive(fields, obj.getClass());
	    return fields;
	}

	private static List<Field> getAllFieldsRecursive(List<Field> fields, Class<?> type) {
	    for (Field field: type.getDeclaredFields()) {
	        fields.add(field);
	    }

	    if (type.getSuperclass() != null) {
	        fields = getAllFieldsRecursive(fields, type.getSuperclass());
	    }

	    return fields;
	}
}
