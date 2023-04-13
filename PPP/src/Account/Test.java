package Account;

public class Test {
   public static void main(String args[])
   {
	   A a1=new A();
	     a1.setEid(10);
	     a1.setEaddress("viman nagar");
	     a1.setMobile(9767251568l);
	     a1.setCity("Pune");
	     
	   Office o=new Office();
	        o.setOno(12); 
	        o.setOadd("4th floor");
	        o.setOcity("Pune");
	        o.setAa(a1);
	        
	     System.out.println(o.getOno());
	     System.out.println(o.getOadd());
	     System.out.println(o.getOcity());
	     System.out.println(o.getAa());
	     System.out.println(a1.getEid());
	     System.out.println(a1.getEaddress());
	     System.out.println(a1.getMobile());
	     System.out.println(a1.getCity());
	     
	     
	   
	   
	   
   }
}
