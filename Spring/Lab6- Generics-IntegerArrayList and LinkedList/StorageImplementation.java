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
		for(int i=0;i<os.length;i++) {
			this.add(os[i]);
		}
		return true;
	}
	//@Override
	public boolean addAll(int index, T[] os) {
		 for(int i=0;i<os.length;i++) {
			 this.add(index++, os[i]);
		 }
		return true;
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
		for(int i=0;i<counter;i++ ){
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
		/*if((o.getClass()).equals(this.getClass())) {
			return true;
		}
		return false;*/
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
	
	public void sort() {
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
	
	public int hashCode() {
		int hashcode = 0;
        for(int i = 0; i < this.size(); ++i)
        {
            hashcode += this.get(i).hashCode();
        }
        return hashcode;
	}
}
