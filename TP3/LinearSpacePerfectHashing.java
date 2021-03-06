import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
   static int p = 46337;
   
   QuadraticSpacePerfectHashing<AnyType>[] data;
   int a, b;
   
   LinearSpacePerfectHashing()
   {
      a=b=0; data = null;
   }
   
   LinearSpacePerfectHashing(ArrayList<AnyType> array)
   {
      AllocateMemory(array);
   }
   
   public void SetArray(ArrayList<AnyType> array)
   {
      AllocateMemory(array);
   }
      
   @SuppressWarnings("unchecked")
   private void AllocateMemory(ArrayList<AnyType> array)
   {
      Random generator = new Random( System.nanoTime() );
      
      if(array == null || array.size() == 0)
      {
         // A completer
		 data = null;
         return;
      }
      if(array.size() == 1)
      {
         a = b = 0;
         
         // A completer
		 data = (QuadraticSpacePerfectHashing<AnyType>[]) new Object[1];
		 data[0] = new QuadraticSpacePerfectHashing<AnyType>(array);
         return;
      }
      
      // A completer
	  a = generator.nextInt(p) + 1;
	  b = generator.nextInt(p);
	  ArrayList<ArrayList<AnyType>> tabSamePosition = new ArrayList<ArrayList<AnyType>>(array.size());
	  
	  for(int i = 0; i < array.size(); i++) {
	      tabSamePosition.add(new ArrayList<AnyType>());
	  }

	  int key;
	  for (int i = 0; i < array.size(); i++) {
     	 key = getKey(array.get(i), array);
     	 tabSamePosition.get(key).add(array.get(i));
	  }
	  
	  data = new QuadraticSpacePerfectHashing[tabSamePosition.size()];
	  for (int i = 0; i < tabSamePosition.size(); i++) {
		data[i] = new QuadraticSpacePerfectHashing<AnyType>(tabSamePosition.get(i));
	  }
   }
   
   public int Size()
   {
      if( data == null ) return 0;
      
      int size = 0;
      for(int i=0; i<data.length; ++i)
      {
         size += (data[i] == null ? 1 : data[i].Size());
      }
      return size;
   }
   
   public boolean contains(AnyType x )
   {
      if( Size() == 0 ) return false;
      
      int m = data.length;
      
      int index = ( ( a*x.hashCode() + b ) % p ) % m;
      
      index = ( index < 0 ? index + m : index );
      
      return ((data[index] != null) && (data[index].contains(x)));
   }
   
   private int getKey(AnyType x, ArrayList<AnyType> array) {
	   return ((a * x.hashCode() + b) % p ) % array.size();
   }
}
