package swing.swinggui;

public class Doit2Test {
	public static void main(String[] args) {
		Doit2Select doit2Select = new Doit2Select();
		Doit2Insert doit2Insert = new Doit2Insert();
		Doit2Update doit2Update = new Doit2Update();
		Doit2Delete doit2Delete = new Doit2Delete();
		
		DoitBean doitBean = new DoitBean();
		doitBean.setCol1(null);
		doitBean.setCol2("뿅");
		doitBean.setCol4("1");
		doitBean.setCol4("2");
		doitBean.setCol4("3");
		doitBean.setCol4("4");
		
		doit2Insert.insert(doitBean);
		doit2Select.select();
		
		doitBean.setCol1("104");
		doitBean.setCol4("123");
		doit2Update.update(doitBean);
		doit2Select.select();
		
		doitBean.setCol1("102");
		doit2Delete.delete(doitBean);
		doit2Select.select();
		
	} // end main
	
} // end class
