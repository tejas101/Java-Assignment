/* IntegerSotrageImplementation.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *This program implements the (Inherited )common fucntions of Linked List and ArrayList class
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
public abstract class IntegerStorageImplementation implements IntegerStorage  {
	int counter=0;
	public boolean addAll(int[] os) {
		for(int i=0;i<os.length;i++) {
			this.add(os[i]);
		}
		return true;
	}
	public boolean addAll(int index, int[] os) {
		 for(int i=0;i<os.length;i++) {
			 this.add(index++, os[i]);
			 
		 }
		return true;
	}
	public boolean containsAll(int[] os) {
	for(int i=0;i<os.length;i++) {
		if(!(this.contains(os[i]))) {
			return false;
		}
	}
	return true;
	}
	
	public boolean removeAll(int[] os) {
		boolean flag=false;
		for(int i=0;i<os.length;i++) {
			if((this.remove((Integer)os[i]))) {
				flag=true;
			}
		}
		 return flag;
	}
	
	public int size() {
		
		return counter;
	}
	public boolean isEmpty() {
		if(counter==0) {
			return true;
		}
		return false;
	}
	
	public boolean contentEquals(IntegerStorage o) {
		int arr[] = new int[counter];
		for(int i=1;i<=arr.length;i++ ){
		arr[i-1]=this.get(i);
	}
		if(this.size()==o.size()) {
		if(o.containsAll(arr))
		{
			return true;
		}
		}
		
		return false;
	}
	public boolean equals(Object o) {
		if((o.getClass()).equals(this.getClass())) {
			return true;
		}
		return false;
	}
	public String toString() {
		 String output="";
			for(int i=0;i<counter;i++) {
				if(i+1==counter)
				{output+=this.get(i+1);}
				else {
					output+=this.get(i+1)+"-->";}
				 
			}
			return output;
		 
		
		
	}
	public void sort() {
		for (int i = 0; i < counter-1; i++) {
            for (int j = 0; j < counter-i-1; j++) { 
                if (this.get(j+1) > this.get(j+2)) 
                { 
                    
                    int temp =this.get(j+1); 
                    this.set(j+1,this.get(j+2)); 
                    this.set(j+2,temp);
                } 
	}
	}
	}
	public  int	hashCode() {
		return super.hashCode();
	}
}
