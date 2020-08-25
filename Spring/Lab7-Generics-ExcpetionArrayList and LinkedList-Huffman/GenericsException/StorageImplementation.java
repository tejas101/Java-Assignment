/* StorageImplementation.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *This program implements common methods of ArrayList and LinkedList class
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
public abstract class StorageImplementation<T extends Comparable > implements StorageInterface<T > {
	int counter=0;
	
	public boolean addAll(T[] os) {
		boolean flag = true;
		for(int i=0;i<os.length;i++) {
			flag &= this.add(os[i]);
			}
		if(flag == false) {
			//System.out.println("Reverting all add() operations");
			removeAll(os);
		}
		return flag;
	}
	
	//@Override
	public boolean addAll(int index, T[] os) {
		//System.out.println("Length "+os.length);
		if(os.length==0) {
			System.out.println("List passed is empty. Returning false");
			return false;
		}
		boolean flag=true;
		 for(int i=0;i<os.length;i++) {
			 try {
				 if(checkEvenOdd(os[i])) {
					 this.add(index++, os[i]);
						//return true; 
				 }
			} catch (NonEvenException e) {
				// TODO Auto-generated catch block
				flag=false;
				removeAll(os);
				e.printStackTrace();
				return flag;
			}  
		 
		}
		 return flag;
		 }
		
	public boolean containsAll(T[] os) {
		boolean flag=true;
		for(int i=0;i<os.length;i++) {
			flag &= contains(os[i]);
		}
		return flag;
	}

	public boolean removeAll(T[] os) {
			boolean flag=false;
			for(int i=0;i<os.length;i++) {
				if((this.remove(os[i]))) {
					flag=true;
				}
			}
			 return flag;
		}
		/*boolean flag=true;
		for(int i=0;i<os.length;i++) {
				flag = remove(os[i]);
		}
		 return flag;
	}*/
	
	public int size() {
		return counter;
	}
	
	public boolean isEmpty() {
		if(counter==0) {
			return true;
		}
		return false;
	}
	
	public boolean contentEquals(StorageInterface o) {
		
		if(this.size()==o.size()&&o.size()!=0) {
		for(int i=0;i<=counter;i++ ){
		if(!this.contains((T) o.get(i)))
			return false;
	}
		return true;
	}
		else {
		return false;
		}		 
	}
	
	public boolean equals(Object o) {
		return o == this;
	}
	
	public String toString() {
		 String output="";
			for(int i=0;i<counter;i++) {
				if(i+1==counter)
				{output+=this.get(i);}
				else {
					output+=this.get(i)+"-->";}
				 
			}
			return output;
	}
	
	public void sort()  {
		for (int i = 0; i < counter-1; i++) {
            for (int j = 0; j < counter-i-1; j++) { 
                //if (this.get(j+1) > this.get(j+2)) 
                	if (0 > (this.get(j+1).compareTo(this.get(j)))) 
                { 
                    
                    T temp =this.get(j+1); 
                    this.set(j+1,this.get(j)); 
                    this.set(j,temp);
                } 
	}
	}
	}
	public boolean checkEvenOdd(T o)  throws NonEvenException{
    	if(o instanceof Integer) {
				 if((Integer) o % 2 != 0) {
					 throw(new NonEvenException());	  
			}  
		}
		if(o instanceof String) {
				if((Integer) ((String) o).length() % 2 != 0) {
					throw(new NonEvenException());
				}
		}
		return true;
    }
	
	/*public int hashCode() {
		int hashcode = 0;
        for(int i = 0; i < size(); ++i)
        {
            hashcode += get(i);
        }
        return hashcode;
	}*/
}
