import java.util.*;

class Cache   
{  
  int key;               
  long value;           
 Cache(int key, long value) {  
    this.key = key;  
    this.value = value;  
  }  
}  

class lru{
    
    Deque<Integer> q = new LinkedList<>();  
    Map<Integer, Cache> m = new HashMap<>();  
    int CACHE_CAPACITY = 4; 
    
    /*lru(int n){ to set cache size
        CACHE_CAPACITY=n;
    }
    lru(){
        
    }*/
    
    long giveelement(int k){//k->key ,val->value
        if(m.containsKey(k)){
            q.remove(k);
            q.addFirst(k);
            Cache curr=m.get(k);//curr containg val
            return curr.value;
        }
        return -1;//key not pesent in map
    }
    
    void putelement(int k,long v){
        if(m.containsKey(k)){
            q.remove(k);  //removed from queue ,not map(as we required it whitout-computatin)
            q.addFirst(k);
        }
        else{
            if(q.size()==CACHE_CAPACITY){
                int tem=q.removeLast();//gives last queue element
                m.remove(tem);   //removed from queue and map
            }
            q.addFirst(k); //added k in front queue
            //now add value in map
            Cache item=new Cache(k,v); //conversted to staore in map
            m.put(k,item);
        }
        // **key->input ,value->output
    }
    
    void Manager(int k){
            if(giveelement(k)==-1){  //in C++ there would be a direct function ->absent/present
                long fac =1;//1  //pre-computed not available;start process of computation
                for(int i=1;i<=k;i++){
                    fac=fac*i;
                }
                System.out.println(" running ");
                System.out.println(" fac ="+fac+"\n");
                putelement(k,fac);
            }
            else{//present element(computaion avalaible)
                System.out.println(" fac ="+giveelement(k)+"\n");
            }
    }
}

class demo{
    public static void main(String[] args){
        lru l1=new lru();
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println(" ENTER 'y' to find SUM OR 'q' for keys" );
            char ch= sc.next().charAt(0);
            if(ch=='q'){
             System.out.println(l1.q); 
            }
            else if(ch=='y'){
                
            System.out.println(" ENTER integer :");
            int k=sc.nextInt();
                        long startTime = System.nanoTime();
            l1.Manager(k);
                        long endTime   = System.nanoTime();
                        long totalTime = endTime - startTime;
                        System.out.println(totalTime/1000+"u_secs");
                        
            }
            else{
            System.out.println("EXIT:0 ");
            break;
            }
            
    }
}
}
