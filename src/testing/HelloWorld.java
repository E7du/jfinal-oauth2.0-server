/**
 * 
 */

/**
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class HelloWorld implements Hello {

	/**
	 * 
	 */
	private Hello hello = null;
	
	public HelloWorld() {
	}
	
	public void setHello(Hello hello){
		this.hello = hello;
	}
	
	public String getWorld(){
		return hello.ask("朱丛启")+"world";
	}

	public static void main(String [] args){
		HelloWorld hw = new HelloWorld();
		hw.setHello(hw);
		System.out.println(hw.getWorld());
	}

	@Override
	public String ask(String name) {
		return "Hello "+name;
	}
}
