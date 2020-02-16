/* CrossEcore is a cross-platform modeling framework that generates C#, TypeScript, 
 * JavaScript, Swift code from Ecore models with embedded OCL (http://www.crossecore.org/).
 * The original Eclipse Modeling Framework is available at https://www.eclipse.org/modeling/emf/.
 * 
 * contributor: Simon Schwichtenberg
 */

package Ocllib;


import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.InternalEObject;

public class Set<T> extends AbstractCollection<T> {

    public Set(Class<?> dataClass)
    {
        super(dataClass);
    }

    public Set(Class<?> dataClass, InternalEObject owner, int featureId)
    {
    	super(dataClass, owner, featureId, NO_FEATURE);
    }

    public Set(Class<?> dataClass,InternalEObject owner, int featureId, int oppositeFeatureId)
    {
    	super(dataClass, owner, featureId, oppositeFeatureId);
    }
    
    
    public <T2> Set<T2> collect(Class<T2> c, Function<T, T2> lambda){
    	Set<T2> result = new Set<T2>(c);

        for (T element : this)
        {
            result.doAddUnique(lambda.apply(element));
        }

        return result;
    }
    
    public <T2> Set<T2> collect2(Class<T2> c, Function<T, Collection<T2>> lambda){
    	Set<T2> result = new Set<T2>(c);

    	
        for (T element : this)
        {
        	
        	Collection<T2> e = lambda.apply(element);
        	
        	for(T2 ee : e) {
        		result.doAddUnique(ee);
        	}
            
        }

        return result;
    }
    
    public Set<T> select(Predicate<T> lambda)
    {
    	Set<T> result = new Set<T>(getDataClass());

        for (T element : this)
        {
        	if(lambda.test(element)) {
        		
        		result.doAddUnique(element);
        	}
        }

        return result;
    }
    
    
    
	
}
